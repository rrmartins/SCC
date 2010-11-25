package domain;

import java.sql.Time;
import java.util.Date;


public class Entrega {

    private int codEntrega;

    private int quilometragemFinal;

    private Date dataEntrega;

    private Time horaEntrega;

    private Locacao Locacao;

    public Locacao getLocacao() {
        return Locacao;
    }

    public void setLocacao(Locacao codLocacao) {
        this.Locacao = codLocacao;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }
    
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

    public Time getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Time horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public int getQuilometragemFinal () {
        return quilometragemFinal;
    }

    public void setQuilometragemFinal (int val) {
        this.quilometragemFinal = val;
    }

}

