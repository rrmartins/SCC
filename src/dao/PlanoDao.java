package dao;

import domain.Plano; 
import java.sql.SQLException;
import java.util.ArrayList; 
import util.ConexaoException;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3E1F13EE-1723-C07B-EC18-BF410510620A]
// </editor-fold> 
public interface PlanoDao {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0152047E-98C4-4069-E5BE-0C4CA6590E1D]
    // </editor-fold> 
    public ArrayList<Plano> selecionarTodosPlanos ();

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7531FAA0-1386-F391-9F36-0F11237482DD]
    // </editor-fold> 
    public Plano selecionarPlano (Plano plano);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A4481EF4-F87F-E991-955D-D964A04A27D6]
    // </editor-fold> 
    public boolean removerPlano (Plano plano) throws ConexaoException, SQLException;;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DD037517-B1D9-90CA-C79C-B965DAED8797]
    // </editor-fold> 
    public boolean alterarPlano (Plano plano) throws ConexaoException, SQLException;;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6274BC77-E0F5-C35B-04E0-DF7A490B264D]
    // </editor-fold> 
    public void inserirPlano (Plano plano) throws ConexaoException, SQLException;

}

