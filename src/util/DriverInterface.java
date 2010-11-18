package util;

import java.sql.Connection;

public interface DriverInterface {
    public Connection obterConexao() throws MinhaException;

    public Conexao obterConexaoRelatorio() throws ConexaoException;

}
