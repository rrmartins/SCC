

package boundary;

import control.ControladoraLocacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;



public class FrmAtualizaLocacao extends FrmRegistrarLocacao implements ActionListener{

    ControladoraLocacao controladora;
    Vector campos;

    public FrmAtualizaLocacao(ControladoraLocacao controladora) throws ConexaoException {
        this.setModal(true);
        this.controladora = controladora;
        this.preencherCampos();
        this.bConfirmar.addActionListener(this);
        this.bCancelar.addActionListener(this);
    }

    private void preencherCampos() throws ConexaoException{
        this.campos = this.controladora.linhaLocacaoCompleto(this.controladora.getVetLocacao().get(this.controladora.getMarc()));
    
        this.ftCpf.setText(campos.get(3).toString());
        this.confereCpf();

        Date dataL = (Date) campos.get(7);
        Date dataE = (Date) campos.get(9);

        this.dataLocacao.setDate(dataL);
        this.dataEntrega.setDate(dataE);

        this.cbHoraLocacao.setSelectedItem(campos.get(8));
        this.cbHoraEntrega.setSelectedItem(campos.get(9));

        this.tfCarro.setText(campos.get(5).toString());
        this.tfPlacaCarro.setText(campos.get(6).toString());

        String plan = campos.get(15).toString();
        this.cbPlano.setSelectedItem(plan);

        Vector car = new Vector();
        car.add(campos.get(4));
        car.add(campos.get(5));
        car.add(campos.get(6));
        car.add(campos.get(16));
        car.add(campos.get(11));

        this.carro = car;
        
        Vector grc = null;
        try {
            grc = this.controladoraGrupoCarro.dadosGrupo(car);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        } catch (MinhaException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
        this.cbGrupoCarro.setSelectedItem(grc.get(1).toString());
        this.tfQuilometragem.setText(campos.get(12).toString());
        this.tfValorTotal.setText(campos.get(14).toString());


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.bConfirmar) {

            if (dataEntrega.getDate().before(dataLocacao.getDate())) {
                JOptionPane.showMessageDialog(null, " A data de Entrega não pode ser antes da data de Locação !", "Informação", JOptionPane.INFORMATION_MESSAGE);
                this.dataEntrega.setDate(new Date());


            } else
            {
                if (this.validaCampos())
                {

                    Vector locacao = new Vector();
                    this.criarLocacao(locacao);

                    try
                    {
                        this.controladora.alterarLocacao(locacao);
                        this.dispose();
                    }

                    catch (ConexaoException ex) {
                            Logger.getLogger(FrmAtualizaLocacao.class.getName()).log(Level.SEVERE, null, ex);
                    }                    catch (MinhaException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (SQLException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ParseException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

        }
        else if(e.getSource() == this.bCancelar){

            this.dispose();
        }
    }

}
