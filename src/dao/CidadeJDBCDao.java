package dao;

import domain.Cidade;
import domain.UF;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class CidadeJDBCDao implements CidadeDao {

    private Conexao connection;
    private String sql;
    
    public Conexao getConnection () {
        return connection;
    }

    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Vector<Cidade> selecionarTodasCidades() throws SQLException, MinhaException,ConexaoException {
        sql = "Select * from cidade";

        this.connection = FabricaConexao.obterConexao();
        
        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        Vector cidades = new Vector();
        while (res.next()) {
            Cidade cidad = new Cidade();
            UF uf = new UF();
            
            uf.setNomeUF(res.getString("sigla_uf"));
            
            cidad.setCodCidade(res.getInt("cod_cidade"));
            cidad.setUF(uf);
            cidad.setNomeCidade(res.getString("nome_cidade"));

            cidades.addElement(cidad);
        }
        this.connection.close();
        return cidades;
    }

    public Cidade selecionarCidade(Cidade cidade) throws MinhaException, ConexaoException, SQLException {

        Cidade cidadeBuscada = new Cidade();

        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);

            this.sql = "SELECT * FROM cidade WHERE nome_cidade = ?;";
            PreparedStatement pStmt = this.connection.prepareStatement(sql);

            pStmt.setString(1, cidade.getNomeCidade());

            ResultSet result = pStmt.executeQuery();
            result.next();

            do
            {
                cidadeBuscada.setCodCidade(result.getInt("cod_cidade"));
                cidadeBuscada.setNomeCidade(result.getString("nome_cidade"));

            }while(result.next());

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return cidadeBuscada;
        }
    }

    public void removerCidade(Cidade cidade) throws SQLException, MinhaException, ConexaoException
    {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
             sql = "DELETE FROM cidade " +
                "WHERE cod_cidade = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            prepSt.setInt(1, cidade.getCodCidade());
            prepSt.executeUpdate();
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
        }
    }

    public void alterarCidade(Cidade cidade) throws SQLException, MinhaException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE cidade SET " +
                    "sigla_uf = ? ," +
                    "nome_cidade = ? "+
                    "WHERE cod_cidade = ? ;";

            PreparedStatement prepSt = this.connection.prepareStatement(sql);
            
            prepSt.setString(1, cidade.getUF().getNomeUF());
            prepSt.setString(2, cidade.getNomeCidade());
            prepSt.setInt(3, cidade.getCodCidade());

            prepSt.executeUpdate();
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
        }
    }

    public void inserirCidade(Cidade cidade) throws SQLException, MinhaException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(false);
            sql = "INSERT INTO cidade (sigla_uf,nome_cidade) values (?,?);";
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            pstmt.setString(1, cidade.getUF().getNomeUF());
            pstmt.setString(2, cidade.getNomeCidade());
            
            pstmt.executeUpdate();
            this.connection.commit();
        } catch (SQLException erro) {
            this.connection.rollback();
            throw erro;
        } finally {
            this.connection.close();
        }

    }

    public Vector<Cidade> obterCidade(String estado) throws MinhaException, SQLException, ConexaoException {
        sql = "Select nome_cidade from cidade where sigla_uf = '"+ estado +"'";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        Vector cidades = new Vector();
        while (res.next()) {
            Cidade cidade = new Cidade();
            cidade.setNomeCidade(res.getString("nome_cidade"));

            cidades.addElement(cidade);
        }
        this.connection.close();
        return cidades;
    }

    public int obterCodCidade(String cidade) throws MinhaException, SQLException, ConexaoException {
        sql = "Select cod_cidade from cidade where nome_cidade = '"+ cidade +"'";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        int codCidade = 0;
        if(res.next()){
            codCidade = res.getInt(1);
        }
        
        this.connection.close();
        return codCidade;
    }

    public Cidade selecionarCidadePorCodigo(int codigo)  throws MinhaException, SQLException, ConexaoException
    {
        Cidade cidadeBuscada = new Cidade();
        UF uf = new UF();

        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);

            this.sql = "SELECT * FROM cidade WHERE cod_cidade = ?;";
            PreparedStatement pStmt = this.connection.prepareStatement(sql);

            pStmt.setInt(1, codigo);

            ResultSet result = pStmt.executeQuery();
            result.next();


            cidadeBuscada.setCodCidade(result.getInt("cod_cidade"));
            cidadeBuscada.setNomeCidade(result.getString("nome_cidade"));
            uf.setUF(result.getString("sigla_uf"));
            cidadeBuscada.setUF(uf);

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            throw erro;
        }
        finally
        {
            this.connection.close();
            return cidadeBuscada;
        }

    }

    public Vector<Cidade> selecionarTodasCidadesPorEstado(UF uf) throws MinhaException, ConexaoException, SQLException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        this.sql = "SELECT * " +
                "FROM cidade " +
                "WHERE sigla_uf = ? ;";

        PreparedStatement psStm = this.connection.prepareStatement(this.sql);
        psStm.setString(1, uf.getUF());

        ResultSet result = psStm.executeQuery();
        //result.next();

        Vector<Cidade> vetCidade = new Vector<Cidade>();

        if(result == null || !result.next())
        {
            this.connection.close();
            return vetCidade;
        }
        else
        {
            do
            {
                Cidade novaCidade = new Cidade();
                novaCidade.setCodCidade(result.getInt("cod_cidade"));
                novaCidade.setNomeCidade(result.getString("nome_cidade"));
                novaCidade.setUF(uf);

                vetCidade.addElement(novaCidade);

            }while(result.next());
        }

        this.connection.close();

        return vetCidade;
    }

    @SuppressWarnings("unchecked")
    public Vector<Cidade> obterUFCidade(int cod) throws MinhaException, SQLException, ConexaoException {
        sql = "select ci.nome_cidade, "+
                     "ci.sigla_uf "+
                "from cidade ci "+
                "where ci.cod_cidade = "+ cod +";";

        this.connection = FabricaConexao.obterConexao();

        PreparedStatement pstmt = this.connection.prepareStatement(sql);
        ResultSet res = pstmt.executeQuery();

        Vector ufCidade = new Vector();
        while (res.next()) {
            Cidade cid = new Cidade();

            UF uf = new UF();
            uf.setNomeUF(res.getString("sigla_uf"));

            cid.setNomeCidade(res.getString("nome_cidade"));
            cid.setUF(uf);
            
            ufCidade.addElement(cid);
        }
        this.connection.close();
        return ufCidade;
    }

}

