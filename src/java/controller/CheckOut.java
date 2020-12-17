package controller;

import dao.CustommerInfoDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Account;
import entity.Cart;
import entity.CustomerInfo;
import entity.Order;
import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import sevice.OrderService;

@WebServlet(name = "CheckOut", urlPatterns = {"/checkOut"})
public class CheckOut extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String mobile = request.getParameter("sdt");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String note = request.getParameter("note");

        CustomerInfo cf = CustomerInfo.builder().name(name).mobile(mobile).email(email).address(address).build();//1
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        int aid = account.getId();
        int customerId = new CustommerInfoDao().addCustomerInfoReturnId(cf);//add thành công trả về id, ko thì trả về 0

        if (customerId > 0) {
            session = request.getSession();
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            double totalPrice = 0;
            for (Cart cart : listCart) {
                totalPrice += cart.getTotalPrice();
            }
            Order o = Order.builder().customer_info_id(customerId).//2
                    total_price(totalPrice).
                    note(note).
                    status(2)
                    .accountId(aid).build();
            int orderId = new OrderDao().addOrderReturnId(o);
            if (orderId > 0) {
                boolean isCheck = new OrderDetailDao().addListCart(listCart, orderId);
                if (isCheck) {//neu mua thanh cong
                    session.removeAttribute("listCart");
                    response.sendRedirect("thanks.jsp");

                } else {
                    //delete order
                    //delete cf           
                    response.sendRedirect("error.jsp");
                }
            } else {
                System.out.println("oderid");//remove cf
                response.sendRedirect("error.jsp");

            }
        } else {//add failed
            System.out.println("cus");
            response.sendRedirect("error.jsp");

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
