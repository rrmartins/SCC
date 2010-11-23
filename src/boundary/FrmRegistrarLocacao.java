
package boundary;

import control.ControladoraCarros;
import control.ControladoraCliente;
import control.ControladoraGrupoCarro;
import control.ControladoraLocacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import util.ConexaoException;
import util.MinhaException;
import util.Validador;



public class FrmRegistrarLocacao extends javax.swing.JDialog implements ActionListener{

    protected ControladoraLocacao controladoraLocacao = new ControladoraLocacao();
    protected ControladoraGrupoCarro controladoraGrupoCarro = new ControladoraGrupoCarro();
    protected ControladoraCliente controladoraCliente = new ControladoraCliente();
    protected ControladoraCarros controladoraCarros = new ControladoraCarros();

    protected Vector clienteTela = new Vector();
    protected Vector grupo = new Vector();
    protected Vector carro = new Vector();

    
    private double valorInicial;
    DecimalFormat dcFormato = new DecimalFormat("#.00");
    Vector gc = new Vector();
    DefaultComboBoxModel gcarro = new DefaultComboBoxModel();


    
    public FrmRegistrarLocacao() throws ConexaoException {
        initComponents();
        gc = preencherComboGrupoCarro();
        
        for(int i = 0; i < gc.size(); i++){
            gcarro.addElement(gc.elementAt(i));
        }
        cbGrupoCarro.setModel(gcarro);
        
        this.setLocationRelativeTo(null);
        this.dataLocacao.setDate(new Date());
        this.dataEntrega.setDate(new Date());
        this.dataLocacao.setEnabled(false);
        this.bConfirmar.addActionListener(this);
        this.bCancelar.addActionListener(this);
        
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
        if(tipoDiaria.equals("Diaria Simples")){

            double valor = (Double) this.grupo.get(2);

            int diaria = this.calcularDatas();
            if(diaria == 0)
                diaria = 1;

            this.valorInicial = 0;
            this.valorInicial = valor * diaria;

            String valorFormatado = dcFormato.format(this.valorInicial);

            //this.lPrevisao.setText( valorFormatado);
            this.tfValorTotal.setText(valorFormatado);
        }
        else if(tipoDiaria.equals("Diaria Quilometrada")){

            double valor = (Double) this.grupo.get(3);

            int diaria = this.calcularDatas();
            if(diaria == 0)
                diaria = 1;

            this.valorInicial = 0;
            this.valorInicial = valor * diaria;

            String valorFormatado = dcFormato.format(this.valorInicial);

            //this.lPrevisao.setText( valorFormatado);
            this.tfValorTotal.setText(valorFormatado);
        }
        

    }

    
    
    public void setarCarro()
    {
        if(this.controladoraCarros.getCarros().size() > 0)
        {
            this.carro = this.controladoraCarros.montarCarroTela(this.controladoraCarros.getCarros().get(this.controladoraCarros.getMarc()));
            this.tfCarro.setText(this.carro.get(1).toString());
            this.tfPlacaCarro.setText(this.carro.get(2).toString());
            this.calcularValor("Diaria Simples");
        }
        
    }

    
    
