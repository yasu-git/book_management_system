package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")){
            HttpSession session = ((HttpServletRequest)request).getSession();

            //セッションに保存されたユーザー情報を取得
            User u =(User)session.getAttribute("login_user");

            //ログイン画面以外について
            if(!servlet_path.equals("/login") && (!servlet_path.equals("/userRegistration/new"))){
                //ログアウトしている状態であれば
                //ログイン画面にリダイレクト
                if(u == null){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }

                //ユーザー管理機能は管理者のみが閲覧できるようにする
                if(servlet_path.matches("/users.*") && u.getAdmin_flag() == 0){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }

              //ユーザー管理機能は管理者のみが閲覧できるようにする
                if(servlet_path.matches("/books.index") && u.getAdmin_flag() == 0){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }


            }else{
                /*
                 * ログイン画面について
                 * ログインしているのにログイン画面を表示させようとした場合は
                 * システムのトップページにリダイレクト
                 */

                if(u != null){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                }
            }
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
