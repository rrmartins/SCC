package dao;

import domain.GrupoCarro;
import domain.Reserva; 
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public interface ReservaDao {

    public Reserva selecionarTodasReservas ();

    public Reserva selecionarReserva (Reserva reserva);

    public void removerReserva (Reserva reserva) throws SQLException, ClassNotFoundException, ConexaoException, MinhaException;

    public void alterarReserva (Reserva reserva) throws SQLException, ParseException, ClassNotFoundException, ConexaoException, MinhaException;

    public Reserva selecionarReserva (String cpf);

    public void inserirReserva(Reserva reserva) throws SQLException, MinhaException, ParseException, ConexaoException;

    public Vector<Reserva> selecionarReservasPorIntervaloDeDatas(Date dataL, Date dataE, GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException, ParseException;

    public Vector<Reserva> obterReservas(String texto) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException;

    public void alterarStatus(Reserva reserva) throws SQLException, ConexaoException, ClassNotFoundException, MinhaException;
}