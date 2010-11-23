

package boundary;

import control.ControladoraCarros;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;


public class FrmListagemCarros extends javax.swing.JDialog {

    private ControladoraCarros controladoraCarros = new ControladoraCarros();

    
    public FrmListagemCarros() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.limparTabela();
        this.preencherTabela();
    }



        private void preencherTabela() throws ConexaoException{

        try {
            Vector linhasTabela = this.controladoraCarros.obterTodosCarros();

            DefaultTableModel modelo = (DefaultTableModel) this.tabelaTodosCarros.getModel();


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

        DefaultTableModel modelo = (DefaultTableModel)this.tabelaTodosCarros.getModel();
        int numLinhas = modelo.getRowCount();

        for(int i = 0; i < numLinhas; i++)
            modelo.removeRow(0);

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTodosCarros = new javax.swing.JTable();
        b_Inserir = new javax.swing.JButton();
        b_Alterar = new javax.swing.JButton();
        b_Remover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Listagem de Carros");

        tabelaTodosCarros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Modelo", "Marca", "Ano", "Grupo de Carro", "Placa", "Chassi", "Disponibilidade", "Quilometragem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaTodosCarros);
        tabelaTodosCarros.getColumnModel().getColumn(0).setMinWidth(60);
        tabelaTodosCarros.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabelaTodosCarros.getColumnModel().getColumn(0).setMaxWidth(60);
        tabelaTodosCarros.getColumnModel().getColumn(1).setMinWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(2).setMinWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(2).setMaxWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(3).setMinWidth(50);
        tabelaTodosCarros.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabelaTodosCarros.getColumnModel().getColumn(3).setMaxWidth(50);
        tabelaTodosCarros.getColumnModel().getColumn(4).setMinWidth(90);
        tabelaTodosCarros.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabelaTodosCarros.getColumnModel().getColumn(4).setMaxWidth(90);
        tabelaTodosCarros.getColumnModel().getColumn(5).setMinWidth(70);
        tabelaTodosCarros.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabelaTodosCarros.getColumnModel().getColumn(5).setMaxWidth(70);
        tabelaTodosCarros.getColumnModel().getColumn(6).setMinWidth(110);
        tabelaTodosCarros.getColumnModel().getColumn(6).setPreferredWidth(110);
        tabelaTodosCarros.getColumnModel().getColumn(6).setMaxWidth(110);
        tabelaTodosCarros.getColumnModel().getColumn(7).setMinWidth(90);
        tabelaTodosCarros.getColumnModel().getColumn(7).setPreferredWidth(90);
        tabelaTodosCarros.getColumnModel().getColumn(7).setMaxWidth(90);
        tabelaTodosCarros.getColumnModel().getColumn(8).setMinWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabelaTodosCarros.getColumnModel().getColumn(8).setMaxWidth(100);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_Inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Remover)
                    .addComponent(b_Alterar)
                    .addComponent(b_Inserir))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_InserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_InserirActionPerformed
        try {
            new FrmCadastroCarro().setVisible(true);
            this.limparTabela();
            this.preencherTabela();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemCarros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_b_InserirActionPerformed

    private void b_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_AlterarActionPerformed

        int linhaSelecionada = this.tabelaTodosCarros.getSelectedRow();

        if(linhaSelecionada < 0)
            JOptionPane.showMessageDialog(this, "É necessário selecionar um registro para atualizar", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else{
            this.controladoraCarros.setMarc(linhaSelecionada);

            JDialog janela = null;
            try {
                janela = new FrmAtualizaCarro( this.controladoraCarros);
                janela.setVisible(true);
                this.limparTabela();
                this.preencherTabela();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemCarros.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_b_AlterarActionPerformed

    private void b_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_RemoverActionPerformed

        int linhaSelecionada = this.tabelaTodosCarros.getSelectedRow();

        if(linhaSelecionada < 0)
            JOptionPane.showMessageDialog(this, "É necessário selecionar um registro para remover", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else{

            this.controladoraCarros.setMarc(linhaSelecionada);

            try{
                this.controladoraCarros.removerCarro();
                DefaultTableModel modelo = (DefaultTableModel) this.tabelaTodosCarros.getModel();
                modelo.removeRow(linhaSelecionada);
            }
            catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemCarros.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (MinhaException erro){
                JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            catch (SQLException erro){

                int num = Integer.parseInt(erro.getSQLState());

                if(num == 23503)
                    JOptionPane.showMessageDialog(null, "Você não pode excluir este Carro, \nPois existem registros associados a ele!", "Erro", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_b_RemoverActionPerformed




//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmListagemCarros().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_Alterar;
    private javax.swing.JButton b_Inserir;
    private javax.swing.JButton b_Remover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaTodosCarros;
    // End of variables declaration//GEN-END:variables



}
