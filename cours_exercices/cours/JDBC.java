package cours_exercices.cours;

// Classe JDBC
public class JDBC {
    public static void main(String args[]) {
        final String str1 = "CREATE DATABASE IF NOT EXISTS Test CHARACTER SET utf8;";
        final String str2 = "use Test";
        final String str3 = """
                            CREATE TABLE IF NOT EXISTS Utilisateurs (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            nom VARCHAR(50) NOT NULL,
                            adresse VARCHAR(255))""";
        final String str4 = "INSERT INTO Utilisateurs VALUES (DEFAULT, 'm2i', 'Tours')";
        final String str5 = "INSERT INTO Utilisateurs VALUES (DEFAULT, 'FI86', 'Poitiers')";
        final String str6 = "INSERT INTO Utilisateurs VALUES (DEFAULT, 'test', 'test')";
        final String str7 = "UPDATE Utilisateurs SET nom = 'M2I Formation' WHERE nom = 'm2i'";
        final String str8 = "DELETE FROM Utilisateurs WHERE nom = 'test'";
        final String str9 = "SELECT * FROM Utilisateurs";
        
        // Suppression du contenu d'une table complete
        // DELETE ne reinitialise pas l'auto incrementation s'il y en a une.
        // DELETE FROM nom_table
        // TRUNCATE permet de reinitialiser l'auto incremenation s'il y en a une.
        // TRUNCATE TABLE nom_table
        final String str0 = "TRUNCATE TABLE Utilisateurs";
        
        final String str10 = "INSERT INTO Utilisateurs VALUES(DEFAULT, ?, ?)";
        final String str11 = "DROP DATABASE test";
        
            // Etape 1: charger la classe driver
            // Ancienne version
            // Nouvelle version
            
            // Etape 2: creer l'objet de connexion
            
            // Etape 3: creer l'objet statement 
            
            // Etape 4: executer des requetes
            
            // Pour la lecture on utlise la methode executeQuery qui retourne un ResultSet
              
            // Informations sur la table a partir d'un ResultSet
            
            // Parcours du ResultSet
            // Il existe des methodes tel que next, previous, first, last ...

            // Premier parametre de la requete
            
            // Deuxieme parametre de la requete
            
            //////////////////// ROWSET ////////////////////
            // Creation et execution de RowSet
            // Attention il faut preciser le nom de la table dans le setURL
            
            ///////////////// FIN ROWSET //////////////////
            
            // Suppression de la BDD
            
            // Informations sur la connexion
            
            // Etape 5: fermer l'objet de connexion
    }
}