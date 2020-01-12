package controllers.usersRegistration;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UserRegistrationEdit
 */
@WebServlet("/userRegistration/edit")
public class UserRegistrationEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        User u = (User)request.getSession().getAttribute("login_user");

        em.close();

        request.setAttribute("user", u);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("user_id", u.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/userRegistration/edit.jsp");
        rd.forward(request, response);
    }

}
