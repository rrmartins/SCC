package control;

import dao.AcessorioDao;
import dao.FabricaDao;
import domain.Acessorio;
import domain.GrupoCarro;
import groovyjarjarcommonscli.ParseException;
import java.sql.SQLException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;



public class ControladoraAcessorio {

    private AcessorioDao acessorioDao;
    private Vector<Acessorio> vetAcessorio;
    private int marc;

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public ControladoraAcessorio() {
        this.acessorioDao = FabricaDao.getAcessorioDao("JDBC");
    }

    public Vector<Acessorio> selecionarAcessoriosGrupo(GrupoCarro gc) throws MinhaException, SQLException, ConexaoException{

        this.acessorioDao = FabricaDao.getAcessorioDao("JDBC");
        Vector acessorios = this.acessorioDao.selecionarAcessorioGrupo(gc);

        return acessorios;

    }


    public Vector obterAcessoriosGrupo(GrupoCarro gc) throws MinhaException, SQLException, ConexaoException {

        Vector linhasAcessorios = new Vector();
        Vector<Acessorio> acessorioGrupo = this.selecionarAcessoriosGrupo(gc);

        for (int i = 0; i < acessorioGrupo.size(); i++) {

            Acessorio acessorio = acessorioGrupo.get(i);
            linhasAcessorios.addElement(this.criarLinhaAcessorio(acessorio));
        }
        return linhasAcessorios;

    }

    public void inserirNovoAcessorio(Vector linha) throws MinhaException, SQLException, ConexaoException {
        Acessorio acessorio = new Acessorio();
        this.atualizarAcessorio(acessorio, linha);
        acessorioDao.inserirAcessorio(acessorio);
    }
   
    private void atualizarAcessorio(Acessorio acessorio, Vector linha) {
        acessorio.setDescAcessorio(linha.get(0).toString());
        
    }

    private Vector<Acessorio> obterAcessorio() throws MinhaException, SQLException, ConexaoException {
        vetAcessorio = acessorioDao.selecionarTodosAcessorio();
        return vetAcessorio;
    }

    @SuppressWarnings("unchecked")
    public Vector obterLinhasAcessorio() throws MinhaException, SQLException, ConexaoException {
        Vector<Acessorio> acessorios = obterAcessorio();
        Vector linhas = new Vector();
        // Montando as linhas
        for (int i = 0; i < acessorios.size(); i++) {
            Acessorio acesso = acessorios.get(i);
            linhas.addElement(this.criarLinhaAcessorio(acesso));
        }

        return linhas;
    }

    private Vector criarLinhaAcessorio(Acessorio acessorio) {
        Vector linha = new Vector();
        linha.addElement(acessorio.getCodAcessorio());
        linha.addElement(acessorio.getDescAcessorio());
        return linha;
    }

    public void deletarAcessorio() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Acessorio acess = this.vetAcessorio.get(marc);

        acessorioDao.removerAcessorio(acess);

        this.vetAcessorio.remove(marc);
    }

    public void alterarAcessorio(Vector linha) throws ConexaoException, SQLException, ParseException, ClassNotFoundException, MinhaException {
        Acessorio acess = new Acessorio();
        this.atualizarAcessorio(acess, linha);
        acess.setCodAcessorio(Integer.parseInt(linha.get(1).toString()));
        acessorioDao.alterarAcessorio(acess);
    }


}