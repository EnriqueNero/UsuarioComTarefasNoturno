package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession(false) != null) {
            req.getSession().invalidate(); // Mata a sessão
        }
        resp.sendRedirect("index.html"); // Redireciona para tela de login/cadastro
    }
}