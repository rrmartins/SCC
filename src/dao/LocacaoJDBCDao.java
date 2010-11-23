package dao;

import domain.Carro;
import domain.Cliente;
import domain.GrupoCarro;
import domain.Locacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class LocacaoJDBCDao implements LocacaoDao {

    private Conexao connection;
    private String sql;

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }
    

    public void inserirLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException {
        
        CarroJDBCDao carroJDBCDao = new CarroJDBCDao();

        this.connection = FabricaConexao.obterConexao();
        
        try{
            
            sql = "INSERT INTO locacao (cod_carro, cod_cliente, quilometragem_inicial, data_locacao, data_entrega, hora_locacao, hora_entrega, quilometragem_prevista, cobertura, valor_previsto, nome_plano) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ; ";
            
            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            
            pStmt.setInt(1, locacao.getCarro().getCodCarro());
            pStmt.setInt(2, locacao.getCliente().getCodCliente());
            pStmt.setInt(3, locacao.getCarro().getQuilometragem());
            pStmt.setDate(4, new java.sql.Date(locacao.getDateLocacao().getTime()));
            pStmt.setDate(5, new java.sql.Date(locacao.getDataEntrega().getTime()));
            pStmt.setTime(6, locacao.getHoraLocacao());
            pStmt.setTime(7, locacao.getHoraEntrega());
            pStmt.setInt(8, locacao.getQuilometragemPrevista());
            pStmt.setBoolean(9, locacao.getCobertura());
            pStmt.setDouble(10, locacao.getValorPrevisto());
            pStmt.setString(11, locacao.getPlano());
            
            pStmt.executeUpdate();

            carroJDBCDao.mudarDisponibilidade(this.connection, locacao.getCarro());

            this.connection.commit();
            
        }
        catch(SQLException erro){
            
            this.connection.rollback();
            erro.printStackTrace();
            throw erro;
        }
        finally{
            
            this.connection.close();
        }
        
    }

    public void removerLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException {
        
        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        sql = "DELETE FROM locacao WHERE cod_locacao = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, locacao.getCodLocacao());

        pStmt.executeUpdate();

        this.connection.close();
    }

    public void alterarLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException {
        
        this.connection = FabricaConexao.obterConexao();
        
        
        try{
            this.connection.setAutoCommit(false);
            
            sql = "UPDATE locacao " +
                    "SET cod_carro = ? ," +
                    "cod_cliente = ?, " +
                    "quilometragem_inicial = ? , " +
                    "data_locacao = ? , " +
                    "data_entrega = ? , " +
                    "hora_locacao = ? , " +
                    "hora_entrega = ? , " +
                    "quilometragem_prevista = ? , " +
                    "cobertura = ? , " +
                    "valor_previsto = ? , " +
                    "nome_plano = ? " +
                    "WHERE cod_locacao = ? ; ";
            
            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            
            pStmt.setInt(1, locacao.getCarro().getCodCarro());
            pStmt.setInt(2, locacao.getCliente().getCodCliente());
            pStmt.setInt(3, locacao.getQuilometragemInicial());
            pStmt.setDate(4, new java.sql.Date(locacao.getDateLocacao().getTime()));
            pStmt.setDate(5, new java.sql.Date(locacao.getDataEntrega().getTime()));
            pStmt.setTime(6, locacao.getHoraLocacao());
            pStmt.setTime(7, locacao.getHoraEntrega());
            pStmt.setInt(8, locacao.getQuilometragemPrevista());
            pStmt.setBoolean(9, locacao.getCobertura());
            pStmt.setDouble(10, locacao.getValorPrevisto());
            pStmt.setString(11, locacao.getPlano());
            pStmt.setInt(12, locacao.getCodLocacao());
            
            pStmt.executeUpdate();
            this.connection.commit();
            
        }
        catch(SQLException erro){
            
            this.connection.rollback();
            throw erro;
        }
        finally{
            
            this.connection.close();
        }
        
    }
    
    public Vector<Locacao> selecionarTodasLocacoes() throws MinhaException, SQLException, ConexaoException {
        
        
        this.connection = FabricaConexao.obterConexao();

         sql = "SELECT l.cod_locacao as cod_locacao, " +
                "c.nome as nome, c.cod_cliente as cod_cliente, c.cpf, " +
                "l.data_locacao as data_locacao, l.data_entrega as data_entrega, " +
                "car.placa as placa, car.modelo as modelo, car.cod_carro as cod_carro, car.chassi as chassi, " +
                "l.quilometragem_inicial as quilometragem_inicial, l.quilometragem_prevista as quilometragem_prevista, " +
                "l.valor_previsto as valor_previsto, l.cobertura as cobertura, " +
                "l.hora_locacao as hora_locacao, hora_entrega as hora_entrega, " +
                "l.nome_plano " +
                "FROM locacao l, " +
                    "cliente c, " +
                    "carro car " +
                "WHERE l.cod_carro = car.cod_carro AND " +
                "l.cod_cliente = c.cod_cliente ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        ResultSet result = pStmt.executeQuery();
        Vector<Locacao> locacoes = new Vector<Locacao>();

        if(result == null || !result.next())
            throw new MinhaException("Não existem Locações cadastrados no Sistema !");
        else{
            do{
                Locacao locacao = new Locacao();
                Cliente cliente = new Cliente();
                Carro carro = new Carro();

                cliente.setCodCliente(result.getInt("cod_cliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                
                carro.setCodCarro(result.getInt("cod_carro"));
                carro.setModelo(result.getString("modelo"));
                carro.setPlaca(result.getString("placa"));
                carro.setChassi(result.getString("chassi"));
                

                locacao.setCodLocacao(result.getInt("cod_locacao"));
                locacao.setQuilometragemInicial(result.getInt("quilometragem_inicial"));
                locacao.setDateLocacao(result.getDate("data_locacao"));
                locacao.setDataEntrega(result.getDate("data_entrega"));
                locacao.setHoraLocacao(result.getTime("hora_locacao"));
                locacao.setHoraEntrega(result.getTime("hora_entrega"));
                locacao.setQuilometragemPrevista(result.getInt("quilometragem_prevista"));
                locacao.setValorPrevisto(result.getDouble("valor_previsto"));
                locacao.setCobertura(result.getBoolean("cobertura"));
                locacao.setPlano(result.getString("nome_plano"));
                locacao.setCarro(carro);
                locacao.setCliente(cliente);

                locacoes.add(locacao);

            }while(result.next());
        }

        return locacoes;
    
    }

    public Locacao selecionarLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        sql = "SELECT * FROM locacao WHERE cod_locacao = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, locacao.getCodLocacao());

        ResultSet resultSet = pStmt.executeQuery();

        Locacao loc = new Locacao();

        if(resultSet == null || !resultSet.next())
            throw new MinhaException("Não existem Locações cadastrados no Sistema !");
        else{
            
            Carro carro = new Carro();
            Cliente cliente = new Cliente();
            
            carro.setCodCarro(resultSet.getInt("cod_carro"));
            cliente.setCodCliente(resultSet.getInt("cod_cliente"));

            loc.setCarro(carro);
            loc.setCliente(cliente);
            loc.setCobertura(resultSet.getBoolean("cobertura"));
            loc.setCodLocacao(resultSet.getInt("cod_locacao"));
            loc.setDataEntrega(resultSet.getDate("data_entrega"));
            loc.setDateLocacao(resultSet.getDate("data_locacao"));
            loc.setHoraEntrega(resultSet.getTime("hora_entrega"));
            loc.setHoraLocacao(resultSet.getTime("hora_locacao"));
            loc.setQuilometragemInicial(resultSet.getInt("quilometragem_inicial"));
            loc.setQuilometragemPrevista(resultSet.getInt("quilometragem_prevista"));
            loc.setValorPrevisto(resultSet.getDouble("valor_previsto"));

        }

        return loc;
    }

    public Locacao selecionarCarroPorCpf(String cpf) throws MinhaException, ConexaoException, SQLException {

        Locacao loca = new Locacao();
        Cliente cliente = new Cliente();
        Carro carro = new Carro();


        this.connection = FabricaConexao.obterConexao();


        sql = "select max(lo.cod_locacao) as codigo ,"+
                      "lo.cod_carro, "+
                      "lo.cod_cliente ,"+
                      "lo.quilometragem_inicial ,"+
                      "lo.data_locacao ,"+
                      "lo.data_entrega ,"+
                      "lo.hora_locacao ,"+
                      "lo.hora_entrega ,"+
                      "lo.quilometragem_prevista ,"+
                      "lo.cobertura ,"+
                      "lo.valor_previsto ,"+
                      "lo.nome_plano "+
		"from locacao lo, "+
			"cliente cl "+
		"where lo.cod_cliente = cl.cod_cliente and "+
		      "cl.cpf = '"+cpf+"' "+
		 "group by lo.cod_carro ,"+
                      "lo.cod_cliente ,"+
                      "lo.quilometragem_inicial ,"+
                      "lo.data_locacao ,"+
                      "lo.data_entrega ,"+
                      "lo.hora_locacao ,"+
                      "lo.hora_entrega ,"+
                      "lo.quilometragem_prevista ,"+
                      "lo.cobertura ,"+
                      "lo.valor_previsto ,"+
                      "lo.nome_plano "+
                      "ORDER BY codigo desc "+
                    "LIMIT 1";


        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        ResultSet result = pStmt.executeQuery();

        if(result == null || !result.next())
        {
            this.connection.close();
            throw  new MinhaException(" Não existe cliente cadastrado com esse CPF !");
        }
        else
        {
            cliente.setCodCliente(result.getInt("cod_cliente"));

            carro.setCodCarro(result.getInt("cod_carro"));

            loca.setCodLocacao(result.getInt("codigo"));
            loca.setQuilometragemInicial(result.getInt("quilometragem_inicial"));
            loca.setQuilometragemPrevista(result.getInt("quilometragem_prevista"));
            loca.setDataEntrega(result.getDate("data_entrega"));
            loca.setDateLocacao(result.getDate("data_locacao"));
            loca.setHoraEntrega(result.getTime("hora_entrega"));
            loca.setHoraLocacao(result.getTime("hora_locacao"));
            loca.setCobertura(result.getBoolean("cobertura"));
            loca.setValorPrevisto(result.getDouble("valor_previsto"));
            loca.setPlano(result.getString("nome_plano"));
            loca.setCliente(cliente);
            loca.setCarro(carro);

        }

        this.connection.close();

        return loca;
    }


}

