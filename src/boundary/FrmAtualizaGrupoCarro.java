

package boundary;

import control.ControladoraGrupoCarro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;


public class FrmAtualizaGrupoCarro extends FrmCadastroGrupoCarro implements ActionListener{


    public FrmAtualizaGrupoCarro(ControladoraGrupoCarro controladora) throws ConexaoException {
        this.setModal(true);
        this.controladoraGrupoCarro = controladora;
        this.preencherCampos();
        this.jb_InserirAcessorio.setEnabled(false);
        this.jLAcessorios.setEnabled(false);
        this.bCancelar.addActionListener(this);
        this.bConfirmar.addActionListener(this);

    }

    private void preencherCampos() throws ConexaoException {

        Vector campos = this.controladoraGrupoCarro.montarLinhaGrupoCarro(this.controladoraGrupoCarro.getVetGrupos().get(this.controladoraGrupoCarro.getMarc()));
        Vector acessorios = new Vector();
        try {
            acessorios = this.controladoraAcessorio.obterAcessoriosGrupo(this.controladoraGrupoCarro.getVetGrupos().get(this.controladoraGrupoCarro.getMarc()));
        } catch (MinhaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        this.jtf_NomeGrupo.setText(campos.get(1).toString());
        this.jtf_precoDiaria.setText(campos.get(3).toString());
        this.jcb_TipoCarro.setSelectedItem(campos.get(2).toString());
        this.jtf_PrecoDiariaQuilometrada.setText(campos.get(4).toString());
        this.jtfPrecoCobertura.setText(campos.get(5).toString());
        this.tf_AdicionalQuilometro.setText(campos.get(6).toString());


        if(!acessorios.isEmpty()){

            this.acessoriosGrupo = acessorios;
            DefaultListModel modelo = new DefaultListModel();
            for (int i = 0; i < acessorios.size(); i++) {
                Vector acess = (Vector) acessorios.get(i);
                modelo.addElement(acess.get(1));

            }
            this.jLAcessorios.setModel(modelo);

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.bConfirmar){

            Vector grupo = this.criarGrupo();

            if(grupo != null){

                try {
                    this.controladoraGrupoCarro.alterarGrupoCarro(grupo, this.controladoraTipoCarro, this.acessoriosGrupo);
                    this.setVisible(false);
                } catch (MinhaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } 
            }
        }
        else if(e.getSource() == this.bCancelar){
            this.dispose();
        }
    }

}
