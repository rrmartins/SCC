/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmCadastroOficina.java
 *
 * Created on 26/08/2010, 15:56:06
 */

package boundary;

import control.ControladoraOficina;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;

/**
 *
 * @author Rodrigo Martins
 */
public class FrmOficinaAlterar extends javax.swing.JDialog {

    ControladoraOficina cadastroOficina;
    Vector uf = new Vector();
    Vector cidade = new Vector();
    Vector linha = new Vector();
    Vector cidadeUf = new Vector();
    DefaultComboBoxModel cidades = new DefaultComboBoxModel();
    DefaultComboBoxModel estados = new DefaultComboBoxModel();

    /** Creates new form FrmCadastroOficina
     * @param linha 
     */
    public FrmOficinaAlterar(Vector linha) throws MinhaException, SQLException, ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.linha = linha;
        this.cadastroOficina = new ControladoraOficina();
        this.jCBES.removeAllItems();
        this.jCBCidade.removeAllItems();
        uf = cadastroOficina.obterLinhasUF();

        cidadeUf = cadastroOficina.obterUFPorCidade(linha.get(1).toString());

        String ufS ="UF";
        estados.addElement(ufS);
        for(int i = 0; i < uf.size(); i++){
            estados.addElement(uf.elementAt(i));
        }
        jCBES.setModel(estados);
        jCBES.setSelectedItem(cidadeUf.get(0));
        cidades.addElement(linha.get(1).toString());
        jCBCidade.setModel(cidades);
        jCBCidade.setSelectedItem(cidades);

