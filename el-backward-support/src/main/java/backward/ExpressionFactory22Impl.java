package backward;

import java.util.Properties;

import com.sun.el.ExpressionFactoryImpl;

public class ExpressionFactory22Impl extends ExpressionFactoryImpl {
    
    private static final String BACKWARD_COMPATIBILITY = "javax.el.bc2.2";
    private static final Properties PROPERTIES = new Properties();
    
    static {
        String support = System.getProperty(BACKWARD_COMPATIBILITY, "false");
        PROPERTIES.put(BACKWARD_COMPATIBILITY, support);
    }

    public ExpressionFactory22Impl() {
        this(PROPERTIES);
    }

    public ExpressionFactory22Impl(Properties properties) {
        super(properties);
    }

}
