package controllers.books;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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

        keyword = keyword.replaceAll("　", " ");

        String keys[] = keyword.split(" ");

        List<Book> book = new ArrayList<Book>();
        long books_count =0L;

        //検索件数のものが心配
        if (!keyword.equals("")) {
            List<Book> books = new ArrayList<Book>();
            for (String key : keys) {

                if (!key.equals("")) {
                    List<Book> result = em.createNamedQuery("getMyBooksSearch", Book.class)
                            .setParameter("user", login_user)
                            .setParameter("au1", "%" + key + "%")
                            .setParameter("au2", "%" + key + "%")
                            .setParameter("au3", "%" + key + "%")
                            .setParameter("pu", "%" + key + "%")
                            .setParameter("tit", "%" + key + "%")
                            .getResultList();

                    books.addAll(result);
                }
            }
            //検索結果の重複部分をLinkedHashSetで取り除く
            books = new ArrayList<Book>(new LinkedHashSet<>(books));

            books_count =books.size();

            for(int i =0 + (page-1)*15 ;i <= page * 15 -1; i++){
                //15件情報を挿入する処理
                //情報が途中で亡くなった場合エラーが出るので途中で破棄する
                try{

                    book.add(books.get(i));
                }catch(Exception e){
                    break;
                }

            }

        }else{
            book = em.createNamedQuery("getMyAllBooks", Book.class)
                    .setParameter("user", login_user)
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();

            books_count = (long)em.createNamedQuery("getMyBooksCount", Long.class)
                    .setParameter("user", login_user)
                    .getSingleResult();
        }



        em.close();

        request.setAttribute("books", book);
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
