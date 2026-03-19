package corriges.cours.JDBC;

import java.sql.*;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

// Classe JDBC
public class JDBC {
    public static void main(String args[]) {
        final String creationBDD = "CREATE DATABASE IF NOT EXISTS Test CHARACTER SET utf8;";
        final String utilisationBDD = "use Test";
        final String creationTable = """
                            CREATE TABLE IF NOT EXISTS Utilisateurs (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            nom VARCHAR(50) NOT NULL,
                            adresse VARCHAR(255)
                            ) ENGINE=InnoDB"""; // InnoDB permet d'utiliser les transactions.
        final String insertion1 = "INSERT INTO Utilisateurs VALUES (DEFAULT, 'm2i', 'Tours')";
        final String insertion2 = "INSERT INTO Utilisateurs VALUES (DEFAULT, 'FI86', 'Poitiers')";
        final String insertion3 = "INSERT INTO Utilisateurs VALUES (DEFAULT, 'test', 'test')";
        final String modification = "UPDATE Utilisateurs SET nom = 'M2I Formation' WHERE nom = 'm2i'";
        final String suppressionSelective = "DELETE FROM Utilisateurs WHERE nom = 'test'";
        final String selection = "SELECT * FROM Utilisateurs";
        
        // Suppression du contenu d'une table complete
        // DELETE ne reinitialise pas l'auto incrementation s'il y en a une.
        // DELETE FROM nom_table
        // TRUNCATE permet de reinitialiser l'auto incremenation s'il y en a une.
        // TRUNCATE TABLE nom_table
        final String suppressionDonneesTable = "TRUNCATE TABLE Utilisateurs";
        
        final String insertionAvecParametres = "INSERT INTO Utilisateurs VALUES(DEFAULT, ?, ?)";
        final String suppressionTable = "DROP TABLE Utilisateurs";
        final String suppressionBDD = "DROP DATABASE test";
        
        try {
            // Etape 1: charger la classe driver
            // Ancienne version
            //Class.forName("com.mysql.jdbc.Driver");
            // Nouvelle version
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Etape 2: creer l'objet de connexion
            // Sans pool de connexion
            Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false", "root", "");
            // Avec pool de connexion
            // Connection co = PoolConnectionHikari.getDataSource().getConnection();
            
            // Etape 3: creer l'objet statement
            Statement stmt = co.createStatement();
            
            // Verification de l'auto commit
            System.out.println("Auto commit : " + co.getAutoCommit());
            
            // Etape 4: executer des requetes
            System.out.println("Creation de la BDD...");
            stmt.executeUpdate(creationBDD);
            System.out.println("Base de donnees cree avec succes.");
            System.out.println();
            
            System.out.println("Utilisation de la BDD");
            stmt.executeUpdate(utilisationBDD);
            System.out.println();
            
            System.out.println("Creation de la table...");
            stmt.executeUpdate(creationTable);
            System.out.println("Table creer avec succes.");
            System.out.println();
            
            // Execution d'une transaction
            co.setAutoCommit(false);
            // Verification de l'auto commit
            System.out.println("Auto commit : " + co.getAutoCommit());
            // Insertion de donnees.
            System.out.println("Insertion de donnees.");
            stmt.executeUpdate(insertion1);
            stmt.executeUpdate(insertion2);
            stmt.executeUpdate(insertion3);
            
            // Validation de la transaction
            System.out.println("Validation des donnees.");
            co.commit();
            
            System.out.println("Modification de donnees.");
            stmt.executeUpdate(modification);
            System.out.println("Annulation des modifications de donnees.");
            co.rollback();
            // remettre auto commit a true apres les transactions.
            co.setAutoCommit(true);
            // Verification de l'auto commit
            System.out.println("Auto commit : " + co.getAutoCommit());
            System.out.println();
            
            System.out.println("Modification de donnees.");
            stmt.executeUpdate(modification);
            System.out.println();

            System.out.println("Suppresion de donnees.");
            stmt.executeUpdate(suppressionSelective);
            System.out.println();
            
            // Pour la lecture on utlise la methode executeQuery qui retourne un ResultSet
            System.out.println("Lecture des donnees...");
            ResultSet res = stmt.executeQuery(selection);
            System.out.println();
            
            // Informations sur la table a partir d'un ResultSet
            ResultSetMetaData rsMeta = res.getMetaData();
            System.out.println("Nom de la table : " + rsMeta.getTableName(1));
            System.out.println("Total des colonnes : " + rsMeta.getColumnCount());
            System.out.println("Nom de la premiere colonne : " + rsMeta.getColumnName(1));
            System.out.println("Type de la premiere colonne : " + rsMeta.getColumnType(1));
            System.out.println("Type SQL de la premiere colonne : " + rsMeta.getColumnTypeName(1));
            System.out.println("Type JAVA de la premiere colonne : " + rsMeta.getColumnClassName(1));
            
            System.out.println();
            
            // Parcours du ResultSet
            // Il existe des methodes tel que next, previous, first, last ...
            while (res.next()) {
                String nom = res.getString("nom");
                String adresse = res.getString("adresse");
                
                System.out.println("Nom : " + nom);
                System.out.println("Adresse : " + adresse);
                System.out.println();
            }
            
            System.out.println("Suppression des donnees de la table Utilisateurs.");
            stmt.executeUpdate(suppressionDonneesTable);
            System.out.println();
            
            System.out.println("Insertion de donnees avec PreparedStatement");
            PreparedStatement ps = co.prepareStatement(insertionAvecParametres);
            // Premier parametre de la requete
            ps.setString(1, "FI86");
            // Deuxieme parametre de la requete
            ps.setString(2, "Poitiers");
            
            int i = ps.executeUpdate();
            
            System.out.println(i + " enregistrement insere avec succes.");
            System.out.println();
            
            //////////////////// ROWSET ////////////////////
            // Creation et execution de RowSet
            // Attention il faut preciser le nom de la table dans le setURL
            JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
            rowSet.setUrl("jdbc:mysql://localhost:3306/Test?useSSL=false");
            rowSet.setUsername("root");
            rowSet.setPassword("");
            
            rowSet.setCommand(selection);
            rowSet.execute();
            
            while(rowSet.next()) {
                System.out.println("Id : " + rowSet.getString(1));
                System.out.println("Nom : " + rowSet.getString(2));
                System.out.println("Adresse : " + rowSet.getString(3));
            }
            ///////////////// FIN ROWSET //////////////////
            
            System.out.println();
            
            // Suppression d'une table
            System.out.println("Suppression de la table Utilisateurs...");
            stmt.executeUpdate(suppressionTable);
            System.out.println("Suppression effectuee.");
            // Suppression de la BDD
            System.out.println("Suppression de la BDD test...");
            stmt.executeUpdate(suppressionBDD);
            System.out.println("Suppression effectuee.");
            System.out.println();
            
            // Informations sur la connexion
            DatabaseMetaData dbMeta = co.getMetaData();
            System.out.println("Nom du pilote : " + dbMeta.getDriverName());
            System.out.println("Version du pilote : " + dbMeta.getDriverVersion());
            System.out.println("Nom et ip de l'utilisateur principal : " + dbMeta.getUserName());
            System.out.println("Nom du SGBDR : " + dbMeta.getDatabaseProductName());
            System.out.println("Version du SGBDR : " + dbMeta.getDatabaseProductVersion());
            
            // Etape 5: fermer l'objet de connexion
            co.close();
            System.out.println("Fermeture des connexions.");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}