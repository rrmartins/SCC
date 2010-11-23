/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dao.ClienteDao;
import dao.FabricaDao;
import domain.Cidade;
import domain.Cliente;
import domain.Endereco;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

/**
 *
 * @author aluno-info3
 */
public class ControladoraCliente {

    private Vector<Cliente> vetClientes = new Vector<Cliente>();
    private int marc;
    private SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<Cliente> getVetClientes() {
        return vetClientes;
    }

    public void setVetClientes(Vector<Cliente> vetClientes) {
        this.vetClientes = vetClientes;
    }

    public Vector montarLinhasClientes(Cliente cliente){

        Vector novaLinha = new Vector();

        String data = formatoData.format(cliente.getDataNasc());

        novaLinha.addElement(cliente.getCodCliente());
        novaLinha.addElement(cliente.getNome());
        novaLinha.addElement(cliente.getCpf());
        novaLinha.addElement(cliente.getEmail());
        novaLinha.addElement(cliente.getTelefone());
        novaLinha.addElement(data);
        novaLinha.addElement(cliente.getEndereco().getNomeRua());
        novaLinha.addElement(cliente.getEndereco().getNumero());
        novaLinha.addElement(cliente.getEndereco().getNomeBairro());
        novaLinha.addElement(cliente.getEndereco().getCidade().getNomeCidade());
        novaLinha.addElement(cliente.getEndereco().getCidade().getUF().getUF());

        return novaLinha;
    }



    public Vector montarLinhaClienteCompleto(Cliente cliente){

        Vector linha = new Vector();

        String data = formatoData.format(cliente.getDataNasc());

        linha.addElement(cliente.getCodCliente());
        linha.addElement(cliente.getNome());
        linha.addElement(cliente.getCpf());
        linha.addElement(cliente.getEmail());
        linha.addElement(cliente.getTelefone());
        linha.addElement(data);
        linha.addElement(cliente.getLogin());
        linha.addElement(cliente.getSenha());
        linha.addElement(cliente.getCartaoCredito());
        linha.addElement(cliente.getEndereco().getNomeRua());
        linha.addElement(cliente.getEndereco().getNumero());
        linha.addElement(cliente.getEndereco().getNomeBairro());
        linha.addElement(cliente.getEndereco().getCidade().getNomeCidade());
        linha.addElement(cliente.getEndereco().getCidade().getUF().getUF());
        linha.addElement(cliente.getEndereco().getCidade().getUF().getNomeUF());


        return linha;
    }
    


    public void criaCliente(Cliente cliente, Vector novoCliente, ControladoraCidade controladoraCidade) throws ParseException, MinhaException, SQLException
    {

        String novaData = novoCliente.get(2).toString();
        java.sql.Date data = new java.sql.Date(formatoData.parse(novaData).getTime());

        int numCasa = Integer.parseInt(novoCliente.get(4).toString());

        Cidade cidade = new Cidade();
        cidade = controladoraCidade.getVetCidades().get(controladoraCidade.getMarc());

        Endereco endereco = new Endereco();
        endereco.setNomeRua(novoCliente.get(3).toString());
        endereco.setNumero(numCasa);
        endereco.setNomeBairro(novoCliente.get(5).toString());
        endereco.setCidade(cidade);

        cliente.setNome(novoCliente.get(0).toString());
        cliente.setCpf(novoCliente.get(1).toString());
        cliente.setDataNasc(data);
        cliente.setEndereco(endereco);
        cliente.setEmail(novoCliente.get(8).toString());
        cliente.setTelefone(novoCliente.get(9).toString());
        cliente.setCartaoCredito(novoCliente.get(10).toString());
        cliente.setLogin(novoCliente.get(11).toString());
        cliente.setSenha(novoCliente.get(12).toString());
    }


    public void inserirCliente(Vector novoCliente, ControladoraCidade controladoraCidade) throws ParseException, SQLException, MinhaException, ConexaoException
    {
        Cliente cliente = new Cliente();
        this.criaCliente(cliente, novoCliente, controladoraCidade);

        ClienteDao clienteDao = FabricaDao.getClienteDao("JDBC");
        clienteDao.inserirCliente(cliente);

    }

    public void alterarCliente (Vector alteracaoCliente, ControladoraCidade controladoraCidade) throws ParseException, MinhaException, SQLException, ConexaoException
    {

        Cliente cliente = this.vetClientes.get(getMarc());
        this.criaCliente(cliente, alteracaoCliente, controladoraCidade);

        ClienteDao clienteDao = FabricaDao.getClienteDao("JDBC");
        clienteDao.alterarCliente(cliente);

    }

    public void removerCliente () throws MinhaException, SQLException, ConexaoException
    {

        Cliente cliente = this.vetClientes.get(getMarc());

        ClienteDao clienteDao = FabricaDao.getClienteDao("JDBC");
        clienteDao.removerCliente(cliente);

        this.vetClientes.remove(marc);

    }

    public Vector<Cliente> selecionarTodosClientes () throws MinhaException, SQLException, ConexaoException
    {

        ClienteDao clienteDao = FabricaDao.getClienteDao("JDBC");
        this.vetClientes = clienteDao.selecionarTodosClientes();

        return this.vetClientes;
    }

    public Cliente selecionarClientePorCpf(String cpf) throws SQLException, MinhaException, ConexaoException
    {

        ClienteDao clienteDao = FabricaDao.getClienteDao("JDBC");
        Cliente cliente = clienteDao.selecionarClientePorCpf(cpf);

        return cliente;
    }

    public Vector obterCliente(String cpf) throws SQLException, MinhaException, ConexaoException
    {
        Vector clienteBuscado = new Vector();
        Cliente cliente = this.selecionarClientePorCpf(cpf);

        clienteBuscado.addElement(cliente.getCodCliente());
        clienteBuscado.addElement(cliente.getNome());
        clienteBuscado.addElement(cliente.getCpf());
        clienteBuscado.addElement(cliente.getDataNasc());
        clienteBuscado.addElement(cliente.getEndereco().getNomeRua());
        clienteBuscado.addElement(cliente.getEndereco().getNumero());
        clienteBuscado.addElement(cliente.getEndereco().getNomeBairro());
        clienteBuscado.addElement(cliente.getEmail());
        clienteBuscado.addElement(cliente.getTelefone());
        clienteBuscado.addElement(cliente.getCartaoCredito());
        clienteBuscado.addElement(cliente.getEndereco().getCidade().getCodCidade());
        clienteBuscado.addElement(cliente.getEndereco().getCidade().getNomeCidade());
        clienteBuscado.addElement(cliente.getEndereco().getCidade().getUF().getUF());

        return clienteBuscado;

    }

    public Vector obterLinhasClientes() throws MinhaException, SQLException, ConexaoException
    {

        Vector<Cliente> clientes = this.selecionarTodosClientes();
        Vector linhas = new Vector();

        for(int i = 0; i < clientes.size(); i++){
            Cliente cliente = clientes.get(i);

            linhas.addElement(this.montarLinhasClientes(cliente));
        }

        return linhas;
    }


}
