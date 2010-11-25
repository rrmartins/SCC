package dao;

import domain.Locacao; 
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface LocacaoDao {

    public Vector<Locacao> selecionarTodasLocacoes() throws MinhaException, SQLException, ConexaoException;

    public Locacao selecionarLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException;

    public void removerLocacao (Locacao locacao) throws MinhaException, SQLException, ConexaoException;
 
    public void alterarLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException;

    public void inserirLocacao(Locacao locacao) throws MinhaException, SQLException, ConexaoException;

    public Locacao selecionarCarroPorCpf(String cpf) throws MinhaException, SQLException, ConexaoException;

    public int obterCodLocacao(String nome) throws MinhaException, ConexaoException, SQLException;
}

