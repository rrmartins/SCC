

package boundary;

import control.ControladoraFuncionario;
import control.ControladoraOficina;
import control.ControladoraRevisao;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;



public class FrmAtualizaRevisao extends FrmRevisao {

    private ControladoraRevisao controladora;

    public FrmAtualizaRevisao(ControladoraFuncionario controladoraFuncionario, ControladoraOficina controladoraOficina, ControladoraRevisao controladoraRevisao, Vector dados) {
        super(controladoraFuncionario, controladoraOficina, controladoraRevisao, dados);
        this.setModal(true);
        this.controladora = controladoraRevisao;
        this.preencherCampos();

    }

    private void preencherCampos() {

        Vector campos = this.controladora.buscarDadosCompletos(this.controladora.getVetRevisoes().get(this.controladora.getMarc()));

        this.tfNEntrega.setText(campos.get(4).toString());
        this.tfNomeModelo.setText(campos.get(1).toString());
        this.tfPlaca.setText(campos.get(2).toString());
        this.tfChassi.setText(campos.get(3).toString());
        this.taDescricao.setText(campos.get(7).toString());
        this.tfValorTotal.setText(campos.get(8).toString());
        this.jDC_dataEntrada.setDateFormatString( campos.get(9).toString());
        this.jDC_dataSaida.setDateFormatString((campos.get(10).toString()));

        if(campos.get(5).toString().equals("funcionario")){
            this.rbMec.setSelected(true);
            this.cbResponsavel.setSelectedItem(campos.get(6).toString());
        }
        else{
            this.rbOfic.setSelected(true);
            this.cbResponsavel.setSelectedItem(campos.get(6).toString());
        }
        
    }



    public void actionPerformed(ActionEvent e) throws ParseException, ConexaoException{

        if(e.getSource() == this.bConfirmar){

            if(this.verificaCamposVazios()){

                Vector revisao = this.criarRevisao();

                try {
                    this.controladora.alterarRevisao(revisao);
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
        else if(e.getSource() == this.bCancelar)
            this.dispose();
    }


}
