
package boundary;

import control.ControladoraAcessorio;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;




public class FrmInserirAcessorio extends javax.swing.JDialog {

    
    private Vector<Vector> todosAcessorios;
    static  Vector<Vector> acessoriosEscolhidos = new Vector<Vector>();
    private DefaultListModel modeloLista1 = new DefaultListModel();
    private DefaultListModel modeloLista2 = new DefaultListModel();
    

    public static Vector<Vector> getAcessoriosEscolhidos() {
        return acessoriosEscolhidos;
    }

    public static void setAcessoriosEscolhidos(Vector<Vector> acessoriosEscolhidos) {
        FrmInserirAcessorio.acessoriosEscolhidos = acessoriosEscolhidos;
    }
   
    
    public FrmInserirAcessorio(Vector acessorios, java.awt.Frame parent, boolean modal, ControladoraAcessorio controladoraAcessorio) throws ConexaoException {
        super(parent, modal);
        initComponents();
        this.removerItensVetor();
        this.setLocationRelativeTo(null);
        this.listarAcessorios(controladoraAcessorio);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBPassDireita = new javax.swing.JButton();
        jBVoltEsquerda = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLAcessorio = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLAGC = new javax.swing.JList();
        jBOK = new javax.swing.JButton();
        jBLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Acessorio - Grupo de Carro");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Acessorios");

        jLabel2.setText(" Acessorios do");

        jBPassDireita.setText(">");
        jBPassDireita.setMaximumSize(new java.awt.Dimension(80, 22));
        jBPassDireita.setMinimumSize(new java.awt.Dimension(80, 22));
        jBPassDireita.setPreferredSize(new java.awt.Dimension(80, 22));
        jBPassDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPassDireitaActionPerformed(evt);
            }
        });

        jBVoltEsquerda.setText("<");
        jBVoltEsquerda.setMaximumSize(new java.awt.Dimension(80, 22));
        jBVoltEsquerda.setMinimumSize(new java.awt.Dimension(80, 22));
        jBVoltEsquerda.setPreferredSize(new java.awt.Dimension(80, 22));
        jBVoltEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVoltEsquerdaActionPerformed(evt);
            }
        });

        jLabel3.setText("Grupo de Carro");

        jScrollPane3.setViewportView(jLAcessorio);

        jScrollPane4.setViewportView(jLAGC);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBPassDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBVoltEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jBPassDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBVoltEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jBOK.setText("Ok");
        jBOK.setMaximumSize(new java.awt.Dimension(63, 23));
        jBOK.setMinimumSize(new java.awt.Dimension(63, 23));
        jBOK.setPreferredSize(new java.awt.Dimension(63, 23));
        jBOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBOKActionPerformed(evt);
            }
        });

        jBLimpar.setText("Cancelar");
        jBLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBOK, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBLimpar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void setarAcessoriosLista(){
        
        for(int i = 0; i < this.todosAcessorios.size(); i++){
                this.modeloLista1.addElement(this.todosAcessorios.get(i).get(1));
            }
            
            this.jLAcessorio.setModel(this.modeloLista1);
    }
    
    
    private void listarAcessorios(ControladoraAcessorio controladoraAcessorio) throws ConexaoException{
        try {

            this.todosAcessorios = controladoraAcessorio.obterLinhasAcessorio();
            this.setarAcessoriosLista();
                
        } catch (MinhaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    
    private void jBPassDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPassDireitaActionPerformed

        if(this.jLAcessorio.getSelectedValue() == null)
            JOptionPane.showMessageDialog(this, "Selecione um acessório !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else{
            
            if(this.modeloLista2.contains(this.jLAcessorio.getSelectedValue()))
                JOptionPane.showMessageDialog(this, "Este Acessório já está relacionado !", "Informação", JOptionPane.INFORMATION_MESSAGE);
            else{
                this.modeloLista2.addElement(this.jLAcessorio.getSelectedValue());
                
                Vector acessorio = new Vector();
                acessorio.addElement(this.jLAcessorio.getSelectedIndex());
                acessorio.addElement(this.jLAcessorio.getSelectedValue());
                
                FrmInserirAcessorio.acessoriosEscolhidos.addElement(acessorio);

                this.jLAGC.setModel(modeloLista2);
            }

        }
        

    }//GEN-LAST:event_jBPassDireitaActionPerformed



    private void jBLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLimparActionPerformed


    }//GEN-LAST:event_jBLimparActionPerformed


    
    private void jBVoltEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVoltEsquerdaActionPerformed
        
    }//GEN-LAST:event_jBVoltEsquerdaActionPerformed


    
    
    private void jBOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBOKActionPerformed

        this.setVisible(false);

    }//GEN-LAST:event_jBOKActionPerformed

    /**
     * @param args the command line arguments
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new FrmInserirAcessorio(acessorio).setVisible(true);
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmInserirAcessorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmInserirAcessorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBLimpar;
    private javax.swing.JButton jBOK;
    private javax.swing.JButton jBPassDireita;
    private javax.swing.JButton jBVoltEsquerda;
    private javax.swing.JList jLAGC;
    private javax.swing.JList jLAcessorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

    private void removerItensVetor() {

        for(int i = 0; i < FrmInserirAcessorio.getAcessoriosEscolhidos().size(); i++){
            FrmInserirAcessorio.acessoriosEscolhidos.remove(i);
        }
    }

   
}
