package com.bh.at.main;

import com.bh.at.tester.BaseTester;
import com.bh.commonutil.CyOptArgs;
import io.cucumber.core.cli.Main;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bh.at.atutil.CyAtConst.*;
import static com.bh.at.main.AppConfig.*;

@RunWith(Cucumber.class)
//@CucumberOptions(
//            features = "src/main/resources/Features",
//                    glue={"com/bh/at/step"},
//            tags="@UI",
//            plugin = { "pretty", "json:cucumber-reports/Cucumber.json","html:cucumber-reports/Cucumber.html","junit:cucumber-reports/Cucumber.xml" },
//            monochrome = true

//)

public class Runner {
    private static final String DEF_LOG_CONF = "log.conf";
    private static final Logger LOG = setupLogConf();

    public static void main(String[] args) {

        LOG.info("ei Runner Started");
        System.out.println("file.encoding : " + System.getProperty("file.encoding"));
        AppConfig.printIpAddress();
        HandleArgs(args);
        AppConfig.setEnv();
        Main.main(AppConfig.getCucumberRunTimeParam());
    }

    /**
     * Handle args.
     *
     * @param args the args
     */
    private static void HandleArgs(String[] args) {

        Map<String, String> optionsArgs = new HashMap<>();
        List<String> cmdArgs = new ArrayList<>();

        CyOptArgs.processArgs(args, optionsArgs, cmdArgs);

        if (cmdArgs.size() > 1) {
            AppConfig.AppErrorExit(new Exception("Exactly ZERO or ONE argument expected"));
        } else {
            envParamsFile = (0 == cmdArgs.size()) ? "envParams.json" : cmdArgs.get(0);
        }

        optionsArgs.forEach((String key, String value) -> {
//      System.out.println(key + " -> " + value);

            switch (key) {
                case "suites":
                    suites = value;
                    break;

                case "browser":
                    runtimeOptions[BROWSERS_OR_APPS_NDX] = value;
                    break;

                case "device":
                    runtimeOptions[DEVICE_NDX] = value;
                    break;

                case "platform":
                    runtimeOptions[PLATFORM_NDX] = value;
                    break;

                case "report":
                    reportsFolder = value;
                    break;

                case "env":
                    envParamsFile = value;
                    break;

                case "tfs":
                    isTfsReportingEnabled = Boolean.parseBoolean(value);
                    break;

                case "tfsUpload":
                    isTfsUploadingEnabled = Boolean.parseBoolean(value);
                    break;

                case "tenant":
                    if (value != null)
                        if (value.length() > 1)
                            BaseTester.currentTenant = value;
                    break;

                default:
                    System.out.println("Unknown option " + key);
                    printUsageAndExit();
                    break;
            }
        });

        envParamsFile = envParamsFile.trim();

        if (0 == envParamsFile.length()) {
            LOG.error("Argument cannot be an empty string");
            printUsageAndExit();
        }
    }

    /**
     * Set the log configuration for tinylog
     */
    private static Logger setupLogConf() {

        String logConfiguration = appName;
        if (null == System.getProperty("tinylog.configuration")) {
            String jarName = new java.io.File(Runner.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                    .getName();
            // jarName will not work when running from the ide works only on command line
            if (jarName.endsWith(".jar")) {
                Matcher m = Pattern.compile("(.+)(-\\d+\\..+\\.jar)").matcher(jarName);
                if (m.find()) {
                    logConfiguration = m.group(1);
                }
            }

            logConfiguration += "." + DEF_LOG_CONF;

            if (Files.exists(Path.of(logConfiguration))) {
                System.setProperty("tinylog.configuration", logConfiguration);
            } else if (Files.exists(Path.of(DEF_LOG_CONF))) {
                System.setProperty("tinylog.configuration", DEF_LOG_CONF);
            }
        }

        return LoggerFactory.getLogger(Runner.class);
    }

    /**
     * Print usage and exit.
     */
    private static void printUsageAndExit() {
        System.out.println("Usage: java -jar <jarName> --env=<environment parameters file> --suites=<suite tags for cucumber> --browser=<browse to start> --report=<target folder name for the generated cucumber.json , xml and html files>");
        System.out.println("\tAll arguments are optional, defaults are as follows");
        System.out.println("\tenvParams.json default for --env");
        System.out.println("\tAll features default for --suites");
        System.out.println("\tvalue from env params is the default for --browser");
        System.out.println("\trelative to current folder is the default for --report");

        System.exit(1);
    }
}

