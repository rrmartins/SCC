package dao;

import domain.Revisao; 
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;

public interface RevisaoDao {

    public int ultRevisao() throws MinhaException, SQLException, ConexaoException;

    public Vector<Revisao> selecionarTodasRevisoes() throws MinhaException, SQLException, ConexaoException;

    public Revisao selecionarRevisao(Revisao revisao) throws MinhaException, SQLException;

    public void removerRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException;

    public void alterarRevisao(Revisao revisao, Conexao connect) throws MinhaException, SQLException, ConexaoException;

    public void inserirRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException;

}

