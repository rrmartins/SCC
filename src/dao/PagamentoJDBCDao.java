package dao;

import domain.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;

public class PagamentoJDBCDao implements PagamentoDao {

    private Conexao connection;
    String sql;
    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public void inserirPagamento(Pagamento pagamento) throws MinhaException, SQLException, ConexaoException  {

        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);
            sql = "insert into pagamento (cod_locacao, cod_entrega, quantidade_vezes, valor_total) " +
                    "values (?,?,?,?);";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            pstmt.setInt(1, pagamento.getEntrega().getCodLocacao().getCodLocacao());
            pstmt.setInt(2, pagamento.getEntrega().getCodEntrega());
            pstmt.setInt(3, pagamento.getQuantidadeVezes());
            pstmt.setDouble(4, pagamento.getValorTotal());

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }
    }


}

