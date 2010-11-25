
package boundary;

import control.ControladoraFuncionario;
import control.ControladoraOficina;
import control.ControladoraRevisao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;




public class FrmRevisaoAlterar extends javax.swing.JDialog {

    private ControladoraFuncionario controlFunc = new ControladoraFuncionario();
    private ControladoraOficina controlOf = new ControladoraOficina();
    private ControladoraRevisao controlRevisao = new ControladoraRevisao();
    private Vector dadosEntrega;
    Double valorTotal;
    int ultRevisao;
    int codLocacao;
    int codRevisao;

    public FrmRevisaoAlterar() {
    }
    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public FrmRevisaoAlterar(Vector dados) throws MinhaException, SQLException, ConexaoException, ParseException {
        initComponents();
        this.setModal(true);
        this.dadosEntrega = dados;
        codLocacao = controlRevisao.obterCodLocacao(dadosEntrega.get(1).toString());
        codRevisao = Integer.parseInt(dadosEntrega.get(0).toString());
        this.setLocationRelativeTo(null);
        this.preencherCampos();
    }

    public boolean verificaCamposVazios(){

        if(!this.tfNEntrega.getText().isEmpty())
        {
           if(!this.taDescricao.getText().isEmpty())
           {
               if(!this.tfValorTotal.getText().isEmpty())
               {
                   if(this.cbResponsavel.getSelectedIndex() >= 0)
                   {
                       return true;
                   }
                   else
                       JOptionPane.showMessageDialog(null, "É necessário escolher um Responsavel pela Revisão", "Atenção", JOptionPane.WARNING_MESSAGE);
               }
               else
                   JOptionPane.showMessageDialog(null, "O campo Valor Total da Revisão não pode estar vazio",  "Atenção", JOptionPane.WARNING_MESSAGE);
           }
           else
               JOptionPane.showMessageDialog(null, "Voce deve inserir uma descrição para a revisão",  "Atenção", JOptionPane.WARNING_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "O número da entrega deve ser preenchido",  "Atenção", JOptionPane.WARNING_MESSAGE);

        return false;
    }

    public Vector criarRevisao()
    {
        Vector revisao = new Vector();

        if(this.verificaCamposVazios()){

            Date dataEntra = jDC_dataEntrada.getDate();
            Date dataSai   = jDC_dataSaida.getDate();
            java.sql.Date dataE = new java.sql.Date(dataEntra.getTime());
            java.sql.Date dataS = new java.sql.Date(dataSai.getTime());

            revisao.add(this.tfNEntrega.getText());
            revisao.add(this.taDescricao.getText());
            revisao.add(this.tfValorTotal.getText());
            revisao.add(dataE);
            revisao.add(dataS);
            
            String responsavel;
            if(this.rbMec.isSelected())
                 responsavel = "mecanico";
            else
                responsavel = "oficina";
            
            revisao.add(responsavel);
            revisao.add(cbResponsavel.getSelectedItem());
            revisao.add(codLocacao);
            revisao.add(codRevisao);
            return revisao;
        }

        else
            return null;

    }

    private Vector opcoesCombo(String responsavel) throws ConexaoException
    {
        Vector nomes = new Vector();

        if(responsavel.equals("Mecanico"))
        {
            try {
                nomes = this.controlFunc.obterFuncionariosPorCargo(responsavel);
            } catch (MinhaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
        if(responsavel.equals("Oficina"))
        {
            try {
                nomes = this.controlOf.obterOficinas();
            } catch (MinhaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return nomes;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgRevisao = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lNEntrega = new javax.swing.JLabel();
        tfNEntrega = new javax.swing.JTextField();
        lNome = new javax.swing.JLabel();
        lPlaca = new javax.swing.JLabel();
        lChassi = new javax.swing.JLabel();
        tfNomeModelo = new javax.swing.JTextField();
        tfPlaca = new javax.swing.JTextField();
        tfChassi = new javax.swing.JTextField();
        separador1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescricao = new javax.swing.JTextArea();
        lDescricao = new javax.swing.JLabel();
        rbMec = new javax.swing.JRadioButton();
        rbOfic = new javax.swing.JRadioButton();
        lResponsavel = new javax.swing.JLabel();
        cbResponsavel = new javax.swing.JComboBox();
        separador2 = new javax.swing.JSeparator();
        lValor = new javax.swing.JLabel();
        lMoeda = new javax.swing.JLabel();
        tfValorTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDC_dataEntrada = new com.toedter.calendar.JDateChooser();
        jDC_dataSaida = new com.toedter.calendar.JDateChooser();
        bCancelar = new javax.swing.JButton();
        bConfirmar = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Revisão");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lNEntrega.setText("Numero da Entrega");

        tfNEntrega.setEnabled(false);

        lNome.setText("Modelo");

        lPlaca.setText("Placa do Veiculo");

        lChassi.setText("Chassi do Veiculo");

        tfNomeModelo.setEditable(false);

        tfPlaca.setEditable(false);

        tfChassi.setEditable(false);

        taDescricao.setColumns(20);
        taDescricao.setRows(5);
        jScrollPane2.setViewportView(taDescricao);

        lDescricao.setText("Descrição da Revisão");

        bgRevisao.add(rbMec);
        rbMec.setText("Mecânico");
        rbMec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMecActionPerformed(evt);
            }
        });

        bgRevisao.add(rbOfic);
        rbOfic.setText("Oficina");
        rbOfic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOficActionPerformed(evt);
            }
        });

        lResponsavel.setText("Responsável pela Revisão");

