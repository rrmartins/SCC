package domain;


public class Endereco {

    private String nomeRua;

    private int numero;

    private String nomeBairro;

    private Cidade mCidade;

    public Endereco () {
    }

    public Cidade getCidade () {
        return mCidade;
    }

    public void setCidade (Cidade val) {
        this.mCidade = val;
    }

    public String getNomeBairro () {
        return nomeBairro;
    }

    public void setNomeBairro (String val) {
        this.nomeBairro = val;
    }

    public String getNomeRua () {
        return nomeRua;
    }

    public void setNomeRua (String val) {
        this.nomeRua = val;
    }

    public int getNumero () {
        return numero;
    }
 
    public void setNumero (int val) {
        this.numero = val;
    }

}

