package backward;

import java.util.Properties;
import java.util.logging.Logger;

import com.sun.el.ExpressionFactoryImpl;

public class ExpressionFactory22Impl extends ExpressionFactoryImpl {
    
    private static final Logger LOGGER = Logger.getLogger(ExpressionFactory22Impl.class.getName());
    private static final String BACKWARD_COMPATIBILITY = "javax.el.bc2.2";
    private static final Properties PROPERTIES = new Properties();
    
    static {
        String support = System.getProperty(BACKWARD_COMPATIBILITY, "false");
        PROPERTIES.put(BACKWARD_COMPATIBILITY, support);
        LOGGER.info(BACKWARD_COMPATIBILITY + " is set to " + support);
    }

    public ExpressionFactory22Impl() {
        this(PROPERTIES);
    }

    public ExpressionFactory22Impl(Properties properties) {
        super(PROPERTIES);
    }

}
