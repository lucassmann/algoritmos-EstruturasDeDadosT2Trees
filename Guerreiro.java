public class Guerreiro {
    
    private String nome;
    private String nomePai;
    private Integer terras;

    public Guerreiro(String nomePai, String nome, Integer terras){
        this.nome = nome;
        this.nomePai = nomePai;
        this.terras = terras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public Integer getTerras() {
        return terras;
    }

    public void setTerras(Integer terras) {
        this.terras = terras;
    }

    
}
