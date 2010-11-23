

package control;

import dao.FabricaDao;
import dao.RevisaoDao;
import domain.Entrega;
import domain.Funcionario;
import domain.Oficina;
import domain.Revisao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
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

        if(rev.getFuncionario() == null)
            revisao.addElement(rev.getOficina().getNomeOficina());
        else
            revisao.addElement(rev.getFuncionario().getNome());

        revisao.addElement(rev.getDescRevisao());
        revisao.addElement(rev.getValorRevisao());

        String data = formatoData.format(rev.getDataEntrada());
        revisao.addElement(data);

        data = formatoData.format(rev.getDataSaida());
        revisao.addElement(data);
        

        return revisao;
    }

    public Revisao novaRevisao(Vector revisao, ControladoraFuncionario cf, ControladoraOficina co) throws ParseException{

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        Revisao nova = new Revisao();

        nova.setDescRevisao(revisao.get(1).toString());
        
        String valorTotal = revisao.get(2).toString();
        valorTotal = valorTotal.replace(",", ".");
        
        nova.setValorRevisao(Double.parseDouble(valorTotal));

        java.sql.Date dataE = new java.sql.Date(formatoData.parse(revisao.get(5).toString()).getTime());
        java.sql.Date dataS = new java.sql.Date(formatoData.parse(revisao.get(6).toString()).getTime());

        nova.setDataEntrada(dataE);
        nova.setDataSaida(dataS);
        
        if(revisao.get(5).toString().equals("mecanico"))
            nova.setFuncionario(cf.getVetFuncionarios().get(cf.getMarc()));
        else
            nova.setOficina(co.getVetOficina().get(co.getMarc()));

        return nova;
    }

    public void alterarRevisao (Vector revisao) throws MinhaException, SQLException, ParseException, ConexaoException {

        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        //Revisao atualizada = this.novaRevisao(revisao);

        //revisaoDao.alterarRevisao(atualizada);

    }

    public void inserirRevisao (Vector revisao) throws MinhaException, SQLException, ParseException {

        RevisaoDao revisaoDao = FabricaDao.getRevisaoDao("JDBC");
        //Revisao nova = this.novaRevisao(revisao);

        //revisaoDao.inserirRevisao(nova, null);


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
        this.vetRevisoes = revisaoDao.selecionarTodasRevisoes();
             
        return this.vetRevisoes;
    }

    public Vector obterLinhasRevisao() throws MinhaException, SQLException, ConexaoException{

        Vector<Revisao> revisoes = this.selecionarTodasRevisoes();
        Vector linhas = new Vector();

        for (int i = 0; i < revisoes.size(); i++) {
            linhas.addElement(this.criarLinhaRevisao(revisoes.get(i)));
        }

        return linhas;
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

}