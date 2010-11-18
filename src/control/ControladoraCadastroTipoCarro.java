package control;


import dao.*;
import domain.*;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import javax.swing.JComboBox;
import javax.swing.JTable;
import util.ConexaoException;
import java.sql.*;
import java.util.*;
import javax.swing.JTextField;
import util.Conexao;
import util.MinhaException;

public class ControladoraCadastroTipoCarro {

    private TipoCarroDao tipoCarroDao;

    public ControladoraCadastroTipoCarro() {
        this.tipoCarroDao = FabricaDao.getTipoCarroDao("JDBC");
    }

   public void inserirNovoTipoCarro(Vector linha) throws ConexaoException, SQLException, MinhaException {
        TipoCarro tipoCarro = new TipoCarro();
        this.atualizarTipoCarro(tipoCarro, linha);
        tipoCarroDao.inserirTipoCarro(tipoCarro);
    }
   
    private void atualizarTipoCarro(TipoCarro tipoCarro, Vector linha) {
        tipoCarro.setNomeTipoCarro(linha.get(0).toString());
        tipoCarro.setDescTipoCarro(linha.get(1).toString());
    }
}