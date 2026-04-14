package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sessioninfo")
public class SessionInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            Usuario u = (Usuario) session.getAttribute("usuario");
            resp.getWriter().write("{\"login\":\""+u.getLogin()+"\",\"tipo\":\""+u.getTipo()+"\"}");
        } else {
            resp.getWriter().write("{}");
        }
    }
}
