package domain;

import java.util.Date;


public class Pagamento {

    private int codPagamento;

    private int quantidadeVezes;

    private Date dataEmissao;

    private Date dataVencimento;

    private Double valorTotal;

    private Entrega mEntrega;

    public Pagamento () {
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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

