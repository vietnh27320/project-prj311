package controller;

import dao.CategoryDao;
import dao.ImgDao;
import dao.ProductDao;
import entity.Category;
import entity.Image;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "d", urlPatterns = {"/d"})
public class SortUp extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Product> products = new ProductDao().sortx();

        List<Image> listImg = new ImgDao().getAll();
        List<Category> listCate = new CategoryDao().getAll();
        List<Image> listImgCover = new ArrayList<>();
        List<Image> listImgCoverFilter = new ArrayList<>();

        for (Image image : listImg) {
            if (image.isPicture_cover()) {
                listImgCover.add(image);
            }
        }

        for (Image image : listImgCover) {
            for (Product p : products) {
                if (image.getProId() == p.getId()) {
                    listImgCoverFilter.add(image);
                }
            }

        }
        List<Image> listImagecoverMapping = new ImgDao().listImageCoverMapping(listImgCover, products);
        request.setAttribute("results", products);
        request.setAttribute("category", listCate);
        request.setAttribute("listImagecoverMapping", listImagecoverMapping);
        request.getRequestDispatcher("newjsp.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
