package domain;

import java.util.Date;


public class Pessoa {

    private String nome;

    private String cpf;

    private String login;

    private String senha;

    private Endereco mEndereco;

    private Date dataNasc;

    private String email;

    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa () {
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCpf () {
        return cpf;
    }

    public void setCpf (String val) {
        this.cpf = val;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String val) {
        this.login = val;
    }

    public Endereco getEndereco () {
        return mEndereco;
    }

    public void setEndereco (Endereco val) {
        this.mEndereco = val;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String val) {
        this.nome = val;
    }

    public String getSenha () {
        return senha;
    }

    public void setSenha (String val) {
        this.senha = val;
    }

}

