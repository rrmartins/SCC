package domain;

import java.sql.Time;
import java.util.Date;

public class Reserva {

   private int codReserva;

    public boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    private Date dataLocacao;

    private Date dataEntrega;

    private Time horaLocacao;

    private Time horaEntrega;
 
    private float valorPrevisto;

    private boolean cobertura;

    private boolean situacao;

    private Cliente mCliente;

    private GrupoCarro mGrupoCarro;

    public Reserva () {
    }

    public boolean getCobertura () {
        return cobertura;
    }

    public void setCobertura (boolean val) {
        this.cobertura = val;
    }

    public int getCodReserva () {
        return codReserva;
    }

    public void setCodReserva (int val) {
        this.codReserva = val;
    }

    public Date getDataEntrega () {
        return dataEntrega;
    }

    public void setDataEntrega (Date val) {
        this.dataEntrega = val;
    }

    public Date getDataLocacao () {
        return dataLocacao;
    }

    public void setDataLocacao (Date val) {
        this.dataLocacao = val;
    }

    public Time getHoraEntrega () {
        return horaEntrega;
    }

    public void setHoraEntrega (Time val) {
        this.horaEntrega = val;
    }

    public Time getHoraLocacao () {
        return horaLocacao;
    }

    public void setHoraLocacao (Time val) {
        this.horaLocacao = val;
    }

    public Cliente getCliente() {
        return mCliente;
    }

    public void setCliente(Cliente mCliente) {
        this.mCliente = mCliente;
    }

    public GrupoCarro getGrupoCarro() {
        return mGrupoCarro;
    }

    public void setGrupoCarro(GrupoCarro mGrupoCarro) {
        this.mGrupoCarro = mGrupoCarro;
    }

    public float getValorPrevisto () {
        return valorPrevisto;
    }

    public void setValorPrevisto (float val) {
        this.valorPrevisto = val;
    }

}

