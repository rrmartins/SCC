package dao;

import domain.TipoCarro; 
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface TipoCarroDao {

    public Vector<TipoCarro> selecionarTodosTipoCarro()  throws MinhaException, SQLException, ConexaoException;

    public TipoCarro selecionarTipoCarro (TipoCarro tipoCarro);

    public void removerTipoCarro (TipoCarro tipoCarro) throws SQLException, MinhaException, ConexaoException;

    public void alterarTipoCarro (TipoCarro tipoCarro) throws MinhaException, SQLException, ConexaoException;

    public void inserirTipoCarro (TipoCarro tipoCarro)  throws MinhaException, SQLException, ConexaoException;

    public TipoCarro selecionarTipoCarro(String nome)  throws ConexaoException ,MinhaException, SQLException ;

}

