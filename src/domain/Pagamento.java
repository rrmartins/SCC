package domain;


public class Pagamento {

    private int codPagamento;

    private int quantidadeVezes;

    private Double valorTotal;

    private Entrega mEntrega;

    public Pagamento () {
    }

    public int getCodPagamento () {
        return codPagamento;
    }

    public void setCodPagamento (int val) {
        this.codPagamento = val;
    }

    public Entrega getEntrega () {
        return mEntrega;
    }

    public void setEntrega (Entrega val) {
        this.mEntrega = val;
    }

    public int getQuantidadeVezes () {
        return quantidadeVezes;
    }

    public void setQuantidadeVezes (int val) {
        this.quantidadeVezes = val;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

}

