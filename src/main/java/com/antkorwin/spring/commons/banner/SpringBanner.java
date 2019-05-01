package com.antkorwin.spring.commons.banner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Print a banner after running of the application
 * with the additional info:
 *  - application name
 *  - host / port
 *  - active profiles
 *
 * @author Korovin Anatoliy
 */
public class SpringBanner {

    public static void print(Supplier<ConfigurableApplicationContext> springRunner) {
        // Run
        ConfigurableApplicationContext applicationContext = springRunner.get();
        // Arrange
        Logger logger = LoggerFactory.getLogger(applicationContext.getClass());
        Environment env = applicationContext.getEnvironment();
        // Print
        try {
            logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! \n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Profile(s): \t{}\n" +
                        "----------------------------------------------------------\n",
                        env.getProperty("spring.application.name"),
                        env.getProperty("server.port"),
                        InetAddress.getLocalHost().getHostAddress(),
                        env.getProperty("server.port"),
                        Arrays.toString(env.getActiveProfiles()));
        } catch (UnknownHostException e) {
            logger.error("Exception host-address", e);
        }
    }
}
