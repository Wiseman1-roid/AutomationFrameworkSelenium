package org.base.test.drivers.strategies;

import org.base.test.utils.Constants;

public class DriverStrategyImplementer {
    public static DriverStrategy chooseStrategy(String strategy){
        return switch (strategy) {
            case Constants.CHROME -> new Chrome();
            case Constants.PHANTOMJS -> new PhantomJs();
            case Constants.FIREFOX -> new Firefox();
            default -> null;
        };
    }
}
