package model;

import java.util.Date;

public class Professor {
    private int codigo;
    private String nome;
    private Disciplina disciplina; 
    private String especialidade;
    private Date dataAdmissao;

    public Professor(int codigo, String nome, Disciplina disciplina, String especialidade, Date dataAdmissao) {
        this.codigo = codigo;
        this.nome = nome;
        this.disciplina = disciplina;
        this.especialidade = especialidade;
        this.dataAdmissao = dataAdmissao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
}
