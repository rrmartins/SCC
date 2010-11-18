package dao;

import domain.Plano;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Conexao;
import util.ConexaoException;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D815A27B-0DEF-F33D-92FD-74BEA17BA9A8]
// </editor-fold> 
public class PlanoJDBCDao implements PlanoDao {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.994ACCB1-C47C-AA00-B28F-E4CA31C1F4EF]
    // </editor-fold> 
    private Connection connection;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2EF0ABC8-7A0B-5176-5B54-C72298E4BFEE]
    // </editor-fold> 
    public Connection getConnection () {
        return connection;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CAC1EA9F-3BD4-8544-9FF7-63C95E205DA4]
    // </editor-fold> 
    public void setConnection (Connection val) {
        this.connection = val;
    }

    public ArrayList<Plano> selecionarTodosPlanos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Plano selecionarPlano(Plano plano) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removerPlano(Plano plano) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean alterarPlano(Plano plano) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void inserirPlano(Plano plano) throws ConexaoException, SQLException  {
        Conexao conexao = FabricaConexao.obterConexao();

        try {
            conexao.setAutoCommit(false);
            String sql = "INSERT INTO plano (nome_plano,desc_plano) values (?,?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, plano.getNomePlano().toString());
            pstmt.setString(2, plano.getDescPlano().toString());

            pstmt.executeUpdate();
            conexao.commit();
        } catch (SQLException erro) {
            conexao.rollback();
            throw erro;
        } finally {
            conexao.close();
        }
    }

}

