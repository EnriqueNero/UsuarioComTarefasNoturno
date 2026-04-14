package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class Ouvinte implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent evento) {
        ServletContext application = evento.getServletContext();
        Set<Usuario> usuarios = new HashSet<>();

        // 1. Corrigido: Adicionado o "tipo" (ex: "admin") ao criar o Usuario
        Usuario u = new Usuario(1, "José", "jose", "123", "admin");

        // 2. Corrigido: Adicionada a "categoria" ao criar as Tarefas
        u.getTarefas().add(new Tarefa(3, "Reunião", "Reunião com pais", LocalDate.parse("2026-03-31"), "Trabalho"));
        u.getTarefas().add(new Tarefa(4, "Compras", "Comprar pão", null, "Pessoal"));

        usuarios.add(u);

        // 3. Corrigido: Adicionado o "tipo" (ex: "normal") ao segundo Usuario
        usuarios.add(new Usuario(2, "Maria", "maria", "abcd", "normal"));

        application.setAttribute("usuarios", usuarios);
        application.setAttribute("serial", 4);

        // Inicializa categorias padrão
        Set<Categoria> categorias = new HashSet<>();
        categorias.add(new Categoria(1, "Trabalho"));
        categorias.add(new Categoria(2, "Pessoal"));
        categorias.add(new Categoria(3, "Estudo"));
        application.setAttribute("categorias", categorias);
    }
}