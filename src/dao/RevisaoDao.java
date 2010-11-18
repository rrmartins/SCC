package dao;

import domain.Revisao; 
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;

public interface RevisaoDao {

    public Vector<Revisao> selecionarTodasRevisoes() throws MinhaException, SQLException, ConexaoException;

    public Revisao selecionarRevisao(Revisao revisao) throws MinhaException, SQLException;

    public void removerRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException;

    public void alterarRevisao(Revisao revisao) throws MinhaException, SQLException, ConexaoException ;

    public void inserirRevisao(Revisao revisao, Conexao connection) throws MinhaException, SQLException;

}

