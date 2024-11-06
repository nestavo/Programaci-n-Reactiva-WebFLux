package es.nestavo.reactivo.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "es.nestavo.reactivo")
public class MongoConfig {
    // Configuración adicional si es necesaria
}

