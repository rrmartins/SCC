package dao;

import domain.TipoCarro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class TipoCarroJDBCDao implements TipoCarroDao {

    private Conexao connection;
    private String sql;
    
    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<TipoCarro> selecionarTodosTipoCarro()  throws MinhaException, SQLException, ConexaoException {

        sql = "Select * from tipo_carro";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        Vector tipoCarros = new Vector();
        while (res.next()) {
            TipoCarro tipoCarro = new TipoCarro();
            tipoCarro.setCodTipoCarro(res.getInt("cod_tipo_carro"));
            tipoCarro.setNomeTipoCarro(res.getString("nome_tipo_carro"));
            tipoCarro.setDescTipoCarro(res.getString("desc_tipo_carro"));

            tipoCarros.addElement(tipoCarro);
        }
        this.connection.close();
        return tipoCarros;
    }

    public TipoCarro selecionarTipoCarro(TipoCarro tipoCarro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerTipoCarro(TipoCarro tipoCarro) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
             sql = "DELETE FROM tipo_carro " +
                "WHERE cod_tipo_carro = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setInt(1, tipoCarro.getCodTipoCarro());
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

    public void alterarTipoCarro(TipoCarro tipoCarro) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE tipo_carro SET " +
                    "nome_tipo_carro = ? ," +     //1
                    "desc_tipo_carro = ? " +     //2
                    "WHERE cod_tipo_carro = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            prepSt.setString(1, tipoCarro.getNomeTipoCarro());
            prepSt.setString(2, tipoCarro.getDescTipoCarro());
            prepSt.setInt(3, tipoCarro.getCodTipoCarro());

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

    public void inserirTipoCarro(TipoCarro tipoCarro) throws MinhaException, SQLException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO tipo_carro (desc_tipo_carro,nome_tipo_carro) values (?,?)";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1, tipoCarro.getDescTipoCarro().toString());
            pstmt.setString(2, tipoCarro.getNomeTipoCarro().toString());

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }
        
    }

    public TipoCarro selecionarTipoCarro(String nome) throws MinhaException, SQLException, ConexaoException  {
        
        TipoCarro tipoCarro = new TipoCarro();

        this.connection = FabricaConexao.obterConexao();

        sql = "select tc.* "+
                     "from tipo_carro tc,"+
                        "grupo_carro gc "+
                     "where tc.cod_tipo_carro = gc.cod_tipo_carro and "+
                        "gc.nome_grupo_carro = ? ";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        pStmt.setString(1, nome);

        ResultSet result = pStmt.executeQuery();

        if(result == null || !result.next())
        {
            this.connection.close();
            throw  new MinhaException(" Não existe cliente cadastrado com esse CPF !");
        }
        else
        {
            tipoCarro.setCodTipoCarro(result.getInt("cod_tipo_carro"));
            tipoCarro.setDescTipoCarro(result.getString("desc_tipo_carro"));
            tipoCarro.setNomeTipoCarro(result.getString("nome_tipo_carro"));
        }

        this.connection.close();

        return tipoCarro;
    }

}

