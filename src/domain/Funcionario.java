package domain;


public class Funcionario extends Pessoa {
 
    private int codFuncionario;

    private String cargo;

    public Funcionario () {
    }

    public String getCargo () {
        return cargo;
    }

    public void setCargo (String val) {
        this.cargo = val;
    }

    public int getCodFuncionario () {
        return codFuncionario;
    }

    public void setCodFuncionario (int val) {
        this.codFuncionario = val;
    }

    public void adicionarRevisao (Revisao revisao) {
    }

}