        jTFNomeOficina.setText(linha.get(2).toString());
        jTFCNPJ.setText(linha.get(3).toString());
        jTFTelefone.setText(linha.get(4).toString());
        jTFRua.setText(linha.get(5).toString());
        jTFNumero.setText(linha.get(6).toString());
        jTFBairro.setText(linha.get(7).toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBCadastrar = new javax.swing.JButton();
        jBLimpar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFCNPJ = new javax.swing.JTextField();
        jTFTelefone = new javax.swing.JTextField();
        jTFNomeOficina = new javax.swing.JTextField();
        jCBES = new javax.swing.JComboBox();
        jCBCidade = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFBairro = new javax.swing.JTextField();
        jTFRua = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Oficina");
        setResizable(false);

        jBCadastrar.setText("OK");
        jBCadastrar.setMaximumSize(new java.awt.Dimension(80, 22));
        jBCadastrar.setMinimumSize(new java.awt.Dimension(80, 22));
        jBCadastrar.setPreferredSize(new java.awt.Dimension(80, 22));
        jBCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCadastrarActionPerformed(evt);
            }
        });

        jBLimpar.setText("Limpar");
        jBLimpar.setMaximumSize(new java.awt.Dimension(80, 22));
        jBLimpar.setMinimumSize(new java.awt.Dimension(80, 22));
        jBLimpar.setPreferredSize(new java.awt.Dimension(80, 22));
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setText("Estado");

        jLabel1.setText("Nome Oficina");

        jLabel2.setText("CNPJ");

        jLabel4.setText("Telefone");

        jLabel8.setText("Cidade");

        jCBES.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBESItemStateChanged(evt);
            }
        });

        jCBCidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione o ESTADO" }));

        jLabel5.setText("Rua");

        jLabel6.setText("Número");

        jLabel7.setText("Bairro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCBES, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCBCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFCNPJ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel5)
                    .addComponent(jTFRua, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addComponent(jTFNomeOficina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jTFBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNomeOficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBESItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBESItemStateChanged
        // TODO add your handling code here:
        String estado;
        estado = (String) this.jCBES.getSelectedItem();
        if (estado.equals("UF")){
            cidades.addElement("Selecione uma UF");
            jCBCidade.setModel(cidades);
        }
        else{
            try {
                try {
                    cidade = cadastroOficina.preencherComboEstado(estado);
                } catch (MinhaException ex) {
                    Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                }
                DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                for(int i = 0; i < cidade.size(); i++){
                    modelo.addElement(cidade.elementAt(i));
                }
                jCBCidade.setModel(modelo);
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }//GEN-LAST:event_jCBESItemStateChanged

    @SuppressWarnings("unchecked")
    private void jBCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarActionPerformed
        // TODO add your handling code here:
        if (!this.jTFNomeOficina.getText().isEmpty())
        {
            if (!this.jTFCNPJ.getText().isEmpty())
            {
               if (!this.jTFTelefone.getText().isEmpty())
               {
                   if (!this.jCBES.getSelectedItem().equals("UF"))
                   {
                       if (!this.jCBCidade.getSelectedItem().equals("Selecione uma UF"))
                       {
                           if (!this.jTFRua.getText().isEmpty())
                           {
                               if (!this.jTFNumero.getText().isEmpty())
                               {
                                  if (!this.jTFBairro.getText().isEmpty())
                                  {
                                        Vector novaOficina = new Vector();
                                        novaOficina.addElement(this.jTFNomeOficina.getText());      //0
                                        novaOficina.addElement(this.jTFCNPJ.getText());             //1
                                        novaOficina.addElement(this.jTFTelefone.getText());         //2
                                        novaOficina.addElement(this.jCBES.getSelectedItem());       //3
                                        novaOficina.addElement(this.jCBCidade.getSelectedItem());   //4
                                        novaOficina.addElement(this.jTFRua.getText());              //5
                                        novaOficina.addElement(this.jTFNumero.getText());           //6
                                        novaOficina.addElement(this.jTFBairro.getText());           //7
                                        novaOficina.addElement(linha.get(0));                       //8
                                        

                                        try
                                        {
                                            try {
                                                this.cadastroOficina.alterarOficina(novaOficina);
                                            }catch (MinhaException ex) {
                                                Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (ParseException ex) {
                                                Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (ClassNotFoundException ex) {
                                                Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (ConexaoException ex) {
                                                Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            this.jTFNomeOficina.setText("");
                                            this.jTFCNPJ.setText("");
                                            this.jTFTelefone.setText("");
                                            estados.addElement("UF");
                                            jCBES.setModel(estados);
                                            cidades.addElement("Selecione uma UF");
                                            jCBCidade.setModel(cidades);
                                            this.jTFRua.setText("");
                                            this.jTFNumero.setText("");
                                            this.jTFBairro.setText("");
                                            this.dispose();

                                        }
                                        catch (SQLException erro)
                                        {
                                            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                  }else
                                     JOptionPane.showMessageDialog(null, "É necessário o Bairro da Oficina.","Atenção" , JOptionPane.WARNING_MESSAGE);
                               }else
                                  JOptionPane.showMessageDialog(null, "É necessário o numeor da Oficina.","Atenção" , JOptionPane.WARNING_MESSAGE);
                           }else
                              JOptionPane.showMessageDialog(null, "É necessário o nome da Rua da Oficina.","Atenção" , JOptionPane.WARNING_MESSAGE);
                       }else
                          JOptionPane.showMessageDialog(null, "É necessário selecionar uma Cidade.","Atenção" , JOptionPane.WARNING_MESSAGE);
                   }else
                      JOptionPane.showMessageDialog(null, "É necessário selecionar um Estado.","Atenção" , JOptionPane.WARNING_MESSAGE);
               }else
                  JOptionPane.showMessageDialog(null, "É necessário o numero do Telefone.","Atenção" , JOptionPane.WARNING_MESSAGE);
            }else
                JOptionPane.showMessageDialog(null, "É necessário o numero do CNPJ.","Atenção" , JOptionPane.WARNING_MESSAGE);
        }else
                JOptionPane.showMessageDialog(null, "É necessário O nome da Oficina.","Atenção" , JOptionPane.WARNING_MESSAGE);


    }//GEN-LAST:event_jBCadastrarActionPerformed

    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed
        // TODO add your handling code here:
        this.jTFNomeOficina.setText("");
        this.jTFCNPJ.setText("");
        this.jTFTelefone.setText("");
        estados.addElement("UF");
        jCBES.setModel(estados);
        cidades.addElement("Selecione uma UF");
        jCBCidade.setModel(cidades);
        this.jTFRua.setText("");
        this.jTFNumero.setText("");
        this.jTFBairro.setText("");
    }//GEN-LAST:event_jBLimparActionPerformed

    /**
    * @param args the command line arguments
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new FrmOficinaAlterar().setVisible(true);
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmOficinaAlterar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCadastrar;
    private javax.swing.JButton jBLimpar;
    private javax.swing.JComboBox jCBCidade;
    private javax.swing.JComboBox jCBES;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFBairro;
    private javax.swing.JTextField jTFCNPJ;
    private javax.swing.JTextField jTFNomeOficina;
    private javax.swing.JTextField jTFNumero;
    private javax.swing.JTextField jTFRua;
    private javax.swing.JTextField jTFTelefone;
    // End of variables declaration//GEN-END:variables

}