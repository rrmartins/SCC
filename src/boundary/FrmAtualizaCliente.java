
package boundary;

import control.ControladoraCliente;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;


/**
 *
 * @author Rodrigo Martins
 */
public class FrmAtualizaCliente extends FrmCadastroCliente{

    private ControladoraCliente controladora;
    
    public FrmAtualizaCliente(ControladoraCliente controladoraCliente){
        this.setModal(true);
        this.controladora = controladoraCliente;
        this.preencherCampos();
        
    }

    
    public void preencherCampos(){
    
        Vector campos = this.controladora.montarLinhaClienteCompleto(this.controladora.getVetClientes().get(this.controladora.getMarc()));
        
        this.tf_Nome.setText(campos.get(1).toString());
        this.ft_Cpf.setText(campos.get(2).toString());
        this.ft_Tel.setText(campos.get(4).toString());
        this.tf_Endereco.setText(campos.get(9).toString());
        this.tf_Numero.setText(campos.get(10).toString());
        this.tf_Bairro.setText(campos.get(11).toString());
        this.tf_Email.setText(campos.get(3).toString());
        this.tf_Login.setText(campos.get(6).toString());
        this.tf_Cartao.setText(campos.get(8).toString());
        this.pf_Senha.setText(campos.get(7).toString());
        this.ft_Nasc.setText(campos.get(5).toString());
        this.cb_Uf.setSelectedItem(campos.get(14).toString());
        this.cb_Cidade.setSelectedItem(campos.get(12).toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.b_Cadastrar){

             if(this.verificaCamposVazios()){

                Vector cliente = this.criarCliente();

                try {
                    this.controladora.alterarCliente(cliente, this.controladoraCidade);
                }
                catch (ParseException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                catch (SQLException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                catch (MinhaException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                catch (ConexaoException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    this.setVisible(false);
                }
            }
        }
        else if(e.getSource() == this.bCancelar)
            this.dispose();
        
    }



}
