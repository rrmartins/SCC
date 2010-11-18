package dao;

import domain.Cliente;
import domain.GrupoCarro;
import domain.Reserva;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class ReservaJDBCDao implements ReservaDao {
 
    private Conexao connection;
    private String sql;

    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Reserva selecionarTodasReservas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reserva selecionarReserva(Reserva reserva) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerReserva(Reserva reserva) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {
        
        this.connection = FabricaConexao.obterConexao();
        
        try
        {
            this.connection.setAutoCommit(false);
            sql = "DELETE FROM reserva " +
                "WHERE cod_reserva = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setInt(1, reserva.getCodReserva());
            prepSt.executeUpdate();
            this.connection.commit();
        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
        }
    }

    /**
     *
     * @param reserva
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ConexaoException
     * @throws MinhaException
     */
    public void alterarReserva(Reserva reserva) throws SQLException, ClassNotFoundException, ConexaoException, MinhaException, ParseException {

        this.connection = FabricaConexao.obterConexao();
        
        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE reserva SET " +
                    "cod_grupo_carro = ? , " +   //2
                    "cod_cliente = ? , " +       //3
                    "data_locacao = ? , " +      //4
                    "data_entrega = ? ," +        //5
                    "hora_locacao = ? ," +        //6
                    "hora_entrega = ? ," +        //7
                    "valor_previsto = ? ," +      //8
                    "cobertura = ? ," +           //9
                    "situacao = ? " +           //11
                    "WHERE cod_reserva = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");

            String dataLoca = form.format(reserva.getDataLocacao());
            String dataEntre = form.format(reserva.getDataEntrega());

            java.sql.Date dataL = new java.sql.Date(form.parse(dataLoca).getTime());
            java.sql.Date dataE = new java.sql.Date(form.parse(dataEntre).getTime());

            prepSt.setInt(1, reserva.getGrupoCarro().getCodGrupoCarro());
            prepSt.setInt(2, reserva.getCliente().getCodCliente());
            prepSt.setDate(3, dataL);
            prepSt.setDate(4, dataE);
            prepSt.setTime(5, reserva.getHoraLocacao());
            prepSt.setTime(6, reserva.getHoraEntrega());
            prepSt.setFloat(7, reserva.getValorPrevisto());
            prepSt.setBoolean(8, reserva.getCobertura());
            prepSt.setBoolean(9, reserva.getSituacao());
            prepSt.setInt(10, reserva.getCodReserva());

            prepSt.executeUpdate();
            this.connection.commit();

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
        }
    }

    public Reserva selecionarReserva(String cpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void inserirReserva(Reserva reserva) throws SQLException, ConexaoException, MinhaException, ParseException  {
        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO reserva (cod_grupo_carro, " +
                    "cod_cliente, data_locacao, data_entrega, hora_locacao, " +
                    "hora_entrega, valor_previsto, cobertura, situacao) values (?,?,?,?,?,?,?,?,?);";

            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
            
            String dataLoca = form.format(reserva.getDataLocacao());
            String dataEntre = form.format(reserva.getDataEntrega());
            
            java.sql.Date dataL = new java.sql.Date(form.parse(dataLoca).getTime());
            java.sql.Date dataE = new java.sql.Date(form.parse(dataEntre).getTime());

            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            
            pstmt.setInt(1, reserva.getGrupoCarro().getCodGrupoCarro());
            pstmt.setInt(2, reserva.getCliente().getCodCliente());
            pstmt.setDate(3, dataL);
            pstmt.setDate(4, dataE);
            pstmt.setTime(5, reserva.getHoraLocacao());
            pstmt.setTime(6, reserva.getHoraEntrega());
            pstmt.setFloat(7, reserva.getValorPrevisto());
            pstmt.setBoolean(8, reserva.getCobertura());
            pstmt.setBoolean(9, reserva.getSituacao());

            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }
    }

    public Vector<Reserva> selecionarReservasPorIntervaloDeDatas(Date dataL, Date dataE, GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException, ParseException {

        this.connection = FabricaConexao.obterConexao();

        Reserva reserva = new Reserva();
        Cliente cliente = new Cliente();

        this.sql = "SELECT * FROM reserva " +
                "WHERE cod_grupo_carro = ? AND " +
                "data_locacao BETWEEN ? AND ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(this.sql);
        pStmt.setInt(1, grupoCarro.getCodGrupoCarro());
        pStmt.setDate(2,(java.sql.Date) dataL);
        pStmt.setDate(3,(java.sql.Date) dataE);

        ResultSet result = pStmt.executeQuery();

        Vector<Reserva> reservas = new Vector<Reserva>();
        
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Time horaLoca = (Time) formatador.formatToCharacterIterator(reserva.getHoraLocacao());
        Time horaEntre = (Time) formatador.formatToCharacterIterator(reserva.getHoraEntrega());


        if(result == null || !result.next()){
            this.connection.close();
            return reservas;
        }

        else
        {

            do
            {
                 
                cliente.setCodCliente(result.getInt("cod_cliente"));

                reserva.setCodReserva(result.getInt("cod_reserva"));
                reserva.setGrupoCarro(grupoCarro);
                reserva.setCliente(cliente);
                reserva.setDataLocacao(result.getDate("data_locacao"));
                reserva.setDataEntrega(result.getDate("data_entrega"));
                reserva.setHoraLocacao(horaLoca);
                reserva.setHoraEntrega(horaEntre);
                reserva.setValorPrevisto(result.getFloat("valor_previsto"));
                reserva.setCobertura(result.getBoolean("situacao"));

                reservas.addElement(reserva);


            }while(result.next());

        }

        this.connection.close();

        return reservas;
    }

    public Vector<Reserva> selecionarReservasPorIntervaloDeDatas(java.util.Date dataL, java.util.Date dataE, GrupoCarro grupoCarro) throws MinhaException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("unchecked")
    public Vector<Reserva> obterReservas(String texto) throws SQLException,ConexaoException, ClassNotFoundException, MinhaException {
        this.connection = FabricaConexao.obterConexao();

        if (texto.equals("Codigo")) {
            texto = "cod_reserva";
        }
        if (texto.equals("Tipo Carro")) {
            texto = "cod_tipo_carro";
        }
        if (texto.equals("Grupo Carro")) {
            texto = "cod_grupo_carro";
        }
        if (texto.equals("Cliente")) {
            texto = "cod_cliente";
        }
        if (texto.equals("Data Locação")) {
            texto = "data_locacao";
        }
        if (texto.equals("Data Entrega")) {
            texto = "data_entrega";
        }
        if (texto.equals("Hr Locação")) {
            texto = "hora_locacao";
        }
        if (texto.equals("Hr Entrega")) {
            texto = "hora_entrega";
        }
        if (texto.equals("Valor Previsto")) {
            texto = "valor_previsto";
        }

        Vector reser = new Vector();
        try
        {
            String Sql;

            Sql = "SELECT * " +
                "FROM reserva "+
                "order by " + texto + ";";


            this.connection.setAutoCommit(false);
            PreparedStatement prepSt = this.connection.prepareStatement(Sql);

            ResultSet result = prepSt.executeQuery();
            result.next();


            do
            {
                GrupoCarro gc = new GrupoCarro();
                gc.setCodGrupoCarro(result.getInt("cod_grupo_carro"));
                
                Cliente clie = new Cliente();
                clie.setCodCliente(result.getInt("cod_cliente"));

                Reserva res = new Reserva();
                res.setCodReserva(result.getInt("cod_reserva"));
                res.setGrupoCarro(gc);
                res.setCliente(clie);
                res.setDataLocacao(result.getDate("data_locacao"));
                res.setDataEntrega(result.getDate("data_entrega"));
                res.setHoraEntrega(result.getTime("hora_entrega"));
                res.setHoraLocacao(result.getTime("hora_locacao"));
                res.setValorPrevisto(result.getFloat("valor_previsto"));
                res.setCobertura(result.getBoolean("cobertura"));
                res.setSituacao(result.getBoolean("situacao"));

                reser.addElement(res);
            }while(result.next());
        }
        catch(SQLException erro){

            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return reser;
        }
    }

    public void alterarStatus(Reserva reserva) throws SQLException, ConexaoException, ClassNotFoundException, MinhaException {

        this.connection = FabricaConexao.obterConexao();
        
        try
        {
            this.connection.setAutoCommit(false);
            String querySql = "UPDATE reserva SET " +
                    "situacao = ? " +
                    "WHERE cod_reserva = ? ;";

            PreparedStatement pstmt = this.connection.prepareStatement(querySql);

            pstmt.setBoolean(1, reserva.getSituacao());
            pstmt.setInt(2, reserva.getCodReserva());

            pstmt.executeUpdate();
            this.connection.commit();

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
        }
    }

}