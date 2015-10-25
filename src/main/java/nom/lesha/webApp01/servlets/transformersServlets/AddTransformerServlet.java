package nom.lesha.webApp01.servlets.transformersServlets;



import nom.lesha.webApp01.model.Transformer;
import nom.lesha.webApp01.services.TransformerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alexey Levchenko
 */
public class AddTransformerServlet extends HttpServlet {

    String okRef = "dispatcher";
    String failRef = "WEB-INF/transformersJsp/HandleTransformer.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req = TransformerService.validateTransformersParams(req);
        Transformer transformer = (Transformer) req.getAttribute("transformer");

        if (req.getAttribute("transformerValidationReport") == null) {
            boolean success = TransformerService.addItem(transformer);
            if (success) {
                req.setAttribute("action", "to.transformer.list");
                RequestDispatcher dispatcher = req.getRequestDispatcher(okRef);
                dispatcher.forward(req, resp);
            } else {
                resp.setContentType("text/html");
                resp.getWriter().print("<h2><font color='#ff7f50'>Data base error...</font></h2>");
            }
        } else {
            req.setAttribute("action", "to.transformer.add");
            RequestDispatcher dispatcher = req.getRequestDispatcher(failRef);
            dispatcher.forward(req, resp);
        }




//        String s = "dfdf";
//        System.out.println(s[0]);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }



}
