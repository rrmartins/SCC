package dao;

import domain.GrupoCarro;
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;


public interface GrupoCarroDao {


    public GrupoCarro selecionarGrupoCarro(String nomeGrupoCarro) throws MinhaException, SQLException, ConexaoException;

    public Vector<GrupoCarro> selecionarTodosGrupoCarro () throws MinhaException, SQLException, ConexaoException;

    public GrupoCarro selecionarGrupoCarroPorCod(int codGrupoCar) throws MinhaException, SQLException, ConexaoException;

    public GrupoCarro selecionarGrupoCarro (GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;


    public void removerGrupoCarro (GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;


    public void alterarGrupoCarro (GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;


    public void inserirGrupoCarro (GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;

}

