package com.twocats.treehole;

import cn.hutool.core.util.StrUtil;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author frankfyang
 * @date 2023-08-13 20:02
 */
@SpringBootApplication
public class TreeHoleCustomApp {

    private static final Logger LG = LoggerFactory.getLogger(TreeHoleCustomApp.class);

    public static void main(String[] args){
        String logHome = System.getProperty("log.home");
        String logPath = "";
        if (StrUtil.isNotEmpty(logHome)) {
            logPath = logHome + File.separator;
        }
        System.setProperty("spring.pid.file", logPath + "application.pid");
        SpringApplication application = new SpringApplication(TreeHoleCustomApp.class);
        ApplicationPidFileWriter pidFileWriter = new ApplicationPidFileWriter();
        pidFileWriter.setTriggerEventType(ApplicationReadyEvent.class);
        application.addListeners(pidFileWriter);
        application.addListeners((ApplicationListener<ApplicationFailedEvent>) event -> {
            LG.error("spring init error exit process", event.getException());
            System.exit(-2);
        });
        application.run(args);
    }
}
