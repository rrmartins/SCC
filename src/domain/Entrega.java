package domain;

import java.util.Date;

public class Entrega {

    private int codEntrega;

    private int quilometragemFinal;

    private Date dataEntrega;

    private String horaEntrega;

    private Locacao codLocacao;

    private Locacao mLocacao;

    public Locacao getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(Locacao codLocacao) {
        this.codLocacao = codLocacao;
    }

    public Locacao getmLocacao() {
        return mLocacao;
    }

    public void setmLocacao(Locacao mLocacao) {
        this.mLocacao = mLocacao;
    }

    public int getQuantidadeVezes() {
        return quantidadeVezes;
    }

    public void setQuantidadeVezes(int quantidadeVezes) {
        this.quantidadeVezes = quantidadeVezes;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    private int quantidadeVezes;
    
    private int valorTotal;

    public Entrega () {
    }

    public int getCodEntrega () {
        return codEntrega;
    }

    public void setCodEntrega (int val) {
        this.codEntrega = val;
    }

    public Date getDataEntrega () {
        return dataEntrega;
    }

    public void setDataEntrega (Date val) {
        this.dataEntrega = val;
    }

    public String getHoraEntrega () {
        return horaEntrega;
    }

    public void setHoraEntrega (String val) {
        this.horaEntrega = val;
    }

    public Locacao getLocacao () {
        return mLocacao;
    }

    public void setLocacao (Locacao val) {
        this.mLocacao = val;
    }

    public int getQuilometragemFinal () {
        return quilometragemFinal;
    }

    public void setQuilometragemFinal (int val) {
        this.quilometragemFinal = val;
    }

}

