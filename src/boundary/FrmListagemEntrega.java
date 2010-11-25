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

import control.ControladoraEntrega;
import control.ControladoraFuncionario;
import control.ControladoraOficina;
import control.ControladoraRevisao;
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
public class FrmListagemEntrega extends javax.swing.JDialog {

    ControladoraEntrega controlEntrega;
    ControladoraFuncionario controlFunc;
    ControladoraOficina controlOf;
    ControladoraRevisao controlRevisao;
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
     * @throws ConexaoException
     */
    public FrmListagemEntrega(Vector userTipo, Vector user) throws ConexaoException {
        initComponents();
        usuarioTipo = userTipo;
        usuario     = user;
        this.setLocationRelativeTo(null);
        this.controlEntrega = new ControladoraEntrega();
        this.controlFunc = new ControladoraFuncionario();
        this.controlOf   = new ControladoraOficina();
        this.controlRevisao = new ControladoraRevisao();

        this.preencherTable();
    }
    
    
    protected void limparTable()
    {
        DefaultTableModel model = (DefaultTableModel) this.jTListagemEntrega.getModel();
        int numeroLinha = model.getRowCount();

        for(int i = 0; i < numeroLinha; i++)
        {
            model.removeRow(0);
        }
    }

    protected void preencherTable() throws ConexaoException
    {
        try{
            String texto = this.jCBCampo.getSelectedItem().toString();
            Vector entregaTabela = this.controlEntrega.obterLinhasEntrega(texto);
            DefaultTableModel model = (DefaultTableModel)this.jTListagemEntrega.getModel();

            for(int i = 0; i < entregaTabela.size(); i++)
            {
                model.insertRow(model.getRowCount(), (Vector)entregaTabela.get(i));
            }
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

    protected void excluirEntrega() throws ConexaoException
    {
        int linhaSelecionada = this.jTListagemEntrega.getSelectedRow();

        if(linhaSelecionada < 0){
            JOptionPane.showMessageDialog(null, "Selecione uma Linha", "Informação", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            this.controlEntrega.setMarc(linhaSelecionada);

            try
            {
                this.controlEntrega.deletarEntrega();
                DefaultTableModel model = (DefaultTableModel)this.jTListagemEntrega.getModel();
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

    protected void alterarEntrega(int linhaSelecionada) throws ConexaoException, SQLException, MinhaException
    {
        int quantColunas = this.jTListagemEntrega.getColumnCount();

            if(linhaSelecionada < 0)
            {
                JOptionPane.showMessageDialog(null, "Selecione uma Linha", "Informação", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                this.controlEntrega.setMarc(linhaSelecionada);

                Vector linha = new Vector();

                for(int i = 0; i < quantColunas; i++)
                {
                    linha.add(i, this.jTListagemEntrega.getModel().getValueAt(linhaSelecionada, i));
                }
                FrmRegistrarEntregaAlterar alterarEntrega = new FrmRegistrarEntregaAlterar(this.controlEntrega, linha, usuarioTipo, usuario);
                alterarEntrega.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                alterarEntrega.setModal(true);
                alterarEntrega.setVisible(true);
                this.limparTable();
                this.preencherTable();
                alterarEntrega.dispose();
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
        jTListagemEntrega = new javax.swing.JTable();
        jBInserir = new javax.swing.JButton();
        jBRemover = new javax.swing.JButton();
        jBAtualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCBCampo = new javax.swing.JComboBox();
        jBFazerRevisao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Listagem Entrega");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTListagemEntrega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Cod Locacao", "KM Final", "Data Entrega", "Hora Entrega", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTListagemEntrega);
        jTListagemEntrega.getColumnModel().getColumn(0).setResizable(false);
        jTListagemEntrega.getColumnModel().getColumn(1).setResizable(false);
        jTListagemEntrega.getColumnModel().getColumn(2).setResizable(false);
        jTListagemEntrega.getColumnModel().getColumn(3).setResizable(false);
        jTListagemEntrega.getColumnModel().getColumn(4).setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBInserir.setText("Inserir");
        jBInserir.setToolTipText("Inserir Reserva");
        jBInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInserirActionPerformed(evt);
            }
        });

        jBRemover.setText("Remover");
        jBRemover.setToolTipText("Remover Reserva");
        jBRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRemoverActionPerformed(evt);
            }
        });

        jBAtualizar.setText("Alterar");
        jBAtualizar.setToolTipText("Alterar Reserva");
        jBAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Listar Por:");

        jCBCampo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Cod Locacao", "KM Final", "Data Entrega", "Hr Entrega", "Valor Total" }));
        jCBCampo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCBCampoFocusLost(evt);
            }
        });

        jBFazerRevisao.setText("Fazer Revisão...");
        jBFazerRevisao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFazerRevisaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(289, 289, 289)
                        .addComponent(jBFazerRevisao)
                        .addGap(18, 18, 18)
                        .addComponent(jBInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCBCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBRemover)
                    .addComponent(jBAtualizar)
                    .addComponent(jBInserir)
                    .addComponent(jBFazerRevisao))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBCampoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCBCampoFocusLost

        this.limparTable();
        try {
            this.preencherTable();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jCBCampoFocusLost

    private void jBRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRemoverActionPerformed
        // TODO add your handling code here:
        try {
            this.excluirEntrega();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBRemoverActionPerformed

    private void jBAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarActionPerformed
        // TODO add your handling code here:
        int linha = this.jTListagemEntrega.getSelectedRow();
        try {
            this.alterarEntrega(linha);
            this.limparTable();
            this.preencherTable();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MinhaException ex) {
                Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBAtualizarActionPerformed

    private void jBInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInserirActionPerformed
        try {
            new FrmRegistrarEntrega(usuarioTipo, usuario).setVisible(true);
            this.limparTable();
            this.preencherTable();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBInserirActionPerformed

    private void jBFazerRevisaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFazerRevisaoActionPerformed
        // TODO add your handling code here:
        int linhaSelecionada = this.jTListagemEntrega.getSelectedRow();
        int quantColunas = this.jTListagemEntrega.getColumnCount();

            if(linhaSelecionada < 0 )
            {
                JOptionPane.showMessageDialog(null, "Selecione apenas uma Linha", "Informação", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                this.controlEntrega.setMarc(linhaSelecionada);

                Vector linha = new Vector();

                for(int i = 0; i < quantColunas; i++)
                {
                    linha.add(i, this.jTListagemEntrega.getModel().getValueAt(linhaSelecionada, i));
                }
                FrmRevisao revisao = null;
                try {
                    revisao = new FrmRevisao(controlFunc, controlOf, controlRevisao, linha);
                    revisao.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    revisao.setModal(true);
                    revisao.setVisible(true);
                    this.limparTable();
                    this.preencherTable();
                } catch (MinhaException ex) {
                    Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemEntrega.class.getName()).log(Level.SEVERE, null, ex);
                }

                revisao.dispose();
            }
    }//GEN-LAST:event_jBFazerRevisaoActionPerformed

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
    private javax.swing.JButton jBFazerRevisao;
    private javax.swing.JButton jBInserir;
    private javax.swing.JButton jBRemover;
    private javax.swing.JComboBox jCBCampo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTListagemEntrega;
    // End of variables declaration//GEN-END:variables

}
