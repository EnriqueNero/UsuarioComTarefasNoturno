package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buscartarefa")
public class BuscarTarefa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.getWriter().write("{}");
            return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String tid = req.getParameter("id");
        if (tid == null || tid.isBlank()) {
            resp.getWriter().write("{}");
            return;
        }
        int id = Integer.parseInt(tid);
        Tarefa tarefa = usuario.getTarefas().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if (tarefa == null) {
            resp.getWriter().write("{}");
            return;
        }
        String data = tarefa.getData() != null ? tarefa.getData().toString() : "";
        resp.getWriter().write("{" +
                "\"id\":" + tarefa.getId() + "," +
                "\"titulo\":\"" + tarefa.getTitulo().replace("\"","\\\"") + "\"," +
                "\"descricao\":\"" + tarefa.getDescricao().replace("\"","\\\"") + "\"," +
                "\"data\":\"" + data + "\"," +
                "\"categoria\":\"" + (tarefa.getCategoria() != null ? tarefa.getCategoria().replace("\"","\\\"") : "") + "\"}" );
    }
}
