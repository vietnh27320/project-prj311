package controller;

import dao.AccountDao;
import entity.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("email");
        String password = request.getParameter("password");
        Account account = new AccountDao().login(username, password);

        if (account == null) {
            request.setAttribute("message", "sai tài khoản hoặc mật khẩu!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("loginAccount", account);
            response.sendRedirect("get-list-product");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
