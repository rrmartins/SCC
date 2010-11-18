package dao;

import domain.Carro; 
import domain.GrupoCarro;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface CarroDao {

    public void inserirCarro(Carro carro) throws MinhaException, SQLException, ConexaoException;

    public Carro selecionarCarro (Carro carro);

    public void removerCarro(Carro carro) throws MinhaException, SQLException, ConexaoException;

    public void alterarCarro(Carro carro) throws MinhaException, SQLException, ConexaoException;

    public Vector<Carro> selecionarTodosCarros() throws MinhaException, SQLException, ConexaoException;

    public Carro selecionarCarro(int codGrupoCar)  throws MinhaException, SQLException, ConexaoException ;

    public Vector<Carro> selecionarCarrosPorGrupo(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;

    public void alterarKMDisponibilidade(Vector linha) throws SQLException, ConexaoException;

}

