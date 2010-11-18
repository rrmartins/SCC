package control;

import dao.FabricaConexao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import util.Conexao;
import util.ConexaoException;
import util.MinhaException;
import java.util.*;


/***
 *
 * @author Rodrigo Martins
 */

public class ControladoraRelatorio {

    private Conexao conexao;
    private Vector vetFuncionario = new Vector();

    public Vector getVetFuncionario() {
        return vetFuncionario;
    }

    public void setVetFuncionario(Vector vetFuncionario) {
        this.vetFuncionario = vetFuncionario;
    }

    public Conexao getConnection() {
        return this.conexao;
    }

    public void setConnection(Conexao conexao) {
        this.conexao = conexao;
    }
    
    /**
     * Carrega o relatorio CarroMaisLocadoPorUmCliente.jasper
     * @param nomeRelatorio
     * @param paramentroInicial
     * @param paramentroFinal
     * @throws MinhaException
     * @throws SQLException
     * @throws ConexaoException 
     */
   
    @SuppressWarnings("unchecked")
    public void relatorio(String nomeRelatorio, String paramentroInicial, String paramentroFinal, Vector usuario) throws MinhaException, SQLException, ConexaoException, ParseException{

        Date dataInicio = null;
        SimpleDateFormat formatBra = null;
        Date dataFim = new Date();
        formatBra = new SimpleDateFormat("dd/MM/yyyy");
        try
          {
             DateFormat formatter = formatBra;
             dataInicio = formatter.parse(paramentroInicial);
             dataFim = formatter.parse(paramentroFinal);
          }
          catch(ParseException e)
          {
             e.printStackTrace();
          }

        try {
            conexao = FabricaConexao.obterConexao();
            String url = System.getProperty("user.dir") + "/src/Relatorios/"+nomeRelatorio+".jasper";
            HashMap parametro = new HashMap();
            parametro.put("data_inicio", dataInicio);
            parametro.put("data_fim", dataFim);
            parametro.put("nomeFuncionario", usuario.get(3).toString());
            parametro.put("tipoFuncionario", usuario.get(2).toString());
            JasperPrint jp = JasperFillManager.fillReport(url, parametro, conexao.getConnection());
            JasperViewer.viewReport(jp, false);
            this.conexao.close();
        } catch (JRException ex)
        {
            
           throw new MinhaException( "Não foi possivel gerar relatório.\nMSG:" + ex.getMessage());

        }
        
    }

    public void relatorio(String nomeRelatorio, Vector usuario) throws MinhaException, SQLException, ConexaoException, ParseException{

        try {
            conexao = FabricaConexao.obterConexao();
            String url = System.getProperty("user.dir") + "/src/Relatorios/"+nomeRelatorio+".jasper";
            HashMap parametro = new HashMap();
            parametro.put("nomeFuncionario", usuario.get(3).toString());
            parametro.put("tipoFuncionario", usuario.get(2).toString());
            JasperPrint jp = JasperFillManager.fillReport(url, parametro, conexao.getConnection());
            JasperViewer.viewReport(jp, false);
            this.conexao.close();
        } catch (JRException ex)
        {
            //ex.printStackTrace();
           throw new MinhaException( "Não foi possivel gerar relatório.\nMSG:" + ex.getMessage());

        }

    }

    public void geraPagamento(String nomeRelatorio, Vector usuario, String cpf) throws MinhaException, SQLException, ConexaoException, ParseException{

        nomeRelatorio = "GeraPagamento";
        try {
            conexao = FabricaConexao.obterConexao();
            String url = System.getProperty("user.dir") + "/src/Relatorios/"+nomeRelatorio+".jasper";
            HashMap parametro = new HashMap();
            parametro.put("nomeFuncionario", usuario.get(3).toString());
            parametro.put("tipoFuncionario", usuario.get(2).toString());
            parametro.put("cpf", cpf);
            JasperPrint jp = JasperFillManager.fillReport(url, parametro, conexao.getConnection());
            JasperViewer.viewReport(jp, false);
            this.conexao.close();
        } catch (JRException ex)
        {

           throw new MinhaException( "Não foi possivel gerar relatório.\nMSG:" + ex.getMessage());

        }

    }


}