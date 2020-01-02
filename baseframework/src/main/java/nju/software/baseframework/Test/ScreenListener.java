package nju.software.baseframework.Test;

import java.util.concurrent.Callable;

/**
 * @author Jingjing
 * @date 2019/11/15
 */
public class ScreenListener implements Callable {
    @Override
    public Object call() throws Exception {
        return ScreenTest.currentSearchResult;
    }
}
