package util;

import java.io.File;
import java.sql.*;

public class Conexao {
    private Connection databaseConnection = null;
    private File file = null;

    public void setConnection(Connection connection) {
        this.databaseConnection = connection;
    }

    public Connection getConnection() {
        return this.databaseConnection;
    }

    public void setAutoCommit(boolean b) throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.setAutoCommit(b);
    }
    public void commit() throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.commit();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (this.databaseConnection != null)
            return databaseConnection.prepareStatement(sql);
        else
            return null;
    }

    public void rollback() throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.rollback();
    }

    public void close() throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.close();
    }

    void setFile(File file) {
        this.file = file;
    }
    
    public File getFile() {
        return this.file;
    }
}
