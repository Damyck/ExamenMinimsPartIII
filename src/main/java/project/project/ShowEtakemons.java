package project.project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marc on 18/11/2016.
 */
public class ShowEtakemons extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String nickname = (req.getParameter("nickname"));

        List<Etakemon> etak = Singleton.getInstance().showuserEtakemon(nickname);



        Iterator<Etakemon> it = etak.iterator();

        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Etakemons you've got</title><style>\n" +
                "table {\n" +
                "    font-family: arial, sans-serif;\n" +
                "    border-collapse: collapse;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "    border: 1px solid #dddddd;\n" +
                "    text-align: left;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "    background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Name</th>\n" +
                "    <th>Type</th>\n");
            while (it.hasNext()) {
                Etakemon et = it.next();
                out.println("<tr>\n" +
                        "<td>" + et.name + "</td>\n" +
                        "<td>" + et.type + "</td>\n" +
                        "</tr>\n");
            }

        out.println("</table></body></html>");

    }





    }

