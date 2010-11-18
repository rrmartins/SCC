/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author Tiago Vailant
 */
public class MinhaException extends Exception {

    String mensagem;

    public MinhaException(String erro)
    {
        this.mensagem = erro;
    }
    
    @Override
    public String getMessage(){
        return this.mensagem;
    }

}
