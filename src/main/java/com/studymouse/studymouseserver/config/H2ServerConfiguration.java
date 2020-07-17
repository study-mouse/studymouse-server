package com.studymouse.studymouseserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * Created by jyami on 2020/07/18
 */
@Configuration
@Profile("test")
public class H2ServerConfiguration {

    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer()
                .start();
    }
}
