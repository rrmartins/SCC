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

        String sql = "SELECT r.cod_revisao as cod_revisao, "+
                            "c.nome as nome," +
                            " r.cod_entrega as cod_entrega, "+
                            "f.nome as nome_funcionario, " +
                            "r.cod_funcionario as cod_funcionario, "+
                            "o.nome_oficina as nome_oficina, " +
                            "r.cod_oficina as cod_oficina, "+
                            "r.desc_revisao as desc_revisao, " +
                            "r.valor_revisao as valor_revisao, "+
                            "r.data_entrada as data_entrada, " +
                            "r.data_saida as data_saida, "+
                            "r.cod_locacao as cod_locacao, "+
                            "car.placa as placa, " +
                            "car.chassi as chassi "+
                        "FROM revisao as r LEFT JOIN entrega e on r.cod_entrega = e.cod_entrega "+
                            "LEFT JOIN locacao as l on e.cod_locacao = l.cod_locacao "+
                            "LEFT JOIN cliente as c on l.cod_cliente = c.cod_cliente "+
                            "LEFT JOIN funcionario as f on r.cod_funcionario = f.cod_funcionario "+
                            "LEFT JOIN oficina as o on r.cod_oficina = o.cod_oficina "+
                            "LEFT JOIN carro as car on l.cod_carro = car.cod_carro; ";

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

    public int ultRevisao() throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        String sql = "select max(cod_revisao) from revisao;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        ResultSet result = pStmt.executeQuery();

        int ultRevisao ;

        if(result == null || !result.next())
            throw new MinhaException("Não existem Revisões cadastrados no Sistema !");
        else{
            do{

                ultRevisao = result.getInt("cod_revisao");


            }while(result.next());
        }

        this.connection.close();

        return ultRevisao;

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

    public void alterarRevisao(Revisao revisao, Conexao connect) throws MinhaException, SQLException, ConexaoException {

        //this.connection = FabricaConexao.obterConexao();
        
        connect.setAutoCommit(true);

            String sql = "UPDATE revisao set " +
                    "cod_locacao = ? , " +
                    "cod_entrega = ? , " +
                    "cod_funcionario = ? , " +
                    "cod_oficina = ? , " +
                    "desc_revisao = ? , " +
                    "valor_revisao = ? , " +
                    "data_entrada = ? , " +
                    "data_saida = ? " +
                    "WHERE cod_revisao = ? ; ";

            PreparedStatement pStmt = connect.prepareStatement(sql);
            pStmt.setInt(1, revisao.getEntrega().getLocacao().getCodLocacao());
            pStmt.setInt(2, revisao.getEntrega().getCodEntrega());

            String func = String.valueOf(revisao.getFuncionario());
            if (func.equals("null")){
                pStmt.setObject(3, null);
            }else
            {
                pStmt.setInt(3, revisao.getFuncionario().getCodFuncionario());
            }
            
            String ofici = String.valueOf(revisao.getOficina());
            if (ofici.equals("null")){
                pStmt.setObject(4, null);
            }else
            {
                pStmt.setInt(4, revisao.getOficina().getCodOficina());
            }
            pStmt.setString(5, revisao.getDescRevisao());
            pStmt.setDouble(6, revisao.getValorRevisao());
            pStmt.setDate(7, revisao.getDataEntrada());
            pStmt.setDate(8, revisao.getDataSaida());
            pStmt.setInt(9, revisao.getCodRevisao());

            pStmt.executeUpdate();

            //this.connection.close();


    }
    
    public void inserirRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);

            String sql = "INSERT INTO revisao (cod_locacao, cod_entrega, cod_funcionario , " +
                    "cod_oficina, desc_revisao, valor_revisao, data_entrada, data_saida) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            pStmt.setInt(1, revisao.getEntrega().getLocacao().getCodLocacao());
            pStmt.setInt(2, revisao.getEntrega().getCodEntrega());
            
            String func = String.valueOf(revisao.getFuncionario());
            if (func.equals("null")){
                pStmt.setObject(3, null);
            }else
            {
                pStmt.setInt(3, revisao.getFuncionario().getCodFuncionario());
            }

            String oficina = String.valueOf(revisao.getOficina());
            if (oficina.equals("null"))
            {
                pStmt.setObject(4, null);
            }else {
                pStmt.setInt(4, revisao.getOficina().getCodOficina());
            }
            
            pStmt.setString(5, revisao.getDescRevisao());
            pStmt.setDouble(6, revisao.getValorRevisao());
            pStmt.setDate(7,revisao.getDataEntrada());
            pStmt.setDate(8,revisao.getDataSaida());

            pStmt.executeUpdate();
            this.connection.commit();
        }
        catch (SQLException erro) {
            this.connection.rollback();
            throw new MinhaException(erro.getMessage());
        }
        finally {
            this.connection.close();
        }
    }
}

