package control;

import dao.ClienteDao;
import dao.FabricaDao;
import dao.GrupoCarroDao;
import dao.ReservaDao;
import dao.TipoCarroDao;
import domain.Acessorio;
import domain.Cliente;
import domain.GrupoCarro;
import domain.Reserva;
import domain.TipoCarro;
import java.text.ParseException;
import util.ConexaoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import util.MinhaException;

public class ControladoraReserva {

    private ControladoraGrupoCarro controladoraGrupoCarro = new ControladoraGrupoCarro();
    private ReservaDao reservaDao;
    private ClienteDao clieDao;
    private Vector<GrupoCarro> grupoCar;
    private GrupoCarro gCar;
    private TipoCarro tCar;
    private TipoCarroDao tipoCarDao;
    private GrupoCarroDao grupoCarroDao;
    private Vector<Reserva> vetReservas = new Vector<Reserva>();
    private Vector<Cliente> vetCliente = new Vector<Cliente>();
    private int marc;
    

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public ControladoraReserva() {
        this.reservaDao = FabricaDao.getReservaDao("JDBC");
        this.grupoCarroDao = FabricaDao.getGrupoCarroDao("JDBC");
        this.tipoCarDao = FabricaDao.getTipoCarroDao("JDBC");
    }

   public void inserirNovaReserva(Vector linha) throws MinhaException, SQLException, ParseException, ConexaoException {
        Reserva reserva = new Reserva();
        this.atualizarReserva(reserva, linha);
        reservaDao.inserirReserva(reserva);
    }
   
    private void atualizarReserva(Reserva reserva, Vector linha) throws ParseException{
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataLocacao = null, dataEntrega = null;
        String dataLoca = form.format(linha.get(3));
        String dataEntre = form.format(linha.get(5));
        dataLocacao = formato.parse(dataLoca);
        dataEntrega = formato.parse(dataEntre);

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Date horaL = formatador.parse(linha.get(4).toString());
        Date horaE = formatador.parse(linha.get(6).toString());
        Time horaLoca = new Time(horaL.getTime());
        Time horaEntre = new Time(horaE.getTime());

        GrupoCarro codGc = new GrupoCarro();
        codGc.setCodGrupoCarro(Integer.parseInt(linha.get(0).toString()));
        Cliente codCliente = new Cliente();
        codCliente.setCodCliente(Integer.parseInt(linha.get(2).toString()));

        reserva.setGrupoCarro(codGc);
        reserva.setCliente(codCliente);
        reserva.setDataLocacao(dataLocacao);
        reserva.setHoraLocacao(horaLoca);
        reserva.setDataEntrega(dataEntrega);
        reserva.setHoraEntrega(horaEntre);
        reserva.setValorPrevisto(Float.parseFloat(linha.get(7).toString()));
        reserva.setCobertura(Boolean.valueOf(linha.get(8).toString()));
        reserva.setSituacao(false);
    }

    private Vector<Cliente> obterCliente(String nomeCliente) throws ConexaoException, SQLException, MinhaException {
        vetCliente = clieDao.obterCodCliente(nomeCliente);

        return vetCliente;
    }

    private Vector criarLinhaAcessorio(Acessorio acessorio) {
        Vector linha = new Vector();
        linha.addElement(acessorio.getDescAcessorio());
        return linha;
    }

    private Vector<GrupoCarro> obterGrupoCarro() throws MinhaException, SQLException, ConexaoException {
        grupoCar = grupoCarroDao.selecionarTodosGrupoCarro();
        return grupoCar;
    }

    private GrupoCarro obterGrupoCarro(String nome) throws ConexaoException, SQLException, MinhaException {
        gCar = grupoCarroDao.selecionarGrupoCarro(nome);
        return gCar;
    }

    public Vector obertNomeGrupoCarro() throws MinhaException, SQLException, ConexaoException {
        Vector<GrupoCarro> grupoCarro = obterGrupoCarro();
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < grupoCarro.size(); i++) {
            GrupoCarro grupoCa = grupoCarro.get(i);
            
            linhas.addElement(grupoCa.getNomeGrupo());
                    
        }

