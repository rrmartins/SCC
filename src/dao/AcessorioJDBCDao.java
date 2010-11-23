/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Acessorio;
import domain.GrupoCarro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;

/**
 *
 * @author Rodrigo Martins
 */
public class AcessorioJDBCDao implements AcessorioDao {

    private Conexao connection;
    String sql;

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public void inserirAcessorio(Acessorio acessorio) throws MinhaException, SQLException, ConexaoException  {
        
        this.connection = FabricaConexao.obterConexao();
        
        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO acessorio (descricao) values (?);";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.setString(1, acessorio.getDescAcessorio().toString());

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }
    }

    public Acessorio selecionarAcessorio(Acessorio acessorio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerAcessorio(Acessorio acessorio) throws SQLException, MinhaException, ConexaoException {
        
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
             sql = "DELETE FROM acessorio " +
                "WHERE cod_acessorio = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setInt(1, acessorio.getCodAcessorio());
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

    public void alterarAcessorio(Acessorio acessorio) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE acessorio SET " +
                    "descricao = ? " +     //1
                    "WHERE cod_acessorio = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            prepSt.setString(1, acessorio.getDescAcessorio());
            prepSt.setInt(2, acessorio.getCodAcessorio());
            
            prepSt.executeUpdate();
            this.connection .commit();

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

    public Vector<Acessorio> selecionarTodosAcessorio() throws MinhaException, SQLException, ConexaoException{
        sql = "Select * from acessorio";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        Vector acessorios = new Vector();
        while (res.next()) {
            Acessorio acessorio = new Acessorio();
            acessorio.setCodAcessorio(res.getInt("cod_acessorio"));
            acessorio.setDescAcessorio(res.getString("descricao"));

            acessorios.addElement(acessorio);
        }
        this.connection.close();
        return acessorios;
    }

    public Vector<Acessorio> selecionarAcessorioGrupo(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException{

        String sql = "SELECT a.cod_acessorio, a.descricao " +
                "FROM acessorio a, " +
                "grupo_carro gc, " +
                "acessorio_grupo_carro agc " +
                "WHERE a.cod_acessorio = agc.cod_acessorio AND " +
                "gc.cod_grupo_carro = agc.cod_grupo_carro AND " +
                "gc.cod_grupo_carro = ? ;";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        pstmt.setInt(1, grupoCarro.getCodGrupoCarro());

        ResultSet res = pstmt.executeQuery();

        Vector<Acessorio> acessorios = new Vector<Acessorio>();

        if(res == null || !res.next())
            return null;

        while (res.next()) {
            Acessorio acessorio = new Acessorio();
            acessorio.setCodAcessorio(res.getInt("cod_acessorio"));
            acessorio.setDescAcessorio(res.getString("descricao"));

            acessorios.addElement(acessorio);
        }
        this.connection.close();
        return acessorios;
    }

}
