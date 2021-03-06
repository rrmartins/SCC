package control;


import dao.*;
import domain.*;
import util.ConexaoException;
import java.sql.*;
import java.util.*;
import util.MinhaException;

public class ControladoraPagamento {

    private PagamentoDao pagamentoDao;
    private ControladoraEntrega controlEntrega;
    private Vector<Entrega> vetEntrega;
    private int marc;

    public int getMarc() {
        return marc;
    }

    public void setMarc(int marc) {
        this.marc = marc;
    }

    public ControladoraPagamento() {
        this.pagamentoDao = FabricaDao.getPagamentoDao("JDBC");
        this.controlEntrega = new ControladoraEntrega();
    }

    public void inserirNovoPagamento(Vector linha) throws MinhaException, SQLException, ConexaoException, ClassNotFoundException {
        Pagamento paga = new Pagamento();
        Vector ent = controlEntrega.obterLinhasEntrega();
        vetEntrega = (Vector<Entrega>) ent.get(0);
        this.atualizarPagamento(paga, linha, vetEntrega);
        pagamentoDao.inserirPagamento(paga);
    }
   
    private void atualizarPagamento(Pagamento paga, Vector linha, Vector entrega) {

        java.util.Date dataAtual = new java.util.Date();
        java.util.Date dataVencimento = new java.util.Date();

        dataVencimento.setTime(dataAtual.getTime() + 10);

        Locacao loca = new Locacao();
        loca.setCodLocacao(Integer.parseInt(linha.get(0).toString()));

        Entrega ent = new Entrega();
        ent.setCodEntrega(Integer.parseInt(entrega.get(0).toString()));
        ent.setLocacao(loca);

        paga.setDataEmissao(dataAtual);
        paga.setDataVencimento(dataVencimento);
        paga.setQuantidadeVezes(Integer.parseInt(linha.get(5).toString()));
        paga.setEntrega(ent);
        paga.setValorTotal(Double.parseDouble(linha.get(4).toString()));
        
    }

}