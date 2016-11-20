package project.project;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Marc on 18/11/2016.
 */
@WebServlet("/addetakemon")
public class AddEtakemon extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String nickname = (req.getParameter("nickname"));
        String name = (req.getParameter("name"));
        String type = (req.getParameter("type"));
        int id=0;
        Etakemon etak = new Etakemon(id, name, type);

        try {
            Singleton.getInstance().addEtakemon(nickname, etak);
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
