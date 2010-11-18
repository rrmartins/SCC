package util;

import java.sql.*;

public class PostGresNativeDriver extends SQLDriver implements DriverInterface{
    private String ip, porta;

    public PostGresNativeDriver(String nomeBanco, String usuario, String senha, String ip, String porta){
        super(nomeBanco, usuario, senha);
        this.ip = ip;
        this.porta = porta;
    }

    public PostGresNativeDriver(String nomeBanco, String usuario, String senha){
        super(nomeBanco, usuario, senha);
        this.ip = "localhost";
        this.porta = "5432";
    }

    public Conexao obterConexaoRelatorio() throws ConexaoException {
        try {
            String url;
            url = "jdbc:postgresql://" + this.ip + ":" + this.porta + "/" + this.nomeBanco;
            Class.forName("org.postgresql.Driver");
            Conexao conexao = new Conexao();
            conexao.setConnection(DriverManager.getConnection(url, this.usuario, this.senha));
            return conexao;
        }catch(ClassNotFoundException erro){
            throw new ConexaoException(erro.getMessage());
        }catch(SQLException erro){
            throw new ConexaoException(erro.getMessage());
        }
    }

    public Connection obterConexao() throws MinhaException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
