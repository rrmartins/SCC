package domain;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.021238AA-26E2-8448-5DCF-51CBD3ED1A0C]
// </editor-fold> 
public class Plano {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.90A943E1-2712-C093-FF3F-2068D1641F0F]
    // </editor-fold> 
    private int codPlano;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A5AE1753-383D-C2D9-8456-049CEBE5765B]
    // </editor-fold> 
    private String nomePlano;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.20D94E6F-E746-F713-5811-1D2450F2FA33]
    // </editor-fold> 
    private String descPlano;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D8BB77F1-6C80-FBEF-1A01-03D1DC506E1C]
    // </editor-fold> 
    public Plano () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.03233165-1648-0640-5633-E5C867CB7508]
    // </editor-fold> 
    public int getCodPlano () {
        return codPlano;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.97569B81-6642-F327-B966-E3FB42128CBF]
    // </editor-fold> 
    public void setCodPlano (int val) {
        this.codPlano = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5F235415-761B-BD05-4AB1-97567CBE027F]
    // </editor-fold> 
    public String getDescPlano () {
        return descPlano;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F3316B75-424E-5D13-B8B5-029772478ED9]
    // </editor-fold> 
    public void setDescPlano (String val) {
        this.descPlano = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CA49DF76-574B-D2FE-2059-A9D20B800ACA]
    // </editor-fold> 
    public String getNomePlano () {
        return nomePlano;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.FCC9113B-868D-C986-B61F-C83CD79CC0F5]
    // </editor-fold> 
    public void setNomePlano (String val) {
        this.nomePlano = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.379E6997-011F-83F5-5028-1FC2E00105B5]
    // </editor-fold> 
    public void adicionarLocacao (Locacao locacao) {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D01DE2CC-23AC-49D2-4454-00A3899B271D]
    // </editor-fold> 
    public void adicionarReserva (Reserva reserva) {
    }

}

