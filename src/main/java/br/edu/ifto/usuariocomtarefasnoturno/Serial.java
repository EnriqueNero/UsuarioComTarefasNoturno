package br.edu.ifto.usuariocomtarefasnoturno;

import jakarta.servlet.ServletContext;

public class Serial {
    public static int proximo(ServletContext application){
        int serial=(Integer) application.getAttribute("serial");
        serial++;
        application.setAttribute("serial",serial);
        return serial;
    }
}
