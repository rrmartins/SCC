package domain;
 
public class Cidade {
 
    private int codCidade;

    private String nomeCidade;

    private UF mUF;
 
    public Cidade () {
    }
 
    public int getCodCidade () {
        return codCidade;
    }

    public void setCodCidade (int val) {
        this.codCidade = val;
    }

    public UF getUF () {
        return mUF;
    }

    public void setUF (UF val) {
        this.mUF = val;
    }

    public String getNomeCidade () {
        return nomeCidade;
    }

    public void setNomeCidade (String val) {
        this.nomeCidade = val;
    }

    public void adicionarEndereco (Endereco endereco) {
    }

}

