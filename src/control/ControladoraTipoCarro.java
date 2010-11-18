package control;

import dao.FabricaDao;
import dao.TipoCarroDao;
import domain.TipoCarro;
import util.ConexaoException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import util.MinhaException;

public class ControladoraTipoCarro {

    private TipoCarroDao tipoCarroDao;
    private TipoCarro tCar;
    private Vector<TipoCarro> vetTipoCarro;
    private int marc;

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<TipoCarro> getVetTipoCarro() {
        return vetTipoCarro;
    }

    public void setVetTipoCarro(Vector<TipoCarro> vetTipoCarro) {
        this.vetTipoCarro = vetTipoCarro;
    }

    public ControladoraTipoCarro() {
        this.tipoCarroDao = FabricaDao.getTipoCarroDao("JDBC");
    }

   public void inserirNovoTipoCarro(Vector linha) throws MinhaException, SQLException, ConexaoException {
        TipoCarro tipoCarro = new TipoCarro();
        this.atualizarTipoCarro(tipoCarro, linha);
        tipoCarroDao.inserirTipoCarro(tipoCarro);
    }
   
   private void atualizarTipoCarro(TipoCarro tipoCarro, Vector linha) {
        tipoCarro.setNomeTipoCarro(linha.get(0).toString());
        tipoCarro.setDescTipoCarro(linha.get(1).toString());
    }

   private Vector<TipoCarro> obterTipoCarro() throws MinhaException, SQLException, ConexaoException, ConexaoException, ConexaoException {
        vetTipoCarro = tipoCarroDao.selecionarTodosTipoCarro();
        return vetTipoCarro;
    }

    public Vector obterLinhasTipoCarro() throws MinhaException, SQLException, ConexaoException {
        Vector<TipoCarro> tCarro = obterTipoCarro();
        Vector linhas = new Vector();
        // Montando as linhas
        for (int i = 0; i < tCarro.size(); i++) {
            TipoCarro tC = tCarro.get(i);
            linhas.addElement(this.criarLinhaTipoCarro(tC));
        }

        return linhas;
    }

    private Vector criarLinhaTipoCarro(TipoCarro tC) {
        Vector linha = new Vector();
        linha.addElement(tC.getCodTipoCarro());
        linha.addElement(tC.getNomeTipoCarro());
        linha.addElement(tC.getDescTipoCarro());
        return linha;
    }

    public void deletarTipoCarro() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        TipoCarro tCarro = this.vetTipoCarro.get(marc);

        tipoCarroDao.removerTipoCarro(tCarro);

        this.vetTipoCarro.remove(marc);
    }

    public void alterarTipoCarro(Vector linha) throws ConexaoException, SQLException, ParseException, ClassNotFoundException, MinhaException {
        TipoCarro tc = new TipoCarro();
        this.atualizarTipoCarro(tc, linha);
        tc.setCodTipoCarro(Integer.parseInt(linha.get(2).toString()));
        tipoCarroDao.alterarTipoCarro(tc);
    }

    public Vector obterNomesTipoCarro() throws MinhaException, SQLException, ConexaoException {
        Vector<TipoCarro> tCarro = obterTipoCarro();
        Vector linhas = new Vector();
        for (int i = 0; i < tCarro.size(); i++) {
            TipoCarro tC = tCarro.get(i);
            linhas.addElement(tC.getNomeTipoCarro());
        }

        return linhas;
    }


    
}