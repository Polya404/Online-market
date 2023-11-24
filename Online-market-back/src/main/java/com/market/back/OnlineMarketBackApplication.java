package com.market.back;

import com.market.back.services.DataMigrationService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class OnlineMarketBackApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OnlineMarketBackApplication.class, args);

        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:15432/postgres", "user", "secret").load();
        flyway.migrate();

        DataMigrationService migrationService = context.getBean(DataMigrationService.class);
        migrationService.migrateData();
    }

}
