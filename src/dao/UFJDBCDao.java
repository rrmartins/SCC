package dao;

import domain.UF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class UFJDBCDao implements UFDao {
 
    private Conexao connection;
    private String sql;

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<UF> selecionarTodasUF() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public UF selecionarUF(UF uf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerUF(UF uf) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
             sql = "DELETE FROM uf " +
                "WHERE sigla_uf = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setString(1, uf.getUF());
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

    public void alterarUF(UF uf) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE uf SET " +
                    "nome_uf = ? " +     //1
                    "WHERE sigla_uf = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            prepSt.setString(1, uf.getNomeUF());
            prepSt.setString(2, uf.getUF());

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

    public void inserirUF(UF uf) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();
        
        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO uf (sigla_uf,nome_uf) values (?,?)";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1, uf.getUF().toString());
            pstmt.setString(2, uf.getNomeUF().toString());

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }

    }

    public Vector<UF> obterUF() throws MinhaException, SQLException, ConexaoException {
        sql = "Select * from uf";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        Vector ufs = new Vector();
        while (res.next()) {
            UF uf = new UF();
            uf.setUF(res.getString("sigla_uf"));
            uf.setNomeUF(res.getString("nome_uf"));

            ufs.addElement(uf);
        }
        this.connection.close();
        return ufs;
    }

}

