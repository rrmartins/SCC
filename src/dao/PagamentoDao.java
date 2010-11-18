package dao;

import domain.Pagamento; 
import java.sql.SQLException;
import java.util.ArrayList; 
import util.ConexaoException;
import util.MinhaException;

public interface PagamentoDao {

    public void inserirPagamento (Pagamento pagamento) throws MinhaException, SQLException, ConexaoException;

}

