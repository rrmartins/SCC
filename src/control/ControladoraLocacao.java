

package control;

import dao.FabricaDao;
import dao.LocacaoDao;
import domain.Locacao;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;




public class ControladoraLocacao {

    private int marc;
    private Vector<Locacao> vetLocacao = new Vector<Locacao>();



    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<Locacao> getVetLocacao() {
        return vetLocacao;
    }


    private Vector linhaLocacao(Locacao locacao){

        Vector linha = new Vector();
        String cobertura;
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        if(locacao.getCobertura() == true)
            cobertura = "Sim";
        else
            cobertura = "Não";

        linha.addElement(locacao.getCodLocacao());
        linha.addElement(locacao.getCliente().getNome());
        linha.addElement(locacao.getCarro().getModelo());
        linha.addElement(locacao.getCarro().getPlaca());
        linha.addElement(formatoData.format(locacao.getDateLocacao()));
        linha.addElement(locacao.getHoraLocacao());
        linha.addElement(formatoData.format(locacao.getDataEntrega()));
        linha.addElement(locacao.getHoraEntrega());
        linha.addElement(locacao.getQuilometragemInicial());
        linha.addElement(locacao.getQuilometragemPrevista());
        linha.addElement(cobertura);
        linha.addElement(decimalFormat.format(locacao.getValorPrevisto()));

        return linha;

    }


    public void alterarLocacao (Vector locacao) throws MinhaException, SQLException, ConexaoException {

        Locacao loc = this.vetLocacao.get(marc);
        this.montarLocacao(locacao, loc);

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        locacaoDao.alterarLocacao(loc);

    }



    public void inserirLocacao (Vector locacao) throws MinhaException, SQLException, ConexaoException {

        Locacao loc = new Locacao();
        this.montarLocacao(locacao, loc);

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        locacaoDao.inserirLocacao(loc);
    }



    public void removerLocacao () throws MinhaException, SQLException, ConexaoException {

        Locacao loc = this.vetLocacao.get(this.marc);

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        locacaoDao.removerLocacao(loc);

        this.vetLocacao.remove(this.marc);
    }



    public Locacao selecionarLocacao (Locacao locacao) {
        return null;
    }



    public Vector<Locacao> selecionarTodasLocacoes () throws MinhaException, SQLException, ConexaoException{

        LocacaoDao locacaoDao = FabricaDao.getLocacaoDao("JDBC");
        this.vetLocacao = locacaoDao.selecionarTodasLocacoes();

        return this.vetLocacao;

    }



    public Vector obterLinhasLocacao() throws MinhaException, SQLException, ConexaoException{

        Vector<Locacao> locs = this.selecionarTodasLocacoes();
        Vector linhas = new Vector();

        for(int i = 0; i < locs.size(); i++){
            linhas.add(this.linhaLocacao(locs.get(i)));
        }

        return linhas;
    }


    private Locacao montarLocacao(Vector locacao, Locacao loc) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
