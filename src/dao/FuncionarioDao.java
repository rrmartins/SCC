package dao;

import domain.Funcionario;

import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;


public interface FuncionarioDao {


    public Vector<Funcionario> selecionarTodosFuncionarios() throws MinhaException, SQLException, ConexaoException;


    public Vector<Funcionario> selecionarFuncionario(String login) throws MinhaException, ConexaoException, SQLException;


    public void removerFuncionario(Funcionario funcionario) throws MinhaException, SQLException, ConexaoException;


    public void alterarFuncionario(Funcionario funcionario) throws MinhaException, SQLException, ConexaoException;


    public boolean inserirFuncionario (Funcionario funcionario) throws MinhaException, ConexaoException, SQLException ;

    public Vector<Funcionario> selecionarFuncionariosPorCargo(String cargo) throws MinhaException, ConexaoException, SQLException;

}

