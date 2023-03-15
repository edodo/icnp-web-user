/***************************************************
 * Copyright(c) 2023 in-soft All right reserved.
 * This software is the proprietary information of in-soft.
 *
 * Revision History
 * Author                         Date          Description
 * --------------------------     ----------    ----------------------------------------
 * smchoi@in-soft.co.kr           2023. 01. 26.
 *
 ****************************************************/
package icnp.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import icnp.util.LibraryPrintTest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * @Project : web-sample
 * @FileName : MyRunner.java
 * @Date : 2023. 01. 26.
 * @author : smchoi@in-soft.co.kr
 * @description : 기동시 값들 체크
 */
@RequiredArgsConstructor
@Component
public class ConfigRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(ConfigRunner.class);

    private final Environment env;

//    @Autowired
//    DataSource dataSource;

//    @Autowired
//    LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy;
    public void run(ApplicationArguments args) throws Exception {
        logger.info("logger Class = {}", logger.getClass().getName());

        LibraryPrintTest lt = new LibraryPrintTest();
        logger.info("icnp.common.lib Module Test = {}", lt.printMessage());

//        Connection conn = dataSource.getConnection();
//        logger.info("DBCP: " + dataSource.getClass()); // 사용하는 DBCP 타입 확인
//        logger.info("DB URL = {}", conn.getMetaData().getURL());
//        logger.info("DB UserName = {}", conn.getMetaData().getUserName());

        logger.info("server.port = {}", env.getProperty("server.port") );
        logger.info("feign.url.product = {}", env.getProperty("feign.url.product") );


//        final HikariPoolMXBean hikariPoolMXBean = ((HikariDataSource) dataSource).getHikariPoolMXBean();
//        System.out.println("################################");
//        System.out.println("현재 active인 connection의 수 : " + hikariPoolMXBean.getActiveConnections());
//        System.out.println("현재 idle인 connection의 수 : " + hikariPoolMXBean.getIdleConnections());
//        System.out.println("################################");

//        final HikariPoolMXBean hikariPoolMXBean = ((HikariDataSource)lazyConnectionDataSourceProxy.getTargetDataSource()).getHikariPoolMXBean();
//        System.out.println("################################");
//        System.out.println("현재 active인 connection의 수 : " + hikariPoolMXBean.getActiveConnections());
//        System.out.println("현재 idle인 connection의 수 : " + hikariPoolMXBean.getIdleConnections());
//        System.out.println("################################");
    }
}