package dao;

import domain.Cidade;
import domain.Endereco;
import domain.Funcionario;
import domain.Pessoa;
import domain.UF;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;



public class FuncionarioJDBCDao implements FuncionarioDao {


    private Conexao connection;


    public Conexao getConnection () {
        return connection;
    }


    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<Funcionario> selecionarTodosFuncionarios() throws MinhaException, ConexaoException, SQLException{

        this.connection = FabricaConexao.obterConexao();

        String sql = "SELECT f.*, " +
                "cid.nome_cidade as nome_cidade, " +
                "uf.sigla_uf as sigla_uf,  " +
                "uf.nome_uf as nome_uf " +
                "FROM funcionario f, " +
                "cidade cid, " +
                "uf " +
                "WHERE f.cod_cidade = cid.cod_cidade and " +
                "cid.sigla_uf = uf.sigla_uf ; ";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        ResultSet result = pStmt.executeQuery();
        Vector<Funcionario> funcionarios = new Vector<Funcionario>();

        if(result == null || !result.next()) {
            throw new MinhaException("Não existem Funcionarios cadastrados no Sistema !");
        }
        else{
            do{
                Funcionario funcionario = new Funcionario();
                Endereco endereco = new Endereco();
                Cidade cid = new Cidade();
                UF uf = new UF();

                uf.setUF(result.getString("sigla_uf"));
                uf.setNomeUF(result.getString("nome_uf"));

                cid.setCodCidade(result.getInt("cod_cidade"));
                cid.setNomeCidade(result.getString("nome_cidade"));
                cid.setUF(uf);

                endereco.setNomeRua(result.getString("rua"));
                endereco.setNumero(result.getInt("numero_casa"));
                endereco.setNomeBairro(result.getString("bairro"));
                endereco.setCidade(cid);

                funcionario.setCodFuncionario(result.getInt("cod_funcionario"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setCpf(result.getString("cpf"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setTelefone(result.getString("telefone"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setDataNasc((result.getDate("data_nasc")));
                funcionario.setCargo(result.getString("cargo"));
                funcionario.setEndereco(endereco);

                funcionarios.add(funcionario);

            }while(result.next());
        }

        return funcionarios;
    }

    public Vector<Funcionario> selecionarFuncionario(String login) throws MinhaException, ConexaoException, SQLException {
        this.connection = FabricaConexao.obterConexao();

        String sql = "SELECT * FROM funcionario where login ='"+login+"';";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        ResultSet result = pStmt.executeQuery();
        result.next();
        Vector<Funcionario> funcionariosEncontrados = new Vector<Funcionario>();

        do{
            Funcionario funcionario = new Funcionario();
            Endereco endereco = new Endereco();
            Cidade cidade = new Cidade();

            cidade.setCodCidade(result.getInt("cod_cidade"));

            endereco.setNomeRua(result.getString("rua"));
            endereco.setNumero(result.getInt("numero_casa"));
            endereco.setNomeBairro(result.getString("bairro"));
            endereco.setCidade(cidade);

   /*0*/     funcionario.setCodFuncionario(result.getInt("cod_funcionario"));
   /*1*/     funcionario.setNome(result.getString("nome"));
   /*2*/     funcionario.setCpf(result.getString("cpf"));
   /*3*/     funcionario.setLogin(result.getString("login"));
   /*4*/     funcionario.setSenha(result.getString("senha"));
               

   /*5*/     //funcionario.setEmail(result.getString("email"));
   /*6*/     //funcionario.setTelefone(result.getString("telefone"));
   /*7*/     //funcionario.setDataNasc((result.getDate("datanascimento")));
   /*8*/     funcionario.setCargo(result.getString("cargo"));
   /*9*/     funcionario.setEndereco(endereco);

            funcionariosEncontrados.add(funcionario);


        }while(result.next());

        this.connection.close();

        return funcionariosEncontrados;
    }

    public void removerFuncionario(Funcionario funcionario) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        String sql = "DELETE FROM funcionario WHERE cod_funcionario = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, funcionario.getCodFuncionario());

        pStmt.executeUpdate();

        this.connection.close();


    }

    public void alterarFuncionario(Funcionario funcionario) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();
        this.connection.setAutoCommit(true);

        String sql = "UPDATE funcionario " +
                "SET nome = ?, " +
                "cpf = ?, " +
                "data_nasc = ?, " +
                "rua = ?, " +
                "numero_casa = ?, " +
                "bairro = ?, " +
                "email = ?, " +
                "telefone = ?, " +
                "login = ?, " +
                "senha = ?, " +
                "cargo = ?, " +
                "cod_cidade = ? " +
                "WHERE cod_funcionario = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setString(1, funcionario.getNome());
        pStmt.setString(2, funcionario.getCpf());
        pStmt.setDate(3,(Date) funcionario.getDataNasc());
        pStmt.setString(4, funcionario.getEndereco().getNomeRua());
        pStmt.setInt(5, funcionario.getEndereco().getNumero());
        pStmt.setString(6, funcionario.getEndereco().getNomeBairro());
        pStmt.setString(7, funcionario.getEmail());
        pStmt.setString(8, funcionario.getTelefone());
        pStmt.setString(9, funcionario.getLogin());
        pStmt.setString(10, funcionario.getSenha());
        pStmt.setString(11, funcionario.getCargo());
        pStmt.setInt(12, funcionario.getEndereco().getCidade().getCodCidade());
        pStmt.setInt(13, funcionario.getCodFuncionario());

        pStmt.executeUpdate();

        this.connection.close();
    }

    public boolean inserirFuncionario(Funcionario funcionario) throws MinhaException, ConexaoException, SQLException {

        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);

            String sql = "INSERT INTO funcionario(nome, cpf, data_nasc, rua, numero_casa, bairro, email, telefone, login, senha, cargo, cod_cidade) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);

            pStmt.setString(1, funcionario.getNome());
            pStmt.setString(2, funcionario.getCpf());
            pStmt.setDate(3,(Date) funcionario.getDataNasc());
            pStmt.setString(4, funcionario.getEndereco().getNomeRua());
            pStmt.setInt(5, funcionario.getEndereco().getNumero());
            pStmt.setString(6, funcionario.getEndereco().getNomeBairro());
            pStmt.setString(7, funcionario.getEmail());
            pStmt.setString(8, funcionario.getTelefone());
            pStmt.setString(9, funcionario.getLogin());
            pStmt.setString(10, funcionario.getSenha());
            pStmt.setString(11, funcionario.getCargo());
            pStmt.setInt(12, funcionario.getEndereco().getCidade().getCodCidade());

            pStmt.executeUpdate();
            this.connection.commit();
        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return true;
        }
       
    }

    public Vector<Funcionario> selecionarFuncionariosPorCargo(String cargo) throws MinhaException, ConexaoException, SQLException {

        this.connection = FabricaConexao.obterConexao();


        String sql = "SELECT * FROM funcionario " +
                "WHERE cargo = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        pStmt.setString(1, cargo);

        ResultSet result = pStmt.executeQuery();
        result.next();

        Vector<Funcionario> funcionariosEncontrados = new Vector<Funcionario>();

        do{
            Funcionario funcionario = new Funcionario();
            Endereco endereco = new Endereco();
            Cidade cidade = new Cidade();

            cidade.setCodCidade(result.getInt("cod_cidade"));

            endereco.setNomeRua(result.getString("rua"));
            endereco.setNumero(result.getInt("numero_casa"));
            endereco.setNomeBairro(result.getString("bairro"));
            endereco.setCidade(cidade);

            funcionario.setCodFuncionario(result.getInt("cod_funcionario"));
            funcionario.setNome(result.getString("nome"));
            funcionario.setCpf(result.getString("cpf"));

            funcionario.setEmail(result.getString("email"));
            funcionario.setTelefone(result.getString("telefone"));
            funcionario.setDataNasc((result.getDate("data_nasc")));
            funcionario.setCargo(result.getString("cargo"));
            funcionario.setEndereco(endereco);

            funcionariosEncontrados.add(funcionario);


        }while(result.next());

        this.connection.close();

        return funcionariosEncontrados;
    }

}