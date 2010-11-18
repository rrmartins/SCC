package control;


import dao.*;
import domain.*;
import util.ConexaoException;
import java.sql.*;
import java.util.*;
import util.MinhaException;

public class ControladoraLogin {

    private ClienteDao clienteDao;
    private FuncionarioDao funcDao;
    private Vector<Funcionario> vFuncionario;
    private Vector<Cliente> vCliente;

    public ControladoraLogin() {
        this.clienteDao = FabricaDao.getClienteDao("JDBC");
        this.funcDao = FabricaDao.getFuncionarioDao("JDBC");
    }

    private Vector<Funcionario> obterFuncionarios(String login) throws ConexaoException, SQLException, MinhaException {
        vFuncionario = funcDao.selecionarFuncionario(login);
        return vFuncionario;
    }

    private Vector<Cliente> obterCliente(String login) throws ConexaoException, SQLException, MinhaException  {
        vCliente = clienteDao.selecionarCliente(login);
        return vCliente;
    }

    @SuppressWarnings("unchecked")
    public Vector consultaUsuario(Vector login) throws ConexaoException, SQLException, MinhaException {
        Vector linhas = new Vector();
        if (login.get(2).equals("Cliente"))
        {
            Vector<Cliente> client = obterCliente(login.get(0).toString());
            if (client.get(0).getSenha().equals(login.get(1)))
            {
                if (client.get(0).getLogin().equals(login.get(0)))
                {
                    
                    Cidade cid = new Cidade();
                    Endereco end = new Endereco();
                    // Montando as linhas
                    for (int i = 0; i < client.size(); i++) {
                        Cliente clien = client.get(i);
                        linhas.addElement(clien.getCodCliente());
                        linhas.addElement(cid.getCodCidade());
                        linhas.addElement(clien.getEmail());
                        linhas.addElement(clien.getTelefone());
                        linhas.addElement(clien.getDataNasc());
                        linhas.addElement(clien.getCartaoCredito());
                        linhas.addElement(clien.getNome());
                        linhas.addElement(clien.getCpf());
                        linhas.addElement(clien.getLogin());
                        linhas.addElement(clien.getSenha());
                        linhas.addElement(end.getNomeRua());
                        linhas.addElement(end.getNumero());
                        linhas.addElement(end.getNomeBairro());
                    }
                }else {
                    Vector error = new Vector();
                    error.addElement("Erro");
                    return error ;
                }
            }else {
                Vector error = new Vector();
                error.addElement("Erro");
                return error ;
            }
        }

        
        if (login.get(2).equals("Funcionario"))
        {
            Vector<Funcionario> func = obterFuncionarios(login.get(0).toString());
            
            if (func.get(0).getSenha().equals(login.get(1)))
            {
                if (func.get(0).getLogin().equals(login.get(0)))
                {
                    
                    Cidade cid = new Cidade();
                    Endereco end = new Endereco();
                    // Montando as linhas
                    for (int i = 0; i < func.size(); i++) {
                        Funcionario funcionario = func.get(i);
                        linhas.addElement(funcionario.getCodFuncionario());
                        linhas.addElement(cid.getCodCidade());
                        linhas.addElement(funcionario.getCargo());
                        linhas.addElement(funcionario.getNome());
                        linhas.addElement(funcionario.getCpf());
                        linhas.addElement(funcionario.getLogin());
                        linhas.addElement(funcionario.getSenha());
                        linhas.addElement(end.getNomeRua());
                        linhas.addElement(end.getNumero());
                        linhas.addElement(end.getNomeBairro());
                    }
                }else {
                    Vector error = new Vector();
                    error.addElement("Erro");
                    return error ;
                }

            }else {
                Vector error = new Vector();
                error.addElement("Erro");
                return error ;
            }
          }
        

        return linhas;
    }

}