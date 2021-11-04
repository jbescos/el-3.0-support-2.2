package backward;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import javax.el.ExpressionFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.el.ExpressionFactoryImpl;

@SuppressWarnings("serial")
public class ServletExample extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExpressionFactory factory = ExpressionFactory.newInstance();
        PrintWriter pw = resp.getWriter();
        pw.println("ExpressionFactory is: " + factory);
        try {
            Field privateField = ExpressionFactoryImpl.class.getDeclaredField("isBackwardCompatible22");
            privateField.setAccessible(true);
            Boolean isBackwardCompatible22 = (Boolean) privateField.get(factory);
            pw.println("Is backwards compatible?: " + isBackwardCompatible22);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new ServletException("Cannot invoke isBackwardCompatible22 field in " + factory, e);
        }
        
    }

}
