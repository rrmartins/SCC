package control;

import dao.FabricaDao;
import dao.LocacaoDao;
import domain.Carro;
import domain.Cliente;
import domain.GrupoCarro;
import domain.Locacao;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

public class ControladoraLocacao {

    private int marc;
    private Vector<Locacao> vetLocacao = new Vector<Locacao>();

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<Locacao> getVetLocacao() {
        return vetLocacao;
    }


    public Vector linhaLocacaoCompleto(Locacao locacao){

        Vector linha = new Vector();
        String cobertura;
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        if (locacao.getCobertura() == true) {
            cobertura = "Sim";
        } else {
            cobertura = "Não";
        }

        linha.addElement(locacao.getCodLocacao());
        linha.addElement(locacao.getCliente().getCodCliente());
        linha.addElement(locacao.getCliente().getNome());
        linha.addElement(locacao.getCliente().getCpf());
        linha.addElement(locacao.getCarro().getCodCarro());
        linha.addElement(locacao.getCarro().getModelo());
        linha.addElement(locacao.getCarro().getPlaca());
        linha.addElement(locacao.getDateLocacao());
        linha.addElement(formatoHora.format(locacao.getHoraLocacao()));
        linha.addElement(locacao.getDataEntrega());
        linha.addElement(formatoHora.format(locacao.getHoraEntrega()));
        linha.addElement(locacao.getQuilometragemInicial());
        linha.addElement(locacao.getQuilometragemPrevista());
        linha.addElement(cobertura);
        linha.addElement(decimalFormat.format(locacao.getValorPrevisto()));
        linha.addElement(locacao.getPlano());
        linha.addElement(locacao.getCarro().getChassi());

        return linha;

    }

    private Vector linhaLocacao(Locacao locacao) {

        Vector linha = new Vector();
        String cobertura;
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        if (locacao.getCobertura() == true) {
            cobertura = "Sim";
        } else {
            cobertura = "Não";
        }

        linha.addElement(locacao.getCodLocacao());
        linha.addElement(locacao.getCliente().getNome());
        linha.addElement(locacao.getCarro().getModelo());
        linha.addElement(locacao.getCarro().getPlaca());
        linha.addElement(formatoData.format(locacao.getDateLocacao()));
        linha.addElement(locacao.getHoraLocacao());
        linha.addElement(formatoData.format(locacao.getDataEntrega()));
        linha.addElement(locacao.getHoraEntrega());
        linha.addElement(locacao.getQuilometragemInicial());
        linha.addElement(locacao.getQuilometragemPrevista());
        linha.addElement(cobertura);
        linha.addElement(decimalFormat.format(locacao.getValorPrevisto()));

        return linha;

    }

    public void alterarLocacao(Vector locacao) throws MinhaException, SQLException, ParseException, ConexaoException {

        Locacao loc = this.vetLocacao.get(marc);
        this.montarLocacao(locacao, loc);

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        locacaoDao.alterarLocacao(loc);

    }

    public void inserirLocacao(Vector locacao) throws MinhaException, SQLException, ParseException, ConexaoException {

        Locacao loc = new Locacao();
        this.montarLocacao(locacao, loc);

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        locacaoDao.inserirLocacao(loc);
    }

    public void removerLocacao() throws MinhaException, SQLException, ConexaoException {

        Locacao loc = this.vetLocacao.get(this.marc);

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        locacaoDao.removerLocacao(loc);

        this.vetLocacao.remove(this.marc);
    }

    public Locacao selecionarLocacao(Locacao locacao) {
        return null;
    }

    public Vector<Locacao> selecionarTodasLocacoes() throws MinhaException, SQLException, ConexaoException {

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        this.vetLocacao = locacaoDao.selecionarTodasLocacoes();

        return this.vetLocacao;

    }

    public Vector obterLinhasLocacao() throws MinhaException, SQLException, ConexaoException {

        Vector<Locacao> locs = this.selecionarTodasLocacoes();
        Vector linhas = new Vector();

        for (int i = 0; i < locs.size(); i++) {
            linhas.add(this.linhaLocacao(locs.get(i)));
        }

        return linhas;
    }

    private Locacao montarLocacao(Vector locacao, Locacao loc) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");

        Carro carro = new Carro();
        Cliente cliente = new Cliente();
        GrupoCarro grupoCarro = new GrupoCarro();


        Vector client = new Vector((Vector) locacao.get(5));
        int codCliente = Integer.parseInt(client.get(0).toString());
        Vector car = new Vector((Vector) locacao.get(3));
        int codCarro = Integer.parseInt(car.get(0).toString());

        cliente.setCodCliente(codCliente);
        carro.setCodCarro(codCarro);

        Vector grupo = new Vector((Vector)locacao.get(13));
        int codGrupo = Integer.parseInt(grupo.get(0).toString());
        grupoCarro.setCodGrupoCarro(codGrupo);

        carro.setGrupoCarro(grupoCarro);

        Date dataLocacao = (Date) locacao.get(1);
        Date dataEntrega = (Date) locacao.get(2);

        String horaLocacao = (String) locacao.get(6);
        String horaEntrega = (String) locacao.get(7);

        Date dL = formato.parse(horaLocacao);
        Time horaL = new Time(dL.getTime());

        Date dE = formato.parse(horaEntrega);
        Time horaE = new Time(dE.getTime());

        int qInicial = Integer.parseInt(car.get(4).toString());
        int qPrevista = Integer.parseInt(locacao.get(12).toString());

        boolean cobertura;
        if (locacao.get(9).toString().equals("Sim")) {
            cobertura = true;
        } else {
            cobertura = false;
        }

        double val = 0;
        String valorPrevisto = locacao.get(4).toString();
        String valor2 = valorPrevisto.replace(",", ".");

        val = Double.parseDouble(valor2);

        loc.setPlano(locacao.get(14).toString());
        loc.setDateLocacao(dataLocacao);
        loc.setDataEntrega(dataEntrega);
        loc.setHoraLocacao(horaL);
        loc.setHoraEntrega(horaE);
        loc.setCobertura(cobertura);
        loc.setQuilometragemInicial(qInicial);
        loc.setQuilometragemPrevista(qPrevista);
        loc.setCarro(carro);
        loc.setCliente(cliente);
        loc.setValorPrevisto(val);

        return loc;
    }
}
