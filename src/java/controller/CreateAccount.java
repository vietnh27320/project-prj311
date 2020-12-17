package controller;

import dao.AccountDao;
import dao.AccountDetailDao;
import entity.Account;
import entity.AccountDetail;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateAccount", urlPatterns = {"/createAccount"})
public class CreateAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Acname = request.getParameter("cusName");
        String AcMobile = request.getParameter("mobile");
        String Acgender = request.getParameter("gender");
        String AcAddress = request.getParameter("address");
        String email = request.getParameter("Nemail");
        String password = request.getParameter("Npassword");

        AccountDetailDao accountDetailDao = new AccountDetailDao();
        AccountDetail accountDetail = AccountDetail.builder().name(Acname)
                .mobile(AcMobile).gender(Integer.parseInt(Acgender)).address(AcAddress).build();
        int id = accountDetailDao.addAccountReturnId(accountDetail);
        AccountDao ad = new AccountDao();
        Account account = Account.builder().account_detail_id(id).email(email).password(password).build();
        ad.add(account);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