        return linhas;
    }

    public Vector obertGrupoCarro(String nomeGrupo) throws ConexaoException, SQLException, MinhaException {
        GrupoCarro grupoCarro = obterGrupoCarro(nomeGrupo);

        TipoCarro tipoCarro = obterTipoCarro(nomeGrupo);

        Vector linhas = new Vector();

        linhas.addElement(grupoCarro.getNomeGrupo());
        linhas.addElement(grupoCarro.getPrecoDiaria());
        linhas.addElement(grupoCarro.getPrecoDiariaQuilometrada());
        linhas.addElement(grupoCarro.getPrecoCobertura());
        linhas.addElement(tipoCarro.getCodTipoCarro());
        linhas.addElement(grupoCarro.getCodGrupoCarro());

        return linhas;
    }

    private TipoCarro obterTipoCarro(String nome) throws ConexaoException, SQLException, MinhaException {
        tCar = tipoCarDao.selecionarTipoCarro(nome);
        return tCar;
    }

    public Vector obterCodCliente(String nomeCliente) throws ConexaoException, SQLException, MinhaException {

        Vector<Cliente> clien = obterCliente(nomeCliente);

        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < clien.size(); i++) {
            Cliente cli = clien.get(i);

            linhas.addElement(cli.getCodCliente());

        }

        return linhas;

    }

    public int quantReservasPorData(Date dataL, Date dataE, Vector grupo) throws MinhaException, SQLException, ParseException, ConexaoException{

        GrupoCarro grupoCarro = new GrupoCarro();

        grupoCarro = this.controladoraGrupoCarro.atualizarGrupoCarro(grupo);

        Vector reservasPeriodo = this.selecionarReservasPorIntervaloDeDatas(dataL, dataE, grupoCarro);
        int nItens = reservasPeriodo.size();

        return nItens;
    }

    public Vector<Reserva> selecionarReservasPorIntervaloDeDatas(Date dataL, Date dataE, GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException, ParseException{

        reservaDao = FabricaDao.getReservaDao("JDBC");
        this.vetReservas = reservaDao.selecionarReservasPorIntervaloDeDatas(dataL, dataE, grupoCarro);

        return this.vetReservas;
    }

    private Vector<Reserva> obterReserva(String texto)throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        this.vetReservas = reservaDao.obterReservas(texto);

        return vetReservas;
    }

    public Vector obterLinhasReserva(String texto) throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Vector<Reserva> reserva = this.obterReserva(texto);
        Vector linhasReserva = new Vector();

        for(int i = 0; i < reserva.size(); i++)
        {
            Reserva reserv = reserva.get(i);
            linhasReserva.addElement(this.criadorLinhaReserva(reserv));
        }

        return linhasReserva;
    }

    @SuppressWarnings({"unchecked"})
    public Vector criadorLinhaReserva(Reserva res)
    {
        Vector linha = new Vector();
        boolean cobertura, situacao;
        linha.addElement(res.getCodReserva());
        linha.addElement(res.getGrupoCarro().getNomeGrupo());
        linha.addElement(res.getCliente().getNome());
        linha.addElement(res.getDataLocacao());
        linha.addElement(res.getDataEntrega());
        linha.addElement(res.getHoraLocacao());
        linha.addElement(res.getHoraEntrega());
        linha.addElement(res.getValorPrevisto());

        cobertura = res.getCobertura();
        situacao = res.getSituacao();

        if (cobertura==true){
            linha.addElement("Sim");
        }else{
            linha.addElement("Não");
        }

        if (situacao == true){
            linha.addElement("Sim");
        }else{
            linha.addElement("Não");
        }
        return linha;
    }

    public void alterarStatus(Vector linha) throws ParseException, SQLException, ConexaoException, ClassNotFoundException, ClassNotFoundException, MinhaException{
        Reserva reserva = new Reserva();
        if (linha.get(10).toString().equals("Não")){
            reserva.setSituacao(true);
        }else{
            reserva.setSituacao(false);
        }
        reserva.setCodReserva(Integer.parseInt(linha.get(0).toString()));
        reservaDao.alterarStatus(reserva);
    }

    public void deletarReserva() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Reserva res = this.vetReservas.get(marc);

        reservaDao.removerReserva(res);

        this.vetReservas.remove(marc);
    }

    public void alterarReserva(Vector linha) throws ConexaoException, SQLException, ParseException, ClassNotFoundException, MinhaException {
        Reserva reserva = new Reserva();
        this.atualizarReserva(reserva, linha);
        reserva.setCodReserva(Integer.parseInt(linha.get(9).toString()));
        reservaDao.alterarReserva(reserva);
    }
}