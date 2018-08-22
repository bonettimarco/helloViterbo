/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controle;

import java.io.*;
import java.text.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;

/**
 *
 * @author marco
 */
@WebServlet(name = "helloViterbo", urlPatterns = {"/"})

public class HelloViterbo extends HttpServlet {

    /**
     * @param args the command line arguments
     */
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<HTML><BODY>");
        out.println("Hello World!");
        out.println("</HTML></BODY>");
}

public static void main(String[] args) {
    HelloViterbo hello = new HelloViterbo();
    }

}
