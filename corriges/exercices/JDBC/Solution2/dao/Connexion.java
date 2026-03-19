package corriges.exercices.JDBC.Solution2.dao;

import java.sql.*;

import corriges.exercices.JDBC.Solution2.IConstantes;

/**
 * Connexion a MySQL
 * @author Twixy
 */
public class Connexion implements IConstantes {

    /**
     * Connexion a MySQL
     * @return la connexion si Ok
     * @throws Exception : message d'erreur personalise si besoin.
     */
    public static Connection connecterSansBase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Connexion a MySQL impossible.");
        }
    }

    /**
     * Connexion a la Base de donnees defini en constante dans IConstantes.java
     * @return la connexion si Ok
     * @throws Exception : message d'erreur personnalise si besoin
     */
    public static Connection connecterAvecBase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NOM_BDD + "?useSSL=false", "root", "");
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Connexion a la BDD (" + NOM_BDD + ") impossible.");
        }
    }
}