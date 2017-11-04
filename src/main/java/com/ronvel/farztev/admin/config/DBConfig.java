package com.ronvel.farztev.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration of the database.
 * @author mronvel
 *
 */
@Configuration
@EnableJpaRepositories(value = "com.ronvel.farztev.admin.dao")
@EnableTransactionManagement
public class DBConfig {

}
