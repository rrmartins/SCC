package dao;

import domain.Entrega;
import domain.Locacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class EntregaJDBCDao implements EntregaDao {

    private Conexao connection;
    private String sql;

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Entrega selecionarTodasEntregas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Entrega selecionarEntrega(Entrega entrega) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerEntrega(Entrega entre) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {

        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "DELETE FROM entrega " +
                "WHERE cod_entrega = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setInt(1, entre.getCodEntrega());
            prepSt.executeUpdate();
            
            this.connection.commit();
        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
        }
    }

    public void alterarEntrega(Entrega entrega) throws SQLException,ParseException, ClassNotFoundException, ConexaoException, MinhaException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "update entrega set "+
                            "cod_locacao = ?, "+
                            "quilometragem_final = ?,"+
                            "data_entrega = ?,"+
                            "valor_total = ?,"+
                            "quantidade_vezes = ?,"+
                            "hora_entrega = ? "+
                            "where cod_entrega = ?;" ;

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

            String dataEntre = form.format(entrega.getDataEntrega());

            java.sql.Date dataE = new java.sql.Date(form.parse(dataEntre).getTime());

            prepSt.setInt(1, entrega.getCodLocacao().getCodLocacao());
            prepSt.setInt(2, entrega.getQuilometragemFinal());
            prepSt.setDate(3, dataE);
            prepSt.setFloat(4, entrega.getValorTotal());
            prepSt.setInt(5, entrega.getQuantidadeVezes());
            prepSt.setString(6, entrega.getHoraEntrega());
            prepSt.setInt(7, entrega.getCodEntrega());

            prepSt.executeUpdate();
            this.connection.commit();

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
        }
    }

    public void inserirEntrega(Entrega entrega) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO entrega (cod_locacao,quilometragem_final,data_entrega,hora_entrega,valor_total,quantidade_vezes) " +
                    "values (?,?,?,?,?,?);";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            java.sql.Date dt = new java.sql.Date (entrega.getDataEntrega().getTime());

            pstmt.setInt(1, entrega.getCodLocacao().getCodLocacao());
            pstmt.setInt(2, entrega.getQuilometragemFinal());
            pstmt.setDate(3, dt);
            pstmt.setString(4, entrega.getHoraEntrega());
            pstmt.setInt(5, entrega.getValorTotal());
            pstmt.setInt(6, entrega.getQuantidadeVezes());

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }
    }

    @SuppressWarnings("unchecked")
    public Vector<Entrega> obterEntrega(String texto) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {
        this.connection = FabricaConexao.obterConexao();

        if (texto.equals("Codigo")) {
            texto = "cod_entrega";
        }
        if (texto.equals("Cod Locacao")) {
            texto = "cod_locacao";
        }
        if (texto.equals("KM Final")) {
            texto = "quilometragem_final";
        }
        if (texto.equals("Data Entrega")) {
            texto = "data_entrega";
        }
        if (texto.equals("Hr Entrega")) {
            texto = "hora_entrega";
        }
        if (texto.equals("Valor Total")) {
            texto = "valor_total";
        }

        Vector entre = new Vector();
        try
        {

            sql = "SELECT * " +
                "FROM entrega "+
                "order by " + texto + ";";


            this.connection.setAutoCommit(false);
            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            ResultSet result = prepSt.executeQuery();
            result.next();


            do
            {
                Entrega ent = new Entrega();
                Locacao loca = new Locacao();
                loca.setCodLocacao(result.getInt("cod_locacao"));
                ent.setCodEntrega(result.getInt("cod_entrega"));
                ent.setCodLocacao(loca);
                ent.setQuilometragemFinal(result.getInt("quilometragem_final"));
                ent.setDataEntrega(result.getDate("data_entrega"));
                ent.setHoraEntrega(result.getString("hora_entrega"));
                ent.setValorTotal(result.getInt("valor_total"));
                ent.setQuantidadeVezes(result.getInt("quantidade_vezes"));

                entre.addElement(ent);
            }while(result.next());
        }
        catch(SQLException erro){

            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return entre;
        }
    }

    @SuppressWarnings("unchecked")
    public Vector<Entrega> obterEntrega() throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {
        this.connection = FabricaConexao.obterConexao();

        Vector entre = new Vector();
        try
        {

            sql = "SELECT max(cod_entrega) as cod_entrega  " +
                     "FROM entrega;";


//            this.connection.setAutoCommit(false);
            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            ResultSet result = prepSt.executeQuery();
           
            Entrega ent = new Entrega();

            result.next();
            ent.setCodEntrega(result.getInt("cod_entrega"));

            entre.addElement(ent);
           
           
        }
        catch(SQLException erro){

            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return entre;
        }
    }

}

