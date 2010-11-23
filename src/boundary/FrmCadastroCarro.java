
package boundary;

import control.ControladoraCarros;
import control.ControladoraGrupoCarro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;



public class FrmCadastroCarro extends javax.swing.JDialog implements ActionListener{

    protected ControladoraGrupoCarro controladoraGrupoCarro = new ControladoraGrupoCarro();
    protected ControladoraCarros controladoraCarro = new ControladoraCarros();
    Vector gc = new Vector();
    DefaultComboBoxModel gcarro = new DefaultComboBoxModel();
    
    
    public FrmCadastroCarro() throws ConexaoException {
        this.setModal(true);
        initComponents();
        this.setLocationRelativeTo(null);
        gc = this.carregaGrupos();
        for (int i = 0; i < gc.size(); i++){
            gcarro.addElement(gc.elementAt(i));
        }
        cb_grupoCarro.setModel(gcarro);
        this.b_Confirmar.addActionListener(this);
        this.b_Cancelar.addActionListener(this);

    }

    private Vector carregaGrupos() throws ConexaoException {
        
        Vector comboGrupos = new Vector();

        try{
            comboGrupos = this.controladoraGrupoCarro.obterLinhasGrupoCarro();
        }
        catch(MinhaException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return comboGrupos;
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_Disponivel = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        l_modelo = new javax.swing.JLabel();
        l_placa = new javax.swing.JLabel();
        tf_Modelo = new javax.swing.JTextField();
        l_grupoCarro = new javax.swing.JLabel();
        l_chassi = new javax.swing.JLabel();
        cb_grupoCarro = new javax.swing.JComboBox();
        tf_Chassi = new javax.swing.JTextField();
        l_quilometragem = new javax.swing.JLabel();
        tf_Quilometragem = new javax.swing.JTextField();
        ft_Placa = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_Marca = new javax.swing.JTextField();
        l_disponibilidade = new javax.swing.JLabel();
        rb_Sim = new javax.swing.JRadioButton();
        rb_Nao = new javax.swing.JRadioButton();
        cbAno = new javax.swing.JComboBox();
        b_Confirmar = new javax.swing.JButton();
        b_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Carro");
        setResizable(false);

        panelPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        l_modelo.setText("Modelo");

        l_placa.setText("Placa");

        l_grupoCarro.setText("Grupo do Carro");

        l_chassi.setText("Chassi");

        cb_grupoCarro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FrmCadastroCarro.this.itemStateChanged(evt);
            }
        });

        l_quilometragem.setText("Quilometragem");

        try {
            ft_Placa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("***-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("Marca");

        jLabel2.setText("Ano");

        l_disponibilidade.setText("Disponivel");

        bg_Disponivel.add(rb_Sim);
        rb_Sim.setSelected(true);
        rb_Sim.setText("Sim");

        bg_Disponivel.add(rb_Nao);
        rb_Nao.setText("Não");

        cbAno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tf_Modelo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(l_chassi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tf_Chassi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addComponent(cb_grupoCarro, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(l_modelo)
                    .addComponent(l_grupoCarro))
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(tf_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(l_placa)
                                    .addComponent(ft_Placa, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(tf_Quilometragem)
                                    .addComponent(l_quilometragem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbAno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(l_disponibilidade)))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(rb_Sim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_Nao)))
                .addGap(173, 173, 173))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32)
                        .addComponent(l_quilometragem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Quilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(l_modelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_chassi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Chassi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_placa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ft_Placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_grupoCarro)
                    .addComponent(l_disponibilidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_grupoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_Sim)
                    .addComponent(rb_Nao))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        b_Confirmar.setText("Confirmar");

        b_Cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b_Cancelar)
                        .addGap(18, 18, 18)
                        .addComponent(b_Confirmar))
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Confirmar)
                    .addComponent(b_Cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public boolean verificaCampos(){

        if(!this.tf_Modelo.getText().isEmpty()){
            if(!this.tf_Marca.getText().isEmpty()){
                if(!this.ft_Placa.getText().isEmpty()){
                    if(!this.tf_Chassi.getText().isEmpty()){
                        if(!this.tf_Quilometragem.getText().isEmpty()){
                            if(this.cb_grupoCarro.getSelectedIndex() >= 0){
                                return true;
                            }
                            else
                                JOptionPane.showMessageDialog(null, "É preciso escolher um grupo para o carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "É preciso indicar a quilometragem do carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "É preciso preencher o numero Chassi do carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "É preciso preencher a placa do carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "É preciso indicar a marca do carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "É preciso indicar o modelo do carro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);

        return false;
    }




    public Vector montaObjeto(){

        Vector novo = new Vector();

        novo.add(this.tf_Modelo.getText());
        novo.add(this.tf_Marca.getText());
        novo.add(this.ft_Placa.getText());
        novo.add(this.tf_Chassi.getText());
        novo.add(this.tf_Quilometragem.getText());
        novo.add(Integer.parseInt(this.cbAno.getSelectedItem().toString()));

        if(this.rb_Sim.isSelected())
            novo.add("True");
        else
            novo.add("False");

        return novo;
    }
    
    
    
    private void itemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemStateChanged

        this.controladoraGrupoCarro.setMarc(this.cb_grupoCarro.getSelectedIndex());
    }//GEN-LAST:event_itemStateChanged


    

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton b_Cancelar;
    protected javax.swing.JButton b_Confirmar;
    private javax.swing.ButtonGroup bg_Disponivel;
    protected javax.swing.JComboBox cbAno;
    protected javax.swing.JComboBox cb_grupoCarro;
    protected javax.swing.JFormattedTextField ft_Placa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel l_chassi;
    private javax.swing.JLabel l_disponibilidade;
    private javax.swing.JLabel l_grupoCarro;
    private javax.swing.JLabel l_modelo;
    private javax.swing.JLabel l_placa;
    private javax.swing.JLabel l_quilometragem;
    private javax.swing.JPanel panelPrincipal;
    protected javax.swing.JRadioButton rb_Nao;
    protected javax.swing.JRadioButton rb_Sim;
    protected javax.swing.JTextField tf_Chassi;
    protected javax.swing.JTextField tf_Marca;
    protected javax.swing.JTextField tf_Modelo;
    protected javax.swing.JTextField tf_Quilometragem;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.b_Confirmar){

            if(this.verificaCampos()){

                Vector carroAtualizacao = this.montaObjeto();

                try {
                    try {
                        this.controladoraCarro.inserirCarro(carroAtualizacao, this.controladoraGrupoCarro);
                    } catch (ConexaoException ex) {
                        Logger.getLogger(FrmCadastroCarro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                catch (SQLException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                catch (MinhaException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    this.setVisible(false);
                }
            }
        }
        else if(e.getSource() == this.b_Cancelar){

            this.dispose();
        }
    }

}
