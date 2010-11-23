package dao;

import domain.Carro;
import domain.GrupoCarro;
import domain.TipoCarro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;


public class GrupoCarroJDBCDao implements GrupoCarroDao {


    private Conexao connection;
    private String sql;


    public Conexao getConnection () {
        return connection;
    }


    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public GrupoCarro selecionarGrupoCarro(String nomeGrupoCarro) throws MinhaException, SQLException, ConexaoException {

        GrupoCarro grupoCarro = new GrupoCarro();

        this.connection = FabricaConexao.obterConexao();

        sql = "select gc.* " +
                "from grupo_carro gc " +
                "where gc.nome_grupo_carro = ? ";

        PreparedStatement pStmt = null;
        pStmt = this.connection.prepareStatement(sql);

        pStmt.setString(1, nomeGrupoCarro);


        ResultSet result;

        result = pStmt.executeQuery();

        if (result == null || !result.next()) {
            this.connection.close();

            throw new MinhaException(" NÃ£o existe cliente cadastrado com esse CPF !");

        } else {
            grupoCarro.setCodGrupoCarro(result.getInt("cod_grupo_carro"));
            grupoCarro.setNomeGrupo(result.getString("nome_grupo_carro"));
            grupoCarro.setPrecoCobertura(result.getFloat("preco_cobertura"));
            grupoCarro.setPrecoDiaria(result.getFloat("preco_diaria"));
            grupoCarro.setPrecoDiariaQuilometrada(result.getFloat("preco_diaria_quilometrada"));
        }

        this.connection.close();

        return grupoCarro;

    }


    public GrupoCarro selecionarGrupoCarroPorCod(int codGrupoCar) throws MinhaException, SQLException, ConexaoException {

        GrupoCarro grupoCarro = new GrupoCarro();

        this.connection = FabricaConexao.obterConexao();

        sql = "select gc.* "+
                "from grupo_carro gc, "+
                      "carro ca "+
                "where gc.cod_grupo_carro = ca.cod_grupo_carro and "+
                "ca.cod_carro = ?";

        PreparedStatement pStmt = null;
        pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, codGrupoCar);


        ResultSet result;

        result = pStmt.executeQuery();


        if (result == null || !result.next()) {
            this.connection.close();

            throw new MinhaException(" Não existe esse grupo cadastrado no sistema !");

        } else {
            grupoCarro.setCodGrupoCarro(result.getInt("cod_grupo_carro"));
            grupoCarro.setNomeGrupo(result.getString("nome_grupo_carro"));
            grupoCarro.setPrecoCobertura(result.getFloat("preco_cobertura"));
            grupoCarro.setPrecoDiaria(result.getFloat("preco_diaria"));
            grupoCarro.setPrecoDiariaQuilometrada(result.getFloat("preco_diaria_quilometrada"));
        }


