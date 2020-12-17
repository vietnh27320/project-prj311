package controller;

import dao.ImgDao;
import dao.ProductDao;
import entity.Cart;
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
import util.NumberHelper;

@WebServlet(name = "AddToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int productId = NumberHelper.getValidateId(request.getParameter("id"));
            int page = NumberHelper.getValidateId(request.getParameter("page"));
            int quantity = 1;
            List<Cart> listCart = null;

            if (productId > 0) {
                Product p = new ProductDao().getOne(productId);
                List<Image> listImg = new ImgDao().getAllByProID(productId);
                String image = "";
                for (Image img : listImg) {
                    if (img.isPicture_cover()) {
                        image = img.getImgName();
                    }
                }
                if (p != null) {
                    Cart c = Cart.builder()
                            .productId(p.getId())
                            .productName(p.getName())
                            .productImage(image)
                            .productPrice(p.getPrice())
                            .quantity(quantity)
                            .totalPrice(p.getPrice() * quantity).build();
                    //check session
                    HttpSession session = request.getSession();
                    listCart = (List<Cart>) session.getAttribute("listCart");
                    if (listCart == null)//chưa add sp 
                    {
                        listCart = new ArrayList<>();
                        listCart.add(c);
                        session.setAttribute("listCart", listCart);
                    } else {//TH trong giỏ hàng đã có sp nên phải update quantity
                        boolean flag = true;
                        for (Cart cart : listCart) {
                            if (cart.getProductId() == productId) {
                                cart.setQuantity(cart.getQuantity() + 1);//nếu gặp thằng trùng thì mới tăng quantity
                                cart.setTotalPrice(cart.getQuantity() * p.getPrice());
                                flag = false;
                            }

                        }
                        if (flag) {//nếu ko tìm thấy thằng id nào trùng lặp thì mới tạo thêm 1 thằng sp mới dc add
                            listCart.add(c);
                        }
                        session.setAttribute("listCart", listCart);

                    }
                    if (page > 0) {
                        switch (page) {
                            case 1:
                                response.sendRedirect("/get-list-product?flag=1");
                                break;
                            case 2:
                                response.sendRedirect("GetProDetail?productId=" + productId);
                                break;
                            default:
                                response.sendRedirect("error.jsp");//TH return null
                                break;
                        }
                    } else {
                        response.sendRedirect("error.jsp");//TH bị sửa page
                    }
                } else {
                    response.sendRedirect("error.jsp");//TH ko có sp đó
                }
            } else {
                response.sendRedirect("error.jsp");//TH user sửa  id <0
            }
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
