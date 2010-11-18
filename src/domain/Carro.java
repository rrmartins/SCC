package domain;


public class Carro {


    private int codCarro;


    private String descCarro;


    private String placa;


    private String chassi;


    private boolean disponivel;


    private int quilometragem;


    private GrupoCarro grupoCarro;


    private String modelo;


    private String marca;


    private int ano;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    public Carro () {
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }





    public String getChassi () {
        return chassi;
    }


    public void setChassi (String val) {
        this.chassi = val;
    }


    public int getCodCarro () {
        return codCarro;
    }


    public void setCodCarro (int val) {
        this.codCarro = val;
    }


    public String getDescCarro () {
        return descCarro;
    }


    public void setDescCarro (String val) {
        this.descCarro = val;
    }


    public boolean getDisponivel () {
        return disponivel;
    }


    public void setDisponivel (boolean val) {
        this.disponivel = val;
    }

    public GrupoCarro getGrupoCarro() {
        return grupoCarro;
    }

    public void setGrupoCarro(GrupoCarro grupoCarro) {
        this.grupoCarro = grupoCarro;
    }


    public String getPlaca () {
        return placa;
    }


    public void setPlaca (String val) {
        this.placa = val;
    }

    public int getQuilometragem () {
        return quilometragem;
    }


    public void setQuilometragem (int val) {
        this.quilometragem = val;
    }

}

