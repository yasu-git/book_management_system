package controllers.usersRegistration;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Book;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UserRegistrationDestroyServlet
 */
@WebServlet("/userRegistration/destroy")
public class UserRegistrationDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO Auto-generated method stub
        String _token = (String) request.getParameter("_token");

        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            User u = em.find(User.class, (Integer) (request.getSession().getAttribute("user_id")));

            List<Book> books = em.createNamedQuery("getMyAllBooks", Book.class)
                    .setParameter("user", u)
                    .getResultList();

            //ユーザーの削除処理
            em.getTransaction().begin();

            //ユーザー情報を消す前に本情報を消すこと!!
            //ユーザーを消すとエラーがでる
            for (Book book : books) {
                em.remove(book);
            }

            //ユーザー情報を入れると消せる
            em.remove(u);

            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "削除が完了しました。");

            request.getSession().removeAttribute("login_user");

            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}
