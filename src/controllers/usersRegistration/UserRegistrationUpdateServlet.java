package controllers.usersRegistration;

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

import models.User;
import models.validators.UserValidators;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UserRegistrationUpdate
 */
@WebServlet("/userRegistration/update")
public class UserRegistrationUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            User u = em.find(User.class , (Integer)(request.getSession().getAttribute("user_id")));

            //現在の値と異なるユーザー番号が入力されていたら
            //重複チェックを行う指定をする

            Boolean uerId_duplicate_check = true;

            if(u.getUserId().equals(request.getParameter("userId"))){
                uerId_duplicate_check = false;
            }else{
                u.setUserId(request.getParameter("userId"));
            }

            ///パスワード欄に入力があったら
            //パスワードの入力値チェックを行う指定をする

            Boolean password_check_flag = true;

            String password = request.getParameter("password");

            if(password == null || password.equals("")){
                password_check_flag = false;
            }else{
                u.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("salt")
                                )
                        );
            }

            u.setUserName(request.getParameter("userName"));
            u.setAdmin_flag(0);
            u.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = UserValidators.validate(u, uerId_duplicate_check, password_check_flag);

            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", u);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/userRegistration/edit.jsp");
                rd.forward(request, response);

            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました");

                request.getSession().removeAttribute("user_id");
                request.getSession().setAttribute("login_user", u);

                response.sendRedirect(request.getContextPath() + "/");

            }
        }

    }

}
