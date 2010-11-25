package dao;


public class FabricaDao {

    public FabricaDao () {
    }

    public static AcessorioDao getAcessorioDao (String tipo)
    {
        if(tipo.equals("JDBC"))
        {
            return new AcessorioJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static GrupoCarroDao getGrupoCarroDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new GrupoCarroJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static FuncionarioDao getFuncionarioDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new FuncionarioJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static EventualDao getEventualDao (String tipo) {
        return null;
    }

    public static EntregaDao getEntregaDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new EntregaJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static ClienteDao getClienteDao (String tipo) {

        if(tipo.equals("JDBC"))
        {
            return new ClienteJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static CidadeDao getCidadeDao (String tipo)
    {
        if(tipo.equals("JDBC"))
        {
            return new CidadeJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static CarroDao getCarroDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new CarroJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static ReservaDao getReservaDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new ReservaJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static PagamentoDao gatPagamentoDao (String tipo) {
        return null;
    }

    public static OficinaDao getOficinaDao (String tipo) {
         if(tipo.equals("JDBC"))
        {
            return new OficinaJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static LocacaoDao getLocacaoDao (String tipo) {
       if(tipo.equals("JDBC"))
        {
            return new LocacaoJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static UFDao getUFDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new UFJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static PagamentoDao getPagamentoDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new PagamentoJDBCDao();
        }
        else
        {
            return null;
        }
    }
 
    public static TipoCarroDao getTipoCarroDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new TipoCarroJDBCDao();
        }
        else
        {
            return null;
        }
    }

    public static RevisaoDao getRevisaoDao (String tipo) {
        if(tipo.equals("JDBC"))
        {
            return new RevisaoJDBCDao();
        }
        else
        {
            return null;
        }
    }

}

