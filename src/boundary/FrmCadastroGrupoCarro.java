

package boundary;

import control.ControladoraAcessorio;
import control.ControladoraGrupoCarro;
import control.ControladoraTipoCarro;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import util.ConexaoException;
import util.MinhaException;



public class FrmCadastroGrupoCarro extends javax.swing.JDialog {

    protected ControladoraGrupoCarro controladoraGrupoCarro = new ControladoraGrupoCarro();
    protected ControladoraAcessorio controladoraAcessorio = new ControladoraAcessorio();
    private ControladoraTipoCarro controladoraTipoCarro = new ControladoraTipoCarro();
    
    
    
    /**
     *  Vetor de acessorios passado como referencia para a Tela Inserir
     *  Acessorios, pois retornará com os acessórios escolhidos que o Grupo Possui
    */
    Vector<Vector> acessoriosGrupo = new Vector();
    
    
    
    //Tipos de Carros existentes para cadastro de Grupos
    public void setarComponentesComboBox() throws ConexaoException{
        
        DefaultComboBoxModel tipoCarros = new DefaultComboBoxModel(this.buscarTiposDeCarros());
        
        this.jcb_TipoCarro.setModel(tipoCarros);
        
    }
    
    
    //Buscando os tipos de carro para listagem
    public Vector buscarTiposDeCarros() throws ConexaoException{

        Vector comboTipoCarro = new Vector();
        try {
            comboTipoCarro = this.controladoraTipoCarro.obterNomesTipoCarro();
            
        } catch (MinhaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return comboTipoCarro;
    }



     private void carregarAcessoriosEscolhidos() {

         for(int i = 0; i < this.jLAcessorios.getMaxSelectionIndex(); i++){
             this.jLAcessorios.remove(i);
         }

         DefaultListModel listModel = new DefaultListModel();
         
         for(int i = 0; i < this.acessoriosGrupo.size(); i++){
             listModel.addElement(this.acessoriosGrupo.get(i).get(1));
         }

         this.jLAcessorios.setModel(listModel);
    }


    
    @SuppressWarnings("unchecked")
    public FrmCadastroGrupoCarro(java.awt.Frame parent, boolean modal) throws ConexaoException {
        super(parent, modal);
        initComponents();
        this.setarComponentesComboBox();
        this.setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        l_precoDiaria = new javax.swing.JLabel();
        jtf_precoDiaria = new javax.swing.JTextField();
        jtf_PrecoDiariaQuilometrada = new javax.swing.JTextField();
        l_PrecoDiariaQuilometrada = new javax.swing.JLabel();
        l_nomeGrupo = new javax.swing.JLabel();
        l_TipoCarro = new javax.swing.JLabel();
        jcb_TipoCarro = new javax.swing.JComboBox();
        jtf_NomeGrupo = new javax.swing.JTextField();
        l_precoCobertura = new javax.swing.JLabel();
        jtfPrecoCobertura = new javax.swing.JTextField();
        jb_InserirAcessorio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLAcessorios = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        tf_AdicionalQuilometro = new javax.swing.JTextField();
        bConfirmar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Grupo de Carros");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        l_precoDiaria.setText("Diária");

        l_PrecoDiariaQuilometrada.setText("Diária Quilometrada");

        l_nomeGrupo.setText("Nome do Grupo");

        l_TipoCarro.setText("Tipo de Carro");

        jcb_TipoCarro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FrmCadastroGrupoCarro.this.itemStateChanged(evt);
            }
        });

        l_precoCobertura.setText("Preço de Cobertura");

        jb_InserirAcessorio.setText("Inserir");
        jb_InserirAcessorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_InserirAcessorioActionPerformed(evt);
            }
        });

        jLabel1.setText("Acessorios");

        jLAcessorios.setEnabled(false);
        jScrollPane1.setViewportView(jLAcessorios);

        jLabel2.setText("Adicional Quilometro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_precoDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_precoDiaria))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtf_PrecoDiariaQuilometrada)
                            .addComponent(l_PrecoDiariaQuilometrada))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_AdicionalQuilometro)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(l_nomeGrupo)
                    .addComponent(jtf_NomeGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jb_InserirAcessorio)
                        .addComponent(l_TipoCarro)
                        .addComponent(jcb_TipoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfPrecoCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l_precoCobertura)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_nomeGrupo)
                    .addComponent(l_TipoCarro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_NomeGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_TipoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(l_precoDiaria)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtf_precoDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(l_precoCobertura)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfPrecoCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(l_PrecoDiariaQuilometrada)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtf_PrecoDiariaQuilometrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_AdicionalQuilometro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jb_InserirAcessorio))
                .addContainerGap())
        );

        bConfirmar.setText("OK");
        bConfirmar.setMaximumSize(new java.awt.Dimension(80, 22));
        bConfirmar.setMinimumSize(new java.awt.Dimension(80, 22));
        bConfirmar.setPreferredSize(new java.awt.Dimension(80, 22));
        bConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmarActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.setMaximumSize(new java.awt.Dimension(80, 22));
        bCancelar.setMinimumSize(new java.awt.Dimension(80, 22));
        bCancelar.setPreferredSize(new java.awt.Dimension(80, 22));
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    @SuppressWarnings("unchecked")
    private void jb_InserirAcessorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_InserirAcessorioActionPerformed

        this.acessoriosGrupo.removeAllElements();

        JDialog janela = null;
        try {
            janela = new FrmInserirAcessorio(this.acessoriosGrupo, null, true, controladoraAcessorio);
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmCadastroGrupoCarro.class.getName()).log(Level.SEVERE, null, ex);
        }
        janela.setVisible(true);

        for(int i = 0; i < FrmInserirAcessorio.getAcessoriosEscolhidos().size(); i++){
            this.acessoriosGrupo.add(FrmInserirAcessorio.getAcessoriosEscolhidos().get(i));
        }
        this.carregarAcessoriosEscolhidos();

        
    }//GEN-LAST:event_jb_InserirAcessorioActionPerformed

    
    

    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarActionPerformed

            Vector grupo = this.criarGrupo();
            
            if(grupo != null){
             
                try {
                    this.controladoraGrupoCarro.inserirGrupoCarro(grupo, this.controladoraTipoCarro, this.acessoriosGrupo);
                    this.setVisible(false);
                }catch (ConexaoException ex) {
                    Logger.getLogger(FrmCadastroGrupoCarro.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MinhaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
       
    }//GEN-LAST:event_bConfirmarActionPerformed

private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed

    this.dispose();
    
}//GEN-LAST:event_bCancelarActionPerformed

private void itemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemStateChanged

    this.controladoraTipoCarro.setMarc(this.jcb_TipoCarro.getSelectedIndex());

}//GEN-LAST:event_itemStateChanged


    
//
//    public static void main(String args[]) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmCadastroGrupoCarro(null, true).setVisible(true);
//            }
//        });
//    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bConfirmar;
    private javax.swing.JList jLAcessorios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_InserirAcessorio;
    private javax.swing.JComboBox jcb_TipoCarro;
    private javax.swing.JTextField jtfPrecoCobertura;
    private javax.swing.JTextField jtf_NomeGrupo;
    private javax.swing.JTextField jtf_PrecoDiariaQuilometrada;
    private javax.swing.JTextField jtf_precoDiaria;
    private javax.swing.JLabel l_PrecoDiariaQuilometrada;
    private javax.swing.JLabel l_TipoCarro;
    private javax.swing.JLabel l_nomeGrupo;
    private javax.swing.JLabel l_precoCobertura;
    private javax.swing.JLabel l_precoDiaria;
    private javax.swing.JTextField tf_AdicionalQuilometro;
    // End of variables declaration//GEN-END:variables

    private Vector criarGrupo() {

        if(this.verificaCamposVazios()){

            Vector grupo = new Vector();

            grupo.add(this.jtf_NomeGrupo.getText());
            grupo.add(this.jtf_precoDiaria.getText());
            grupo.add(this.jtf_PrecoDiariaQuilometrada.getText());
            grupo.add(this.jtfPrecoCobertura.getText());
            grupo.add(this.tf_AdicionalQuilometro.getText());

            return grupo;

        }
        return null;
    }

    private boolean verificaCamposVazios() {

        if(!this.jtf_NomeGrupo.getText().isEmpty()){
            if(!this.jtf_precoDiaria.getText().isEmpty()){
                if(!this.jtf_PrecoDiariaQuilometrada.getText().isEmpty()){
                    if(!this.jtfPrecoCobertura.getText().isEmpty()){
                        if(!this.tf_AdicionalQuilometro.getText().isEmpty()){
                            if(this.jcb_TipoCarro.getSelectedIndex() >= 0){
                                return true;
                            }
                            else
                                JOptionPane.showMessageDialog(null, "É preciso escolher um tipo de Carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "É preciso indicar o valor Adicional do Quilometro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "É preciso preencher o campo Preço Cobertura !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo Preço Diaria Quilometrada !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Preço Diaria !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "É preciso preencher o campo Nome do grupo de carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);

        return false;
    }


}
