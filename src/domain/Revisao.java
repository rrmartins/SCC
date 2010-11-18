package domain;

import java.util.Date;


public class Revisao {

    private int codRevisao;

    private String descRevisao;

    private Double valorRevisao;

    private Entrega mEntrega;

    private Oficina mOficina;

    private Funcionario mFuncionario;

    private Date dataEntrada;

    private Date dataSaida;


    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Revisao () {
    }

    public int getCodRevisao () {
        return codRevisao;
    }

    public void setCodRevisao (int val) {
        this.codRevisao = val;
    }

    public String getDescRevisao () {
        return descRevisao;
    }

    public void setDescRevisao (String val) {
        this.descRevisao = val;
    }

    public Entrega getEntrega () {
        return mEntrega;
    }

    public void setEntrega (Entrega val) {
        this.mEntrega = val;
    }

    public Funcionario getFuncionario () {
        return mFuncionario;
    }

    public void setFuncionario (Funcionario val) {
        this.mFuncionario = val;
    }

    public Oficina getOficina () {
        return mOficina;
    }

    public void setOficina (Oficina val) {
        this.mOficina = val;
    }

    public Double getValorRevisao () {
        return valorRevisao;
    }

    public void setValorRevisao (Double val) {
        this.valorRevisao = val;
    }

}

