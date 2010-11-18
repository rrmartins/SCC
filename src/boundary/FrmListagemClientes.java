
package boundary;

import control.ControladoraCliente;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;


public class FrmListagemClientes extends javax.swing.JDialog {

    private ControladoraCliente controladoraCliente = new ControladoraCliente();



    public FrmListagemClientes() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cbOrdenacao.setModel(new DefaultComboBoxModel(this.preencherComboOrdenacao()));
        this.montarTabela();
    }



    public void limparTabela(){
        
        DefaultTableModel modelo = (DefaultTableModel)this.tabelaClientes.getModel();
        int numLinhas = modelo.getRowCount();
        for(int i = 0; i < numLinhas; i++)
            modelo.removeRow(0);
    }



    public void montarTabela() throws ConexaoException{
        try {
            Vector linhasTabela = this.controladoraCliente.obterLinhasClientes();

            DefaultTableModel modelo = (DefaultTableModel) this.tabelaClientes.getModel();


            for(int i = 0; i < linhasTabela.size(); i++)
                modelo.insertRow(modelo.getRowCount(), (Vector) linhasTabela.get(i));


        } catch (MinhaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    public Vector preencherComboOrdenacao(){
        
        Vector ordenacao = new Vector();
        
        for(int i = 0; i < this.tabelaClientes.getColumnCount(); i++)
            ordenacao.addElement(this.tabelaClientes.getColumnName(i));
        
        return ordenacao;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bRemover = new javax.swing.JButton();
        bAlterar = new javax.swing.JButton();
        bInserir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        lLista = new javax.swing.JLabel();
        cbOrdenacao = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Clientes");

        bRemover.setText("Remover");
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

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "E-Mail", "Telefone", "Nascimento", "Logradouro", "N°", "Bairro", "Cidade", "UF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaClientes);
        tabelaClientes.getColumnModel().getColumn(0).setMinWidth(50);
        tabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaClientes.getColumnModel().getColumn(1).setMinWidth(130);
        tabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabelaClientes.getColumnModel().getColumn(1).setMaxWidth(130);
        tabelaClientes.getColumnModel().getColumn(2).setMinWidth(90);
        tabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabelaClientes.getColumnModel().getColumn(2).setMaxWidth(90);
        tabelaClientes.getColumnModel().getColumn(3).setMinWidth(120);
        tabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(120);
        tabelaClientes.getColumnModel().getColumn(3).setMaxWidth(120);
        tabelaClientes.getColumnModel().getColumn(4).setMinWidth(70);
        tabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(70);
        tabelaClientes.getColumnModel().getColumn(4).setMaxWidth(70);
        tabelaClientes.getColumnModel().getColumn(5).setMinWidth(70);
        tabelaClientes.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabelaClientes.getColumnModel().getColumn(5).setMaxWidth(70);
        tabelaClientes.getColumnModel().getColumn(6).setMinWidth(120);
        tabelaClientes.getColumnModel().getColumn(6).setPreferredWidth(120);
        tabelaClientes.getColumnModel().getColumn(6).setMaxWidth(120);
        tabelaClientes.getColumnModel().getColumn(7).setMinWidth(40);
        tabelaClientes.getColumnModel().getColumn(7).setPreferredWidth(40);
        tabelaClientes.getColumnModel().getColumn(7).setMaxWidth(40);
        tabelaClientes.getColumnModel().getColumn(8).setMinWidth(90);
        tabelaClientes.getColumnModel().getColumn(8).setPreferredWidth(90);
        tabelaClientes.getColumnModel().getColumn(8).setMaxWidth(90);
        tabelaClientes.getColumnModel().getColumn(9).setMinWidth(100);
        tabelaClientes.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabelaClientes.getColumnModel().getColumn(9).setMaxWidth(100);
        tabelaClientes.getColumnModel().getColumn(10).setMinWidth(40);
        tabelaClientes.getColumnModel().getColumn(10).setPreferredWidth(40);
        tabelaClientes.getColumnModel().getColumn(10).setMaxWidth(40);

        lLista.setText("Listar Por:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbOrdenacao, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                        .addComponent(bInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lLista)
                    .addComponent(cbOrdenacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bRemover)
                    .addComponent(bAlterar)
                    .addComponent(bInserir))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
private void bInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInserirActionPerformed

    //JDialog janelaInsert = new FrmCadastroCliente(this, true);
    JDialog janelaInsert = new FrmCadastroCliente(null,true);
    janelaInsert.setVisible(true);
    this.limparTabela();
        try {
            this.montarTabela();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_bInserirActionPerformed



private void bAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlterarActionPerformed

    int selecionada = this.tabelaClientes.getSelectedRow();
    if(selecionada < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um registro para fazer a Alteração !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    else{
        this.controladoraCliente.setMarc(selecionada);

        JDialog janela = new FrmAtualizaCliente(null,true,controladoraCliente);
        janela.setVisible(true);
        this.limparTabela();
            try {
                this.montarTabela();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}//GEN-LAST:event_bAlterarActionPerformed



private void bRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemoverActionPerformed

    int selecionada = this.tabelaClientes.getSelectedRow();
    if(selecionada < 0)
        JOptionPane.showMessageDialog(null, "Selecione um registro para Remover !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    else{
        this.controladoraCliente.setMarc(selecionada);
            try
            {
                try {
                    this.controladoraCliente.removerCliente();
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                DefaultTableModel modelo = (DefaultTableModel) this.tabelaClientes.getModel();
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


//
//    public static void main(String args[]) {
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
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmListagemClientes().setVisible(true);
//            }
//        });
//    }

    

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlterar;
    private javax.swing.JButton bInserir;
    private javax.swing.JButton bRemover;
    private javax.swing.JComboBox cbOrdenacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lLista;
    private javax.swing.JTable tabelaClientes;
    // End of variables declaration//GEN-END:variables

}
