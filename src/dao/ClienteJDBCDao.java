package dao;

import domain.Carro;
import domain.Cidade;
import domain.Cliente;
import domain.Endereco;
import domain.GrupoCarro;
import domain.Locacao;
import domain.UF;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import util.Conexao;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;


public class ClienteJDBCDao implements ClienteDao {

    private Conexao connection;
    private String sql;

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<Cliente> selecionarCliente(String login) throws MinhaException, ConexaoException, SQLException {
        
        this.connection = FabricaConexao.obterConexao();

        sql = "SELECT * FROM cliente where login ='"+login+"';";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        ResultSet result = pStmt.executeQuery();
        result.next();

        Vector<Cliente> clienteEncontrados = new Vector<Cliente>();

        do{
            Cliente cliente = new Cliente();
            Endereco endereco = new Endereco();
            Cidade cidade = new Cidade();

            cidade.setCodCidade(result.getInt("cod_cidade"));

            endereco.setNomeRua(result.getString("rua"));
            endereco.setNumero(result.getInt("numero_casa"));
            endereco.setNomeBairro(result.getString("bairro"));
            endereco.setCidade(cidade);

   /*0*/     cliente.setCodCliente(result.getInt("cod_cliente"));
   /*1*/     cliente.setNome(result.getString("nome"));
   /*2*/     cliente.setCpf(result.getString("cpf"));
   /*3*/     cliente.setLogin(result.getString("login"));
   /*4*/     cliente.setSenha(result.getString("senha"));
   /*5*/     cliente.setCartaoCredito(result.getString("cartao_credito"));

   /*6*/     cliente.setEmail(result.getString("email"));
   /*7*/     cliente.setTelefone(result.getString("telefone"));
   /*8*/     cliente.setDataNasc((result.getDate("data_nasc")));
   /*9*/     cliente.setEndereco(endereco);

             clienteEncontrados.add(cliente);


        }while(result.next());

        this.connection.close();

        return clienteEncontrados;
    }

    public void removerCliente(Cliente cliente) throws MinhaException, ConexaoException, SQLException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        sql = "DELETE FROM cliente WHERE cod_cliente = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, cliente.getCodCliente());

        pStmt.executeUpdate();

