package control;

import dao.CidadeDao;
import dao.FabricaDao;
import dao.OficinaDao;
import dao.UFDao;
import domain.Cidade;
import domain.Endereco;
import domain.Oficina;
import domain.UF;
import util.ConexaoException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import util.MinhaException;

public class ControladoraOficina {

    private OficinaDao oficinaDao;
    private Vector<UF> Uf;
    private Vector<Cidade> Cidade;
    private UFDao ufDao;
    private CidadeDao cidadeDao;
    private Vector<Oficina> vetOficina;
    private int marc;

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector<Oficina> getVetOficina() {
        return vetOficina;
    }

    public void setVetOficina(Vector<Oficina> vetOficina) {
        this.vetOficina = vetOficina;
    }
    

    public ControladoraOficina() {
        this.oficinaDao = FabricaDao.getOficinaDao("JDBC");
        this.ufDao = FabricaDao.getUFDao("JDBC");
        this.cidadeDao = FabricaDao.getCidadeDao("JDBC");
    }

   public void inserirNovoOficina(Vector linha) throws ConexaoException, SQLException, MinhaException {
        Oficina Oficina = new Oficina();
        Cidade ci = new Cidade();
        this.atualizarOficina(Oficina, ci, linha);
        int codCidade = cidadeDao.obterCodCidade(ci.getNomeCidade());
        oficinaDao.inserirOficina(Oficina, ci, codCidade);
    }
   
    private void atualizarOficina(Oficina oficina, Cidade ci, Vector linha) {
        Endereco end = new Endereco();

        oficina.setNomeOficina(linha.get(0).toString());
        oficina.setCnpj(linha.get(1).toString());
        oficina.setTelefone(linha.get(2).toString());
        ci.setNomeCidade(linha.get(4).toString());
        oficina.setCodCidade(ci);
        end.setNomeRua(linha.get(5).toString());
        end.setNumero(Integer.parseInt(linha.get(6).toString()));
        end.setNomeBairro(linha.get(7).toString());
        oficina.setEndereco(end);
    }

    private Vector<Cidade> obterCidade(String estado) throws ConexaoException,SQLException, MinhaException {
        Cidade = cidadeDao.obterCidade(estado);
        return Cidade;
    }

    private Vector<UF> obterUF() throws MinhaException, SQLException, ConexaoException {
        Uf = ufDao.obterUF();
        return Uf;
    }

    private Vector<Cidade> obterUFCidade(int cod) throws MinhaException, SQLException, ConexaoException {
        Cidade = cidadeDao.obterUFCidade(cod);
        return Cidade;
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

    public Vector obterUFCidadePorCod(int cod) throws MinhaException, SQLException, ConexaoException {
        Vector<Cidade> ufCidade = obterUFCidade(cod);
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < ufCidade.size(); i++) {
            Cidade cid = ufCidade.get(i);
            linhas.addElement(cid.getUF().getNomeUF());
            linhas.addElement(cid.getNomeCidade());
        }

        return linhas;
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

    private Vector<Oficina> obterOficina(String texto)throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        this.vetOficina = oficinaDao.obterOficina(texto);
        return vetOficina;
    }

    public Vector obterUFPorCidade(String nome) throws MinhaException, SQLException, ConexaoException {
        Vector<UF> ufCidade = obterUF(nome);
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < ufCidade.size(); i++) {
            UF uf = ufCidade.get(i);
            linhas.addElement(uf.getNomeUF());
        }

        return linhas;
    }

    private Vector<UF> obterUF(String nome) throws MinhaException, SQLException, ConexaoException {
        Uf = ufDao.obterUF(nome);
        return Uf;
    }

    @SuppressWarnings("unchecked")
    public Vector obterLinhasOficina(String texto) throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Vector<Oficina> ofic = this.obterOficina(texto);
        Vector linhasOficina = new Vector();

        for(int i = 0; i < ofic.size(); i++)
        {
            Oficina oficin = ofic.get(i);
            linhasOficina.addElement(this.criadorLinhaOficina(oficin));
        }

        return linhasOficina;
    }

    @SuppressWarnings({"unchecked"})
    public Vector criadorLinhaOficina(Oficina ofi)
    {
        Vector linha = new Vector();
        linha.addElement(ofi.getCodOficina());
        linha.addElement(ofi.getCodCidade().getNomeCidade());
        linha.addElement(ofi.getNomeOficina());
        linha.addElement(ofi.getCnpj());
        linha.addElement(ofi.getTelefone());
        linha.addElement(ofi.getEndereco().getNomeRua());
        linha.addElement(ofi.getEndereco().getNumero());
        linha.addElement(ofi.getEndereco().getNomeBairro());
        
        return linha;
    }

    public void alterarOficina(Vector linha) throws ConexaoException, SQLException, ParseException, ClassNotFoundException, MinhaException {
        Oficina ofic = new Oficina();
        Cidade ci = new Cidade();
        this.atualizarOficina(ofic, ci, linha);
        ofic.setCodOficina(Integer.parseInt(linha.get(8).toString()));
        ci.setCodCidade(Integer.parseInt(linha.get(9).toString()));
        ofic.setCodCidade(ci);
        oficinaDao.alterarOficina(ofic);
    }

    public void deletarOficina() throws SQLException, ClassNotFoundException, MinhaException, ConexaoException
    {
        Oficina ofi = this.vetOficina.get(marc);

        oficinaDao.removerOficina(ofi);

        this.vetOficina.remove(marc);
    }

    public Vector<Oficina> selecionarTodasOficinas () throws MinhaException, SQLException, ConexaoException {

        this.vetOficina = oficinaDao.selecionarTodasOficinas();

        return this.vetOficina;
    }

    public Vector obterOficinas() throws MinhaException, SQLException, ConexaoException
    {
        Vector<Oficina> ofic = this.selecionarTodasOficinas();
        Vector oficinasBuscadas = new Vector();

        for(int i = 0; i < ofic.size(); i++)

            oficinasBuscadas.add(ofic.get(i).getNomeOficina());

        return oficinasBuscadas;
    }

}