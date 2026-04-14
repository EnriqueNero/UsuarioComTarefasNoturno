package br.edu.ifto.usuariocomtarefasnoturno;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {
    private int id;
    private String titulo, descricao, categoria; // Atributo categoria adicionado
    private LocalDate data;

    public Tarefa() {}

    // Construtor com categoria (sem data)
    public Tarefa(String titulo, String descricao, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    // Construtor com categoria e data
    public Tarefa(String titulo, String descricao, LocalDate data, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.categoria = categoria;
    }

    // Construtor completo (geralmente usado para carregar dados existentes)
    public Tarefa(int id, String titulo, String descricao, LocalDate data, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id == tarefa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' + // Incluído no toString
                ", data=" + data +
                '}';
    }
}