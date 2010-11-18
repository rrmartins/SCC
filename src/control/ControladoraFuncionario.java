/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dao.FabricaDao;
import dao.FuncionarioDao;
import domain.Cidade;
import domain.Endereco;
import domain.Funcionario;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;

/**
 *
 * @author aluno-info3
 */
public class ControladoraFuncionario {

    private Vector<Funcionario> vetFuncionarios = new Vector<Funcionario>();
    private int marc;
    private SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public Vector<Funcionario> getVetFuncionarios() {
        return vetFuncionarios;
    }

    public void setVetFuncionarios(Vector<Funcionario> vetFuncionarios) {
        this.vetFuncionarios = vetFuncionarios;
    }


    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public Vector montarLinhaFuncionarioCompleto(Funcionario funcionario) {

        Vector linha = new Vector();

        String data = formatoData.format(funcionario.getDataNasc());

        linha.addElement(funcionario.getCodFuncionario());
        linha.addElement(funcionario.getNome());
        linha.addElement(funcionario.getCpf());
        linha.addElement(funcionario.getEmail());
        linha.addElement(funcionario.getTelefone());
        linha.addElement(data);
        linha.addElement(funcionario.getLogin());
        linha.addElement(funcionario.getSenha());
        linha.addElement(funcionario.getCargo());
        linha.addElement(funcionario.getEndereco().getNomeRua());
        linha.addElement(funcionario.getEndereco().getNumero());
        linha.addElement(funcionario.getEndereco().getNomeBairro());
        linha.addElement(funcionario.getEndereco().getCidade().getNomeCidade());
        linha.addElement(funcionario.getEndereco().getCidade().getUF().getUF());
        linha.addElement(funcionario.getEndereco().getCidade().getUF().getNomeUF());

        return linha;

    }

    private Vector montarLinhasFuncionarios(Funcionario funcionario) {

        Vector novaLinha = new Vector();

        String data = formatoData.format(funcionario.getDataNasc());

        novaLinha.addElement(funcionario.getCodFuncionario());
        novaLinha.addElement(funcionario.getNome());
        novaLinha.addElement(funcionario.getCpf());
        novaLinha.addElement(funcionario.getEmail());
        novaLinha.addElement(funcionario.getTelefone());
        novaLinha.addElement(data);
        novaLinha.addElement(funcionario.getCargo());
        novaLinha.addElement(funcionario.getEndereco().getNomeRua());
        novaLinha.addElement(funcionario.getEndereco().getNumero());
        novaLinha.addElement(funcionario.getEndereco().getNomeBairro());
        novaLinha.addElement(funcionario.getEndereco().getCidade().getNomeCidade());
        novaLinha.addElement(funcionario.getEndereco().getCidade().getUF().getUF());


        return novaLinha;
    }

    public void criaNovoFuncionario(Funcionario funcionario, Vector novoFuncionario, ControladoraCidade controladoraCidade) throws ParseException, MinhaException, SQLException
    {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String novaData = novoFuncionario.get(2).toString();
        java.sql.Date data = new java.sql.Date(formato.parse(novaData).getTime());

        int numeroCasa = Integer.parseInt(novoFuncionario.get(4).toString());
        
        
        Cidade cidade = new Cidade();
        cidade = controladoraCidade.getVetCidades().get(controladoraCidade.getMarc());

        Endereco endereco = new Endereco();
        endereco.setNomeRua(novoFuncionario.get(3).toString());
        endereco.setNumero(numeroCasa);
        endereco.setNomeBairro(novoFuncionario.get(5).toString());
        endereco.setCidade(cidade);
        

        funcionario.setNome(novoFuncionario.get(0).toString());
        funcionario.setCpf(novoFuncionario.get(1).toString());
        funcionario.setDataNasc(data);
        funcionario.setEndereco(endereco);
        funcionario.setEmail(novoFuncionario.get(8).toString());
        funcionario.setTelefone(novoFuncionario.get(9).toString());
        funcionario.setCargo(novoFuncionario.get(10).toString());
        funcionario.setLogin(novoFuncionario.get(11).toString());
        funcionario.setSenha(novoFuncionario.get(12).toString());

    }

    public void inserirFuncionario(Vector novoFuncionario ,ControladoraCidade controladoraCidade) throws ParseException, SQLException, MinhaException, ConexaoException
    {
        Funcionario funcionario = new Funcionario();
        this.criaNovoFuncionario(funcionario, novoFuncionario, controladoraCidade);

        FuncionarioDao funcionarioDao = FabricaDao.getFuncionarioDao("JDBC");
        funcionarioDao.inserirFuncionario(funcionario);
    }
        
    public Vector<Funcionario> selecionarvetFuncionariosPorCargo(String cargo) throws MinhaException, SQLException, ConexaoException
    {
        FuncionarioDao funcionarioDao = FabricaDao.getFuncionarioDao("JDBC");
        this.vetFuncionarios = funcionarioDao.selecionarFuncionariosPorCargo(cargo);

        return this.vetFuncionarios;
    }

    public Vector obterFuncionariosPorCargo(String cargo) throws MinhaException, SQLException, ConexaoException
    {
        Vector<Funcionario> func = this.selecionarvetFuncionariosPorCargo(cargo);
        Vector FuncionariosCargo = new Vector();

        for(int i = 0; i < func.size(); i++)
            FuncionariosCargo.add(func.get(i).getNome());

        return FuncionariosCargo;
    }

    public void alterarFuncionario (Vector alteracaoFuncionario, ControladoraCidade controladoraCidade) throws ParseException, MinhaException, SQLException, ConexaoException
    {

        Funcionario funcionario = this.vetFuncionarios.get(this.marc);
        this.criaNovoFuncionario(funcionario, alteracaoFuncionario, controladoraCidade);

        FuncionarioDao funcionarioDao = FabricaDao.getFuncionarioDao("JDBC");
        funcionarioDao.alterarFuncionario(funcionario);

    }

    public void removerFuncionario () throws MinhaException, SQLException, ConexaoException
    {

        Funcionario funcionario = this.vetFuncionarios.get(this.marc);

        FuncionarioDao funcionarioDao = FabricaDao.getFuncionarioDao("JDBC");
        funcionarioDao.removerFuncionario(funcionario);

        this.vetFuncionarios.remove(this.marc);
    }

    public Funcionario selecionarFuncionario (Funcionario funcionario)
    {
        return null;
    }

    public Vector<Funcionario> selecionarTodosFuncionarios () throws MinhaException, SQLException, ConexaoException
    {

        FuncionarioDao funcionarioDao = FabricaDao.getFuncionarioDao("JDBC");
        this.vetFuncionarios = funcionarioDao.selecionarTodosFuncionarios();

        return this.vetFuncionarios;
    }

    public Vector obterLinhasFuncionarios() throws MinhaException, SQLException, ConexaoException
    {

        Vector<Funcionario> funcionarios = this.selecionarTodosFuncionarios();
        Vector linhas = new Vector();

        for(int i = 0; i < funcionarios.size(); i++){
            Funcionario funcionario = funcionarios.get(i);

            linhas.addElement(this.montarLinhasFuncionarios(funcionario));
        }

        return linhas;
    }


}
