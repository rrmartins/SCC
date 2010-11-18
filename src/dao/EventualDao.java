package dao;

import domain.Eventual; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.C9602E07-03AF-28E0-A94C-F7FEEFFCAB96]
// </editor-fold> 
public interface EventualDao {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F9D48985-719C-6E80-77B2-CFBA8D56638A]
    // </editor-fold> 
    public Eventual selecionarTodosEventuais ();

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6F79C733-5B7C-A130-4343-EBA34C35EFC3]
    // </editor-fold> 
    public Eventual selecionarEventual (Eventual eventual);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2B98FD87-003C-9514-2539-559EB02452C4]
    // </editor-fold> 
    public boolean removerEventual (Eventual eventual);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.598F5740-48C8-4A8C-E743-87F984E2F95F]
    // </editor-fold> 
    public boolean alterarEventual (Eventual eventual);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9BDAA0ED-DC9A-F86B-74AA-931E58430162]
    // </editor-fold> 
    public boolean inserirEventual (Eventual eventual);

}

