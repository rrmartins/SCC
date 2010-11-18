package domain;

import java.util.Vector;


public class GrupoCarro {


    private int codGrupoCarro;


    private String nomeGrupo;


    private double precoDiaria;


    private double precoDiariaQuilometrada;


    private double precoCobertura;


    private TipoCarro tipoCarro;


    private Vector<Acessorio> acessorios;


    private double precoQuilometroAdicional;



    public double getPrecoQuilometroAdicional() {
        return precoQuilometroAdicional;
    }

    public void setPrecoQuilometroAdicional(double precoQuilometroAdicional) {
        this.precoQuilometroAdicional = precoQuilometroAdicional;
    }


    public GrupoCarro () {
    }


    public int getCodGrupoCarro () {
        return codGrupoCarro;
    }


    public void setCodGrupoCarro (int val) {
        this.codGrupoCarro = val;
    }


    public TipoCarro getTipoCarro () {
        return tipoCarro;
    }


    public void setTipoCarro (TipoCarro val) {
        this.tipoCarro = val;
    }


    public String getNomeGrupo () {
        return nomeGrupo;
    }


    public void setNomeGrupo (String val) {
        this.nomeGrupo = val;
    }

    public double getPrecoCobertura() {
        return precoCobertura;
    }

    public void setPrecoCobertura(double precoCobertura) {
        this.precoCobertura = precoCobertura;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public double getPrecoDiariaQuilometrada() {
        return precoDiariaQuilometrada;
    }

    public void setPrecoDiariaQuilometrada(double precoDiariaQuilometrada) {
        this.precoDiariaQuilometrada = precoDiariaQuilometrada;
    }

    public Vector<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Vector<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }


    public void adicionarLocacao (Locacao locacao) {
    }

    public void adicionarReserva (Reserva reserva) {
    }

}

