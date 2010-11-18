package dao;

import domain.UF; 
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface UFDao {

    public Vector<UF> selecionarTodasUF ();

    public UF selecionarUF (UF uf);

    public void removerUF (UF uf) throws SQLException, MinhaException, ConexaoException ;

    public void alterarUF (UF uf) throws SQLException, MinhaException, ConexaoException ;

    public void inserirUF (UF uf) throws MinhaException, SQLException, ConexaoException;

    public Vector<UF> obterUF() throws MinhaException, SQLException, ConexaoException;

}