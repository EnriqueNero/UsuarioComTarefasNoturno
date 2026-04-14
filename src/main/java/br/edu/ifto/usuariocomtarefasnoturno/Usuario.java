package br.edu.ifto.usuariocomtarefasnoturno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nome, login, senha, tipo; // Adicionado o campo tipo
    private List<Tarefa> tarefas = new ArrayList<>();

    public Usuario() {}

    // Construtor sem ID (geralmente usado para novos cadastros)
    public Usuario(String nome, String login, String senha, String tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Construtor com ID (geralmente usado para busca no banco de dados)
    public Usuario(int id, String nome, String login, String senha, String tipo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() < 2)
            throw new IllegalArgumentException("Nome precisa de no mínimo 2 caracteres");
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Getter e Setter para o novo campo tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return this.id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", tipo='" + tipo + '\'' + // Incluído no toString
                ", tarefas=" + tarefas +
                '}';
    }
}