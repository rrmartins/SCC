package dao;

import domain.Carro;
import domain.GrupoCarro;
import domain.Locacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;



public class CarroJDBCDao implements CarroDao {


    private Conexao connection;
    private String sql;


    public Conexao getConnection () {
        return connection;
    }


    public void setConnection (Conexao val) {
        this.connection = val;
    }

    public Carro selecionarCarro(int codGrupoCar) throws MinhaException, SQLException, ConexaoException {

        Carro car = new Carro();

        this.connection = FabricaConexao.obterConexao();


        sql = "select ca.* " +
                "from carro ca " +
                "where ca.cod_carro = ? ";

        PreparedStatement pStmt = null;

        pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, codGrupoCar);


        ResultSet result;
        result = pStmt.executeQuery();

        if (result == null || !result.next()) {
            this.connection.close();
            throw new MinhaException(" Não existe cliente cadastrado com esse CPF !");

        } else {
            car.setCodCarro(result.getInt("cod_carro"));
            car.setMarca(result.getString("marca"));
            car.setModelo(result.getString("modelo"));
            car.setDescCarro(result.getString("desc_carro"));
            car.setAno(result.getInt("ano"));
            car.setPlaca(result.getString("placa"));
            car.setChassi(result.getString("chassi"));
            car.setDisponivel(result.getBoolean("disponivel"));
            car.setQuilometragem(result.getInt("quilometragem"));

        }

        this.connection.close();


