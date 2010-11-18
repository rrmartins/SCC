package domain;

public class Oficina {

    private int codOficina;

    private Cidade codCidade;

    private String nomeOficina;

    private String cnpj;

    private String telefone;

//    private String rua;
//
//    private int numero;
//
//    private String bairro;

    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public Cidade getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Cidade codCidade) {
        this.codCidade = codCidade;
    }

    public Oficina () {
    }

    public String getCnpj () {
        return cnpj;
    }

    public void setCnpj (String val) {
        this.cnpj = val;
    }

    public int getCodOficina () {
        return codOficina;
    }

    public void setCodOficina (int val) {
        this.codOficina = val;
    }

    public String getNomeOficina () {
        return nomeOficina;
    }

    public void setNomeOficina (String val) {
        this.nomeOficina = val;
    }

    public String getTelefone () {
        return telefone;
    }

    public void setTelefone (String val) {
        this.telefone = val;
    }

    public void adicionarRevisao (Revisao revisao) {
    }

}

