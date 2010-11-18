
package boundary;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import control.ControladoraCarros;
import control.ControladoraCliente;
import control.ControladoraGrupoCarro;
import control.ControladoraLocacao;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import util.ConexaoException;
import util.MinhaException;
import util.Validador;



public class FrmRegistrarLocacao extends javax.swing.JDialog{

    private ControladoraLocacao controladoraLocacao = new ControladoraLocacao();
    private ControladoraGrupoCarro controladoraGrupoCarro = new ControladoraGrupoCarro();
    private ControladoraCliente controladoraCliente = new ControladoraCliente();
    private ControladoraCarros controladoraCarros = new ControladoraCarros();
    private Vector clienteTela = new Vector();
    private Vector grupo = new Vector();
    private Vector carro = new Vector();
    private Vector cbGrupo = new Vector();
    DefaultComboBoxModel grupoCarro = new DefaultComboBoxModel();
    private double valorInicial, valorTotal;
    DecimalFormat dcFormato = new DecimalFormat("#.00");


    public FrmRegistrarLocacao() throws ConexaoException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.dataLocacao.setDate(new Date());
        this.dataEntrega.setDate(new Date());
        this.dataLocacao.setEnabled(false);
        cbGrupo = this.preencherComboGrupoCarro();
        for(int i = 0; i < cbGrupo.size(); i++){
            grupoCarro.addElement(cbGrupo.elementAt(i));
        }
        cbGrupoCarro.setModel(grupoCarro);        
    }


    
    public Vector preencherComboGrupoCarro() throws ConexaoException{

        Vector comboGrupo = new Vector();

        try{
            comboGrupo = this.controladoraGrupoCarro.obterLinhasGrupoCarro();
        }
        catch(MinhaException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException erro)
        {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        this.controladoraGrupoCarro.setMarc(0);
        this.grupo = this.controladoraGrupoCarro.obterGrupo();

        return comboGrupo;

    }


    

    public void converteDataVetor(int[] dataInicio, int[] dataFim)
    {
        Calendar calendarI = Calendar.getInstance();
        calendarI.setTime(new Date(this.dataLocacao.getDate().getTime()));

        dataInicio[0] = calendarI.get(Calendar.DAY_OF_MONTH);
        dataInicio[1] = calendarI.get(Calendar.MONTH);
        dataInicio[2] = calendarI.get(Calendar.YEAR);

        Calendar calendarF = Calendar.getInstance();
        calendarF.setTime(new Date(this.dataEntrega.getDate().getTime()));

        dataFim[0] = calendarF.get(Calendar.DAY_OF_MONTH);
        dataFim[1] = calendarF.get(Calendar.MONTH);
        dataFim[2] = calendarF.get(Calendar.YEAR);


    }


    

    public int calcularDatas()
    {

        int[] dataInicio = new int[3];
        int[] dataFim = new int[3];

        double diffMillis, diffDays = 0;

        converteDataVetor(dataInicio, dataFim);

        Calendar dataI = new GregorianCalendar(dataInicio[2], dataInicio[1], dataInicio[0]);
        Calendar dataF = new GregorianCalendar(dataFim[2], dataFim[1], dataFim[0]);


        if(dataF.before(dataI)){
            JOptionPane.showMessageDialog(null, " A data de Entrega não  pode ser antes da data de Locação !", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
            
        else{
            diffMillis = dataF.getTimeInMillis() - dataI.getTimeInMillis();

            diffDays = diffMillis / (24*60*60*1000);

        }

        int dias = (int) Math.ceil(diffDays);

        return dias;

    }


    

    public void calcularValor(String tipoDiaria)
    {
        if(tipoDiaria.equals("Simples")){

            double valor = (Double) this.grupo.get(2);

            int diaria = this.calcularDatas();
            if(diaria == 0)
                diaria = 1;

            this.valorInicial = valor * diaria;

            String valorFormatado = dcFormato.format(this.valorInicial);

            this.lPrevisao.setText( valorFormatado);
            this.tfValorTotal.setText(valorFormatado);
        }
        else if(tipoDiaria.equals("Quilometrada")){

            double valor = (Double) this.grupo.get(3);

            int diaria = this.calcularDatas();
            if(diaria == 0)
                diaria = 1;

            this.valorInicial = valor * diaria;

            String valorFormatado = dcFormato.format(this.valorInicial);

            this.lPrevisao.setText( valorFormatado);
            this.tfValorTotal.setText(valorFormatado);
        }
        

    }


    
    public void setarCarro()
    {
        if(this.controladoraCarros.getCarros().size() > 0)
        {
            this.carro = this.controladoraCarros.montarLinhasCarro(this.controladoraCarros.getCarros().get(this.controladoraCarros.getMarc()));
            this.tfCarro.setText(this.carro.get(1).toString());
            this.tfPlacaCarro.setText(this.carro.get(2).toString());
            this.calcularValor("Simples");
        }
        
    }


    
    public Vector criarLocacao()
    {

        Vector locacao = new Vector();

        if(!this.ftCpf.getText().isEmpty())
        {
            if(this.dataLocacao.getDate().toString().equals("") )
            {
                if(this.dataEntrega.getDate().toString().equals(""))
                {
                    if(this.cbGrupoCarro.getSelectedIndex() < 0)
                    {
                        if(this.carro != null)
                        {
                            if((this.cbPlano.getSelectedItem().toString().equals("Diaria Simples")) || ((this.cbPlano.getSelectedItem().toString().equals("Diaria Quilometrada") && !this.tfQuilometragem.getText().isEmpty())))
                            {
                                if(!this.tfValorTotal.getText().isEmpty())
                                {
                                    int q = Integer.parseInt(this.tfQuilometragem.getText().toString());

                                    if(this.cbCobertura.getSelectedItem().toString().equals("Sim")){


                                        locacao.addElement(this.ftCpf.getText());
                                        locacao.addElement(this.dataLocacao.getDate());
                                        locacao.addElement(this.dataEntrega.getDate());
                                        locacao.addElement(this.carro);
                                        locacao.addElement(this.tfValorTotal.getText());
                                        locacao.addElement(this.clienteTela);
                                        locacao.addElement(this.cbHoraLocacao.getSelectedItem());
                                        locacao.addElement(this.cbHoraEntrega.getSelectedItem());
                                        locacao.addElement(this.tfValorTotal.getText());
                                        locacao.addElement(this.cbCobertura.getSelectedItem());

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return locacao;

    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bConfirmar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lValorTotal = new javax.swing.JLabel();
        lMoeda = new javax.swing.JLabel();
        tfValorTotal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cbHoraLocacao = new javax.swing.JComboBox();
        lHoraEntrega = new javax.swing.JLabel();
        cbHoraEntrega = new javax.swing.JComboBox();
        dataLocacao = new com.toedter.calendar.JDateChooser();
        lInfoEntrega = new javax.swing.JLabel();
        dataEntrega = new com.toedter.calendar.JDateChooser();
        lDataEntrega = new javax.swing.JLabel();
        lInfoLocacao = new javax.swing.JLabel();
        lDataLocacao = new javax.swing.JLabel();
        lHoraLocacao = new javax.swing.JLabel();
        lGrupoCarro = new javax.swing.JLabel();
        cbGrupoCarro = new javax.swing.JComboBox();
        tfCarro = new javax.swing.JTextField();
        lCarro = new javax.swing.JLabel();
        bVisualiza = new javax.swing.JButton();
        lPlano = new javax.swing.JLabel();
        cbPlano = new javax.swing.JComboBox();
        lQuilometragemPrev = new javax.swing.JLabel();
        tfQuilometragem = new javax.swing.JTextField();
        lInfoKm = new javax.swing.JLabel();
        lCobertura = new javax.swing.JLabel();
        cbCobertura = new javax.swing.JComboBox();
        lPrevisao = new javax.swing.JLabel();
        tfPlacaCarro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lEndereco = new javax.swing.JLabel();
        lBairro = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        lNumero = new javax.swing.JLabel();
        tfEndereco = new javax.swing.JTextField();
        tfUf = new javax.swing.JTextField();
        lCidade = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        lUf = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        lCpf = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        lNome = new javax.swing.JLabel();
        lEmail = new javax.swing.JLabel();
        lTel = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        tfTel = new javax.swing.JTextField();
        ftCpf = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Locação");
        setResizable(false);

        bConfirmar.setText("Confirmar");
        bConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmarActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lValorTotal.setFont(new java.awt.Font("Tahoma", 0, 14));
        lValorTotal.setForeground(new java.awt.Color(255, 0, 0));
        lValorTotal.setText("VALOR TOTAL");

        lMoeda.setText("R$");

        tfValorTotal.setEditable(false);
        tfValorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        cbHoraLocacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" }));

        lHoraEntrega.setText("Hora");

        cbHoraEntrega.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" }));

        lInfoEntrega.setText("Dados da Entrega Prevista");

        lDataEntrega.setText("Data");

        lInfoLocacao.setText("Locação");

        lDataLocacao.setText("Data");

        lHoraLocacao.setText("Hora");

        lGrupoCarro.setText("Grupo de Carro");

        cbGrupoCarro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbGrupoCarroItemStateChanged(evt);
            }
        });

        lCarro.setText("Carro");

        bVisualiza.setText("Visualizar Carros");
        bVisualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVisualizaActionPerformed(evt);
            }
        });

        lPlano.setText("Plano");

        cbPlano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diaria Simples", "Diaria Quilometrada" }));
        cbPlano.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPlanoItemStateChanged(evt);
            }
        });

        lQuilometragemPrev.setText("Quilometagem Prevista");

        tfQuilometragem.setEnabled(false);
        tfQuilometragem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfQuilometragemFocusLost(evt);
            }
        });

        lInfoKm.setText("KM");

        lCobertura.setText("Cobertura");

        cbCobertura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Não", "Sim" }));
        cbCobertura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCoberturaItemStateChanged(evt);
            }
        });

        lPrevisao.setText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lInfoLocacao)
                            .addComponent(lDataLocacao)
                            .addComponent(dataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbHoraLocacao, 0, 88, Short.MAX_VALUE)
                            .addComponent(lHoraLocacao)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lPlano)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE))
                    .addComponent(cbPlano, 0, 216, Short.MAX_VALUE)
                    .addComponent(lGrupoCarro)
                    .addComponent(cbGrupoCarro, 0, 216, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lCarro)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(tfQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(lInfoKm))
                                    .addComponent(lQuilometragemPrev)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbCobertura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lCobertura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                        .addComponent(lPrevisao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(tfPlacaCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bVisualiza)))))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lInfoEntrega)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lDataEntrega)
                                    .addComponent(dataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(lHoraEntrega))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbHoraEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lInfoLocacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lDataLocacao)
                            .addComponent(lHoraLocacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbHoraLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lInfoEntrega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lDataEntrega)
                            .addComponent(lHoraEntrega))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbHoraEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lGrupoCarro)
                    .addComponent(lCarro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGrupoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bVisualiza)
                    .addComponent(tfCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPlacaCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPlano)
                    .addComponent(lQuilometragemPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCobertura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPlano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfQuilometragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lInfoKm)
                    .addComponent(lPrevisao)
                    .addComponent(cbCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        lEndereco.setText("Endereço");

        lBairro.setText("Bairro");

        tfNumero.setEnabled(false);

        lNumero.setText("Nº");

        tfEndereco.setEnabled(false);

        tfUf.setEnabled(false);

        lCidade.setText("Cidade");

        tfBairro.setEnabled(false);

        lUf.setText("UF");

        tfCidade.setEnabled(false);

        lCpf.setText("CPF");

        tfNome.setEnabled(false);

        lNome.setText("Nome Completo");

        lEmail.setText("E-mail");

        lTel.setText("Telefone");

        tfEmail.setEnabled(false);

        tfTel.setEnabled(false);

        try {
            ftCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftCpfFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lCpf)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lNome)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lCidade)
                                    .addComponent(tfCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lUf)
                                    .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lEndereco)
                                            .addComponent(tfEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lNumero)
                                            .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lEmail)
                                .addGap(139, 139, 139)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lTel)
                                .addComponent(tfTel)
                                .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lBairro)))
                    .addComponent(ftCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lEndereco)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lCpf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lNome))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lNumero)
                                .addComponent(lBairro)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEmail)
                            .addComponent(lTel)
                            .addComponent(lUf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(lMoeda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lValorTotal)
                    .addComponent(tfValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lMoeda))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConfirmar)
                    .addComponent(bCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void ftCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftCpfFocusLost

        
        String cpf = this.ftCpf.getText();
        Validador valida = new Validador();
        cpf = valida.tiraPontosCPF(cpf);

        //if(valida.validarCPF(cpf)){

            try{
            try {
                this.clienteTela = this.controladoraCliente.obterCliente(cpf);
                //this.cliente = this.controladoraCliente.obterCliente(cpf);
                //this.cliente.getEndereco().setCidade(this.controladoraCidade.selecionarCidadePorCodigo(this.cliente.getEndereco().getCidade().getCodCidade()));
            } catch (ConexaoException ex) {
                Logger.getLogger(FrmRegistrarLocacao.class.getName()).log(Level.SEVERE, null, ex);
            }

                //this.cliente = this.controladoraCliente.obterCliente(cpf);
                //this.cliente.getEndereco().setCidade(this.controladoraCidade.selecionarCidadePorCodigo(this.cliente.getEndereco().getCidade().getCodCidade()));
                

                this.tfNome.setText(this.clienteTela.get(1).toString());
                this.tfEndereco.setText(this.clienteTela.get(4).toString());
                this.tfNumero.setText(this.clienteTela.get(5).toString());
                this.tfBairro.setText(this.clienteTela.get(6).toString());
                //this.tfCidade.setText(this.cliente.getEndereco().getCidade().getNomeCidade());
                //this.tfUf.setText(this.cliente.getEndereco().getCidade().getUF().getUF());
                this.tfEmail.setText(this.clienteTela.get(7).toString());
                this.tfTel.setText(this.clienteTela.get(8).toString());
            }
            catch(SQLException erro){
                JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            catch(MinhaException erro){
                JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }

        //}
        //else{
            //JOptionPane.showMessageDialog(null, "CPF não encontrado");

        //}*/
         
}//GEN-LAST:event_ftCpfFocusLost


    
    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarActionPerformed

        if(dataEntrega.getDate().before(dataLocacao.getDate())){
            JOptionPane.showMessageDialog(null, " A data de Entrega não pode ser antes da data de Locação !", "Informação", JOptionPane.INFORMATION_MESSAGE);
            this.dataEntrega.setDate(new Date());
            
        }
            
        else
        {
            Vector locacaoInsert = this.criarLocacao();
            try
            {
                this.controladoraLocacao.inserirLocacao(locacaoInsert);
            }catch (ConexaoException ex) {
                 Logger.getLogger(FrmRegistrarLocacao.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(MinhaException erro)
            {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException erro)
            {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            

        }
}//GEN-LAST:event_bConfirmarActionPerformed


    
    private void cbGrupoCarroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbGrupoCarroItemStateChanged
    
        this.controladoraGrupoCarro.setMarc(this.cbGrupoCarro.getSelectedIndex());
        this.grupo = this.controladoraGrupoCarro.obterGrupo();

        
}//GEN-LAST:event_cbGrupoCarroItemStateChanged



    private void bVisualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVisualizaActionPerformed

        
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");

        
        String locacao = form.format(this.dataLocacao.getDate());
        String entrega = form.format(this.dataEntrega.getDate());
        
        java.sql.Date dataL = null;
        java.sql.Date dataE = null;
        
        try
        {
            dataL = new java.sql.Date(form.parse(locacao).getTime());
            dataE = new java.sql.Date(form.parse(entrega).getTime());

            if(dataE.before(dataL)){
                JOptionPane.showMessageDialog(null, " A data de Entrega não pode ser antes da data de Locação !", "Informação", JOptionPane.INFORMATION_MESSAGE);
                this.dataEntrega.setDate(new Date());

            }

            else
            {
                try {
                    new FrmVisualizaCarros(null, true, grupo, dataL, dataE, this.controladoraCarros);
                } catch (ConexaoException ex) {
                    Logger.getLogger(FrmRegistrarLocacao.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.setarCarro();
            }
        }
        catch(ParseException erro){
            erro.getMessage();
        }
        
}//GEN-LAST:event_bVisualizaActionPerformed



    
    private void cbPlanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPlanoItemStateChanged

        if(this.cbPlano.getSelectedItem().toString().equals("Diaria Quilometrada"))
        {
            this.tfQuilometragem.setEnabled(true);
            this.calcularValor("Quilometrada");
        }
            
        else
        {
            this.tfQuilometragem.setEnabled(false);
            this.tfQuilometragem.setText("");
            this.calcularValor("Simples");
        }
            
}//GEN-LAST:event_cbPlanoItemStateChanged

    private void tfQuilometragemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfQuilometragemFocusLost

        if(this.tfQuilometragem.getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Você deve indicar uma quilometragem base", "Informação", JOptionPane.INFORMATION_MESSAGE);
        else
        {
            int quilometragem = Integer.parseInt(this.tfQuilometragem.getText());
            
            int diaria = this.calcularDatas();
            if(diaria == 0)
                diaria = 1;
            
            //this.valorInicial = (this.carro.getGrupoCarro().getPrecoDiariaQuilometrada() * diaria) + (this.carro.getPrecoKm() * quilometragem);

            String valorFomatado = dcFormato.format(this.valorInicial);

            this.lPrevisao.setText(valorFomatado);
            this.tfValorTotal.setText(valorFomatado);

        }
}//GEN-LAST:event_tfQuilometragemFocusLost



    
    private void cbCoberturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCoberturaItemStateChanged

        if(this.cbCobertura.getSelectedItem().toString().equals("Sim")){
            
            //double total = this.valorTotal = this.valorInicial + this.carro.getGrupoCarro().getPrecoCobertura();

            //String valorFormatado = dcFormato.format(total);
            //this.tfValorTotal.setText(valorFormatado);
        }
        else{
            
        }
}//GEN-LAST:event_cbCoberturaItemStateChanged


    /**
    * @param args the command line arguments
  
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        UIManager.setLookAndFeel(new PlasticXPLookAndFeel());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistrarLocacao().setVisible(true);
            }
        });
    }
  */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bConfirmar;
    private javax.swing.JButton bVisualiza;
    private javax.swing.JComboBox cbCobertura;
    private javax.swing.JComboBox cbGrupoCarro;
    private javax.swing.JComboBox cbHoraEntrega;
    private javax.swing.JComboBox cbHoraLocacao;
    private javax.swing.JComboBox cbPlano;
    private com.toedter.calendar.JDateChooser dataEntrega;
    private com.toedter.calendar.JDateChooser dataLocacao;
    private javax.swing.JFormattedTextField ftCpf;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lBairro;
    private javax.swing.JLabel lCarro;
    private javax.swing.JLabel lCidade;
    private javax.swing.JLabel lCobertura;
    private javax.swing.JLabel lCpf;
    private javax.swing.JLabel lDataEntrega;
    private javax.swing.JLabel lDataLocacao;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lEndereco;
    private javax.swing.JLabel lGrupoCarro;
    private javax.swing.JLabel lHoraEntrega;
    private javax.swing.JLabel lHoraLocacao;
    private javax.swing.JLabel lInfoEntrega;
    private javax.swing.JLabel lInfoKm;
    private javax.swing.JLabel lInfoLocacao;
    private javax.swing.JLabel lMoeda;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel lNumero;
    private javax.swing.JLabel lPlano;
    private javax.swing.JLabel lPrevisao;
    private javax.swing.JLabel lQuilometragemPrev;
    private javax.swing.JLabel lTel;
    private javax.swing.JLabel lUf;
    private javax.swing.JLabel lValorTotal;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCarro;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNumero;
    private javax.swing.JTextField tfPlacaCarro;
    private javax.swing.JTextField tfQuilometragem;
    private javax.swing.JTextField tfTel;
    private javax.swing.JTextField tfUf;
    private javax.swing.JTextField tfValorTotal;
    // End of variables declaration//GEN-END:variables


}