        this.connection.close();

    }

    public void alterarCliente(Cliente cliente) throws MinhaException, ConexaoException, SQLException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        sql = "UPDATE cliente " +
                "SET nome = ? ," +
                "cpf = ? ," +
                "data_nasc = ? ," +
                "rua = ?, " +
                "numero_casa = ? , " +
                "bairro = ? , " +
                "email = ? , " +
                "telefone = ? , " +
                "login = ? , " +
                "senha = ? , " +
                "cartao_credito = ? , " +
                "cod_cidade = ? " +
                "WHERE cod_cliente = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        pStmt.setString(1, cliente.getNome());
        pStmt.setString(2, cliente.getCpf());
        pStmt.setDate(3,(Date) cliente.getDataNasc());
        pStmt.setString(4, cliente.getEndereco().getNomeRua());
        pStmt.setInt(5, cliente.getEndereco().getNumero());
        pStmt.setString(6, cliente.getEndereco().getNomeBairro());
        pStmt.setString(7, cliente.getEmail());
        pStmt.setString(8, cliente.getTelefone());
        pStmt.setString(9, cliente.getLogin());
        pStmt.setString(10, cliente.getSenha());
        pStmt.setString(11, cliente.getCartaoCredito());
        pStmt.setInt(12, cliente.getEndereco().getCidade().getCodCidade());
        pStmt.setInt(13, cliente.getCodCliente());

        pStmt.executeUpdate();

        this.connection.close();

    }

    public boolean inserirCliente(Cliente cliente) throws MinhaException, ConexaoException, SQLException{

        this.connection = FabricaConexao.obterConexao();


        try
        {
            this.connection.setAutoCommit(false);

            sql = "INSERT INTO cliente(nome, cpf, data_nasc, rua, numero_casa, bairro, email, telefone, login, senha, cartao_credito, cod_cidade) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);

            pStmt.setString(1, cliente.getNome());
            pStmt.setString(2, cliente.getCpf());
            pStmt.setDate(3,(Date) cliente.getDataNasc());
            pStmt.setString(4, cliente.getEndereco().getNomeRua());
            pStmt.setInt(5, cliente.getEndereco().getNumero());
            pStmt.setString(6, cliente.getEndereco().getNomeBairro());
            pStmt.setString(7, cliente.getEmail());
            pStmt.setString(8, cliente.getTelefone());
            pStmt.setString(9, cliente.getLogin());
            pStmt.setString(10, cliente.getSenha());
            pStmt.setString(11, cliente.getCartaoCredito());
            pStmt.setInt(12, cliente.getEndereco().getCidade().getCodCidade());

            pStmt.executeUpdate();
            this.connection.commit();

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return true;
        }
    }

    public Cliente selecionarClientePorCpf(String cpf) throws MinhaException, ConexaoException, SQLException {

        this.connection = FabricaConexao.obterConexao();

        sql = "SELECT * " +
                "FROM cliente " +
                "WHERE cpf = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        pStmt.setString(1, cpf);

        ResultSet result = pStmt.executeQuery();

        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        Cidade cid = new Cidade();

        if(result == null || !result.next())
        {
            this.connection.close();
            throw  new MinhaException(" Não existe cliente cadastrado com esse CPF !");
        }
        else
        {
            cid.setCodCidade(result.getInt("cod_cidade"));

            endereco.setNomeRua(result.getString("rua"));
            endereco.setNumero(result.getInt("numero_casa"));
            endereco.setNomeBairro(result.getString("bairro"));
            endereco.setCidade(cid);

            cliente.setCodCliente(result.getInt("cod_cliente"));
            cliente.setNome(result.getString("nome"));
            cliente.setCpf(result.getString("cpf"));
            cliente.setEmail(result.getString("email"));
            cliente.setTelefone(result.getString("telefone"));
            cliente.setDataNasc((result.getDate("data_nasc")));
            cliente.setCartaoCredito(result.getString("cartao_credito"));
            cliente.setEndereco(endereco);

        }

        this.connection.close();

        return cliente;

    }

    @SuppressWarnings("unchecked")
    public Vector selecionarClientePelaLocacao(String codLocacao) throws MinhaException, ConexaoException, SQLException {
        this.connection = FabricaConexao.obterConexao();

        int codLoca = Integer.parseInt(codLocacao);

        sql = "select cl.cpf, "+
                     "cl.nome, "+
                     "ca.placa, "+
                     "gc.nome_grupo_carro, " +
                     "ca.modelo, "+
                     "lo.quilometragem_inicial, "+
                     "lo.cobertura, "+
                     "lo.valor_previsto "+
              "from cliente cl, "+
                   "carro ca, "+
                   "grupo_carro gc, "+
                   "locacao lo "+
              "where lo.cod_cliente = cl.cod_cliente and "+
                    "lo.cod_grupo_carro = gc.cod_grupo_carro and "+
                    "gc.cod_grupo_carro = ca.cod_grupo_carro and "+
                    "lo.cod_locacao = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, codLoca);

        ResultSet result = pStmt.executeQuery();

        Locacao loca = new Locacao();
        GrupoCarro gc = new GrupoCarro();
        Cliente cliente = new Cliente();
        Carro ca = new Carro();

        Vector vetConsult = new Vector();


        if(result == null || !result.next())
        {
            this.connection.close();
            throw  new MinhaException(" Não existe cliente cadastrado com esse CPF !");
        }
        else
        {
            vetConsult.addElement(result.getString("cpf"));
            vetConsult.addElement(result.getString("nome"));
            vetConsult.addElement(result.getString("placa"));
            vetConsult.addElement(result.getString("modelo"));
            vetConsult.addElement(result.getString("nome_grupo_carro"));
            vetConsult.addElement(result.getInt("quilometragem_inicial"));
            
            Boolean cobertura = result.getBoolean("cobertura");
            if (cobertura.equals(true)) {
                vetConsult.addElement("Sim");
            }
            if (cobertura.equals(false)) {
                vetConsult.addElement("Não");
            }
            
            vetConsult.addElement(result.getFloat("valor_previsto"));
        }

        this.connection.close();

        return vetConsult;

    }

    public Vector<Cliente> selecionarTodosClientes () throws MinhaException, ConexaoException, SQLException {
        
        this.connection = FabricaConexao.obterConexao();
        
        sql = "SELECT c.*, " +
                "cid.nome_cidade as nome_cidade, " +
                "uf.sigla_uf as sigla_uf,  " +
                "uf.nome_uf as nome_uf " +
                "FROM cliente c, " +
                "cidade cid, " +
                "uf " +
                "WHERE c.cod_cidade = cid.cod_cidade and " +
                "cid.sigla_uf = uf.sigla_uf ;";
        
        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        
        ResultSet result = pStmt.executeQuery();
        
        Vector<Cliente> clientes = new Vector<Cliente>();

        if(result == null || !result.next())
        {
            this.connection.close();
            throw  new MinhaException(" Não existem clientes cadastrados !");
        }
        else
        {
            do{
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();
                Cidade cid = new Cidade();
                UF uf = new UF();

                uf.setUF(result.getString("sigla_uf"));
                uf.setNomeUF(result.getString("nome_uf"));
                
                cid.setCodCidade(result.getInt("cod_cidade"));
                cid.setNomeCidade(result.getString("nome_cidade"));
                cid.setUF(uf);

                endereco.setNomeRua(result.getString("rua"));
                endereco.setNumero(result.getInt("numero_casa"));
                endereco.setNomeBairro(result.getString("bairro"));
                endereco.setCidade(cid);

                cliente.setCodCliente(result.getInt("cod_cliente"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                cliente.setEmail(result.getString("email"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setLogin(result.getString("login"));
                cliente.setSenha(result.getString("senha"));
                cliente.setDataNasc((result.getDate("data_nasc")));
                cliente.setCartaoCredito(result.getString("cartao_credito"));
                cliente.setEndereco(endereco);
                
                clientes.add(cliente);
                
            }while(result.next());
            
        }
        
        this.connection.close();
        
        return clientes;
    }

}