        this.connection.close();
        return grupoCarro;

    }


    public Vector<GrupoCarro> selecionarTodosGrupoCarro() throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        this.sql = "SELECT gc.cod_grupo_carro, gc.nome_grupo_carro, gc.preco_diaria, " +
                "gc.preco_diaria_quilometrada, gc.preco_cobertura, gc.preco_quilometro_adicional, " +
                "tc.nome_tipo_carro, gc.cod_tipo_carro " +
                "FROM grupo_carro gc, " +
                "tipo_carro tc " +
                "WHERE gc.cod_tipo_carro = tc.cod_tipo_carro ;";

        PreparedStatement pStmt = this.connection.prepareStatement(this.sql);

        ResultSet result = pStmt.executeQuery();

        if(result == null || !result.next())
            throw new MinhaException("Não existem Grupos de Carros cadastrados no Sistema !");

        Vector<GrupoCarro> grupoCarros = new Vector<GrupoCarro>();

        do{
            TipoCarro tipoCarro = new TipoCarro();
            tipoCarro.setCodTipoCarro(result.getInt("cod_tipo_carro"));
            tipoCarro.setNomeTipoCarro(result.getString("nome_tipo_carro"));

            GrupoCarro grupo = new GrupoCarro();
            grupo.setCodGrupoCarro(result.getInt("cod_grupo_carro"));
            grupo.setNomeGrupo(result.getString("nome_grupo_carro"));
            grupo.setPrecoDiaria(result.getDouble("preco_diaria"));
            grupo.setPrecoDiariaQuilometrada(result.getDouble("preco_diaria_quilometrada"));
            grupo.setPrecoCobertura(result.getDouble("preco_cobertura"));
            grupo.setPrecoQuilometroAdicional(result.getDouble("preco_quilometro_adicional"));
            grupo.setTipoCarro(tipoCarro);

            grupoCarros.addElement(grupo);

        }while(result.next());

        this.connection.close();

        return grupoCarros;
    }

    public GrupoCarro selecionarGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removerGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        this.sql = "DELETE FROM grupo_carro WHERE cod_grupo_carro = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, grupoCarro.getCodGrupoCarro());
        pStmt.executeUpdate();

        this.connection.close();

    }

    public void alterarGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        try{

            this.connection.setAutoCommit(false);

            this.sql = "UPDATE grupo_carro " +
                    "SET cod_tipo_carro = ? ," +
                    "nome_grupo_carro = ? , " +
                    "preco_diaria = ? ," +
                    "preco_diaria_quilometrada = ? , " +
                    "preco_cobertura = ? , " +
                    "preco_quilometro_adicional =  ? " +
                    "WHERE cod_grupo_carro = ? ;";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            pStmt.setInt(1, grupoCarro.getTipoCarro().getCodTipoCarro());
            pStmt.setString(2, grupoCarro.getNomeGrupo());
            pStmt.setDouble(3, grupoCarro.getPrecoDiaria());
            pStmt.setDouble(4, grupoCarro.getPrecoDiariaQuilometrada());
            pStmt.setDouble(5, grupoCarro.getPrecoCobertura());
            pStmt.setDouble(6, grupoCarro.getPrecoQuilometroAdicional());
            pStmt.setInt(7, grupoCarro.getCodGrupoCarro());

            pStmt.executeUpdate();

            this.connection.commit();

        }
        catch (SQLException erro){
            this.connection.rollback();
            throw new MinhaException(erro.getMessage());

        }
        finally{
            this.connection.close();
        }


    }

    public int selecionarCodigoCadastrado(Conexao conn) throws SQLException, MinhaException{

        try{

            this.sql = "SELECT max(cod_grupo_carro) as ultimo_codigo " +
                "FROM grupo_carro ;";


            conn.setAutoCommit(false);
            PreparedStatement pStmt = conn.prepareStatement(sql);

            ResultSet result = pStmt.executeQuery();
            result.next();

            int resultado = result.getInt("ultimo_codigo");

            return resultado;

        }
        catch (SQLException erro){
            this.connection.rollback();
            throw new MinhaException(erro.getMessage());

        }


    }

    public void inserirGrupoCarro(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException {


        this.connection = FabricaConexao.obterConexao();

        try{

            this.connection.setAutoCommit(false);

            this.sql = "INSERT INTO grupo_carro(cod_tipo_carro, nome_grupo_carro, preco_diaria, " +
                    "preco_diaria_quilometrada, preco_cobertura, preco_quilometro_adicional) " +
                    "VALUES (?, ?, ?, ?, ?, ?) ;";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            pStmt.setInt(1, grupoCarro.getTipoCarro().getCodTipoCarro());
            pStmt.setString(2, grupoCarro.getNomeGrupo());
            pStmt.setDouble(3, grupoCarro.getPrecoDiaria());
            pStmt.setDouble(4, grupoCarro.getPrecoDiariaQuilometrada());
            pStmt.setDouble(5, grupoCarro.getPrecoCobertura());
            pStmt.setDouble(6, grupoCarro.getPrecoQuilometroAdicional());

            pStmt.executeUpdate();

            int codigo = this.selecionarCodigoCadastrado(connection);
            this.inserirAcessoriosGrupoCarro(codigo, connection, grupoCarro);

            this.connection.commit();

        }
        catch (SQLException erro){
            this.connection.rollback();
            throw new MinhaException(erro.getMessage());

        }
        finally{
            this.connection.close();
        }

    }

    private void inserirAcessoriosGrupoCarro(int codigo, Conexao connection, GrupoCarro gc) throws MinhaException, SQLException {

        try{

            connection.setAutoCommit(false);

            int quantAcessorios = gc.getAcessorios().size();
            for(int i = 0; i < quantAcessorios; i++){

                this.sql = "INSERT INTO acessorio_grupo_carro (cod_grupo_carro, cod_acessorio) " +
                        "VALUES (?, ?) ;";

                PreparedStatement pStmt = this.connection.prepareStatement(sql);
                pStmt.setInt(1, codigo);
                pStmt.setInt(2, gc.getAcessorios().get(i).getCodAcessorio());

                pStmt.executeUpdate();

            }

        }
        catch (SQLException erro){
            connection.rollback();
            throw new MinhaException(erro.getMessage());

        }
    }

    public GrupoCarro selecionarGrupoCarroPorCarro(Carro carro) throws SQLException, MinhaException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.sql = "SELECT gc.cod_grupo_carro, gc.nome_grupo_carro " +
                "FROM carro c, " +
                "grupo_carro gc " +
                "WHERE c.cod_grupo_carro = gc.cod_grupo_carro AND " +
                "c.cod_carro = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);
        pStmt.setInt(1, carro.getCodCarro());

        ResultSet result = pStmt.executeQuery();

        GrupoCarro grupo = new GrupoCarro();

        if(result == null || !result.next())
            throw new MinhaException("Não existem Grupos de Carros cadastrados no Sistema !");
        else{

            grupo.setCodGrupoCarro(result.getInt("cod_grupo_carro"));
            grupo.setNomeGrupo(result.getString("nome_grupo_carro"));
        }

        return grupo;

    }

}

