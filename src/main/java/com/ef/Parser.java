package com.ef;

import com.ef.service.ParserService;
import static com.ef.util.LogUtil.info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for Parser application
 * @author jdiaz86
 */
@SpringBootApplication
public class Parser implements CommandLineRunner {
    
    @Autowired
    ParserService parserService;

    public static void main(String... args) {
        info("**** processing data... ****");
        new SpringApplication(Parser.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        parserService.run(args);
    }

}
