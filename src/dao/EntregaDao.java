package dao;

import domain.Entrega; 
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface EntregaDao {

    public Entrega selecionarTodasEntregas ();

    public Entrega selecionarEntrega (Entrega entrega);

    public void removerEntrega (Entrega entrega) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException;

    public void alterarEntrega (Entrega entrega) throws SQLException,ParseException, ClassNotFoundException, ConexaoException, MinhaException;

    public void inserirEntrega (Entrega entrega) throws SQLException, MinhaException, ConexaoException;

    public Vector<Entrega> obterEntrega(String texto) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException;

    public Vector<Entrega> obterEntrega() throws SQLException,ConexaoException, ClassNotFoundException, MinhaException;

}

