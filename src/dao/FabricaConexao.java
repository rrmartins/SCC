/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import util.Conexao;
import util.ConexaoException;
import util.DriverInterface;
import util.JdbcDriver;
import util.MinhaException;
import util.PostGresNativeDriver;

/**
 *
 * @author Rodrigo Martins
 */
public class FabricaConexao {

    public static Connection obterConexaoRelatorio() throws MinhaException {
        DriverInterface driver = new JdbcDriver();
        return driver.obterConexao();

    }

    public static Conexao obterConexao() throws SQLException, ConexaoException {
        DriverInterface driver = new PostGresNativeDriver("scc", "postgres", "natali");
        return driver.obterConexaoRelatorio();
    }
}
