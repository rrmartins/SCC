package dao;

import domain.Cidade;
import domain.Oficina; 
import java.sql.SQLException; 
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface OficinaDao {

    public Vector<Oficina> selecionarTodasOficinas() throws MinhaException, ConexaoException, SQLException;

    public Oficina selecionarOficinaPorNome(String nome) throws SQLException, ConexaoException;

    public void removerOficina (Oficina oficina) throws SQLException, ClassNotFoundException, ConexaoException, MinhaException;

    public void alterarOficina (Oficina oficina) throws SQLException,  MinhaException, ConexaoException;

    public void inserirOficina (Oficina oficina, Cidade ci, int codCidade) throws MinhaException, SQLException, ConexaoException;

    public Vector<Oficina> obterOficina(String texto) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException;


}

