/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Rodrigo Martins
 */
public class Acessorio {


    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.34B9B0EF-84D6-D8F8-930A-7F53E1652322]
    // </editor-fold>
    private int codAcessorio;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.31420DBB-2E7D-2E5E-0B3D-41947789B3C3]
    // </editor-fold>
    private String descAcessorio;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker ">
    // #[regen=yes,id=DCE.DCBE3606-53C2-682B-0270-33D31C41C43C]
    // </editor-fold>
    public Acessorio () {
    }

    public int getCodAcessorio() {
        return codAcessorio;
    }

    public void setCodAcessorio(int codAcessorio) {
        this.codAcessorio = codAcessorio;
    }

    public String getDescAcessorio() {
        return descAcessorio;
    }

    public void setDescAcessorio(String descAcessorio) {
        this.descAcessorio = descAcessorio;
    }

}
