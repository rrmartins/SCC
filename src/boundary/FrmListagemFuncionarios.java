
package boundary;

import control.ControladoraFuncionario;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;


public class FrmListagemFuncionarios extends javax.swing.JDialog {

    private ControladoraFuncionario controladoraFuncionario = new ControladoraFuncionario();
    


    public FrmListagemFuncionarios() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cbOrdenacao.setModel(new DefaultComboBoxModel(this.preencherComboOrdenacao()));
        this.limparTabela();
        this.montarTabela();
    }
    
    
    
    public Vector preencherComboOrdenacao(){
        
        Vector ordenacao = new Vector();
        
        for(int i = 0; i < this.tabelaFuncionarios.getColumnCount(); i++)
            ordenacao.addElement(this.tabelaFuncionarios.getColumnName(i));
        
        return ordenacao;
    }
    
    
    public void limparTabela(){
        
        DefaultTableModel modelo = (DefaultTableModel)this.tabelaFuncionarios.getModel();
        int numLinhas = modelo.getRowCount();
        for(int i = 0; i < numLinhas; i++)
            modelo.removeRow(0);
    }



    public void montarTabela() throws ConexaoException{
        try {
            Vector linhasTabela = this.controladoraFuncionario.obterLinhasFuncionarios();

            DefaultTableModel modelo = (DefaultTableModel) this.tabelaFuncionarios.getModel();


            for(int i = 0; i < linhasTabela.size(); i++)
                modelo.insertRow(modelo.getRowCount(), (Vector) linhasTabela.get(i));


        } catch (MinhaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bRemover = new javax.swing.JButton();
        bAlterar = new javax.swing.JButton();
        bInserir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbOrdenacao = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Funcionários");

        bRemover.setText("Remover");
        bRemover.setMaximumSize(new java.awt.Dimension(63, 63));
        bRemover.setMinimumSize(new java.awt.Dimension(63, 63));
        bRemover.setPreferredSize(null);
        bRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemoverActionPerformed(evt);
            }
        });

        bAlterar.setText("Alterar");
        bAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlterarActionPerformed(evt);
            }
        });

        bInserir.setText("Inserir");
        bInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInserirActionPerformed(evt);
            }
        });

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Nome", "CPF", "E-Mail", "Telefone", "Nascimento", "Cargo", "Logradouro", "N°", "Bairro", "Cidade", "UF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaFuncionarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaFuncionarios);
        tabelaFuncionarios.getColumnModel().getColumn(0).setMinWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(1).setMinWidth(110);
        tabelaFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabelaFuncionarios.getColumnModel().getColumn(1).setMaxWidth(110);
        tabelaFuncionarios.getColumnModel().getColumn(2).setMinWidth(80);
        tabelaFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabelaFuncionarios.getColumnModel().getColumn(2).setMaxWidth(80);
        tabelaFuncionarios.getColumnModel().getColumn(3).setMinWidth(130);
        tabelaFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabelaFuncionarios.getColumnModel().getColumn(3).setMaxWidth(130);
        tabelaFuncionarios.getColumnModel().getColumn(4).setMinWidth(70);
        tabelaFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabelaFuncionarios.getColumnModel().getColumn(4).setMaxWidth(70);
        tabelaFuncionarios.getColumnModel().getColumn(5).setMinWidth(70);
        tabelaFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabelaFuncionarios.getColumnModel().getColumn(5).setMaxWidth(70);
        tabelaFuncionarios.getColumnModel().getColumn(6).setMinWidth(72);
        tabelaFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(72);
        tabelaFuncionarios.getColumnModel().getColumn(6).setMaxWidth(72);
        tabelaFuncionarios.getColumnModel().getColumn(7).setMinWidth(120);
        tabelaFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(120);
        tabelaFuncionarios.getColumnModel().getColumn(7).setMaxWidth(120);
        tabelaFuncionarios.getColumnModel().getColumn(8).setMinWidth(40);
        tabelaFuncionarios.getColumnModel().getColumn(8).setPreferredWidth(40);
        tabelaFuncionarios.getColumnModel().getColumn(8).setMaxWidth(40);
        tabelaFuncionarios.getColumnModel().getColumn(9).setMinWidth(92);
        tabelaFuncionarios.getColumnModel().getColumn(9).setPreferredWidth(92);
        tabelaFuncionarios.getColumnModel().getColumn(9).setMaxWidth(92);
        tabelaFuncionarios.getColumnModel().getColumn(10).setMinWidth(125);
        tabelaFuncionarios.getColumnModel().getColumn(10).setPreferredWidth(125);
        tabelaFuncionarios.getColumnModel().getColumn(10).setMaxWidth(125);
        tabelaFuncionarios.getColumnModel().getColumn(11).setMinWidth(30);
        tabelaFuncionarios.getColumnModel().getColumn(11).setPreferredWidth(30);
        tabelaFuncionarios.getColumnModel().getColumn(11).setMaxWidth(30);

        jLabel1.setText("Listar Por:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbOrdenacao, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                        .addComponent(bInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(bAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(bRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAlterar)
                    .addComponent(bInserir)
                    .addComponent(jLabel1)
                    .addComponent(cbOrdenacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed

        int selecionada = this.tabelaFuncionarios.getSelectedRow();
            if(selecionada < 0)
                JOptionPane.showMessageDialog(null, "Selecione um registro para fazer a Alteração !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            else{
                this.controladoraFuncionario.setMarc(selecionada);

                JDialog janela = new FrmAtualizaFuncionario(null,true,this.controladoraFuncionario);
                janela.setVisible(true);
                this.limparTabela();
            try {
                this.montarTabela();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }//GEN-LAST:event_bAlterarActionPerformed



    
    private void bRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoverActionPerformed
        
        int selecionada = this.tabelaFuncionarios.getSelectedRow();
        if(selecionada < 0)
            JOptionPane.showMessageDialog(null, "Selecione um registro para Remover !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        else{
            this.controladoraFuncionario.setMarc(selecionada);

            try
            {
                try {
                    this.controladoraFuncionario.removerFuncionario();
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                DefaultTableModel modelo = (DefaultTableModel) this.tabelaFuncionarios.getModel();
                modelo.removeRow(selecionada);

            } catch (MinhaException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex)
            {
                int num = Integer.parseInt(ex.getSQLState());

                if(num == 23503)
                    JOptionPane.showMessageDialog(null, "Você não pode excluir este Cliente, \nPois existem registros associados a ele!", "Erro", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }//GEN-LAST:event_bRemoverActionPerformed

    private void bInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInserirActionPerformed

        JDialog janelaInsert = new FrmCadastroFuncionario();
        janelaInsert.setVisible(true);
        this.limparTabela();
        try {
            this.montarTabela();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bInserirActionPerformed

    
    
//
//    public static void main(String args[]) {
//
//        try {
//
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmListagemFuncionarios().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterar;
    private javax.swing.JButton bInserir;
    private javax.swing.JButton bRemover;
    private javax.swing.JComboBox cbOrdenacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaFuncionarios;
    // End of variables declaration//GEN-END:variables

}
