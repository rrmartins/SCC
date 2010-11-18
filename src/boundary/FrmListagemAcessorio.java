/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmListagemReserva.java
 *
 * Created on 18/10/2010, 19:05:23
 */

package boundary;

import control.ControladoraAcessorio;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;

/**
 *
 * @author Rodrigo Martins
 */
public class FrmListagemAcessorio extends javax.swing.JDialog {

    ControladoraAcessorio controlAcessorio;
    Vector usuarioTipo = new Vector();
    Vector usuario     = new Vector();

    public Vector getUsuario() {
        return usuario;
    }

    public void setUsuario(Vector usuario) {
        this.usuario = usuario;
    }

    public Vector getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(Vector usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    /** Creates new form FrmListagemReserva
     * @param userTipo
     * @param user
     * @throws MinhaException
     * @throws ConexaoException
     */
    public FrmListagemAcessorio(Vector userTipo, Vector user) throws MinhaException, ConexaoException {
        initComponents();
        usuarioTipo = userTipo;
        usuario     = user;
        this.setLocationRelativeTo(null);
        this.controlAcessorio = new ControladoraAcessorio();

        this.preencherTable();
    }
      
    protected void limparTable()
    {
        DefaultTableModel model = (DefaultTableModel) this.jTListagemAcessorio.getModel();
        int numeroLinha = model.getRowCount();

        for(int i = 0; i < numeroLinha; i++)
        {
            model.removeRow(0);
        }
    }

    protected void preencherTable() throws MinhaException, ConexaoException
    {
        try{
            Vector reservaTabela = this.controlAcessorio.obterLinhasAcessorio();
            DefaultTableModel model = (DefaultTableModel)this.jTListagemAcessorio.getModel();

            for(int i = 0; i < reservaTabela.size(); i++)
            {
                model.insertRow(model.getRowCount(), (Vector)reservaTabela.get(i));
            }
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void excluirAcessorio() throws ConexaoException
    {
        int linhaSelecionada = this.jTListagemAcessorio.getSelectedRow();

        if(linhaSelecionada < 0){
            JOptionPane.showMessageDialog(null, "Selecione uma Linha", "Informação", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            this.controlAcessorio.setMarc(linhaSelecionada);

            try
            {
                this.controlAcessorio.deletarAcessorio();
                DefaultTableModel model = (DefaultTableModel)this.jTListagemAcessorio.getModel();
                model.removeRow(linhaSelecionada);
            }
            catch(SQLException erro)
            {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch(ClassNotFoundException erro)
            {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch(MinhaException erro)
            {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void alterarAcessorio(int linhaSelecionada) throws MinhaException, SQLException, ConexaoException
    {
        int quantColunas = this.jTListagemAcessorio.getColumnCount();

        if(linhaSelecionada < 0)
        {
            JOptionPane.showMessageDialog(null, "Selecione uma Linha", "Informação", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            this.controlAcessorio.setMarc(linhaSelecionada);

            Vector linha = new Vector();

            for(int i = 0; i < quantColunas; i++)
            {
                linha.add(i, this.jTListagemAcessorio.getModel().getValueAt(linhaSelecionada, i));
            }
            FrmAcessorioAlterar alterarAcessorio = new FrmAcessorioAlterar(linha);
            alterarAcessorio.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            alterarAcessorio.setModal(true);
            alterarAcessorio.setVisible(true);
            this.limparTable();
            this.preencherTable();
            alterarAcessorio.dispose();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListagemAcessorio = new javax.swing.JTable();
        jBInserir = new javax.swing.JButton();
        jBRemover = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Listagem Acessorio");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTListagemAcessorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Acessório"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTListagemAcessorio);
        jTListagemAcessorio.getColumnModel().getColumn(0).setResizable(false);
        jTListagemAcessorio.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jBInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus_symbol.png"))); // NOI18N
        jBInserir.setToolTipText("Inserir Reserva");
        jBInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInserirActionPerformed(evt);
            }
        });

        jBRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x_symbol.png"))); // NOI18N
        jBRemover.setToolTipText("Remover Reserva");
        jBRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRemoverActionPerformed(evt);
            }
        });

        jBAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alter.png"))); // NOI18N
        jBAtualizar.setToolTipText("Alterar Reserva");
        jBAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBInserir, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(jBAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jBInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRemoverActionPerformed
        // TODO add your handling code here:
        try {
            this.excluirAcessorio();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBRemoverActionPerformed

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        // TODO add your handling code here:
        int linha = this.jTListagemAcessorio.getSelectedRow();
        try {
            try {
                this.alterarAcessorio(linha);
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.limparTable();
            try {
                this.preencherTable();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MinhaException ex) {
            Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void jBInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInserirActionPerformed
        try {
            new FrmCadastroAcessorio().setVisible(true);
            this.limparTable();
            try {
                this.preencherTable();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MinhaException ex) {
            Logger.getLogger(FrmListagemAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBInserirActionPerformed

    /**
    * @param args the command line arguments
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmListagemReserva().setVisible(true);
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemReserva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtualizar;
    private javax.swing.JButton jBInserir;
    private javax.swing.JButton jBRemover;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListagemAcessorio;
    // End of variables declaration//GEN-END:variables

}