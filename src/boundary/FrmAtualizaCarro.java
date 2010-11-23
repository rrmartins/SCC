
package boundary;

import control.ControladoraCarros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;


public class FrmAtualizaCarro extends FrmCadastroCarro implements ActionListener{


    
    public FrmAtualizaCarro( ControladoraCarros controladoraCarros) throws ConexaoException {
     this.setModal(true);
        this.controladoraCarro = controladoraCarros;
        this.preencherCampos();
        this.b_Confirmar.addActionListener(this);
        this.b_Cancelar.addActionListener(this);

    }

    private void preencherCampos() {

        Vector campos = this.controladoraCarro.montarLinhasCarro(this.controladoraCarro.getCarros().get(this.controladoraCarro.getMarc()));


        if(campos.get(7).equals("SIM"))
            this.rb_Sim.setSelected(true);
        else
            this.rb_Sim.setSelected(true);


        this.tf_Modelo.setText(campos.get(1).toString());
        this.tf_Marca.setText(campos.get(2).toString());
        this.cbAno.setSelectedItem(Integer.parseInt(campos.get(3).toString()));
        this.ft_Placa.setText(campos.get(5).toString());
        this.tf_Chassi.setText(campos.get(6).toString());
        this.tf_Quilometragem.setText(campos.get(8).toString());
        this.cb_grupoCarro.setSelectedItem(campos.get(4).toString());
        this.controladoraGrupoCarro.setMarc(this.cb_grupoCarro.getSelectedIndex());

        
    }


    public void actionPerformed(ActionEvent e){

        if(e.getSource() == this.b_Confirmar){

            if(this.verificaCampos()){

                Vector carroAtualizacao = this.montaObjeto();

                try {
                    try {
                        this.controladoraCarro.alterarCarro(carroAtualizacao, this.controladoraGrupoCarro);
                    } catch (ConexaoException ex) {
                        Logger.getLogger(FrmAtualizaCarro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                catch (SQLException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                catch (MinhaException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    this.setVisible(false);
                }
            }
        }
        else if(e.getSource() == this.b_Cancelar){
            this.dispose();
        }

    }


}
