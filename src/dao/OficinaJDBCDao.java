package dao;

import domain.Cidade;
import domain.Endereco;
import domain.Oficina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class OficinaJDBCDao implements OficinaDao {

    private Conexao connection;
    private String sql;

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<Oficina> selecionarTodasOficinas() throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        sql = "SELECT * FROM oficina ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        ResultSet result = pStmt.executeQuery();
        result.next();

        Vector<Oficina> vetOficinas = new Vector<Oficina>();

        do{
            Oficina oficina = new Oficina();
            Endereco endereco = new Endereco();
            Cidade cidade = new Cidade();

            cidade.setCodCidade(result.getInt("cod_cidade"));

            endereco.setNomeRua(result.getString("rua"));
            endereco.setNumero(result.getInt("numero"));
            endereco.setNomeBairro(result.getString("bairro"));
            endereco.setCidade(cidade);

            oficina.setCodOficina(result.getInt("cod_oficina"));
            oficina.setCnpj(result.getString("cnpj"));
            oficina.setNomeOficina(result.getString("nome_oficina"));
            oficina.setTelefone(result.getString("telefone"));
            oficina.setEndereco(endereco);

            vetOficinas.add(oficina);

        }while(result.next());

        this.connection.close();

        return vetOficinas;
    }

    public Oficina selecionarOficina(Oficina oficina) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerOficina(Oficina oficina) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {

        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "DELETE FROM oficina " +
                   "WHERE cod_oficina = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setInt(1, oficina.getCodOficina());
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

    public void alterarOficina(Oficina oficina) throws SQLException, MinhaException, ConexaoException {
        
        this.connection = FabricaConexao.obterConexao();
        
        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE oficina SET " +
                    "cod_cidade = ? ," +     //1
                    "nome_oficina = ? , " +   //2
                    "cnpj = ? , " +       //3
                    "telefone = ? , " +      //4
                    "rua = ? ," +        //5
                    "numero_oficina = ? ," +        //6
                    "bairro = ? " +        //7
                    "WHERE cod_oficina = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            prepSt.setInt(1, oficina.getCodCidade().getCodCidade());
            prepSt.setString(2, oficina.getNomeOficina());
            prepSt.setString(3, oficina.getCnpj());
            prepSt.setString(4, oficina.getTelefone());
            prepSt.setString(5, oficina.getEndereco().getNomeRua());
            prepSt.setInt(6, oficina.getEndereco().getNumero());
            prepSt.setString(7, oficina.getEndereco().getNomeBairro());
            prepSt.setInt(8, oficina.getCodOficina());

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

    public void inserirOficina(Oficina oficina, Cidade ci, int codCidade) throws MinhaException, ConexaoException, SQLException {
        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO oficina (nome_oficina, cnpj, telefone, rua, numero_oficina, bairro, cod_cidade ) " +
                    "values (?,?,?,?,?,?,?);";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1, oficina.getNomeOficina().toString());
            pstmt.setString(2, oficina.getCnpj().toString());
            pstmt.setString(3, oficina.getTelefone().toString());
            pstmt.setString(4, oficina.getEndereco().getNomeRua().toString());
            pstmt.setInt(5, oficina.getEndereco().getNumero());
            pstmt.setString(6, oficina.getEndereco().getNomeBairro().toString());
            pstmt.setInt(7, codCidade);

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    public Vector<Oficina> obterOficina(String texto) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {
        this.connection = FabricaConexao.obterConexao();

        if (texto.equals("Codigo")) {
            texto = "cod_oficina";
        }
        if (texto.equals("Cod Cidade")) {
            texto = "cod_cidade";
        }
        if (texto.equals("Nome")) {
            texto = "nome_oficina";
        }
        if (texto.equals("CNPJ")) {
            texto = "cnpj";
        }
        if (texto.equals("Telefone")) {
            texto = "telefone";
        }
        if (texto.equals("Rua")) {
            texto = "rua";
        }
        if (texto.equals("Numero")) {
            texto = "numero_oficina";
        }
        if (texto.equals("Bairro")) {
            texto = "bairro";
        }

        Vector ofic = new Vector();
        try
        {
            sql = "SELECT * " +
                    "FROM oficina "+
                    "order by " + texto + ";";

            this.connection.setAutoCommit(false);
            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            ResultSet result = prepSt.executeQuery();
            result.next();

            do
            {
                Oficina oficina = new Oficina();
                Cidade cid = new Cidade();
                Endereco end = new Endereco();

                cid.setCodCidade(result.getInt("cod_cidade"));

                end.setNomeRua(result.getString("rua"));
                end.setNumero(result.getInt("numero_oficina"));
                end.setNomeBairro(result.getString("bairro"));
                end.setCidade(cid);

                oficina.setCodOficina(result.getInt("cod_oficina"));
                oficina.setCodCidade(cid);
                oficina.setNomeOficina(result.getString("nome_oficina"));
                oficina.setCnpj(result.getString("cnpj"));
                oficina.setTelefone(result.getString("telefone"));
                oficina.setEndereco(end);
                

                ofic.addElement(oficina);
            }while(result.next());
        }
        catch(SQLException erro){

            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return ofic;
        }
    }

}

