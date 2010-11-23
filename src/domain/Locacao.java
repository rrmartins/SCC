package domain;

import java.sql.Time;
import java.util.Date;


public class Locacao {

    private int codLocacao;

    private int quilometragemInicial;

    private Date dateLocacao;

    private Date dataEntrega;

    private Time horaLocacao;

    private Time horaEntrega;

    private int quilometragemPrevista;

    private double valorPrevisto;

    private boolean cobertura;

    private double valorTotal;

    private Cliente mCliente;

    private String plano;

    private GrupoCarro mGrupoCarro;

    private Carro mCarro;

    public Locacao () {
    }

    public boolean getCobertura () {
        return cobertura;
    }

    public void setCobertura (boolean val) {
        this.cobertura = val;
    }

    public int getCodLocacao () {
        return codLocacao;
    }

    public void setCodLocacao (int val) {
        this.codLocacao = val;
    }

    public Date getDataEntrega () {
        return dataEntrega;
    }

    public void setDataEntrega (Date val) {
        this.dataEntrega = val;
    }

    public Date getDateLocacao () {
        return dateLocacao;
    }

    public void setDateLocacao (Date val) {
        this.dateLocacao = val;
    }

    public Time getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Time horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public Time getHoraLocacao() {
        return horaLocacao;
    }

    public void setHoraLocacao(Time horaLocacao) {
        this.horaLocacao = horaLocacao;
    }

    public Carro getCarro () {
        return mCarro;
    }

    public void setCarro (Carro val) {
        this.mCarro = val;
    }

    public Cliente getCliente () {
        return mCliente;
    }

    public void setCliente (Cliente val) {
        this.mCliente = val;
    }

    public GrupoCarro getGrupoCarro () {
        return mGrupoCarro;
    }

    public void setGrupoCarro (GrupoCarro val) {
        this.mGrupoCarro = val;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public int getQuilometragemInicial () {
        return quilometragemInicial;
    }

    public void setQuilometragemInicial (int val) {
        this.quilometragemInicial = val;
    }

    public int getQuilometragemPrevista () {
        return quilometragemPrevista;
    }

    public void setQuilometragemPrevista (int val) {
        this.quilometragemPrevista = val;
    }

    public double getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(double valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionaEventual (Eventual eventual) {
    }

}

