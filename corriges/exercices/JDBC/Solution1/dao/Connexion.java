package corriges.exercices.JDBC.Solution1.dao;

import java.sql.*;

import corriges.exercices.JDBC.Solution1.main.Constantes;

/**
 * Connexion a MySQL
 */
public class Connexion {
    /**
     * Connexion à MySQL avec ou sans base de données
     * @param bdd Le nom de la base de données (peut être null pour une connexion sans base)
     * @return la connexion si Ok
     * @throws Exception : message d'erreur personnalisé si besoin.
     */
    public static Connection connecterAvecBase(Boolean bdd) throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            if (bdd) url += Constantes.MABDD;
            url += "?useSSL=false";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, "root", "");
        }
        catch (ClassNotFoundException | SQLException e) {
            String message = (bdd == true) ? "Connexion à la BDD (" + Constantes.MABDD + ") impossible." : "Connexion à MySQL impossible.";
            throw new Exception(message);
        }
    }
}
