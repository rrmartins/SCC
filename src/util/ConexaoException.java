package util;

public class ConexaoException extends Exception {
    private String texto;
    
    public ConexaoException(String texto) {
        this.texto = texto;
    }
    
    public String getText(){
        return "Falha na conexão com banco de dados";
    }
 
    public String getMessage() {
        return "Mensagem: " + this.texto;
    }
}
