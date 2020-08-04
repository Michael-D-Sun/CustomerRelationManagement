package cn.michael.user.web.servlet;

import cn.michael.user.domain.User;
import cn.michael.user.service.UserException;
import cn.michael.user.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.CommonDataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterServlet doPost()");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserService();
        User form = new User();
        try {
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        /*
         Form verification
         */
        Map<String, String> errors = new HashMap<String, String>();
        String username = form.getUsername();
        if(username == null || username.trim().isEmpty()){
            errors.put("username", "username cannot be empty");
        }else  if(username.length() < 3 || username.length() > 15){
            errors.put("username", "The length of username must between 3 and 15");
        }

        String password = form.getPassword();
        if(password == null || password.trim().isEmpty()){
            errors.put("password", "Password cannot be empty");
        }else if(password.length() < 5){
            errors.put("password", "The length of password must be longer than 5");
        }

        if(errors != null && errors.size() > 0){
            request.setAttribute("errors", errors);
            request.setAttribute("user", form);
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }
        System.out.println(form);
        try {
            userService.register(form);
            response.getWriter().print("<h1>Congrats. Register Successfully.</h1><a href='"+ request.getContextPath()+ "/user/login.jsp" + "'>Click here </a>");
        } catch (UserException e) {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterServlet doGet()");
    }


}
