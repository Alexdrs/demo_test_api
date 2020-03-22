/**
 * @author Alexandr.Drasikov
 */
package ru.autotests.helpers.reporter;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class TestFilter extends Filter {
    @Override
    public int decide(LoggingEvent loggingEvent) {
        if (loggingEvent.getLevel() == Level.DEBUG)
            return ACCEPT;
        else
            return DENY;
    }
}