        cbResponsavel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbResponsavelItemStateChanged(evt);
            }
        });

        lValor.setForeground(new java.awt.Color(255, 0, 0));
        lValor.setText("VALOR TOTAL");

        lMoeda.setText("R$");

        tfValorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("Data Entrada");

        jLabel2.setText("Data Saida");

        jDC_dataEntrada.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lDescricao)
                    .addComponent(lResponsavel)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lValor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lMoeda)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(separador2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rbMec)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbOfic)
                                .addGap(18, 18, 18)
                                .addComponent(cbResponsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separador1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNome)
                            .addComponent(tfNomeModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lNEntrega)
                            .addComponent(tfNEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lPlaca)
                            .addComponent(jLabel1)
                            .addComponent(jDC_dataEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(tfPlaca))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(lChassi)
                            .addComponent(tfChassi, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jDC_dataSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNEntrega)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfNEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDC_dataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNome)
                            .addComponent(lChassi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfChassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNomeModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDC_dataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lResponsavel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMec)
                    .addComponent(rbOfic)
                    .addComponent(cbResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMoeda)
                    .addComponent(lValor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bConfirmar.setText("Alterar");
        bConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConfirmar)
                    .addComponent(bCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void preencherCampos() throws MinhaException, SQLException, ConexaoException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
        Date dataEntrada = sdf.parse(dadosEntrega.get(7).toString());
        Date dataSaida = sdf.parse(dadosEntrega.get(8).toString());

        this.jDC_dataEntrada.setDate(dataEntrada);
        this.jDC_dataSaida.setDate(dataSaida);

        Vector carro = controlRevisao.obterCarro(null,codLocacao);

        Vector vetCarro = (Vector) carro.get(0);
        
        int numeroEntrega = Integer.parseInt(this.dadosEntrega.get(3).toString());
        
        this.tfNEntrega.setText("" + numeroEntrega);
        this.tfNomeModelo.setText(vetCarro.get(0).toString());
        this.tfPlaca.setText(vetCarro.get(1).toString());
        this.tfChassi.setText(vetCarro.get(2).toString());

        String responsavel = dadosEntrega.get(4).toString();

        String[] resp = responsavel.split(" | ");
        if (resp[0].equals("O"))
            rbOfic.setSelected(true);
        else
            rbMec.setSelected(true);

        Vector responsa = new Vector();
        for (int i = 2; i < resp.length; i++){
            responsa.add(resp[i]+" ");
        }
        String res = "";
        for (int i = 0; i < responsa.size(); i++){
            res += responsa.get(i).toString();
        }

        res = res.trim();
        DefaultComboBoxModel responsav = new DefaultComboBoxModel();
        responsav.addElement(res);
        cbResponsavel.setModel(responsav);
        taDescricao.setText(dadosEntrega.get(5).toString());
        tfValorTotal.setText(dadosEntrega.get(6).toString());
    }

    private void rbMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMecActionPerformed

        ComboBoxModel modelo = null;
        try {
            modelo = new DefaultComboBoxModel(this.opcoesCombo("Mecanico"));
        } catch (ConexaoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.cbResponsavel.setModel(modelo);

    }//GEN-LAST:event_rbMecActionPerformed

    private void rbOficActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOficActionPerformed

        ComboBoxModel modelo = null;
        try {
            modelo = new DefaultComboBoxModel(this.opcoesCombo("Oficina"));
        } catch (ConexaoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.cbResponsavel.setModel(modelo);

    }//GEN-LAST:event_rbOficActionPerformed

    private void cbResponsavelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbResponsavelItemStateChanged

        if(this.rbMec.isSelected()){

            this.controlFunc.setMarc(this.cbResponsavel.getSelectedIndex());
        }

        else if(this.rbOfic.isSelected()){

            this.controlOf.setMarc(this.cbResponsavel.getSelectedIndex());
        }
    }//GEN-LAST:event_cbResponsavelItemStateChanged

    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarActionPerformed

        if(this.verificaCamposVazios()){
            Vector novaRevisao = this.criarRevisao();
            try {
                this.controlRevisao.alterarRevisao(novaRevisao);
                JOptionPane.showMessageDialog(null, "Revisão Alterada com Sucesso!");
            } catch (MinhaException ex) {
                Logger.getLogger(FrmRevisaoAlterar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                ex.printStackTrace();
                //Logger.getLogger(FrmRevisaoAlterar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(FrmRevisaoAlterar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmRevisaoAlterar.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.dispose();
        }
        
    }//GEN-LAST:event_bConfirmarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed


//
//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmRevisao().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton bConfirmar;
    private javax.swing.ButtonGroup bgRevisao;
    protected javax.swing.JComboBox cbResponsavel;
    protected com.toedter.calendar.JDateChooser jDC_dataEntrada;
    protected com.toedter.calendar.JDateChooser jDC_dataSaida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lChassi;
    private javax.swing.JLabel lDescricao;
    private javax.swing.JLabel lMoeda;
    private javax.swing.JLabel lNEntrega;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel lPlaca;
    private javax.swing.JLabel lResponsavel;
    private javax.swing.JLabel lValor;
    protected javax.swing.JRadioButton rbMec;
    protected javax.swing.JRadioButton rbOfic;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    protected javax.swing.JTextArea taDescricao;
    protected javax.swing.JTextField tfChassi;
    protected javax.swing.JTextField tfNEntrega;
    protected javax.swing.JTextField tfNomeModelo;
    protected javax.swing.JTextField tfPlaca;
    protected javax.swing.JTextField tfValorTotal;
    // End of variables declaration//GEN-END:variables

}
