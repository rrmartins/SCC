

package control;

import dao.FabricaDao;
import dao.GrupoCarroDao;
import domain.Acessorio;
import domain.GrupoCarro;
import domain.TipoCarro;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Vector;
import util.ConexaoException;
import util.MinhaException;


public class ControladoraGrupoCarro {

    private Vector<GrupoCarro> vetGrupos = new Vector<GrupoCarro>();
    private int marc;

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }


    public Vector<GrupoCarro> getVetGrupos() {
        return vetGrupos;
    }


    public void setVetGrupos(Vector<GrupoCarro> vetGrupos) {
        this.vetGrupos = vetGrupos;
    }


    public GrupoCarro atualizarGrupoCarro(Vector linhaGrupo) throws ParseException
    {
        GrupoCarro grupo = new GrupoCarro();

        DecimalFormat dF = new DecimalFormat();

        int codGrupo = Integer.parseInt(linhaGrupo.get(0).toString());

        double diaria = (Double) linhaGrupo.get(2);//dF.parse(linhaGrupo.get(2).toString());
        double diariaQuilometrada = (Double) linhaGrupo.get(3);//dF.parse(linhaGrupo.get(3).toString());
        double cobertura = (Double) linhaGrupo.get(4);//dF.parse(linhaGrupo.get(4).toString());

        grupo.setCodGrupoCarro(codGrupo);
        grupo.setNomeGrupo(linhaGrupo.get(1).toString());
        grupo.setPrecoDiaria(diaria);
        grupo.setPrecoDiariaQuilometrada(diariaQuilometrada);
        grupo.setPrecoCobertura(cobertura);
        //grupo.setTipoCarro(val);
        //grupo.setAcessorios(linhaGrupo);

        return grupo;
    }


    private GrupoCarro criaGrupo(Vector grupo, ControladoraTipoCarro controladoraTipoCarro, Vector acessorios) throws ParseException{

        DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();

        GrupoCarro grp = new GrupoCarro();
        TipoCarro tipo = controladoraTipoCarro.getVetTipoCarro().get(controladoraTipoCarro.getMarc());
        Vector<Acessorio> acc = new Vector<Acessorio>();

        for(int i = 0; i < acessorios.size(); i++){

            acc.addElement((Acessorio) acessorios.get(i));

        }


        String sDiaria = grupo.get(1).toString();
        sDiaria.replace(",", ".");
        String sDiariaQuilometrada = grupo.get(1).toString();
        sDiariaQuilometrada.replace(",", ".");
        String cob = grupo.get(1).toString();
        cob.replace(",", ".");
        String adicional = grupo.get(1).toString();
        adicional.replace(",", ".");


        Number diaria = dff.parse(sDiaria);
        Number diariaQuilometrada = dff.parse(sDiariaQuilometrada);
        Number cobertura = dff.parse(cob);
        Number adicionalQuilometro = dff.parse(adicional);

        grp.setNomeGrupo(grupo.get(0).toString());
        grp.setPrecoDiaria((Double)diaria);
        grp.setPrecoDiariaQuilometrada((Double)diariaQuilometrada);
        grp.setPrecoCobertura((Double)cobertura);
        grp.setPrecoQuilometroAdicional((Double)adicionalQuilometro);
        grp.setTipoCarro(tipo);
        grp.setAcessorios(acc);


        return grp;
    }


    public Vector montarLinhaGrupoCarro(GrupoCarro gc){

        DecimalFormat format = new DecimalFormat("#.00");

        Vector linha = new Vector();

        linha.add(gc.getCodGrupoCarro());
        linha.add(gc.getNomeGrupo());
        linha.add(gc.getTipoCarro().getNomeTipoCarro());
        linha.add(format.format(gc.getPrecoDiaria()));
        linha.add(format.format(gc.getPrecoDiariaQuilometrada()));
        linha.add(format.format(gc.getPrecoCobertura()));
        linha.add(format.format(gc.getPrecoQuilometroAdicional()));


        return linha;
    }


    public void inserirGrupoCarro(Vector grupo, ControladoraTipoCarro controladoraTipoCarro, Vector acessorios ) throws MinhaException, SQLException, ParseException, ConexaoException{

        GrupoCarro grupoCarro = this.criaGrupo(grupo, controladoraTipoCarro, acessorios);

        GrupoCarroDao grupoCarroDao = FabricaDao.getGrupoCarroDao("JDBC");
        grupoCarroDao.inserirGrupoCarro(grupoCarro);

    }


    public void alterarGrupoCarro(Vector grupo, ControladoraTipoCarro controladoraTipoCarro, Vector acessorios ) throws MinhaException, SQLException{



    }


    public void removerGrupoCarro() throws MinhaException, SQLException, ConexaoException{

        GrupoCarroDao grupoCarroDao = FabricaDao.getGrupoCarroDao("JDBC");
        grupoCarroDao.removerGrupoCarro(this.vetGrupos.get(marc));

        this.vetGrupos.remove(marc);

    }



    private Vector<GrupoCarro> obterGrupoCarro() throws MinhaException, SQLException, ConexaoException {

        GrupoCarroDao grupoCarroDao = FabricaDao.getGrupoCarroDao("JDBC");
        this.vetGrupos = grupoCarroDao.selecionarTodosGrupoCarro();

        return this.vetGrupos;
    }


    public Vector selecionarTodosGruposCarro() throws MinhaException, SQLException, ConexaoException{

        Vector<GrupoCarro> grupos = this.obterGrupoCarro();
        Vector linhas = new Vector();

        for(int i = 0; i < grupos.size(); i++){
            linhas.addElement(this.montarLinhaGrupoCarro(grupos.get(i)));
        }

        return linhas;
    }



    public Vector obterLinhasGrupoCarro() throws MinhaException, SQLException, ConexaoException {
        Vector<GrupoCarro> grupos = this.obterGrupoCarro();
        Vector linhas = new Vector();


        for (int i = 0; i < grupos.size(); i++) {
            linhas.addElement(grupos.get(i).getNomeGrupo());
        }

        return linhas;
    }



    public Vector obterGrupo()
    {
        Vector linha = new Vector();

        linha.add(this.vetGrupos.get(marc).getCodGrupoCarro());
        linha.add(this.vetGrupos.get(marc).getNomeGrupo());
        linha.add(this.vetGrupos.get(marc).getPrecoDiaria());
        linha.add(this.vetGrupos.get(marc).getPrecoDiariaQuilometrada());
        linha.add(this.vetGrupos.get(marc).getPrecoCobertura());
        linha.add(this.vetGrupos.get(marc).getTipoCarro());
        linha.add(this.vetGrupos.get(marc).getAcessorios());

        return linha;

    }

}
