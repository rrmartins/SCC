

package control;

import dao.CarroDao;
import dao.FabricaConexao;
import dao.FabricaDao;
import dao.FuncionarioDao;
import dao.LocacaoDao;
import dao.OficinaDao;
import dao.RevisaoDao;
import domain.Carro;
import domain.Entrega;
import domain.Funcionario;
import domain.Locacao;
import domain.Oficina;
import domain.Revisao;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;



public class ControladoraRevisao {

    
    private Vector<Revisao> vetRevisoes = new Vector<Revisao>();
    private int marc;
    private Vector novaRevisao;

    public Vector getNovaRevisao() {
        return novaRevisao;
    }

    public void setNovaRevisao(Vector novaRevisao) {
        this.novaRevisao = novaRevisao;
    }

    
    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<Revisao> getVetRevisoes() {
        return vetRevisoes;
    }

    public Vector criarLinhaRevisao(Revisao rev){

        Vector revisao = new Vector();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        revisao.addElement(rev.getCodRevisao());
        revisao.addElement(rev.getEntrega().getLocacao().getCliente().getNome());
        revisao.addElement(rev.getEntrega().getLocacao().getCarro().getPlaca());
        revisao.addElement(rev.getEntrega().getCodEntrega());

        if(rev.getFuncionario().getCodFuncionario() == 0)
            revisao.addElement("O | "+rev.getOficina().getNomeOficina());
        else
            revisao.addElement("M | "+rev.getFuncionario().getNome());

        revisao.addElement(rev.getDescRevisao());
        revisao.addElement(rev.getValorRevisao());

        String data = formatoData.format(rev.getDataEntrada());
        revisao.addElement(data);

        data = formatoData.format(rev.getDataSaida());
        revisao.addElement(data);
        

        return revisao;
    }

    public Revisao novaRevisao(Vector revisao) throws ParseException, MinhaException, ConexaoException, SQLException{

        Revisao nova = new Revisao();
        
        Locacao loca = new Locacao();
        loca.setCodLocacao(Integer.parseInt(revisao.get(7).toString()));

        Entrega entre = new Entrega();
        entre.setCodEntrega(Integer.parseInt(revisao.get(0).toString()));
        entre.setLocacao(loca);
        nova.setEntrega(entre);


        nova.setDescRevisao(revisao.get(1).toString());
        
        String valorTotal = revisao.get(2).toString();
        valorTotal = valorTotal.replace(",", ".");
        
        nova.setValorRevisao(Double.parseDouble(valorTotal));

        nova.setDataEntrada(Date.valueOf(revisao.get(3).toString()));
        nova.setDataSaida(Date.valueOf(revisao.get(4).toString()));
        
        if(revisao.get(5).toString().equals("mecanico")){
            FuncionarioDao funDao = FabricaDao.getFuncionarioDao("JDBC");
            Funcionario fun = funDao.selecionarFuncionariosPorNome(revisao.get(6).toString());
            nova.setFuncionario(fun);
        }
        else{
            OficinaDao oficDao = FabricaDao.getOficinaDao("JDBC");
            Oficina of = oficDao.selecionarOficinaPorNome(revisao.get(6).toString());
            nova.setOficina(of);
        }

        return nova;
    }

    public void alterarRevisao (Vector revisao) throws MinhaException, SQLException, ParseException, ConexaoException {

        Conexao conexao = FabricaConexao.obterConexao();
        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        Revisao atualizada = this.novaRevisao(revisao);
        atualizada.setCodRevisao(Integer.parseInt(revisao.get(8).toString()));
        int codCarro = carroDao.obterCodCarro(Integer.parseInt(revisao.get(7).toString()));
        Carro car = new Carro();
        car.setCodCarro(codCarro);
        revisaoDao.alterarRevisao(atualizada,conexao);
        carroDao.mudarDisponibilidade(conexao, car);

    }

    public void inserirRevisao (Vector revisao) throws MinhaException, SQLException, ParseException, ConexaoException {

        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        Revisao nova = this.novaRevisao(revisao);

        revisaoDao.inserirRevisao(nova);

    }

    public void removerRevisao () throws MinhaException, SQLException, ConexaoException {

        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        revisaoDao.removerRevisao(this.vetRevisoes.get(this.marc));

        this.vetRevisoes.remove(marc);

    }

    public Revisao selecionarRevisao (Revisao revisao) {
        return null;
    }

    public Vector<Revisao> selecionarTodasRevisoes () throws MinhaException, SQLException, ConexaoException {

        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        vetRevisoes = revisaoDao.selecionarTodasRevisoes();
             
        return vetRevisoes;
    }

    public int ultimaRevisao() throws MinhaException, SQLException, ConexaoException {

        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        int ultRevisao = revisaoDao.ultRevisao();

        return ultRevisao;
    }

    public int codLocacao(String nome) throws MinhaException, SQLException, ConexaoException {

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        int codLocacao = locacaoDao.obterCodLocacao(nome);

        return codLocacao;
    }

    public Vector obterLinhasRevisao() throws MinhaException, SQLException, ConexaoException{

        Vector<Revisao> revisoes = this.selecionarTodasRevisoes();
        Vector linhas = new Vector();

        for (int i = 0; i < revisoes.size(); i++) {
            linhas.addElement(this.criarLinhaRevisao(revisoes.get(i)));
        }

        return linhas;
    }

    public int obterUltimaRevisao() throws MinhaException, SQLException, ConexaoException{

        int valorRevisao = this.ultimaRevisao();

        return valorRevisao;
    }

    public int obterCodLocacao(String nome) throws MinhaException, SQLException, ConexaoException{

        int codLocacao = this.codLocacao(nome);

        return codLocacao;
    }

    public Vector buscarDadosCompletos(Revisao rev) {

        Vector revisao = new Vector();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        revisao.addElement(rev.getCodRevisao()); //0
        revisao.addElement(rev.getEntrega().getLocacao().getCliente().getNome()); //1
        revisao.addElement(rev.getEntrega().getLocacao().getCarro().getPlaca()); //2
        revisao.addElement(rev.getEntrega().getLocacao().getCarro().getChassi()); //3
        revisao.addElement(rev.getEntrega().getCodEntrega()); //4

        if(rev.getFuncionario() == null){
           revisao.addElement("oficina"); //5
           revisao.addElement(rev.getOficina().getNomeOficina()); //6
        }  
        else{
            revisao.addElement("funcionario"); //5
            revisao.addElement(rev.getFuncionario().getNome()); //6
        }
            

        revisao.addElement(rev.getDescRevisao()); //7
        revisao.addElement(rev.getValorRevisao()); //8

        String data = formatoData.format(rev.getDataEntrada()); 
        revisao.addElement(data); //9

        data = formatoData.format(rev.getDataSaida()); 
        revisao.addElement(data); //10


        return revisao;
    }

    public Vector obterCarro(Vector linha, int cod) throws MinhaException, SQLException, ConexaoException {

        Locacao loca = new Locacao();
        if (cod == 0){
            loca.setCodLocacao(Integer.parseInt(linha.get(1).toString()));
        }else if (linha == null ){
            loca.setCodLocacao(cod);
        }
        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        Carro carro = carroDao.selecionarCarro(loca);

        Vector vetCarro = new Vector();

        vetCarro.addElement(criarLinhaCarro(carro));

        return vetCarro;
    }

    public Vector criarLinhaCarro(Carro car){

        Vector vetCarro = new Vector();

        vetCarro.addElement(car.getModelo());
        vetCarro.addElement(car.getPlaca());
        vetCarro.addElement(car.getChassi());

        return vetCarro;
    }

}