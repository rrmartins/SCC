package control;


import dao.*;
import domain.*;
import domain.Cidade;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import javax.swing.JComboBox;
import javax.swing.JTable;
import util.ConexaoException;
import java.sql.*;
import java.util.*;
import javax.swing.JTextField;
import util.Conexao;
import util.MinhaException;

public class ControladoraCidade {

    private CidadeDao CidadeDao;
    private Vector<UF> Uf;
    private UFDao ufDao;
    private Vector<Cidade> Cidade;
    private Vector<Cidade> vetCidades;
    private int marc;

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<Cidade> getVetCidades() {
        return vetCidades;
    }

    public void setVetCidades(Vector<Cidade> vetCidades) {
        this.vetCidades = vetCidades;
    }

    public ControladoraCidade() {
        this.ufDao = FabricaDao.getUFDao("JDBC");
        this.CidadeDao = FabricaDao.getCidadeDao("JDBC");
    }

   public void inserirNovaCidade(Vector linha) throws MinhaException, SQLException, ConexaoException {
        Cidade cidade = new Cidade();
        UF uf = new UF();
        this.atualizarCidade(cidade, uf,  linha);
        CidadeDao.inserirCidade(cidade);
    }
   
    private void atualizarCidade(Cidade ci, UF uf, Vector linha) {
        uf.setNomeUF(linha.get(0).toString());
        ci.setUF(uf);
        ci.setNomeCidade(linha.get(1).toString());
    }

    private Vector<Cidade> obterCidade(String estado) throws ConexaoException,MinhaException, SQLException {
        Cidade = CidadeDao.obterCidade(estado);
        return Cidade;
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
            linhas.addElement(uf.getUF());
        }
        
        return linhas;
    }

    public Vector obterLinhasCidades(UF uf) throws MinhaException, SQLException, ConexaoException{

        Vector<Cidade> cidades = this.selecionarTodasCidadesPorEstado(uf);
        Vector linhasCidades = new Vector();

        for(int i = 0; i < cidades.size(); i++)
        {
            linhasCidades.addElement(cidades.get(i).getNomeCidade());
        }

        return linhasCidades;
    }
    
    public Vector<Cidade> selecionarTodasCidadesPorEstado(UF uf) throws MinhaException, SQLException, ConexaoException{

        CidadeDao cidadeDao = FabricaDao.getCidadeDao("JDBC");
        this.vetCidades = cidadeDao.selecionarTodasCidadesPorEstado(uf);

        return this.vetCidades;
    }

    public Vector preencherComboEstado(String estado) throws ConexaoException, SQLException, MinhaException{
        Vector<Cidade> cidades = obterCidade(estado);
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < cidades.size(); i++) {
            Cidade cidade = cidades.get(i);
            //linhas.addElement(this.criarLinhaUF(uf));
            linhas.addElement(cidade.getNomeCidade());
        }
        
        return linhas;
    }

    public Cidade selecionarCidadePorNome(Cidade cidade) throws MinhaException, SQLException, ConexaoException
    {
        CidadeDao cidadeDao = FabricaDao.getCidadeDao("JDBC");
        Cidade cidadeBuscada = cidadeDao.selecionarCidade(cidade);

        return cidadeBuscada;
    }

    public Cidade selecionarCidadePorCodigo(int codigo)  throws MinhaException, SQLException, ConexaoException
    {
        CidadeDao cidadeDao = FabricaDao.getCidadeDao("JDBC");
        Cidade cidadeBuscada = cidadeDao.selecionarCidadePorCodigo(codigo);

        return cidadeBuscada;

    }

    private Vector<Cidade> obterCid() throws MinhaException, SQLException, ConexaoException {
        vetCidades = CidadeDao.selecionarTodasCidades();
        return vetCidades;
    }

    @SuppressWarnings("unchecked")
    public Vector obterCidade() throws MinhaException, SQLException, ConexaoException {
        Vector<Cidade> cidad = obterCid();
        Vector linhas = new Vector();
        // Montando as linhas
        for (int i = 0; i < cidad.size(); i++) {
            Cidade cid = cidad.get(i);
            linhas.addElement(this.criarLinhaCidade(cid));
        }

        return linhas;
    }

    @SuppressWarnings("unchecked")
    private Vector criarLinhaCidade(Cidade cidade) {
        Vector linha = new Vector();
        linha.addElement(cidade.getCodCidade());
        linha.addElement(cidade.getUF().getNomeUF());
        linha.addElement(cidade.getNomeCidade());
        return linha;
    }

    public void deletarCidade() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Cidade cidad = this.vetCidades.get(marc);

        CidadeDao.removerCidade(cidad);

        this.vetCidades.remove(marc);
    }

    public void alterarCidade(Vector linha) throws ConexaoException, SQLException, ClassNotFoundException, MinhaException {
        Cidade cidade = new Cidade();
        UF uf = new UF();
        this.atualizarCidade(cidade, uf, linha);
        cidade.setCodCidade(Integer.parseInt(linha.get(2).toString()));
        CidadeDao.alterarCidade(cidade);
    }


}