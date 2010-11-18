package domain;

import java.util.Date;

public class Cliente extends Pessoa {

    private int codCliente;

    private String email;

    private String telefone;

    private Date dataNasc;

    private String cartaoCredito;

    public Cliente () {
    }

    public String getCartaoCredito () {
        return cartaoCredito;
    }

    public void setCartaoCredito (String val) {
        this.cartaoCredito = val;
    }

    public int getCodCliente () {
        return codCliente;
    }

    public void setCodCliente (int val) {
        this.codCliente = val;
    }

    public Date getDataNasc () {
        return dataNasc;
    }

    public void setDataNasc (Date val) {
        this.dataNasc = val;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String val) {
        this.email = val;
    }

    public String getTelefone () {
        return telefone;
    }

    public void setTelefone (String val) {
        this.telefone = val;
    }


    public void adicionarLocacao (Locacao locacao) {
    }

    public void adicionarReserva (Reserva reserva) {
    }

}

