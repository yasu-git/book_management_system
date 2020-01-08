package controllers.books;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import googleBookApi.GoogleBookApiJackson;
import models.Book;
import models.User;
import models.validators.IsbnValidator;
import utils.DBUtil;

/**
 * Servlet implementation class BooksCreateServlet
 */
@WebServlet("/books/create")
public class BooksCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooksCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Book b = new Book();

            String strisbn =request.getParameter("isbn");

            try{

                Long isbn = Long.parseLong(strisbn);
                GoogleBookApiJackson google = new GoogleBookApiJackson(isbn);
                b = google.getBook();

            }catch(Exception e){

                b.setAuthor1("");
                b.setListPrice(0);
                b.setDescription("");
                b.setPublishedDate(null);
                b.setThumbnail("");
                b.setSmallThumbnail("");
            }

            b.setUser((User)request.getSession().getAttribute("login_user"));

            b.setEvaluate(0);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            b.setCreated_at(currentTime);

            List<String> errors = IsbnValidator.validate(strisbn);

            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("book", b);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/books/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(b);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/books/index");
            }
        }
    }

}
