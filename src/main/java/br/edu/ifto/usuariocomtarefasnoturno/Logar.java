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

@WebServlet("/logar")
public class Logar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        if (login != null && !login.isBlank() && senha != null && !senha.isBlank()) {
            ServletContext application = getServletContext();
            Set<Usuario> usuarios = (Set<Usuario>) application.getAttribute("usuarios");
            for (Usuario u : usuarios) {
                if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", u);
                    response.sendRedirect("menu.html");
                    return;
                }
            }
            out.print("<p>Login ou senha incorretos.");
        } else {
            out.print("<p>Voce precisa informar o login e a senha.");
        }

    }
}
