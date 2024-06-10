package com.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@Component
@Profile("!test") // Esta configuración no se aplicará cuando se active el perfil "test"
@Slf4j
public class DataBaseInitializer {

    private final ResourceLoader resourceLoader;
    private final JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Autowired
    public DataBaseInitializer(ResourceLoader resourceLoader, JdbcTemplate jdbcTemplate) {
        this.resourceLoader = resourceLoader;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initializeDatabase() {
        try {
            String scriptPath = determineScriptPath();
            // Load the SQL file
            Resource resource = resourceLoader.getResource(scriptPath);
            log.info("Inicializando base de datos con el script: {}", scriptPath);

            // Read the file content
            byte[] contentBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String scriptContent = new String(contentBytes, StandardCharsets.UTF_8);

            // Split the content into individual statements
            String[] statements = scriptContent.split(";");

            // Execute each statement individually
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    jdbcTemplate.execute(statement.trim());
                    log.info("Se ha ejecutado la sentencia: {}", statement.trim());
                }
            }

            log.info("Base de datos inicializada correctamente");

        } catch (IOException e) {
            log.error("Error al inicializar la base de datos", e);
        }
    }

    private String determineScriptPath() {
        if (dataSourceUrl.contains("mysql")) {
            return "classpath:database-script-mysql.sql";
        } else if (dataSourceUrl.contains("h2")) {
            return "classpath:database-script-h2.sql";
        } else {
            throw new IllegalArgumentException("No se ha podido determinar el script de inicialización de la base de datos");
        }
    }
}
