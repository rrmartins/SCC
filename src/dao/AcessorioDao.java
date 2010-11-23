/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Acessorio;
import domain.GrupoCarro;
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

/**
 *
 * @author Rodrigo Martins
 */
public interface AcessorioDao {

    public void inserirAcessorio (Acessorio acessorio) throws MinhaException, SQLException, ConexaoException;

    public Acessorio selecionarAcessorio (Acessorio acessorio);

    public void removerAcessorio (Acessorio acessorio) throws SQLException, MinhaException, ConexaoException ;

    public void alterarAcessorio (Acessorio acessorio) throws SQLException, MinhaException, ConexaoException;

    public Vector<Acessorio> selecionarTodosAcessorio() throws MinhaException, SQLException, ConexaoException;

    public Vector<Acessorio> selecionarAcessorioGrupo(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException;

}
