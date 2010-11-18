package util;

public abstract class SQLDriver {
    protected String nomeBanco;
    protected String usuario;
    protected String senha;
    
    public SQLDriver(String nomeBanco, String usuario, String senha){
        this.nomeBanco = nomeBanco;
        this.usuario = usuario;
        this.senha = senha;
    }    
}