    public void criarLocacao(Vector locacao)
    {       
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
        locacao.addElement(this.cbHoraLocacao.getSelectedItem());
        locacao.addElement(this.cbHoraEntrega.getSelectedItem());
        
        if(!this.tfQuilometragem.getText().isEmpty())
            locacao.addElement(this.tfQuilometragem.getText());
        else
            locacao.addElement(0);
        locacao.addElement(this.grupo);
        locacao.addElement(this.cbPlano.getSelectedItem());
                                        
  

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
        b_Busca = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SCC - Locação");
        setResizable(false);

        bConfirmar.setText("Confirmar");

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

        tfCarro.setEnabled(false);

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
        tfQuilometragem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfQuilometragemKeyPressed(evt);
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

        tfPlacaCarro.setEnabled(false);

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
                            .addComponent(cbHoraLocacao, 0, 120, Short.MAX_VALUE)
                            .addComponent(lHoraLocacao)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lPlano)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE))
                    .addComponent(cbPlano, 0, 248, Short.MAX_VALUE)
                    .addComponent(lGrupoCarro)
                    .addComponent(cbGrupoCarro, 0, 248, Short.MAX_VALUE))
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
                                    .addComponent(tfCarro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cbCobertura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lCobertura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                        .addComponent(lPrevisao, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(tfPlacaCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bVisualiza)))))
                        .addContainerGap(36, Short.MAX_VALUE))
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

        b_Busca.setText("Buscar Cliente");
        b_Busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_BuscaActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ftCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_Busca))
                            .addComponent(lNome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
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
                                    .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lEndereco)
                                            .addComponent(tfEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
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
                            .addComponent(lBairro))))
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ftCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_Busca))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
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
                    JDialog janela = new FrmVisualizaCarros(null, true, grupo, dataL, dataE, this.controladoraCarros);
                } catch (ConexaoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

                this.setarCarro();
            }
        }
        catch(ParseException erro){
            erro.getMessage();
        }
        
}//GEN-LAST:event_bVisualizaActionPerformed



    
    private void cbPlanoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPlanoItemStateChanged

        if(this.cbPlano.getSelectedItem().toString().equals("Diaria Quilometrada") && this.cbCobertura.getSelectedItem().toString().equals("Sim"))
        {
            this.tfQuilometragem.setEnabled(true);
            this.calcularValor("Diaria Quilometrada");
            this.calculaComCombertura();
        }        
        
        else if(this.cbPlano.getSelectedItem().toString().equals("Diaria Quilometrada"))
        {
            this.tfQuilometragem.setEnabled(true);
            this.calcularValor("Diaria Quilometrada");
        }
            
        else if(this.cbPlano.getSelectedItem().toString().equals("Diaria Simples") && this.cbCobertura.getSelectedItem().toString().equals("Sim"))
        {
            this.tfQuilometragem.setEnabled(false);
            this.tfQuilometragem.setText("");
            this.calcularValor("Diaria Simples");
            this.calculaComCombertura();
        }
        
        else if(this.cbPlano.getSelectedItem().toString().equals("Diaria Simples"))
        {
            this.tfQuilometragem.setEnabled(false);
            this.tfQuilometragem.setText("");
            this.calcularValor("Diaria Simples");         
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
            
            double dQuilometrada = this.controladoraGrupoCarro.getVetGrupos().get(this.controladoraGrupoCarro.getMarc()).getPrecoDiariaQuilometrada();
            double adciQ = this.controladoraGrupoCarro.getVetGrupos().get(this.controladoraGrupoCarro.getMarc()).getPrecoQuilometroAdicional();
            this.valorInicial = (dQuilometrada * diaria) + (adciQ * quilometragem);

            String valorFomatado = dcFormato.format(this.valorInicial);

            //this.lPrevisao.setText(valorFomatado);
            this.tfValorTotal.setText(valorFomatado);

        }
}//GEN-LAST:event_tfQuilometragemFocusLost


    
    public void calculaComCombertura(){
       
        double cobertura = this.controladoraGrupoCarro.getVetGrupos().get(this.controladoraGrupoCarro.getMarc()).getPrecoCobertura();
        double total =  this.valorInicial + cobertura;

        String valorFormatado = dcFormato.format(total);
         this.tfValorTotal.setText(valorFormatado);
        
    }

    
    
    private void cbCoberturaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCoberturaItemStateChanged

        if(this.cbCobertura.getSelectedItem().toString().equals("Sim")){
            
            this.calculaComCombertura();
        }
        else{
            
            if(this.cbPlano.getSelectedItem().toString().equals("Diaria Simples")){
                
                this.tfQuilometragem.setEnabled(false);
                this.tfQuilometragem.setText("");
                this.calcularValor("Diaria Simples");  
            }
            else{
                this.tfQuilometragem.setText("");
                this.tfQuilometragem.setEnabled(true);
                this.calcularValor("Diaria Quilometrada");  
            }
               
        }
}//GEN-LAST:event_cbCoberturaItemStateChanged



    public void confereCpf() throws ConexaoException{

        String cpf = this.ftCpf.getText();
        Validador valida = new Validador();
        cpf = valida.tiraPontosCPF(cpf);

        if (cpf.length() < 11) {
            JOptionPane.showMessageDialog(null, "Digite o CPF corretamente", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {

            if (valida.validarCPF(cpf)) {

                try {

                    this.clienteTela = this.controladoraCliente.obterCliente(cpf);

                    this.tfNome.setText(this.clienteTela.get(1).toString());
                    this.tfEndereco.setText(this.clienteTela.get(4).toString());
                    this.tfNumero.setText(this.clienteTela.get(5).toString());
                    this.tfBairro.setText(this.clienteTela.get(6).toString());
                    this.tfCidade.setText(this.clienteTela.get(11).toString());
                    this.tfUf.setText(this.clienteTela.get(12).toString());
                    this.tfEmail.setText(this.clienteTela.get(7).toString());
                    this.tfTel.setText(this.clienteTela.get(8).toString());

                }
                catch (SQLException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                    this.ftCpf.setText("");
                }
                catch (MinhaException erro)
                {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                    this.ftCpf.setText("");
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "CPF não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                this.ftCpf.setText("");
            }
        }
    }
    
    
private void b_BuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_BuscaActionPerformed
        try {
            this.confereCpf();
        } catch (ConexaoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    
}//GEN-LAST:event_b_BuscaActionPerformed

private void tfQuilometragemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfQuilometragemKeyPressed

    if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        this.tfQuilometragemFocusLost(null);

}//GEN-LAST:event_tfQuilometragemKeyPressed




    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton bCancelar;
    protected javax.swing.JButton bConfirmar;
    private javax.swing.JButton bVisualiza;
    private javax.swing.JButton b_Busca;
    protected javax.swing.JComboBox cbCobertura;
    protected javax.swing.JComboBox cbGrupoCarro;
    protected javax.swing.JComboBox cbHoraEntrega;
    protected javax.swing.JComboBox cbHoraLocacao;
    protected javax.swing.JComboBox cbPlano;
    protected com.toedter.calendar.JDateChooser dataEntrega;
    protected com.toedter.calendar.JDateChooser dataLocacao;
    protected javax.swing.JFormattedTextField ftCpf;
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
    protected javax.swing.JTextField tfBairro;
    protected javax.swing.JTextField tfCarro;
    protected javax.swing.JTextField tfCidade;
    protected javax.swing.JTextField tfEmail;
    protected javax.swing.JTextField tfEndereco;
    protected javax.swing.JTextField tfNome;
    protected javax.swing.JTextField tfNumero;
    protected javax.swing.JTextField tfPlacaCarro;
    protected javax.swing.JTextField tfQuilometragem;
    protected javax.swing.JTextField tfTel;
    protected javax.swing.JTextField tfUf;
    protected javax.swing.JTextField tfValorTotal;
    // End of variables declaration//GEN-END:variables

    public boolean validaCampos() {
        
        if(!this.ftCpf.getText().isEmpty()){
            
            String cpf = this.ftCpf.getText();
            Validador valida = new Validador();
            cpf = valida.tiraPontosCPF(cpf);


            if (!cpf.isEmpty() || cpf.length() == 11)
                if (!this.tfCarro.getText().isEmpty())
                    if ((this.cbPlano.getSelectedItem().toString().equals("Diaria Simples")) || ((this.cbPlano.getSelectedItem().toString().equals("Diaria Quilometrada") && !this.tfQuilometragem.getText().isEmpty())))
                        if (!this.tfValorTotal.getText().isEmpty())
                            return true;
                        else 
                            JOptionPane.showMessageDialog(null, "O Valor não pode estar Zerado", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "Insira a Quantidade de quilometros", "Informação", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Escolha um carro", "Informação", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Digite o CPF corretamente", "Informação", JOptionPane.INFORMATION_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Informe o CPF corretamente", "Informação", JOptionPane.INFORMATION_MESSAGE);
        return false;
        
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.bConfirmar){

            if (dataEntrega.getDate().before(dataLocacao.getDate())) {
                JOptionPane.showMessageDialog(null, " A data de Entrega não pode ser antes da data de Locação !", "Informação", JOptionPane.INFORMATION_MESSAGE);
                this.dataEntrega.setDate(new Date());


            } else {
                if (this.validaCampos()) {

                    Vector locacao = new Vector();
                    this.criarLocacao(locacao);

                    try
                    {
                        try {
                            this.controladoraLocacao.inserirLocacao(locacao);
                        } catch (ConexaoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        this.dispose();
                    }
                    catch (MinhaException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (SQLException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ParseException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
        else if (e.getSource() == this.bCancelar){
            this.dispose();
        }
    }


}
