package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/cadastrartarefa")
public class CadastrarTarefa extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String data = request.getParameter("data");
        String categoria = request.getParameter("categoria"); // 1. Recupera a categoria do formulário

        // 2. Validação: Título, Descrição E Categoria agora são obrigatórios
        if (titulo == null || titulo.isBlank() || 
            descricao == null || descricao.isBlank() || 
            categoria == null || categoria.isBlank()) {
            
            out.print("<p style='color:red;'>Erro: Título, descrição e categoria são obrigatórios!</p>");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado.</p>");
            return;
        }

        // 3. Criação da Tarefa
        Tarefa t;
        if (data != null && !data.isBlank()) {
            t = new Tarefa(titulo, descricao, LocalDate.parse(data), categoria);
        } else {
            t = new Tarefa(titulo, descricao, categoria);
        }

        t.setId(Serial.proximo(getServletContext()));
        
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        usuarioLogado.getTarefas().add(t);
        
        out.print("<p>Tarefa '" + titulo + "' na categoria '" + categoria + "' adicionada com sucesso!</p>");
        out.print("<br><a href='menu.html'><button>Voltar</button></a>");
    }
}