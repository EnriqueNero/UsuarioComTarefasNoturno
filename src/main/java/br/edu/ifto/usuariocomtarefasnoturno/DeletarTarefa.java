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

@WebServlet("/deletartarefa")
public class DeletarTarefa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        request.setCharacterEncoding("utf-8");
        String tid=request.getParameter("id");
        if(!(tid!=null && !tid.isBlank())) {
            out.print("<p>Você precisa informar o id.");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }
        int id=Integer.parseInt(tid);
        HttpSession session = request.getSession(false);
        if (session == null) {
            out.print("<p>Você precisa estar logado.");
            out.print("<br><a href='menu.html'><button>Voltar</button></a>");
            return;
        }
        Usuario usuarioLogado=(Usuario) session.getAttribute("usuario");
        boolean deletou=false;
        for(Tarefa t:usuarioLogado.getTarefas()){
            if(t.getId()==id){
                usuarioLogado.getTarefas().remove(t);
                deletou=true;
                out.println("<p>Deletado com sucesso.");
                // Remover botão duplicado: só exibir após o if
                break;
            }
        }
        if(!deletou) {
            out.print("<p>Não foi possível deletar pois o id está incorreto.");
        }
        // Exibir botão de voltar apenas uma vez
        out.print("<br><a href='menu.html'><button>Voltar</button></a>");
    }
}