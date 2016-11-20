package project.project;

/**
 * Created by Marc on 18/11/2016.
 */

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/adduser")
public class AddUser extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String nickname = (req.getParameter("nickname"));
        String password = req.getParameter("password");

        try {
            Singleton.getInstance().insertUser(nickname, password);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(Singleton.getInstance().showUser(nickname).nickname);
        req.getRequestDispatcher("/index.jsp").forward(req, res);



    }
}

