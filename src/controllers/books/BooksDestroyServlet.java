package controllers.books;


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Book;
import utils.DBUtil;

/**
 * Servlet implementation class BooksDestroyServlet
 */
@WebServlet("/books/destroy")
public class BooksDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Book b = em.find(Book.class, (Integer)(request.getSession().getAttribute("book_id")));

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
            em.close();

            //セッションスコープ上の不要になったデータを削除
            request.getSession().setAttribute("flush", "削除が完了しました。");
            request.getSession().removeAttribute("book_id");

            //indexぺーじへリダイレクト
            response.sendRedirect(request.getContextPath() + "/books/index");
        }
    }

}