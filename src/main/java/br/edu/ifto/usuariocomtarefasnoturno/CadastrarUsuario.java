package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/cadastrarusuario")
public class CadastrarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo"); // Capturando o tipo enviado pelo form

        // Definindo um tipo padrão caso venha vazio
        if (tipo == null || tipo.isBlank()) {
            tipo = "normal";
        }

        // Se o tipo for admin, só permite cadastro se houver admin logado
        if ("admin".equalsIgnoreCase(tipo)) {
            jakarta.servlet.http.HttpSession session = request.getSession(false);
            Usuario usuarioLogado = null;
            if (session != null) {
                usuarioLogado = (Usuario) session.getAttribute("usuario");
            }
            if (usuarioLogado == null || !"admin".equalsIgnoreCase(usuarioLogado.getTipo())) {
                out.print("<p style='color:red;'>Erro: Apenas administradores logados podem cadastrar outro administrador.</p>");
                return;
            }
        }

        if (nome != null && !nome.isBlank() && login != null && !login.isBlank()
                && senha != null && !senha.isBlank()) {
            
            ServletContext application = getServletContext();
            Set<Usuario> usuarios = (Set<Usuario>) application.getAttribute("usuarios");

            // --- LÓGICA DE VALIDAÇÃO DE LOGIN DUPLICADO ---
            boolean loginExiste = usuarios.stream()
                    .anyMatch(u -> u.getLogin().equalsIgnoreCase(login));

            if (loginExiste) {
                out.print("<p style='color:red;'>Erro: O login '" + login + "' já está em uso!</p>");
                out.print("<br><a href='index.html'><button>Voltar</button></a>");
            } else {
                // Se não existe, prossegue com o cadastro
                Usuario u = new Usuario(nome, login, senha, tipo);
                u.setId(Serial.proximo(application));

                usuarios.add(u);

                out.print("<p>Cadastrado com sucesso: " + nome + " (" + tipo + ")</p>");
                out.print("<br><a href='index.html'><button>Voltar</button></a>");
                System.out.println("Lista atualizada: " + usuarios);
            }


        } else {
            out.print("<p>Você precisa informar todos os campos.</p>");
        }
    }
}