package dao;

import domain.Eventual;
import java.sql.Connection;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.10884058-99FA-555A-B07B-67304C5B75F5]
// </editor-fold> 
public class EventualJDBCDao implements EventualDao {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2E4057FE-8CAD-CD2D-3475-51C2AC8997B4]
    // </editor-fold> 
    private Connection connection;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0EC1F971-65C4-1DA5-6842-05BD7DD847A8]
    // </editor-fold> 
    public Connection getConnection () {
        return connection;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.930B7040-A110-C1CA-E8EA-D32A757034EC]
    // </editor-fold> 
    public void setConnection (Connection val) {
        this.connection = val;
    }

    public Eventual selecionarTodosEventuais() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Eventual selecionarEventual(Eventual eventual) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removerEventual(Eventual eventual) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean alterarEventual(Eventual eventual) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean inserirEventual(Eventual eventual) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

