package dao;

import domain.Cidade; 
import domain.UF;
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface CidadeDao {

    public Vector<Cidade> obterCidade(String estado) throws MinhaException, SQLException, ConexaoException;

    public int obterCodCidade(String cidade) throws MinhaException, SQLException, ConexaoException;

    public Vector<Cidade> selecionarTodasCidades() throws SQLException, MinhaException, ConexaoException ;

    public Cidade selecionarCidade (Cidade cidade) throws MinhaException, ConexaoException, SQLException;

    public void removerCidade (Cidade cidade) throws SQLException, MinhaException, ConexaoException;

    public void alterarCidade (Cidade cidade) throws SQLException, MinhaException, ConexaoException;

    public void inserirCidade (Cidade cidade) throws MinhaException, SQLException, ConexaoException;

    public Vector<Cidade> selecionarTodasCidadesPorEstado (UF uf) throws MinhaException, ConexaoException, SQLException;

    public Cidade selecionarCidadePorCodigo(int codigo)  throws MinhaException, SQLException, ConexaoException;

    public Vector<Cidade> obterUFCidade(int cod) throws MinhaException, SQLException, ConexaoException;

}

