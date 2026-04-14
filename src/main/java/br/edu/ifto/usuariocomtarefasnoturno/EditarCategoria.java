package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/editarcategoria")
public class EditarCategoria extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            out.print("<p>Você precisa estar logado como administrador.</p>");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        if (!"admin".equalsIgnoreCase(usuarioLogado.getTipo())) {
            out.print("<p>Apenas administradores podem editar categorias.</p>");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }

        String nomeAntigo = request.getParameter("nomeAntigo");
        String nomeNovo = request.getParameter("nomeNovo");
        if (nomeAntigo == null || nomeAntigo.isBlank() || nomeNovo == null || nomeNovo.isBlank()) {
            out.print("<p>Preencha os nomes corretamente.</p>");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }

        ServletContext application = getServletContext();
        Set<Categoria> categorias = (Set<Categoria>) application.getAttribute("categorias");
        Categoria categoria = categorias.stream().filter(c -> c.getNome().equalsIgnoreCase(nomeAntigo)).findFirst().orElse(null);
        if (categoria == null) {
            out.print("<p>Categoria não encontrada.</p>");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }
        categoria.setNome(nomeNovo);
        out.print("<p>Categoria editada com sucesso!</p>");
        out.print("<br><a href='menu.html'><button>Voltar</button></a>");
    }
}