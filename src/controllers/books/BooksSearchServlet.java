package controllers.books;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Book;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class BooksSerachServlet
 */
@WebServlet("/books/search")
public class BooksSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //ログインユーザー
        User login_user = (User) request.getSession().getAttribute("login_user");

        int page;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }

        String keyword = request.getParameter("keyword");

        List<Book> books = em.createNamedQuery("getMyBooksSearch", Book.class)
                .setParameter("user", login_user)
                .setParameter("au1", "%" + keyword + "%")
                .setParameter("au2", "%" + keyword + "%")
                .setParameter("au3", "%" + keyword + "%")
                .setParameter("pu", "%" + keyword + "%")
                .setParameter("tit", "%" + keyword + "%")
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        //本の数
        long books_count = (long) em.createNamedQuery("getMyBooksSearchCount", Long.class)
                .setParameter("user", login_user)
                .setParameter("au1", "%" + keyword + "%")
                .setParameter("au2", "%" + keyword + "%")
                .setParameter("au3", "%" + keyword + "%")
                .setParameter("pu", "%" + keyword + "%")
                .setParameter("tit", "%" + keyword + "%")
                .getSingleResult();

        em.close();

        request.setAttribute("books", books);
        request.setAttribute("books_count", books_count);
        request.setAttribute("page", page);
        request.setAttribute("keyword", keyword);

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/books/search.jsp");
        rd.forward(request, response);

    }

}
