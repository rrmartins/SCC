package control;

import dao.FabricaDao;
import dao.UFDao;
import domain.UF;
import util.ConexaoException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import util.MinhaException;

public class ControladoraUF {

    private UFDao ufDao;
    private Vector<UF> Uf;
    private int marc;

    public Vector<UF> getUf() {
        return Uf;
    }

    public void setUf(Vector<UF> Uf) {
        this.Uf = Uf;
    }

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marcador) {
        this.marc = marcador;
    }

    public ControladoraUF() {
        this.ufDao = FabricaDao.getUFDao("JDBC");
    }

   public void inserirNovaUF(Vector linha) throws MinhaException, SQLException, ConexaoException {
        UF uf = new UF();
        this.atualizarUF(uf,  linha);
        ufDao.inserirUF(uf);
    }
   
    private void atualizarUF(UF uf, Vector linha) {
        uf.setUF(linha.get(0).toString());
        uf.setNomeUF(linha.get(1).toString());
    }

    private Vector<UF> obterUF() throws MinhaException, SQLException, ConexaoException {
        Uf = ufDao.obterUF();
        return Uf;
    }

    public Vector obterLinhasUF() throws MinhaException, SQLException, ConexaoException {
        Vector<UF> ufs = obterUF();
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < ufs.size(); i++) {
            UF uf = ufs.get(i);
            //linhas.addElement(this.criarLinhaUF(uf));
            linhas.addElement(uf.getNomeUF());
        }
        
        return linhas;
    }

    public Vector obterUFs() throws MinhaException, SQLException, ConexaoException {
        Vector<UF> ufs = obterUF();
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < ufs.size(); i++) {
            UF uf = ufs.get(i);
            linhas.addElement(this.criarLinhaUF(uf));
        }

        return linhas;
    }

    private Vector criarLinhaUF(UF uf) {
        Vector linha = new Vector();
        linha.addElement(uf.getUF());
        linha.addElement(uf.getNomeUF());
        return linha;
    }

    public void deletarUF() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        UF uf = this.Uf.get(marc);

        ufDao.removerUF(uf);

        this.Uf.remove(marc);
    }

    public void alterarUF(Vector linha) throws ConexaoException, SQLException, ParseException, ClassNotFoundException, MinhaException {
        UF uf = new UF();
        this.atualizarUF(uf, linha);
        ufDao.alterarUF(uf);
    }


}