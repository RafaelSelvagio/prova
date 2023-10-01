package model;

public class Disciplina {
    private String sigla;
    private String nome;
    private String ementa;

    public Disciplina(String sigla, String nome, String ementa) {
        this.sigla = sigla;
        this.nome = nome;
        this.ementa = ementa;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }
}
