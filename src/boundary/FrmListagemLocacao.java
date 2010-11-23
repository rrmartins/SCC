

package boundary;

import control.ControladoraLocacao;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;



public class FrmListagemLocacao extends javax.swing.JDialog {


    protected ControladoraLocacao controladoraLocacao = new ControladoraLocacao();


    
    public FrmListagemLocacao() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.limparTabela();
        this.preencherTabela();
    }



    private void preencherTabela() throws ConexaoException{

        try {
            Vector linhasTabela = this.controladoraLocacao.obterLinhasLocacao();

            DefaultTableModel modelo = (DefaultTableModel) this.tabelaLocacao.getModel();


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

        DefaultTableModel modelo = (DefaultTableModel)this.tabelaLocacao.getModel();
        int numLinhas = modelo.getRowCount();

        for(int i = 0; i < numLinhas; i++)
            modelo.removeRow(0);

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaLocacao = new javax.swing.JTable();
        b_Inserir = new javax.swing.JButton();
        b_Alterar = new javax.swing.JButton();
        b_Remover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Locações");

        tabelaLocacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cliente", "Carro", "Placa", "Data Locação", "Hora Locação", "Data Locação", "Hora Locação", "KM Inicial", "KM Prevista", "Cobertura", "Valor Previsto"
            }
        ));
        jScrollPane1.setViewportView(tabelaLocacao);
        tabelaLocacao.getColumnModel().getColumn(0).setMinWidth(50);
        tabelaLocacao.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaLocacao.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaLocacao.getColumnModel().getColumn(3).setMinWidth(50);
        tabelaLocacao.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabelaLocacao.getColumnModel().getColumn(3).setMaxWidth(50);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(b_Inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(b_Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(b_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Inserir)
                    .addComponent(b_Alterar)
                    .addComponent(b_Remover))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    private void b_InserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_InserirActionPerformed
                
        JDialog janela = null;
        try {
            janela = new FrmRegistrarLocacao();
            janela.setVisible(true);
            this.limparTabela();
            this.preencherTabela();
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmListagemLocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_b_InserirActionPerformed


    
    private void b_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_AlterarActionPerformed

        int selecionada = this.tabelaLocacao.getSelectedRow();
        if(selecionada < 0)
            JOptionPane.showMessageDialog(this, "Selecione um registro para alterar !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else{
            this.controladoraLocacao.setMarc(selecionada);

            JDialog janela = null;
            try {
                janela = new FrmAtualizaLocacao(this.controladoraLocacao);
                janela.setVisible(true);
                this.limparTabela();
                this.preencherTabela();
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemLocacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
          
    }//GEN-LAST:event_b_AlterarActionPerformed

    private void b_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_RemoverActionPerformed

        int selecionada = this.tabelaLocacao.getSelectedRow();
        if(selecionada < 0)
            JOptionPane.showMessageDialog(this, "Selecione um registro para remover !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else{
            this.controladoraLocacao.setMarc(selecionada);
            try
            {
                this.controladoraLocacao.removerLocacao();
                DefaultTableModel modelo = (DefaultTableModel) this.tabelaLocacao.getModel();
                modelo.removeRow(selecionada);

            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemLocacao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MinhaException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex)
            {
                int num = Integer.parseInt(ex.getSQLState());

                if(num == 23503)
                    JOptionPane.showMessageDialog(null, "Você não pode excluir esta Locação, \nPois existem registros associados a ele!", "Erro", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_b_RemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_Alterar;
    private javax.swing.JButton b_Inserir;
    private javax.swing.JButton b_Remover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaLocacao;
    // End of variables declaration//GEN-END:variables

}
