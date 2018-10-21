/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.galeriaimagenes_diazjose;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                maxFileSize=1024*1024*10,       // 10MB
                maxRequestSize=1024*1024*50)    // 50MB
/**
 *
 * @author josedg
 */
public class ImageManagerServlet extends HttpServlet {

    private static final String SAVE_DIR = "uploads";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        
        // Set file type (necessary to read the html)
        response.setContentType("text/html");
        
        // Create a printwriter so we can fill the page
        PrintWriter out = response.getWriter();
        
        File folder = new File(appPath + File.separator + "uploads");
        
        File[] listOfFiles = folder.listFiles();
        
        // Basic html
        out.println("<title>Image Gallery</title>");
        out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
        out.println("<h1>Image Gallery</h1>");
        out.println("<p><strong>Server info: </strong>List of files found in the <i>uploads</i> directory:</p>");
        out.println("<ul>");
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String savePath = appPath + File.separator + SAVE_DIR;
                File my_file = new File(savePath + File.separator + file.getName());
                out.println("<li><a href=\"" + my_file + "\">" + file.getName() + "</a></li>");
            }
        }
        out.println("</ul>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
        
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        
        // If the folder doesn't exists, we have to create it
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
        
            String fileName = extractFileName(part);

            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
            System.out.println(savePath + File.separator + fileName);            
        }
        
        request.setAttribute("message", "Your upload has been done successfully!");
            getServletContext().getRequestDispatcher("/success.jsp").forward(request,
            response);
        }
        
        /**
    * Extracts file name from HTTP header content-disposition
    */
    private String extractFileName(Part part) {

        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }    
}