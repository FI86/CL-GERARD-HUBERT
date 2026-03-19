package corriges.exercices.JDBC.Solution1.dao;

import java.sql.*;

import corriges.exercices.JDBC.Solution1.main.Constantes;

/**
 * Creation de la base de donnees
 */
public class Creation {
    /**
     * Creation de la base de donnees
     * @return true si la creation est Ok
     */
    public static boolean base() {
        String str1 = "CREATE DATABASE IF NOT EXISTS " + Constantes.MABDD + " CHARACTER SET utf8;";
        String str2 = "use " + Constantes.MABDD;
        
        try (Connection conn = Connexion.connecterAvecBase(false)) {
            Statement stmt = conn.createStatement();
            
            System.out.println("Creation de la BDD " + Constantes.MABDD.toString().toLowerCase() + " si elle n'existe pas.");
            stmt.executeUpdate(str1);
            
            System.out.println("Utilisation de la BDD.");
            stmt.executeUpdate(str2);
            
            // Creation des tables
            tableUtilisateurs();
            tableClients();
            tableFournisseurs();
            tableArticles();
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("Erreur a la creation de la BDD : " + Constantes.MABDD.toString().toLowerCase() + ".");
            System.out.println(e);
            return false;
        }
        
        return true;
    }
    
    /**
     * Creation de la table Utilisateurs si elle n'existe pas
     */
    private static void tableUtilisateurs() {
        String strU = "CREATE TABLE IF NOT EXISTS " + Constantes.UTILISATEURS + " ("
            + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + "numero INT(10) NOT NULL UNIQUE, "
            + "nom VARCHAR(50) NOT NULL, "
            + "prenom VARCHAR(50) NOT NULL, "
            + "email VARCHAR(50) NOT NULL, "
            + "login VARCHAR(50) NOT NULL, "
            + "mdp VARCHAR(50) NOT NULL)";
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            Statement stmt = conn.createStatement();
            
            System.out.println("Creation de la table " + Constantes.UTILISATEURS + " si elle n'existe pas.");
            stmt.executeUpdate(strU);
        }
        catch (Exception e) {
            System.out.println("Erreur a la creation de la table " + Constantes.UTILISATEURS + " : ");
            System.out.println(e);
        }
    }
    
    /**
     * Creation de la table Clients si elle n'existe pas
     */
    private static void tableClients() {
        String strC = "CREATE TABLE IF NOT EXISTS " + Constantes.CLIENTS + " ("
            + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + "numero INT(10) NOT NULL UNIQUE, "
            + "nom VARCHAR(50) NOT NULL, "
            + "prenom VARCHAR(50) NOT NULL, "
            + "email VARCHAR(50) NOT NULL, "
            + "adresse VARCHAR(50) NOT NULL)";
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            Statement stmt = conn.createStatement();
            
            System.out.println("Creation de la table " + Constantes.CLIENTS + " si elle n'existe pas.");
            stmt.executeUpdate(strC);
        }
        catch (Exception e) {
            System.out.println("Erreur a la creation de la table " + Constantes.CLIENTS + " : ");
            System.out.println(e);
        }
    }
    
    /**
     * Creation de la table Fournisseurs si elle n'existe pas
     */
    private static void tableFournisseurs() {
        String strF = "CREATE TABLE IF NOT EXISTS " + Constantes.FOURNISSEURS + " ("
            + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + "numero INT(10) NOT NULL UNIQUE, "
            + "nom VARCHAR(50) NOT NULL, "
            + "email VARCHAR(50) NOT NULL, "
            + "adresse VARCHAR(50) NOT NULL)";
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            Statement stmt = conn.createStatement();
            
            System.out.println("Creation de la table " + Constantes.FOURNISSEURS + " si elle n'existe pas.");
            stmt.executeUpdate(strF);
        }
        catch (Exception e) {
            System.out.println("Erreur a la creation de la table " + Constantes.FOURNISSEURS + " : ");
            System.out.println(e);
        }
    }

    /**
     * Creation de la table Articles si elle n'existe pas
     */
    private static void tableArticles() {
        String strA = "CREATE TABLE IF NOT EXISTS " + Constantes.ARTICLES + " ("
            + "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
            + "numero INT(10) NOT NULL UNIQUE, "
            + "fc VARCHAR(1) NOT NULL, "
            + "nom VARCHAR(50) NOT NULL, "
            + "description VARCHAR(50) NOT NULL)";
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            Statement stmt = conn.createStatement();
            
            System.out.println("Creation de la table " + Constantes.ARTICLES + " si elle n'existe pas.");
            stmt.executeUpdate(strA);
        }
        catch (Exception e) {
            System.out.println("Erreur a la creation de la table " + Constantes.ARTICLES + " : ");
            System.out.println(e);
        }
    }
}
