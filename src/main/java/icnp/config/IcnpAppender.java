package icnp.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Plugin(
        name = "IcnpAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE,
        printObject = true)
public class IcnpAppender extends AbstractAppender {

    protected IcnpAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
        LOGGER.info("IcnpAppender is instantiated...");
    }

    @PluginFactory
    public static IcnpAppender createAppender(@PluginAttribute("name") String name,
                                                  @PluginElement("Layout") Layout<? extends Serializable> layout,
                                                  @PluginElement("Filter") final Filter filter, @PluginAttribute("otherAttribute") String otherAttribute) {
        if (name == null) {
            LOGGER.error("There is no name provided for MyCustomAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new IcnpAppender(name, filter, layout, true, null);

    }


    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    @Override
    public void append(LogEvent event) {


        readLock.lock();
        try {
            final byte[] bytes = getLayout().toByteArray(event);
            System.out.print("[csm0222" + event.getLevel().name() +"] " + new String(bytes));
            switch (event.getLevel().name().toUpperCase()) {
                case "INFO" :
                    LOGGER.info("[csm0222 - INFO] " + new String(bytes));
                    break;
                case "DEBUG" :
                    LOGGER.debug("[csm0222 - DEBUG] " + new String(bytes));
                    break;
                case "TRACE" :
                    LOGGER.trace("[csm0222 - TRACE] " + new String(bytes));
                    break;
                case "WARN" :
                    LOGGER.warn("[csm0222 - WARN] " + new String(bytes));
                    break;
                case "ERROR" :
                    LOGGER.error("[csm0222 - ERROR] " + new String(bytes));
                    break;
                default :
                    break;
            }
        } catch (Exception ex) {
            if (!ignoreExceptions()) {
                throw new AppenderLoggingException(ex);
            }
        } finally {
            readLock.unlock();
        }
    }
}
