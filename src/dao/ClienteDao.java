package dao;

import domain.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;


public interface ClienteDao {

    public Vector<Cliente> selecionarCliente (String login) throws MinhaException, ConexaoException, SQLException;


    public Cliente selecionarClientePorCpf(String cpf) throws MinhaException, ConexaoException, SQLException;


    public void removerCliente(Cliente cliente) throws MinhaException, SQLException, ConexaoException;


    public void alterarCliente(Cliente cliente) throws MinhaException, SQLException, ConexaoException;


    public boolean inserirCliente (Cliente cliente) throws MinhaException, ConexaoException, SQLException;

    /**
     * 
     * @param codLocacao
     * @return
     * @throws MinhaException
     * @throws ConexaoException
     * @throws SQLException
     */
    public Vector selecionarClientePelaLocacao(String codLocacao) throws MinhaException, ConexaoException, SQLException;


    public Vector<Cliente> selecionarTodosClientes() throws MinhaException, SQLException, ConexaoException;
}

