
package boundary;

import control.ControladoraFuncionario;
import control.ControladoraOficina;
import control.ControladoraRevisao;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.ConexaoException;
import util.MinhaException;



public class FrmListagemRevisao extends javax.swing.JDialog {


    private ControladoraRevisao controladoraRevisao = new ControladoraRevisao();
    private ControladoraOficina controladoraOficina = new ControladoraOficina();
    private ControladoraFuncionario controladoraFuncionario = new ControladoraFuncionario();


    public FrmListagemRevisao() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.limparTabela();
        this.preencherTabela();
    }



    private void preencherTabela() throws ConexaoException{

        try {
            Vector linhasTabela = this.controladoraRevisao.obterLinhasRevisao();

            DefaultTableModel modelo = (DefaultTableModel) this.tabelaRevisao.getModel();


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

        DefaultTableModel modelo = (DefaultTableModel)this.tabelaRevisao.getModel();
        int numLinhas = modelo.getRowCount();

        for(int i = 0; i < numLinhas; i++)
            modelo.removeRow(0);
        
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaRevisao = new javax.swing.JTable();
        b_Alterar = new javax.swing.JButton();
        b_Remover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Revisões");

        tabelaRevisao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Locacao", "Placa", "Entrega", "Responsavel", "Descricao", "Valor", "Data de Entrada", "Data de Saida"
            }
        ));
        jScrollPane1.setViewportView(tabelaRevisao);
        tabelaRevisao.getColumnModel().getColumn(0).setMinWidth(60);
        tabelaRevisao.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabelaRevisao.getColumnModel().getColumn(0).setMaxWidth(60);
        tabelaRevisao.getColumnModel().getColumn(1).setMinWidth(100);
        tabelaRevisao.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabelaRevisao.getColumnModel().getColumn(1).setMaxWidth(100);
        tabelaRevisao.getColumnModel().getColumn(2).setMinWidth(70);
        tabelaRevisao.getColumnModel().getColumn(2).setPreferredWidth(70);
        tabelaRevisao.getColumnModel().getColumn(2).setMaxWidth(70);
        tabelaRevisao.getColumnModel().getColumn(3).setMinWidth(60);
        tabelaRevisao.getColumnModel().getColumn(3).setPreferredWidth(60);
        tabelaRevisao.getColumnModel().getColumn(3).setMaxWidth(60);
        tabelaRevisao.getColumnModel().getColumn(4).setMinWidth(100);
        tabelaRevisao.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabelaRevisao.getColumnModel().getColumn(4).setMaxWidth(100);
        tabelaRevisao.getColumnModel().getColumn(5).setMinWidth(160);
        tabelaRevisao.getColumnModel().getColumn(5).setPreferredWidth(160);
        tabelaRevisao.getColumnModel().getColumn(5).setMaxWidth(160);
        tabelaRevisao.getColumnModel().getColumn(6).setMinWidth(80);
        tabelaRevisao.getColumnModel().getColumn(6).setPreferredWidth(80);
        tabelaRevisao.getColumnModel().getColumn(6).setMaxWidth(80);
        tabelaRevisao.getColumnModel().getColumn(7).setMinWidth(110);
        tabelaRevisao.getColumnModel().getColumn(7).setPreferredWidth(110);
        tabelaRevisao.getColumnModel().getColumn(7).setMaxWidth(110);
        tabelaRevisao.getColumnModel().getColumn(8).setMinWidth(110);
        tabelaRevisao.getColumnModel().getColumn(8).setPreferredWidth(110);
        tabelaRevisao.getColumnModel().getColumn(8).setMaxWidth(110);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(b_Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Remover)
                    .addComponent(b_Alterar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    @SuppressWarnings("unchecked")
    private void b_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_AlterarActionPerformed

        int linhaSelecionada = this.tabelaRevisao.getSelectedRow();
        int quantLinhas = this.tabelaRevisao.getColumnCount();

        if(linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para alterar !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            this.controladoraRevisao.setMarc(linhaSelecionada);

            Vector linha = new Vector();

            for(int i = 0; i < quantLinhas; i++)
            {
                linha.add(i, this.tabelaRevisao.getModel().getValueAt(linhaSelecionada, i));
            }
            this.controladoraRevisao.setMarc(linhaSelecionada);
            JDialog janela;
            try {
                try {
                    janela = new FrmRevisaoAlterar(linha);
                    janela.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmListagemRevisao.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.limparTabela();
                this.preencherTabela();
            } catch (MinhaException ex) {
                Logger.getLogger(FrmListagemRevisao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FrmListagemRevisao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmListagemRevisao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }//GEN-LAST:event_b_AlterarActionPerformed



    
    private void b_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_RemoverActionPerformed

        int linhaSelecionada = this.tabelaRevisao.getSelectedRow();

        if(linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro para remover !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            this.controladoraRevisao.setMarc(linhaSelecionada);
            try
            {
                this.controladoraRevisao.removerRevisao();
                DefaultTableModel modelo = (DefaultTableModel) this.tabelaRevisao.getModel();
                modelo.removeRow(linhaSelecionada);

            } catch (ConexaoException ex) {
                    Logger.getLogger(FrmListagemRevisao.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (MinhaException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex)
            {
                int num = Integer.parseInt(ex.getSQLState());

                if(num == 23503)
                    JOptionPane.showMessageDialog(null, "Você não pode excluir esta Revisão, \nPois existem registros associados a ele!", "Erro", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_b_RemoverActionPerformed


//
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmListagemRevisao().setVisible(true);
//            }
//        });
//    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_Alterar;
    private javax.swing.JButton b_Remover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaRevisao;
    // End of variables declaration//GEN-END:variables

}
