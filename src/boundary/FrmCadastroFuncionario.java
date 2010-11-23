

package boundary;

import control.ControladoraCidade;
import control.ControladoraFuncionario;
import control.ControladoraUF;
import domain.UF;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;
import util.Validador;




public class FrmCadastroFuncionario extends javax.swing.JDialog implements ActionListener, ItemListener, FocusListener {

    private ControladoraUF controladoraUF = new ControladoraUF();
    protected ControladoraCidade controladoraCidade = new ControladoraCidade();
    private Validador valida = new Validador();



    public Vector ufCombo(){

        Vector comboUF = new Vector();

        try{
           comboUF = this.controladoraUF.obterLinhasUF();
        }
        catch(MinhaException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        catch (ConexaoException ex) {
            Logger.getLogger(FrmCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comboUF;
    }



    public Vector cidadesCombo() throws ConexaoException{

        Vector comboCidades = new Vector();
        
        this.controladoraUF.setMarc(this.cb_Uf.getSelectedIndex());
        UF uf = new UF();
        uf = this.controladoraUF.getUf().get(this.controladoraUF.getMarc());

        try{
            comboCidades = this.controladoraCidade.obterLinhasCidades(uf);
        }
        catch(MinhaException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
        return comboCidades;
    }



    public boolean verificaCamposVazios(){

        if(!this.tf_Nome.getText().isEmpty())
            {
                if(!this.ft_Cpf.getText().isEmpty())
                {
                    if(!this.ft_Nasc.getText().isEmpty())
                    {
                        if(!this.tf_Endereco.getText().isEmpty())
                        {
                            if(!this.tf_Numero.getText().isEmpty())
                            {
                                if(!this.tf_Bairro.getText().isEmpty())
                                {
                                    if(this.cb_Uf.getSelectedIndex() >= 0)
                                    {
                                        if(this.cb_Cidade.getSelectedIndex() >= 0)
                                        {
                                            if(!this.tf_Email.getText().isEmpty())
                                            {
                                                if(!this.ft_Tel.getText().isEmpty())
                                                {
                                                    if(!this.tf_Cargo.getText().isEmpty())
                                                    {
                                                        if(!this.tf_Login.getText().isEmpty())
                                                        {
                                                            if(this.pf_Senha.getPassword().length > 0)
                                                            {
                                                                 return true;
                                                            }
                                                             else
                                                                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Senha !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                        else
                                                            JOptionPane.showMessageDialog(null, "É preciso preencher o campo Login !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                    else
                                                        JOptionPane.showMessageDialog(null, "É preciso preencher o campo Cargo !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                                }
                                                else
                                                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo Telefone !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            else
                                                JOptionPane.showMessageDialog(null, "É preciso preencher o campo E-mail !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        else
                                            JOptionPane.showMessageDialog(null, "É preciso selecionar uma Cidade !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    else
                                        JOptionPane.showMessageDialog(null, "É preciso preencher um Estado !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo Bairro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Número !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "É preciso preencher o campo Endereço !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "É preciso preencher o campo Data de Nascimento !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo CPF !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Nome !", "Atenção", JOptionPane.INFORMATION_MESSAGE);

            return false;
    }


    public Vector criarFuncionario(){

        Vector novoFuncionario = new Vector();

        String senha = String.copyValueOf(this.pf_Senha.getPassword());
        String cpf = this.valida.tiraPontosCPF(this.ft_Cpf.getText());
        String tel = this.valida.tiraPontosTelefone(this.ft_Tel.getText());
        this.controladoraCidade.setMarc(this.cb_Cidade.getSelectedIndex());

        novoFuncionario.addElement(this.tf_Nome.getText());
        novoFuncionario.addElement(cpf);
        novoFuncionario.addElement(this.ft_Nasc.getText());
        novoFuncionario.addElement(this.tf_Endereco.getText());
        novoFuncionario.addElement(this.tf_Numero.getText());
        novoFuncionario.addElement(this.tf_Bairro.getText());
        novoFuncionario.addElement(this.cb_Uf.getSelectedItem());
        novoFuncionario.addElement(this.cb_Cidade.getSelectedItem());
        novoFuncionario.addElement(this.tf_Email.getText());
        novoFuncionario.addElement(tel);
        novoFuncionario.addElement(this.tf_Cargo.getText());
        novoFuncionario.addElement(this.tf_Login.getText());
        novoFuncionario.addElement(senha);

        return novoFuncionario;

    }



    public FrmCadastroFuncionario() {
        initComponents();
        this.b_Cadastrar.addActionListener(this);
        this.cb_Uf.addItemListener(this);
        this.ft_Cpf.addFocusListener(this);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        l_nome = new javax.swing.JLabel();
        l_Cpf = new javax.swing.JLabel();
        l_Endereco = new javax.swing.JLabel();
        l_Numero = new javax.swing.JLabel();
        l_Bairro = new javax.swing.JLabel();
        tf_Nome = new javax.swing.JTextField();
        tf_Endereco = new javax.swing.JTextField();
        l_Cidade = new javax.swing.JLabel();
        l_Uf = new javax.swing.JLabel();
        l_Login = new javax.swing.JLabel();
        l_Senha = new javax.swing.JLabel();
        tf_Bairro = new javax.swing.JTextField();
        tf_Numero = new javax.swing.JTextField();
        l_DataNascimento = new javax.swing.JLabel();
        cb_Uf = new javax.swing.JComboBox(this.ufCombo());
        cb_Cidade = new javax.swing.JComboBox();
        l_email = new javax.swing.JLabel();
        tf_Email = new javax.swing.JTextField();
        tf_Login = new javax.swing.JTextField();
        pf_Senha = new javax.swing.JPasswordField();
        l_Telefone = new javax.swing.JLabel();
        l_Cargo = new javax.swing.JLabel();
        tf_Cargo = new javax.swing.JTextField();
        ft_Nasc = new javax.swing.JFormattedTextField();
        ft_Cpf = new javax.swing.JFormattedTextField();
        ft_Tel = new javax.swing.JFormattedTextField();
        b_Cancelar = new javax.swing.JButton();
        b_Cadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Funcionário");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        l_nome.setText("Nome");

        l_Cpf.setText("CPF");

        l_Endereco.setText("Endereço");

        l_Numero.setText("Nº");

        l_Bairro.setText("Bairro");

        l_Cidade.setText("Cidade");

        l_Uf.setText("UF");

        l_Login.setText("Login");

        l_Senha.setText("Senha");

        l_DataNascimento.setText("Nascimento");

        l_email.setText("E-mail");

        l_Telefone.setText("Telefone");

        l_Cargo.setText("Cargo");

        try {
            ft_Nasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ft_Cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ft_Tel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l_Uf)
                        .addGap(279, 279, 279))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_nome)
                                    .addComponent(l_Endereco)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tf_Endereco, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tf_Nome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(l_Numero)
                                        .addGap(29, 29, 29)
                                        .addComponent(l_Bairro))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tf_Bairro, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(l_Cpf)
                                            .addComponent(ft_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ft_Nasc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(l_DataNascimento)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(l_email)
                                    .addComponent(tf_Email)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(tf_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(l_Login)
                                                .addGap(94, 94, 94)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(l_Senha)
                                            .addComponent(pf_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cb_Uf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ft_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(l_Telefone))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(l_Cargo)
                                                .addGap(30, 30, 30))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tf_Cargo, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(l_Cidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE))
                                    .addComponent(cb_Cidade, 0, 250, Short.MAX_VALUE))))
                        .addGap(604, 604, 604)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l_nome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_Endereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Cpf)
                            .addComponent(l_DataNascimento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ft_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ft_Nasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Numero)
                            .addComponent(l_Bairro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_Uf)
                    .addComponent(l_Cidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_Cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l_email)
                                .addGap(7, 7, 7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l_Telefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ft_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Login)
                            .addComponent(l_Senha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pf_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l_Cargo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        b_Cancelar.setText("Cancelar");
        b_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_CancelarActionPerformed(evt);
            }
        });

        b_Cadastrar.setText("Confirmar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addComponent(b_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Cadastrar)
                    .addComponent(b_Cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_CancelarActionPerformed

        this.dispose();
    }//GEN-LAST:event_b_CancelarActionPerformed


//
//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FrmCadastroFuncionario dialog = new FrmCadastroFuncionario(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton b_Cadastrar;
    protected javax.swing.JButton b_Cancelar;
    protected javax.swing.JComboBox cb_Cidade;
    protected javax.swing.JComboBox cb_Uf;
    protected javax.swing.JFormattedTextField ft_Cpf;
    protected javax.swing.JFormattedTextField ft_Nasc;
    protected javax.swing.JFormattedTextField ft_Tel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel l_Bairro;
    private javax.swing.JLabel l_Cargo;
    private javax.swing.JLabel l_Cidade;
    private javax.swing.JLabel l_Cpf;
    private javax.swing.JLabel l_DataNascimento;
    private javax.swing.JLabel l_Endereco;
    private javax.swing.JLabel l_Login;
    private javax.swing.JLabel l_Numero;
    private javax.swing.JLabel l_Senha;
    private javax.swing.JLabel l_Telefone;
    private javax.swing.JLabel l_Uf;
    private javax.swing.JLabel l_email;
    private javax.swing.JLabel l_nome;
    protected javax.swing.JPasswordField pf_Senha;
    protected javax.swing.JTextField tf_Bairro;
    protected javax.swing.JTextField tf_Cargo;
    protected javax.swing.JTextField tf_Email;
    protected javax.swing.JTextField tf_Endereco;
    protected javax.swing.JTextField tf_Login;
    protected javax.swing.JTextField tf_Nome;
    protected javax.swing.JTextField tf_Numero;
    // End of variables declaration//GEN-END:variables



    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == this.b_Cadastrar)
        {
            if(!this.tf_Nome.getText().isEmpty())
            {
                if(!this.ft_Cpf.getText().isEmpty())
                {
                    if(!this.ft_Nasc.getText().isEmpty())
                    {
                        if(!this.tf_Endereco.getText().isEmpty())
                        {
                            if(!this.tf_Numero.getText().isEmpty())
                            {
                                if(!this.tf_Bairro.getText().isEmpty())
                                {
                                    //if(this.cb_Uf.)
                                    //{
                                        //if(this.cb_Cidade.getSelectedItem().equals(""))
                                        //{
                                            if(!this.tf_Email.getText().isEmpty())
                                            {
                                                if(!this.ft_Tel.getText().isEmpty())
                                                {
                                                    if(!this.tf_Cargo.getText().isEmpty())
                                                    {
                                                        if(!this.tf_Login.getText().isEmpty())
                                                        {
                                                            if(this.pf_Senha.getPassword().length > 0)
                                                            {

                                                                Vector novoFuncionario = new Vector();

                                                                String senha = String.copyValueOf(this.pf_Senha.getPassword());
                                                                String cpf = this.valida.tiraPontosCPF(this.ft_Cpf.getText());
                                                                String tel = this.valida.tiraPontosTelefone(this.ft_Tel.getText());
                                                                this.controladoraCidade.setMarc(this.cb_Cidade.getSelectedIndex());


                                                                novoFuncionario.addElement(this.tf_Nome.getText());
                                                                novoFuncionario.addElement(cpf);
                                                                novoFuncionario.addElement(this.ft_Nasc.getText());
                                                                novoFuncionario.addElement(this.tf_Endereco.getText());
                                                                novoFuncionario.addElement(this.tf_Numero.getText());
                                                                novoFuncionario.addElement(this.tf_Bairro.getText());
                                                                novoFuncionario.addElement(this.cb_Uf.getSelectedItem());
                                                                novoFuncionario.addElement(this.cb_Cidade.getSelectedItem());
                                                                novoFuncionario.addElement(this.tf_Email.getText());
                                                                novoFuncionario.addElement(tel);
                                                                novoFuncionario.addElement(this.tf_Cargo.getText());
                                                                novoFuncionario.addElement(this.tf_Login.getText());
                                                                novoFuncionario.addElement(senha);

                                                                ControladoraFuncionario controladoraFuncionario = new ControladoraFuncionario();
                                                                try{
                                                                    controladoraFuncionario.inserirFuncionario(novoFuncionario, this.controladoraCidade);
                                                                }
                                                                catch(ParseException erro)
                                                                {
                                                                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                                catch(SQLException erro)
                                                                {
                                                                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                                catch(MinhaException erro)
                                                                {
                                                                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                                catch (ConexaoException erro) {
                                                                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                                finally{
                                                                    this.setVisible(false);
                                                                }
                                                                

                                                            }
                                                            else
                                                                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Senha !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                        else
                                                            JOptionPane.showMessageDialog(null, "É preciso preencher o campo Login !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                    else
                                                        JOptionPane.showMessageDialog(null, "É preciso preencher o campo Cargo !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                                }
                                                else
                                                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo Telefone !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            else
                                                JOptionPane.showMessageDialog(null, "É preciso preencher o campo E-mail !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        //else
                                            //JOptionPane.showMessageDialog(null, "É preciso selecionar uma Cidade !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                    //}
                                    //else
                                        //JOptionPane.showMessageDialog(null, "É preciso selecionar um Estado !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                                //}
                                else
                                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo Bairro !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Número !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "É preciso preencher o campo Endereço !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "É preciso preencher o campo Data de Nascimento !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "É preciso preencher o campo CPF !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "É preciso preencher o campo Nome !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void itemStateChanged(ItemEvent e) {
        try {
            this.cb_Cidade.setModel(new DefaultComboBoxModel(this.cidadesCombo()));
        } catch (ConexaoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }


    public void focusGained(FocusEvent e) {

    }

    
    public void focusLost(FocusEvent e) {

        if(e.getSource() == this.ft_Cpf)
        {
            String cpf = this.valida.tiraPontosCPF(this.ft_Cpf.getText());
            
                if(!this.valida.validarCPF(cpf))
                {
                    JOptionPane.showMessageDialog(null, "CPF inválido");
                    this.ft_Cpf.setText("");

                }
        }
    }

}
