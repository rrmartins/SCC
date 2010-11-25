package control;


import dao.*;
import domain.*;
import java.text.ParseException;
import util.ConexaoException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import util.MinhaException;

public class ControladoraEntrega {

    private EntregaDao entregaDao;
    private CarroDao carroDao;
    Vector<Entrega> vetEntregas = new Vector();
    private int marc;

    public int getMarc() {
        return marc;
    }

    public int obterUltimaEntrega() throws SQLException, ConexaoException, ClassNotFoundException, MinhaException {
        
        this.vetEntregas = entregaDao.obterEntrega();
        
        return this.vetEntregas.get(0).getCodEntrega();
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }
    
    public ControladoraEntrega() {
        this.entregaDao = FabricaDao.getEntregaDao("JDBC");
        this.carroDao = FabricaDao.getCarroDao("JDBC");
    }

   public void inserirNovaEntrega(Vector linha) throws MinhaException, SQLException, ParseException, ConexaoException {
        Entrega entrega = new Entrega();
        
        this.atualizarEntrega(entrega, linha);
        
        entregaDao.inserirEntrega(entrega);
        
        carroDao.alterarKMDisponibilidade(linha);

    }
   
    private void atualizarEntrega(Entrega entrega, Vector linha) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Date horaE = formatador.parse(linha.get(3).toString());
        Time horaEntre = new Time(horaE.getTime());
        
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date data = null;
        String entregas = form.format(linha.get(2));
        data = formato.parse(entregas);
        Locacao loca = new Locacao();
        loca.setCodLocacao(Integer.parseInt(linha.get(0).toString()));
        entrega.setLocacao(loca);
        entrega.setQuilometragemFinal(Integer.parseInt(linha.get(1).toString()));
        entrega.setDataEntrega(data);
        entrega.setHoraEntrega(horaEntre);
        entrega.setValorTotal(Integer.parseInt(linha.get(4).toString()));
        
       
    }

    private Vector<Entrega> obterEntrega(String texto)throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        this.vetEntregas = entregaDao.obterEntrega(texto);

        return vetEntregas;
    }

    private Vector<Entrega> obterEntrega()throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        this.vetEntregas = entregaDao.obterEntrega();

        return vetEntregas;
    }

    public Vector obterLinhasEntrega() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Vector<Entrega> entrega = this.obterEntrega();
        Vector linhasReserva = new Vector();

        for(int i = 0; i < entrega.size(); i++)
        {
            Entrega entre = entrega.get(i);
            linhasReserva.addElement(this.criadorLinhaEntrega(entre));
        }

        return linhasReserva;
    }

    public Vector obterLinhasEntrega(String texto) throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Vector<Entrega> entrega = this.obterEntrega(texto);
        Vector linhasReserva = new Vector();

        for(int i = 0; i < entrega.size(); i++)
        {
            Entrega entre = entrega.get(i);
            linhasReserva.addElement(this.criadorLinhaEntrega(entre));
        }

        return linhasReserva;
    }

    @SuppressWarnings({"unchecked"})
    private Vector criadorLinhaEntrega(Entrega entre)
    {
        Vector linha = new Vector();
        linha.addElement(entre.getCodEntrega());
        linha.addElement(entre.getLocacao().getCodLocacao());
        linha.addElement(entre.getQuilometragemFinal());
        linha.addElement(entre.getDataEntrega());
        linha.addElement(entre.getHoraEntrega());
        linha.addElement(entre.getValorTotal());
        
        return linha;
    }

    public Vector obterCliente(String cpf) throws ConexaoException, SQLException, MinhaException {
        Vector client = new Vector();
        Cliente cli = this.selecionarClientePorCpf(cpf);
            client.addElement(cli.getCodCliente());
            client.addElement(cli.getNome());
            client.addElement(cli.getCpf());
            client.addElement(cli.getDataNasc());
            client.addElement(cli.getEndereco().getNomeRua());
            client.addElement(cli.getEndereco().getNumero());
            client.addElement(cli.getEndereco().getNomeBairro());
            client.addElement(cli.getEmail());
            client.addElement(cli.getTelefone());
            client.addElement(cli.getCartaoCredito());


        return client;
    }

    public Vector obterClientePelaLocacao(String codLocacao) throws ConexaoException, SQLException, MinhaException {
        Vector consulta = this.selecionarClientePelaLocacao(codLocacao);

        return consulta;
    }

    private Vector criarLinhaAcessorio(Acessorio acessorio) {
        Vector linha = new Vector();
        linha.addElement(acessorio.getDescAcessorio());
        return linha;
    }

    private Cliente selecionarClientePorCpf(String cpf) throws MinhaException, SQLException, ConexaoException {
        ClienteDao clientDao = FabricaDao.getClienteDao("JDBC");
        Cliente client = clientDao.selecionarClientePorCpf(cpf);
        return client;
    }

    private Vector selecionarClientePelaLocacao(String codLocacao) throws MinhaException, SQLException, ConexaoException {
        ClienteDao clientDao = FabricaDao.getClienteDao("JDBC");
        Vector consult = clientDao.selecionarClientePelaLocacao(codLocacao);
        return consult;
    }

    public Vector obterCarro(String cpf) throws ConexaoException, SQLException, MinhaException { 
        Vector carroCliente = this.selecionarCarroPorCpf(cpf);
        return carroCliente;
    }

    private Vector selecionarCarroPorCpf(String cpf) throws MinhaException, SQLException, ConexaoException  {
        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        GrupoCarroDao grupoCarroDao = FabricaDao.getGrupoCarroDao("JDBC");
        CarroDao carDao = FabricaDao.getCarroDao("JDBC");

        Locacao loca  = locacaoDao.selecionarCarroPorCpf(cpf);

        int codCar = loca.getCarro().getCodCarro();
        GrupoCarro grupoCar = grupoCarroDao.selecionarGrupoCarroPorCod(codCar);
        Carro car = carDao.selecionarCarro(codCar);
        
        Vector carroCliente = new Vector();

        carroCliente.addElement(car.getPlaca());                                        //0
        carroCliente.addElement(grupoCar.getNomeGrupo());                               //1
        carroCliente.addElement(car.getMarca());                                        //2
        carroCliente.addElement(loca.getQuilometragemInicial());                        //3
        carroCliente.addElement(loca.getCobertura());                                   //4
        carroCliente.addElement(loca.getValorPrevisto());                               //5
        carroCliente.addElement(loca.getDateLocacao());                                 //6
        carroCliente.addElement(loca.getDataEntrega());                                 //7
        carroCliente.addElement(grupoCar.getPrecoDiaria());                             //8
        carroCliente.addElement(grupoCar.getPrecoQuilometroAdicional());                //9
        carroCliente.addElement(codCar);                                                //10
        carroCliente.addElement(loca.getCodLocacao());                                  //11
        carroCliente.addElement(loca.getPlano());                                       //12
        carroCliente.addElement(grupoCar.getPrecoDiariaQuilometrada());                 //13
        carroCliente.addElement(grupoCar.getPrecoCobertura());                          //14
        carroCliente.addElement(car.getModelo());                                       //15
        
        return carroCliente;
    }

    public void deletarEntrega() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Entrega entre = this.vetEntregas.get(marc);

        entregaDao.removerEntrega(entre);

        this.vetEntregas.remove(marc);
    }

    public void alterarEntrega(Vector linha) throws ConexaoException, SQLException, ParseException, ClassNotFoundException, MinhaException {
        Entrega entre = new Entrega();
        this.atualizarEntrega(entre, linha);
        entre.setCodEntrega(Integer.parseInt(linha.get(6).toString()));
        entregaDao.alterarEntrega(entre);
    }


}