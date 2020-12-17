package controller;

import dao.AccountDao;
import dao.CategoryDao;
import dao.ImgDao;
import dao.ProductDao;
import entity.Account;
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
import javax.servlet.http.HttpSession;
//import sevice.ImageService;
//import sevice.ProductService;

@WebServlet(name = "NewServlet", urlPatterns = {"/get-list-product"})
public class GetListPro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //
//             Account account = new AccountDao().getOne(1);
//            HttpSession session = request.getSession();
//            session.setAttribute("cusID", account);
            ProductDao db = new ProductDao();
            int pagesize = 8;
            String raw_pageindex = request.getParameter("page1");
            if (raw_pageindex == null) {
                raw_pageindex = "1";
            }
            int pageindex = Integer.parseInt(raw_pageindex);
            int count = db.count();// số lượng bản ghi thỏa mãn điều kiện 17
            // nếu tổng số bản ghi chia cho số lượng bản ghi trên 1 trang ko dư thì ko cần thêm 1 trang cuối
            int pagecount = 0;   //số trang phân được:

            if (count % pagesize == 0) {
                pagecount = count / pagesize;
            } else {
                pagecount = count / pagesize + 1;
            }

            ArrayList<Product> listPro = db.paging(pageindex, pagesize);

//            List<Product> listPro = new ProductDao().getAll();
            List<Image> listImg = new ImgDao().getAll();
            List<Category> listCate = new CategoryDao().getAll();
            List<Image> listImgCover = new ArrayList<>();
            for (Image image : listImg) {
                if (image.isPicture_cover()) {
                    listImgCover.add(image);
                }
            }
            // hiện thông báo đã add
            String flag = request.getParameter("flag");
            if (flag != null) {
                request.setAttribute("message", "thêm vào giỏ hàng thành công!!!");
            }
            List<Image> listImagecoverMapping = new ImgDao().listImageCoverMapping(listImgCover, listPro);

            request.setAttribute("results", listPro);
            request.setAttribute("listImagecoverMapping", listImagecoverMapping);
            request.setAttribute("category", listCate);
            request.setAttribute("pageCount", pagecount);
            request.setAttribute("pageIndex", pageindex);
            request.setAttribute("htmlHelper", new util.HtmlHelper());
            request.getRequestDispatcher("newjsp.jsp").forward(request, response);

        }
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
