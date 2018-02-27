package com.ef;

import com.ef.service.impl.ParserServiceImpl;
import static com.ef.util.LogUtil.info;

/**
 * Main class for Parser application
 * @author jdiaz86
 */
public class Parser {

    public static void main(String... args) {
        info("**** processing data... ****");
        new ParserServiceImpl().run(args);
    }

}
