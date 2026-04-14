package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/listarcategorias")
public class ListarCategorias extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        ServletContext application = getServletContext();
        Set<Categoria> categorias = (Set<Categoria>) application.getAttribute("categorias");
        StringBuilder json = new StringBuilder("[");
        boolean first = true;
        for (Categoria c : categorias) {
            if (!first) json.append(",");
            json.append("{\"id\":").append(c.getId()).append(",\"nome\":\"").append(c.getNome()).append("\"}");
            first = false;
        }
        json.append("]");
        resp.getWriter().write(json.toString());
    }
}
