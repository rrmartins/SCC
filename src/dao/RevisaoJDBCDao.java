package dao;

import domain.Carro;
import domain.Cliente;
import domain.Entrega;
import domain.Funcionario;
import domain.Locacao;
import domain.Oficina;
import domain.Revisao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class RevisaoJDBCDao implements RevisaoDao {

    private Conexao connection;

    public RevisaoJDBCDao () {
    }

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<Revisao> selecionarTodasRevisoes() throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        String sql = "SELECT r.cod_revisao as cod_revisao, " +
                "c.nome as nome, r.cod_entrega as cod_entrega, " +
                "f.nome as nome_funcionario, r.cod_funcionario as cod_funcionario, " +
                "o.nome_oficina as nome_oficina, r.cod_oficina as cod_oficina, " +
                "r.desc_revisao as desc_revisao, r.valor_revisao as valor_revisao, " +
                "r.data_entrada as data_entrada, r.data_saida as data_saida, " +
                "r.cod_locacao as cod_locacao, " +
                "car.placa as placa, car.chassi as chassi " +
                "FROM revisao r, " +
                    "entrega e, " +
                    "funcionario f, " +
                    "oficina o, " +
                    "locacao l, " +
                    "cliente c, " +
                    "carro car " +
                "WHERE r.cod_entrega = e.cod_entrega AND " +
                "e.cod_locacao = l.cod_locacao AND " +
                "l.cod_cliente = c.cod_cliente AND " +
                "r.cod_funcionario = f.cod_funcionario AND " +
                "r.cod_oficina = o.cod_oficina AND " +
                "l.cod_carro = car.cod_carro ; ";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        ResultSet result = pStmt.executeQuery();

        Vector<Revisao> revisoes = new Vector<Revisao>();

        if(result == null || !result.next())
            throw new MinhaException("Não existem Revisões cadastrados no Sistema !");
        else{
            do{

                Revisao revisao = new Revisao();
                Entrega entrega = new Entrega();
                Locacao locacao = new Locacao();
                Funcionario funcionario = new Funcionario();
                Oficina oficina = new Oficina();
                Cliente cliente = new Cliente();
                Carro carro = new Carro();

                carro.setPlaca(result.getString("placa"));
                carro.setChassi(result.getString("chassi"));

                cliente.setNome(result.getString("nome"));
                locacao.setCodLocacao(result.getInt("cod_locacao"));
                locacao.setCliente(cliente);
                locacao.setCarro(carro);

                entrega.setCodEntrega(result.getInt("cod_entrega"));
                entrega.setLocacao(locacao);

                funcionario.setCodFuncionario(result.getInt("cod_funcionario"));
                funcionario.setNome(result.getString("nome_funcionario"));


                oficina.setCodOficina(result.getInt("cod_oficina"));
                oficina.setNomeOficina(result.getString("nome_oficina"));

                revisao.setCodRevisao(result.getInt("cod_revisao"));
                revisao.setEntrega(entrega);
                revisao.setValorRevisao(result.getDouble("valor_revisao"));
                revisao.setDescRevisao(result.getString("desc_revisao"));
                revisao.setFuncionario(funcionario);
                revisao.setOficina(oficina);
                revisao.setDataEntrada(result.getDate("data_entrada"));
                revisao.setDataSaida(result.getDate("data_entrada"));


                revisoes.add(revisao);


            }while(result.next());
        }

        this.connection.close();

        return revisoes;

    }


    public Revisao selecionarRevisao(Revisao revisao) throws MinhaException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();
        this.connection.setAutoCommit(true);

        String sql = "DELETE FROM revisao WHERE cod_revisao = ? ; ";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, revisao.getCodRevisao());

        pStmt.executeUpdate();

        this.connection.close();
    }

    public void alterarRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();
        
        this.connection.setAutoCommit(true);

            String sql = "UPDATE FROM revisao " +
                    "cod_locacao = ? , " +
                    "cod_entrega = ? , " +
                    "cod_funcionario = ? , " +
                    "cod_oficina = ? , " +
                    "desc_revisao = ? , " +
                    "valor_revisao = ? , " +
                    "data_entrada = ? , " +
                    "data_saida = ? " +
                    "WHERE cod_revisao = ? ; ";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            pStmt.setInt(1, revisao.getEntrega().getLocacao().getCodLocacao());
            pStmt.setInt(2, revisao.getEntrega().getCodEntrega());
            pStmt.setInt(3, revisao.getFuncionario().getCodFuncionario());
            pStmt.setInt(4, revisao.getOficina().getCodOficina());
            pStmt.setString(5, revisao.getDescRevisao());
            pStmt.setDouble(6, revisao.getValorRevisao());
            pStmt.setDate(7, (Date) revisao.getDataEntrada());
            pStmt.setDate(8, (Date) revisao.getDataSaida());
            pStmt.setInt(9, revisao.getCodRevisao());

            pStmt.executeUpdate();

            this.connection.close();


    }
    
    public void inserirRevisao(Revisao revisao, Conexao connection) throws MinhaException, SQLException {

        this.connection = connection;

        try {
            this.connection.setAutoCommit(false);

            String sql = "INSERT INTO revisao (cod_locacao, cod_entrega, cod_funcionario , " +
                    "cod_oficina, desc_revisao, valor_revisao, data_entrada, data_saida) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            pStmt.setInt(1, revisao.getEntrega().getLocacao().getCodLocacao());
            pStmt.setInt(2, revisao.getEntrega().getCodEntrega());
            pStmt.setInt(3, revisao.getFuncionario().getCodFuncionario());
            pStmt.setInt(4, revisao.getOficina().getCodOficina());
            pStmt.setString(5, revisao.getDescRevisao());
            pStmt.setDouble(6, revisao.getValorRevisao());
            pStmt.setDate(7, (Date) revisao.getDataEntrada());
            pStmt.setDate(8, (Date) revisao.getDataSaida());

            pStmt.executeUpdate();

            this.connection.commit();

        }
        catch (SQLException erro) {
            this.connection.rollback();
            throw new MinhaException(erro.getMessage());
        }
        finally{
            this.connection.close();
        }
    }
}

