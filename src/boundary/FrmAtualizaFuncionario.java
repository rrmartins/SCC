
package boundary;

import control.ControladoraFuncionario;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;



public class FrmAtualizaFuncionario extends FrmCadastroFuncionario{

    private ControladoraFuncionario controladora;

    
    public FrmAtualizaFuncionario(java.awt.Frame parent, boolean modal, ControladoraFuncionario controladoraFuncionario){
        this.controladora = controladoraFuncionario;
        this.preencherCampos();
    }


    public void preencherCampos(){

        Vector campos = this.controladora.montarLinhaFuncionarioCompleto(this.controladora.getVetFuncionarios().get(this.controladora.getMarc()));

        this.tf_Nome.setText(campos.get(1).toString());
        this.ft_Cpf.setText(campos.get(2).toString());
        this.ft_Tel.setText(campos.get(4).toString());
        this.tf_Endereco.setText(campos.get(9).toString());
        this.tf_Numero.setText(campos.get(10).toString());
        this.tf_Bairro.setText(campos.get(11).toString());
        this.tf_Email.setText(campos.get(3).toString());
        this.tf_Login.setText(campos.get(6).toString());
        this.tf_Cargo.setText(campos.get(8).toString());
        this.pf_Senha.setText(campos.get(7).toString());
        this.ft_Nasc.setText(campos.get(5).toString());
        this.cb_Uf.setSelectedItem(campos.get(14).toString());
        this.cb_Cidade.setSelectedItem(campos.get(12).toString());
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == this.b_Cadastrar){

             if(this.verificaCamposVazios()){

                Vector funcionario = this.criarFuncionario();

                try {
                   this.controladora.alterarFuncionario(funcionario, this.controladoraCidade);
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
                        Logger.getLogger(FrmAtualizaFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                finally
                {
                    this.setVisible(false);
                }
            }
        }
        else if(e.getSource() == this.b_Cancelar)
            this.preencherCampos();
    }
}