        return car;
    }

    public int obterCodCarro(int codLocacao) throws MinhaException, SQLException, ConexaoException {

        int codCarro;

        this.connection = FabricaConexao.obterConexao();


        sql = "select ca.cod_carro as cod "+
                "from locacao as l, "+
                     "carro as ca "+
                "where ca.cod_carro = l.cod_carro and "+
                      "l.cod_locacao = ? ;";

        PreparedStatement pStmt = null;

        pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, codLocacao);

        ResultSet result;
        result = pStmt.executeQuery();

        if (result == null || !result.next()) {
            this.connection.close();
            throw new MinhaException(" Não existe cliente cadastrado com esse CPF !");

        } else {
            codCarro = result.getInt("cod");
        }

        this.connection.close();


        return codCarro;
    }

    public void inserirCarro(Carro carro) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        try {
            this.connection.setAutoCommit(true);


            this.sql = "INSERT INTO carro (cod_grupo_carro, marca, modelo, ano, placa, chassi, disponivel, quilometragem, desc_carro) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            PreparedStatement pStmt = this.connection.prepareStatement(sql);
            pStmt.setInt(1, carro.getGrupoCarro().getCodGrupoCarro());
            pStmt.setString(2, carro.getMarca());
            pStmt.setString(3, carro.getModelo());
            pStmt.setInt(4, carro.getAno());
            pStmt.setString(5, carro.getPlaca());
            pStmt.setString(6, carro.getChassi());
            pStmt.setBoolean(7, carro.getDisponivel());
            pStmt.setInt(8, carro.getQuilometragem());
            pStmt.setString(9, carro.getModelo());

            pStmt.executeUpdate();

            this.connection.commit();
        }
        catch (SQLException erro) {

            this.connection.rollback();
            throw new MinhaException(erro.getMessage());
        }
        finally{
            this.connection.close();
        }
    }

    public Carro selecionarCarro(Locacao loca) throws MinhaException, SQLException, ConexaoException {

        Carro carro = new Carro();
        this.connection = FabricaConexao.obterConexao();

        sql = "select ca.modelo, "+
                     "ca.placa, "+
                     "ca.chassi "+
               " from carro ca, "+
                    " locacao lo "+
                "where ca.cod_carro = lo.cod_carro and "+
                      "lo.cod_locacao = ?; ";

        PreparedStatement pStmt = null;

        pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, loca.getCodLocacao());


        ResultSet result;
        result = pStmt.executeQuery();

        if (result == null || !result.next()) {
            this.connection.close();
            throw new MinhaException(" Não existe cliente cadastrado com esse CPF !");

        } else {
            carro.setModelo(result.getString("modelo"));
            carro.setPlaca(result.getString("placa"));
            carro.setChassi(result.getString("chassi"));
        }

        this.connection.close();

        return carro;
    }

    public void removerCarro(Carro carro) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        this.sql = "DELETE FROM carro WHERE cod_carro = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(this.sql);
        pStmt.setInt(1, carro.getCodCarro());

        pStmt.executeUpdate();

        this.connection.close();

    }

    public void alterarCarro(Carro carro) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.connection.setAutoCommit(true);

        this.sql = "UPDATE carro " +
                "SET cod_grupo_carro = ? , " +
                "marca = ? , " +
                "modelo = ? , " +
                "ano = ? , " +
                "placa = ? , " +
                "chassi = ? , " +
                "disponivel = ? , " +
                "quilometragem = ? , " +
                "desc_carro = ? " +
                "WHERE cod_carro = ? ;";

        PreparedStatement pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, carro.getGrupoCarro().getCodGrupoCarro());
        pStmt.setString(2, carro.getMarca());
        pStmt.setString(3, carro.getModelo());
        pStmt.setInt(4, carro.getAno());
        pStmt.setString(5, carro.getPlaca());
        pStmt.setString(6, carro.getChassi());
        pStmt.setBoolean(7, carro.getDisponivel());
        pStmt.setInt(8, carro.getQuilometragem());
        pStmt.setString(9, carro.getModelo());
        pStmt.setInt(10, carro.getCodCarro());

        pStmt.executeUpdate();

        this.connection.close();

    }

    public Vector<Carro> selecionarTodosCarros() throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.sql = "SELECT c.cod_carro, c.cod_grupo_carro, c.marca, c.modelo, c.ano, c.placa, c.chassi, c.disponivel, c.quilometragem, gc.nome_grupo_carro " +
                "FROM carro c, " +
                "grupo_carro gc " +
                "WHERE c.cod_grupo_carro = gc.cod_grupo_carro;";

        PreparedStatement pStmt = this.connection.prepareStatement(this.sql);

        ResultSet result = pStmt.executeQuery();

        Vector<Carro> carrosGrupo = new Vector<Carro>();

        if(result == null || !result.next())
        {
            this.connection.close();
            return carrosGrupo;
        }
        else
        {
            do{

                Carro carro = new Carro();
                GrupoCarro grupoCarro = new GrupoCarro();

                grupoCarro.setCodGrupoCarro(result.getInt("cod_grupo_carro"));
                grupoCarro.setNomeGrupo(result.getString("nome_grupo_carro"));

                carro.setCodCarro(result.getInt("cod_carro"));
                carro.setMarca(result.getString("marca"));
                carro.setAno(result.getInt("ano"));
                carro.setModelo(result.getString("modelo"));
                carro.setPlaca(result.getString("placa"));
                carro.setChassi(result.getString("chassi"));
                carro.setDisponivel(result.getBoolean("disponivel"));
                carro.setQuilometragem(result.getInt("quilometragem"));
                carro.setGrupoCarro(grupoCarro);


                carrosGrupo.addElement(carro);

            }while(result.next());
        }

        this.connection.close();

        return carrosGrupo;
    }

    public Vector<Carro> selecionarCarrosPorGrupo(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException {

        this.connection = FabricaConexao.obterConexao();

        this.sql = "SELECT c.* " +
                "FROM carro c, " +
                "grupo_carro gc " +
                "WHERE c.cod_grupo_carro = gc.cod_grupo_carro and " +
                " gc.cod_grupo_carro = ? and " +
                " c.disponivel = TRUE ;";

        PreparedStatement pStmt = this.connection.prepareStatement(this.sql);

        pStmt.setInt(1, grupoCarro.getCodGrupoCarro());

        ResultSet result = pStmt.executeQuery();

        Vector<Carro> carrosGrupo = new Vector<Carro>();

        if(result == null || !result.next())
        {
            this.connection.close();
            return carrosGrupo;
        }
        else
        {
            do{

                Carro carro = new Carro();

                carro.setCodCarro(result.getInt("cod_carro"));
                carro.setModelo(result.getString("modelo"));
                carro.setPlaca(result.getString("placa"));
                carro.setChassi(result.getString("chassi"));
                carro.setDisponivel(result.getBoolean("disponivel"));
                carro.setQuilometragem(result.getInt("quilometragem"));
                carro.setGrupoCarro(grupoCarro);

                carrosGrupo.addElement(carro);

            }while(result.next());
        }

        this.connection.close();

        return carrosGrupo;
    }

    public void mudarDisponibilidade(Conexao connection, Carro carro) throws SQLException{

        try {

            this.sql = "UPDATE carro " +
                "SET disponivel = FALSE " +
                "WHERE cod_carro = ? ;";

            PreparedStatement pStmt = connection.prepareStatement(sql);
            pStmt.setInt(1, carro.getCodCarro());

            pStmt.executeUpdate();

            connection.close();
        } catch (SQLException erro) {

            connection.rollback();
            throw erro;

        }


    }

    public void alterarKMDisponibilidade(Vector linha) throws SQLException, ConexaoException {
        this.connection = FabricaConexao.obterConexao();

        try
        {
            this.connection.setAutoCommit(false);
            sql = "UPDATE carro SET "+
                    "quilometragem = quilometragem + ?, "+
                    "disponivel = true "+
                    "where cod_carro = ?" ;

            PreparedStatement prepSt = this.connection.prepareStatement(sql);

            prepSt.setInt(1, Integer.parseInt(linha.get(1).toString()));
            prepSt.setInt(2, Integer.parseInt(linha.get(6).toString()));

            prepSt.executeUpdate();
            this.connection.commit();

        }
        catch(SQLException erro)
        {
            this.connection.rollback();
            erro.printStackTrace();
        }
        finally
        {
            this.connection.close();
        }
    }

    //obterCodCarro
    /*
    public Carro selecionarCarro(int codGrupoCar) throws MinhaException, SQLException, ConexaoException {

        Carro car = new Carro();

        this.connection = FabricaConexao.obterConexao();


        String sql = "select ca.* " +
                "from carro ca " +
                "where ca.cod_grupo_carro = ? ";

        PreparedStatement pStmt = null;

        pStmt = this.connection.prepareStatement(sql);

        pStmt.setInt(1, codGrupoCar);


        ResultSet result;
        result = pStmt.executeQuery();

        if (result == null || !result.next()) {
            this.connection.close();
            throw new MinhaException(" Não existe cliente cadastrado com esse CPF !");

        } else {
            car.setCodCarro(result.getInt("cod_grupo_carro"));
            car.setMarca(result.getString("marca"));
            car.setModelo(result.getString("modelo"));
            car.setDescCarro(result.getString("desc_carro"));
            car.setAno(result.getString("ano"));
            car.setPlaca(result.getString("placa"));
            car.setChassi(result.getString("chassi"));
            car.setDisponivel(result.getBoolean("disponivel"));
            car.setQuilometragem(result.getInt("quilometragem"));
            car.setValorLitroKM(result.getInt("valor_litro_km"));
        }

        this.connection.close();


        return car;
    }*/


}

