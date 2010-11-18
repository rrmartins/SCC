package control;

import dao.ClienteDao;
import dao.FabricaDao;
import domain.Carro; 
import domain.Cidade; 
import domain.Cliente;
import domain.Entrega; 
import domain.Eventual; 
import domain.Funcionario; 
import domain.GrupoCarro; 
import domain.Locacao; 
import domain.Oficina; 
import domain.Pagamento; 
import domain.Plano; 
import domain.Reserva; 
import domain.Revisao; 
import domain.TipoCarro; 
import domain.UF; 
import java.util.ArrayList; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.884099C1-B218-E7A3-94D1-A54FBE587387]
// </editor-fold> 
public abstract class SCCControladora {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C36B4AD4-BEA2-AD75-3A64-45B8F261554D]
    // </editor-fold> 
    public SCCControladora () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D5CB468B-82E5-4F1E-7FC3-AD84DE34E53F]
    // </editor-fold> 
    public boolean alterarCarro (Carro carro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F63EA87A-7E72-6017-DABE-CDC4192C6CE1]
    // </editor-fold> 
    public boolean inserirCarro (Carro carro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C2EDF55C-487F-B03C-8239-920DB81C12AC]
    // </editor-fold> 
    public boolean removerCarro (Carro carro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B43AD1E2-6F38-2CBB-9790-39134D336651]
    // </editor-fold> 
    public Carro selecionarCarro (Carro carro) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1358BAE1-2DEE-692D-83EE-955601407007]
    // </editor-fold> 
    public ArrayList<Carro> selecionarTodosCarros () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.825C6BE1-2C70-AD57-9AE0-1FC602D99E36]
    // </editor-fold> 
    public boolean alterarCidade (Cidade cidade) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.771D289F-B8D9-C357-C61E-92F296652511]
    // </editor-fold> 
    public boolean inserirCidade (Cidade cidade) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0EC1BD2E-0C34-6C64-F092-C267428876F6]
    // </editor-fold> 
    public boolean removerCidade (Cidade cidade) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.69713DDF-114C-983A-952E-4BCA744DE47A]
    // </editor-fold> 
    public Cidade selecionarCidade (Cidade cidade) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D9ECBAA6-E7C3-95F0-F253-BD282BC6541B]
    // </editor-fold> 
    public ArrayList<Cidade> selecionarTodasCidades () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.20FC834F-07E0-BEFB-D490-430517DB56E0]
    // </editor-fold> 
    public boolean alterarCliente (Cliente cliente) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.88FE0510-F7DD-9601-C650-F10A8EC94FD7]
    // </editor-fold> 
    public boolean inserirCliente (Cliente cliente) {

        ClienteDao clienteDao = FabricaDao.getClienteDao("JDBC");
        try
        {
            return clienteDao.inserirCliente(cliente);
        }
        catch(Exception erro)
        {
            erro.printStackTrace();
            return false;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.95444CBD-87E7-EBCA-23B4-7EE921C6EA8F]
    // </editor-fold> 
    public boolean removerCliente (Cliente cliente) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.36EF2D9C-8AD5-0459-4A0A-15C7A3497CC7]
    // </editor-fold> 
    public Cliente selecionarCliente (Cliente cliente) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8E0CE77A-82C1-6C69-901B-585071948A44]
    // </editor-fold> 
    public boolean alterarEntrega (Entrega entrega) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2C1987F1-90CF-D5A5-DEBF-BBB9F7E20BDF]
    // </editor-fold> 
    public boolean inserirEntrega (Entrega entrega) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.968DF78F-CD25-CEDB-3188-53490D161F95]
    // </editor-fold> 
    public boolean removerEntrega (Entrega entrega) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.567C6D00-4639-E908-DE35-ADECD95E5C9F]
    // </editor-fold> 
    public Entrega selecionarEntrega (Entrega entrega) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.281365A5-276E-94F5-CE24-5ECE06C31611]
    // </editor-fold> 
    public ArrayList<Entrega> selecionarTodasEntregas () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.83E82E92-0CD7-6444-018E-E828C0A91818]
    // </editor-fold> 
    public boolean alterarEventual (Eventual eventual) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6AC1CEEE-79F7-6FD8-2D97-D772F45566EB]
    // </editor-fold> 
    public boolean inserirEventual (Eventual eventual) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CE7FAE67-D2C8-289E-1A47-5EF5C3955AE6]
    // </editor-fold> 
    public boolean removerEventual (Eventual eventual) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9A4ED3FF-4FC5-208F-44C7-07DE7388FFDD]
    // </editor-fold> 
    public Eventual selecionarEventual (Eventual eventual) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A5A524C4-014D-842A-4644-15617A26D05E]
    // </editor-fold> 
    public ArrayList<Eventual> selecionarTodosEventuais () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.27F624F6-857B-005F-436A-915801AF7713]
    // </editor-fold> 
    public boolean alterarFuncionario (Funcionario funcionario) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DE91F14E-D2F0-F283-868F-B9ECCFD8B2BE]
    // </editor-fold> 
    public boolean inserirFuncionario (Funcionario funcionario) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.04E1A8D2-F877-63B7-113C-FF2134A3CFC2]
    // </editor-fold> 
    public boolean removerFuncionario (Funcionario funcionario) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4A864873-BA51-030D-3ED9-0707E2CAE168]
    // </editor-fold> 
    public Funcionario selecionarFuncionario (Funcionario funcionario) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.86FF7A60-600F-ACFB-BC4A-766ED54DD819]
    // </editor-fold> 
    public ArrayList<Funcionario> selecionarFuncionarioPorCargo (String cargo) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.893F4008-C616-641E-1486-038992468446]
    // </editor-fold> 
    public ArrayList<Funcionario> selecionarTodosFuncionarios () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5A9B51F9-D576-6E31-0A77-926D0A134FE9]
    // </editor-fold> 
    public boolean alterarGrupoCarro (GrupoCarro grupoCarro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E55A8190-B42D-4B81-F9B0-008C71414578]
    // </editor-fold> 
    public boolean inserirGrupoCarro (GrupoCarro grupoCarro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C7339713-F999-7ED7-5145-E24F203D253F]
    // </editor-fold> 
    public boolean removerGrupoCarro (GrupoCarro grupoCarro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C7FEF42B-F032-98FA-7EF0-E85E66695214]
    // </editor-fold> 
    public GrupoCarro selecionarGrupoCarro (GrupoCarro grupoCarro) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6FB451D5-55FB-8D71-2205-6320E16218E0]
    // </editor-fold> 
    public ArrayList<GrupoCarro> selecionarTodosGrupoCarro () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FB455EC0-7BD3-5708-EF0F-779156257457]
    // </editor-fold> 
    public boolean alterarLocacao (Locacao locacao) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2E38446E-2275-4C07-65BB-1BD694D635C1]
    // </editor-fold> 
    public boolean inserirLocacao (Locacao locacao) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F5FA0C47-A009-6447-AEC2-AD23FB50D431]
    // </editor-fold> 
    public boolean removerLocacao (Locacao locacao) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.724C52CE-CEEB-AD02-E6CA-FEEE1EECE01D]
    // </editor-fold> 
    public Locacao selecionarLocacao (Locacao locacao) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BA0AAE2E-F094-AD24-E61D-B614B41A8412]
    // </editor-fold> 
    public ArrayList<Locacao> selecionarTodasLocacoes () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.41F06015-22C4-40F5-A3E0-CE064480C3A4]
    // </editor-fold> 
    public boolean alterarOficina (Oficina oficina) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.87533DA6-DC5A-6169-5DFA-6F15E1889744]
    // </editor-fold> 
    public boolean inserirOficina (Oficina oficina) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E8567EA6-C7F3-9CE6-CEB9-0B2389963382]
    // </editor-fold> 
    public boolean removerOficina (Oficina oficina) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.02ABE888-1942-7081-B1D5-B79BBCBDDE63]
    // </editor-fold> 
    public Oficina selecionarOficina (Oficina oficina) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B58C8585-8C0A-6F54-80D6-F1EAC913AD53]
    // </editor-fold> 
    public ArrayList<Oficina> selecionarTodasOficinas () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2557494D-8555-3FC5-CCE2-F6D0D7AFD924]
    // </editor-fold> 
    public boolean alterarPagamento (Pagamento pagamento) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.19B913AE-A742-8B04-CC03-26C2C8B8CDBE]
    // </editor-fold> 
    public boolean inserirPagamento (Pagamento pagamento) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F5FFDA8B-C460-AC99-163B-B7AA3E283682]
    // </editor-fold> 
    public boolean removerPagamento (Pagamento pagamento) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A2632BD9-5E06-E7D6-E7B4-3B060AC6A751]
    // </editor-fold> 
    public Pagamento selecionarPagamento (Pagamento pagamento) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EF20ED79-3FB4-8E44-7BBF-6B2212357D9A]
    // </editor-fold> 
    public ArrayList<Pagamento> selecionarTodosPagamentos () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.753CE3D4-3295-102F-CBEF-4CC614A9FA2B]
    // </editor-fold> 
    public boolean alterarPlano (Plano plano) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BD6ABAD0-F472-7768-0607-9CF95109E323]
    // </editor-fold> 
    public boolean inserirPlano (Plano plano) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3C18D8F6-4702-F7A9-7774-88F9D41E4534]
    // </editor-fold> 
    public boolean removerPlano (Plano plano) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.41DE4036-0663-31AC-DEC9-313FE067A4D7]
    // </editor-fold> 
    public Plano selecionarPlano (Plano plano) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.283E9888-0BB4-C7A0-E9DD-4144CD95A2DC]
    // </editor-fold> 
    public ArrayList<Plano> selecionarTodosPlanos () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DDD59FBF-679F-7174-7107-4804BFA0FD32]
    // </editor-fold> 
    public boolean alterarReserva (Reserva reserva) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B45D153E-DE36-B3F7-B23D-4345CB46887C]
    // </editor-fold> 
    public boolean inserirReserva (Reserva reserva) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.84E8F039-68E1-5FEA-1D78-6769B165BF4F]
    // </editor-fold> 
    public boolean removerReserva (Reserva reserva) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7C6ACD61-84D0-5447-7AD1-48B29489B437]
    // </editor-fold> 
    public Reserva selecionarReserva (Reserva reserva) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.84A9EA95-1576-8F65-78B6-AE6563DFBA6E]
    // </editor-fold> 
    public Reserva selecionarReserva (String cpf) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C13F9A51-9A7D-FC22-2733-1EDAFDD233BA]
    // </editor-fold> 
    public ArrayList<Reserva> selecionarTodasReservas () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F4DEDF63-E50F-110B-7484-838E7DC5993D]
    // </editor-fold> 
    public boolean alterarRevisao (Revisao revisao) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CA1CCBC3-AC5F-C1C3-86A4-1FAFE88A52BC]
    // </editor-fold> 
    public boolean inserirRevisao (Revisao revisao) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F0A2A779-AAD0-4227-C419-862471887B6A]
    // </editor-fold> 
    public boolean removerRevisao (Revisao revisao) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E5770287-4444-EB0C-7FCC-5B1A1739D06A]
    // </editor-fold> 
    public Revisao selecionarRevisao (Revisao revisao) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ABBEC571-A51A-1823-2EF0-3E2B4AE6F61B]
    // </editor-fold> 
    public ArrayList<Revisao> selecionarTodasRevisoes () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6399E15E-4C14-626C-3155-9147B4D3E4C2]
    // </editor-fold> 
    public boolean alterarTipoCarro (TipoCarro tipoCarro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8CDD7E60-8CFC-1198-E379-C4CBA5798125]
    // </editor-fold> 
    public boolean inserirTipoCarro (TipoCarro tipoCarro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.25366BEE-46E3-E569-096B-F146FF194075]
    // </editor-fold> 
    public boolean removerCarro (TipoCarro tipoCarro) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DBC259D6-CF64-60F2-57C1-259905D1B4B9]
    // </editor-fold> 
    public TipoCarro selecionarTipoCarro (TipoCarro tipoCarro) {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FDCF1E64-F125-085E-6621-EBAB51736561]
    // </editor-fold> 
    public ArrayList<TipoCarro> selecionarTodosTipoCarro () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B648AACB-ED75-E58E-1B4C-936724E8E840]
    // </editor-fold> 
    public boolean alterarUF (UF uf) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.05A18AB4-B18C-B512-0AB4-F08FDBB7C8D6]
    // </editor-fold> 
    public boolean inserirUF (UF uf) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.35379613-A3CE-9542-E9BD-0D5877266826]
    // </editor-fold> 
    public boolean removerUF (UF uf) {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E65DFB8D-0AE3-0D63-3AE1-661510AA388B]
    // </editor-fold> 
    public UF selecionarTodasUF () {
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.362813D0-748C-3EC1-7138-DA846865ADA4]
    // </editor-fold> 
    public ArrayList<UF> selecionarUF (UF uf) {
        return null;
    }

}

