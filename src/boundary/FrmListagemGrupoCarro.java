

package boundary;

import control.ControladoraGrupoCarro;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;



public class FrmListagemGrupoCarro extends javax.swing.JDialog {

    
    private ControladoraGrupoCarro controladoraGrupoCarro = new  ControladoraGrupoCarro();

    public FrmListagemGrupoCarro() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.limparTabela();
        this.preencherTabela();
    }


    private void preencherTabela() throws ConexaoException{

        try {
            Vector linhasTabela = this.controladoraGrupoCarro.selecionarTodosGruposCarro();

            DefaultTableModel modelo = (DefaultTableModel) this.tabelaGrupoCarro.getModel();


            for(int i = 0; i < linhasTabela.size(); i++)
                modelo.insertRow(modelo.getRowCount(), (Vector) linhasTabela.get(i));

        }
        catch (MinhaException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        }
        catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        }
    }


    
    private void limparTabela(){

        DefaultTableModel modelo = (DefaultTableModel)this.tabelaGrupoCarro.getModel();
        int numLinhas = modelo.getRowCount();

        for(int i = 0; i < numLinhas; i++)
            modelo.removeRow(0);

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaGrupoCarro = new javax.swing.JTable();
        b_Inserir = new javax.swing.JButton();
        b_Alterar = new javax.swing.JButton();
        b_Remover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Grupos de Carros");

        tabelaGrupoCarro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome do Grupo", "Tipo de Carro", "Preço Diaria", "Preço Diaria Quilometrada", "Preço Cobertura", "Adicional Quilometro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaGrupoCarro);
        tabelaGrupoCarro.getColumnModel().getColumn(0).setMinWidth(50);
        tabelaGrupoCarro.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaGrupoCarro.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaGrupoCarro.getColumnModel().getColumn(3).setMinWidth(90);
        tabelaGrupoCarro.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabelaGrupoCarro.getColumnModel().getColumn(3).setMaxWidth(90);

        b_Inserir.setText("Inserir");
        b_Inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_InserirActionPerformed(evt);
            }
        });

        b_Alterar.setText("Alterar");
        b_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_AlterarActionPerformed(evt);
            }
        });

        b_Remover.setText("Remover");
        b_Remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_RemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_Inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Remover)
                    .addComponent(b_Alterar)
                    .addComponent(b_Inserir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
private void b_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_RemoverActionPerformed

    int linhaSelecionada = this.tabelaGrupoCarro.getSelectedRow();

        if(linhaSelecionada < 0)
            JOptionPane.showMessageDialog(this, "Selecione um registro para remover !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else{
            this.controladoraGrupoCarro.setMarc(linhaSelecionada);
            try
            {
                this.controladoraGrupoCarro.removerGrupoCarro();
                DefaultTableModel modelo = (DefaultTableModel) this.tabelaGrupoCarro.getModel();
                modelo.removeRow(linhaSelecionada);

            } catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemGrupoCarro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MinhaException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex)
            {
                int num = Integer.parseInt(ex.getSQLState());

                if(num == 23503)
                    JOptionPane.showMessageDialog(null, "Você não pode excluir este Grupo de Carro, \nPois existem registros associados a ele!", "Erro", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
}//GEN-LAST:event_b_RemoverActionPerformed




private void b_InserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_InserirActionPerformed


    JDialog janela = null;
        try {
            janela = new FrmCadastroGrupoCarro();
            janela.setVisible(true);
            this.limparTabela();
            this.preencherTabela();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemGrupoCarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}//GEN-LAST:event_b_InserirActionPerformed




private void b_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_AlterarActionPerformed

    int selecionada = this.tabelaGrupoCarro.getSelectedRow();

    if(selecionada < 0)
        JOptionPane.showMessageDialog(null, "Selecione um registro para fazer a Alteração !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    else{
        this.controladoraGrupoCarro.setMarc(selecionada);

        JDialog janela = null;
            try {
                janela = new FrmAtualizaGrupoCarro( this.controladoraGrupoCarro);
                janela.setVisible(true);
                this.limparTabela();
                this.preencherTabela();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemGrupoCarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    
}//GEN-LAST:event_b_AlterarActionPerformed


//
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmListagemGrupoCarro().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_Alterar;
    private javax.swing.JButton b_Inserir;
    private javax.swing.JButton b_Remover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaGrupoCarro;
    // End of variables declaration//GEN-END:variables

}
