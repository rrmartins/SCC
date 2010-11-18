package dao;

import domain.GrupoCarro; 
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;

public interface GrupoCarroDao {

    public Vector<GrupoCarro> selecionarTodosGrupoCarro() throws MinhaException, SQLException, ConexaoException;

    public GrupoCarro selecionarGrupoCarro (String grupoCarro) throws MinhaException, SQLException, ConexaoException ;

    public void removerGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;

    public void alterarGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;

    public int selecionarCodigoCadastrado(Conexao conn) throws SQLException, MinhaException;

    public void inserirGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;

    public GrupoCarro selecionarGrupoCarroPorCod(int codGrupoCar) throws MinhaException, SQLException, ConexaoException ;

}

