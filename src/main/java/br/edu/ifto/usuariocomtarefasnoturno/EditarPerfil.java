package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editarperfil")
public class EditarPerfil extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        // 1. Verifica se o usuário está logado
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            out.print("<p>Erro: Você precisa estar logado para editar seu perfil.</p>");
            return;
        }

        // 2. Recupera o objeto do usuário que está na sessão
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

        // 3. Pega os novos dados enviados pelo formulário
        String novoNome = request.getParameter("nome");
        String novaSenha = request.getParameter("senha");

        // 4. Validação simples
        if (novoNome != null && !novoNome.isBlank() && novaSenha != null && !novaSenha.isBlank()) {
            try {
                // Atualiza os dados do objeto
                usuarioLogado.setNome(novoNome);
                usuarioLogado.setSenha(novaSenha);

                out.print("<h3>Perfil atualizado com sucesso!</h3>");
                out.print("<p>Nome: " + usuarioLogado.getNome() + "</p>");
                out.print("<br><a href='menu.html'><button>Voltar</button></a>");
                out.print("<a href='menu.html'>Voltar ao menu</a>");
            } catch (IllegalArgumentException e) {
                out.print("<p style='color:red;'>Erro: " + e.getMessage() + "</p>");
                out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            }
        } else {
            out.print("<p>Preencha todos os campos corretamente.</p>");
        }
    }
}