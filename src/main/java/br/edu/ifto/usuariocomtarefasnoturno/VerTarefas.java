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

@WebServlet("/vertarefas")
public class VerTarefas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        request.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession(false);
        if(session==null){
            out.print("<p>Você precisa estar logado.");
            return;
        }
        Usuario usuarioLogado=(Usuario) session.getAttribute("usuario");
        out.println("<table border=\"1\">\n" +
                "    <thead>\n" +
                "        <tr>\n" +
                "            <th>Id</th>\n" +
                "            <th>Título</th>\n" +
                "            <th>Descrição</th>\n" +
                "            <th>Data</th>\n" +
                "            <th>Categoria</th>\n" +
                "            <th>Editar</th>\n" +
                "            <th>Deletar</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tbody>");

        for(Tarefa t:usuarioLogado.getTarefas()){
            out.print("<tr><td>"+t.getId()+"</td>");
            out.print("<td>"+t.getTitulo()+"</td>");
            out.print("<td>"+t.getDescricao()+"</td>");
            out.print("<td>"+t.getData()+"</td>");
            out.print("<td>"+t.getCategoria()+"</td>");
            out.print("<td><a href='editartarefa.html?id="+t.getId()+"'>Editar</a></td>");
            out.print("<td><a href='deletartarefa?id="+t.getId()+"' >Deletar</a></td></tr>");
        }

        out.print("</tbody>\n" +
                "</table>");
        out.print("<br><a href='menu.html'>Voltar ao menu</a>");
    }
}