
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class JdbcDriver implements DriverInterface {

    /**
     *
     * @return
     * @throws MinhaException
     */
    public Connection obterConexao() throws MinhaException
    {
        
        try
        {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/scc", "scc", "Postgre2010");

        }
        catch(ClassNotFoundException erro)
        {
            throw new MinhaException(erro.getMessage());
        }
        catch(SQLException erro)
        {
            throw new MinhaException(erro.getMessage());
        }
    }

    public Conexao obterConexaoRelatorio() throws ConexaoException {
        try {
            String url;
            url = "jdbc:postgresql://localhost:5432/scc";
            Class.forName("org.postgresql.Driver");
            Conexao conexao = new Conexao();
            conexao.setConnection(DriverManager.getConnection(url, "postgres", "natali"));
            return conexao;
        }catch(ClassNotFoundException erro){
            throw new ConexaoException(erro.getMessage());
        }catch(SQLException erro){
            throw new ConexaoException(erro.getMessage());
        }
    }


}
