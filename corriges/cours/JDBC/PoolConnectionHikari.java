package corriges.cours.JDBC;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// Classe de pool de connexion
public class PoolConnectionHikari {
    private static final HikariDataSource dataSource;

    // Bloc d'instruction d'initialisation s'executant seulement au chargement de la classe.
    static {
    	// Suppression des messages du logger (SLF4J SimpleLogger.jar) dans la console sauf les erreurs
    	System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
    	
    	// Configuration pour la connexion a la BDD.
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306?useSSL=false");
        config.setUsername("root");
        config.setPassword("");

        // Réglages
        // Nombre de connexion maximum
        // (erreur de connexion si on depasse le nombre de connexion)
        config.setMaximumPoolSize(5);
        // Nombre de connexion inactive minimum
        // (si on descend en dessous de ce seuil Hikari essai de creer d'autre connexion sans depasser le max)
        config.setMinimumIdle(2);
        // Validation automatique par defaut
        config.setAutoCommit(true);

        // Connexion a la BDD.
        dataSource = new HikariDataSource(config);
    }
    
    // Information sur la connexion.
    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}