

package control;

import dao.CarroDao;
import dao.FabricaDao;
import domain.Carro;
import domain.GrupoCarro;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;



public class ControladoraCarros {

    private Vector<Carro> carros;
    private int marc;
    private ControladoraGrupoCarro controladoraGrupoCarro = new ControladoraGrupoCarro();


    public Vector<Carro> getCarros() {
        return carros;
    }

    public void setCarros(Vector<Carro> carros) {
        this.carros = carros;
    }

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    private void criaObjetoCarro(Carro car, Vector carro, ControladoraGrupoCarro controladoraGrupoCarro){

        String placa = carro.get(2).toString();
        placa = placa.replaceAll("-", "");

        int ano = Integer.parseInt(carro.get(5).toString());
        int quilometragem = Integer.parseInt(carro.get(4).toString());

        boolean disponivel = Boolean.parseBoolean(carro.get(6).toString());

        car.setModelo(carro.get(0).toString());
        car.setMarca(carro.get(1).toString());
        car.setPlaca(placa);
        car.setChassi(carro.get(3).toString());
        car.setAno(ano);
        car.setDisponivel(disponivel);
        car.setQuilometragem(quilometragem);
        car.setGrupoCarro(controladoraGrupoCarro.getVetGrupos().get(controladoraGrupoCarro.getMarc()));

    }


    public Vector montarLinhasCarro(Carro carro)
    {

        String disponibilidade;
        if(carro.getDisponivel())
            disponibilidade = "SIM";
        else
            disponibilidade = "NÃO";

        Vector linha = new Vector();
        linha.addElement(carro.getCodCarro());
        linha.addElement(carro.getModelo());
        linha.addElement(carro.getMarca());
        linha.addElement(carro.getAno());
        linha.addElement(carro.getGrupoCarro().getNomeGrupo());
        linha.addElement(carro.getPlaca());
        linha.addElement(carro.getChassi());
        linha.addElement(disponibilidade);
        linha.addElement(carro.getQuilometragem());


        return linha;
    }


    public void alterarCarro (Vector carro, ControladoraGrupoCarro controladoraGrupoCarro) throws ConexaoException, MinhaException, SQLException{

        Carro car = this.carros.get(marc);
        this.criaObjetoCarro(car, carro, controladoraGrupoCarro);
        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        carroDao.alterarCarro(car);

    }


    public void inserirCarro (Vector carro, ControladoraGrupoCarro controladoraGrupoCarro) throws MinhaException, ConexaoException, SQLException{

        Carro car = new Carro();
        this.criaObjetoCarro(car, carro, controladoraGrupoCarro);
        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        carroDao.inserirCarro(car);

    }


    public void removerCarro () throws MinhaException, SQLException, ConexaoException{

        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        Carro carro = this.carros.get(marc);
        carroDao.removerCarro(carro);

        this.carros.remove(marc);

    }

    public void removerCarrosReservados(){

        this.carros.remove(this.marc);
    }


    public Carro selecionarCarro (Carro carro) {
        return null;
    }


    public Vector<Carro> selecionarTodosCarros () throws MinhaException, SQLException, ConexaoException {
        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        this.carros = carroDao.selecionarTodosCarros();

        return carros;
    }

    public Vector<Carro> obterCarros(GrupoCarro grupoCarro) throws MinhaException, SQLException, ConexaoException{

        CarroDao carroDao = FabricaDao.getCarroDao("JDBC");
        this.carros = carroDao.selecionarCarrosPorGrupo(grupoCarro);

        return carros;
    }

    public Vector selecionarCarrosPorGrupo(Vector grupo) throws MinhaException, SQLException, ParseException, ConexaoException{

        GrupoCarro grupoCarro = new GrupoCarro();
        grupoCarro = this.controladoraGrupoCarro.atualizarGrupoCarro(grupo);

        Vector<Carro> vetCarros = this.obterCarros(grupoCarro);
        Vector linhasCarro = new Vector();

        for(int i = 0; i < vetCarros.size(); i++){

            Carro car = vetCarros.get(i);
            linhasCarro.addElement(this.montarLinhasCarro(car));
        }

        return linhasCarro;
    }

    public Vector obterTodosCarros() throws MinhaException, SQLException, ConexaoException {

        Vector<Carro> cs = this.selecionarTodosCarros();
        Vector linhasCarros = new Vector();

        for(int i = 0; i < cs.size(); i++){

           linhasCarros.addElement(this.montarLinhasCarro(this.carros.get(i)));

        }

        return linhasCarros;
    }

}
