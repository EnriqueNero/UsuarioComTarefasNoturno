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

@WebServlet("/editartarefa")
public class EditarTarefa extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado.</p>");
            return;
        }
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

        String tid = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String data = request.getParameter("data");
        String categoria = request.getParameter("categoria");

        if (tid == null || tid.isBlank()) {
            out.print("<p>ID da tarefa é obrigatório.");
            return;
        }
        int id = Integer.parseInt(tid);
        Tarefa tarefa = usuarioLogado.getTarefas().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (tarefa == null) {
            out.print("<p>Tarefa não encontrada.</p>");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }
        if (titulo != null && !titulo.isBlank()) tarefa.setTitulo(titulo);
        if (descricao != null && !descricao.isBlank()) tarefa.setDescricao(descricao);
        if (data != null && !data.isBlank()) tarefa.setData(LocalDate.parse(data));
        if (categoria != null && !categoria.isBlank()) tarefa.setCategoria(categoria);
        out.print("<p>Tarefa editada com sucesso!</p>");
        out.print("<br><a href='menu.html'><button>Voltar</button></a>");
    }
}