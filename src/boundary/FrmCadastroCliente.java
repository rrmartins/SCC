
package boundary;

import control.ControladoraCidade;
import control.ControladoraCliente;
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


public class FrmCadastroCliente extends javax.swing.JDialog implements ActionListener, FocusListener, ItemListener{

    private ControladoraUF controladoraUF = new ControladoraUF();
    protected  ControladoraCidade controladoraCidade = new ControladoraCidade();
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
        catch (ConexaoException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
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
                                                    if(!this.tf_Cartao.getText().isEmpty())
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
                                                        JOptionPane.showMessageDialog(null, "É preciso preencher o campo Cartão !", "Atenção", JOptionPane.INFORMATION_MESSAGE);
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


    public Vector criarCliente(){

        Vector novoCliente = new Vector();

        String senha = String.copyValueOf(this.pf_Senha.getPassword());
        String cpf = this.valida.tiraPontosCPF(this.ft_Cpf.getText());
        String tel = this.valida.tiraPontosTelefone(this.ft_Tel.getText());
        this.controladoraCidade.setMarc(this.cb_Cidade.getSelectedIndex());

        novoCliente.addElement(this.tf_Nome.getText());
        novoCliente.addElement(cpf);
        novoCliente.addElement(this.ft_Nasc.getText());
        novoCliente.addElement(this.tf_Endereco.getText());
        novoCliente.addElement(this.tf_Numero.getText());
        novoCliente.addElement(this.tf_Bairro.getText());
        novoCliente.addElement(this.cb_Uf.getSelectedItem());
        novoCliente.addElement(this.cb_Cidade.getSelectedItem());
        novoCliente.addElement(this.tf_Email.getText());
        novoCliente.addElement(tel);
        novoCliente.addElement(this.tf_Cartao.getText());
        novoCliente.addElement(this.tf_Login.getText());
        novoCliente.addElement(senha);

        return novoCliente;

    }


    public void limparCampos() {

        this.tf_Nome.setText("");
        this.ft_Cpf.setText("");
        this.ft_Tel.setText("");
        this.tf_Endereco.setText("");
        this.tf_Numero.setText("");
        this.tf_Bairro.setText("");
        this.tf_Email.setText("");
        this.tf_Login.setText("");
        this.tf_Cartao.setText("");
        this.pf_Senha.setText("");
        this.ft_Nasc.setText("");
        this.cb_Uf.setSelectedIndex(0);
    }


    public FrmCadastroCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.b_Cadastrar.addActionListener(this);
        this.bCancelar.addActionListener(this);
        this.cb_Uf.addItemListener(this);
        this.ft_Cpf.addFocusListener(this);
        this.setLocationRelativeTo(null);
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
        l_Cartao = new javax.swing.JLabel();
        tf_Cartao = new javax.swing.JTextField();
        ft_Cpf = new javax.swing.JFormattedTextField();
        ft_Nasc = new javax.swing.JFormattedTextField();
        ft_Tel = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        b_Cadastrar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Cliente");
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

        l_Cartao.setText("Cartão de Crédito");

        try {
            ft_Cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            ft_Nasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(l_Numero)
                                        .addGap(29, 29, 29)
                                        .addComponent(l_Bairro))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tf_Bairro, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(l_Cpf)
                                            .addComponent(ft_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(l_DataNascimento)
                                                .addGap(15, 15, 15))
                                            .addComponent(ft_Nasc, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))))
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
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(l_Telefone)
                                                .addGap(77, 77, 77))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ft_Tel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(l_Cartao)
                                                .addGap(30, 30, 30))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tf_Cartao, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(l_Cidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE))
                                    .addComponent(cb_Cidade, 0, 235, Short.MAX_VALUE))))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ft_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ft_Nasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_Endereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_Cpf)
                            .addComponent(l_DataNascimento))
                        .addGap(32, 32, 32)
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
                        .addComponent(l_Cartao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Cartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        b_Cadastrar.setText("Confirmar");

        bCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_Cadastrar)
                    .addComponent(bCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FrmCadastroCliente dialog = new FrmCadastroCliente(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton b_Cadastrar;
    protected javax.swing.JComboBox cb_Cidade;
    protected javax.swing.JComboBox cb_Uf;
    protected javax.swing.JFormattedTextField ft_Cpf;
    protected javax.swing.JFormattedTextField ft_Nasc;
    protected javax.swing.JFormattedTextField ft_Tel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel l_Bairro;
    private javax.swing.JLabel l_Cartao;
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
    protected javax.swing.JTextField tf_Cartao;
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
            if(this.verificaCamposVazios()){

                Vector cliente = this.criarCliente();

                ControladoraCliente controladoraCliente = new ControladoraCliente();
                
                try {
                   controladoraCliente.inserirCliente(cliente, this.controladoraCidade);
                }
                catch (ParseException erro) {
                }
                catch (SQLException erro) {
                }
                catch (MinhaException erro) {
                }
                catch (ConexaoException ex) {
                        Logger.getLogger(FrmCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                    this.setVisible(false);
                }

            }
        }
        else if(e.getSource() == this.bCancelar) {
            this.limparCampos();
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

    public void itemStateChanged(ItemEvent e) {
        try {
            this.cb_Cidade.setModel(new DefaultComboBoxModel(this.cidadesCombo()));
        } catch (ConexaoException ex) {
            Logger.getLogger(FrmCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}