/**
 * Exercice fichiers
 * 
 * Utiliser les fichiers standards avec java.io pour cet exercice.
 * Le but de cet exercice et d'ecrire et lire un fichier CSV nomme monFichier.csv
 * qui doit etre placer dans le meme dossier que cet exercice.
 * 
 * Utiliser la virgule par defaut pour la separation des donnees dans le fichier.
 * 
 * Creer les méthodes statiques ecrireCSV et lireCSV.
 *  
 * La methode ecrireCSV doit ecrire les informations suivantes : 
 * 		Une entete "ID, Nom, Prenom, Age" et au minimum 3 lignes correspondant a l'entete.
 * 		l'ID doit etre un entier positif unique a chaque ligne.  
 * 
 * La methode lireCSV doit lire les informations ecritent par la methode precedente
 * et afficher ces infos dans la console.
 * 
 * Penser a gerer les exceptions liees à l'ecriture et la lecture du fichier,
 * et afficher un message dans la console en cas d'exception.
 *
 * Le programme principal executera l'ecriture du fichier CSV puis la lecture.
 * Afficher quelques commentaires a l'ecriture et la lecture tel que :
 * 		Ecriture fichier CSV en cours ...
 * 		Ecriture fichier CSV reussie.
 * 		Lecture fichier CSV en cours ...
 * 		Lecture fichier CSV reussie.
 */

package corriges.exercices.fichiers;

// Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Classe principale
public class ExFichiers {
	// constante pour definir le dossier de stockage, le nom du fichier et le separateur utilise
	private static final String chemin = "./src/corriges/exercices/fichiers/";
    private static final String fichierCSV = "monFichier.csv";
	private static final String separateur = ",";
	
	// Methode principal
    public static void main(String[] args) {
        // Chemin du fichier CSV à lire et écrire

        // Ecriture dans le fichier CSV
        writeCsv(chemin + fichierCSV);

        // Lecture du fichier CSV
        List<String[]> donnees = lectureCSV(chemin + fichierCSV);

        // Affichage des donnees lues
        System.out.println();
        
        for (String[] ligne : donnees) {
            for (String cellule : ligne) {
            	// affichage sur une ligne formate avec llignement a gauche sur 25 caracteres
                System.out.printf("%-25s" , cellule);
            }
            System.out.println();
        }
    }

    private static void writeCsv(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
        	System.out.println("Ecriture fichier CSV en cours ...");
            // Ecrire l'entete du fichier CSV
            fw.append("Nom" + separateur + "Prenom" + separateur + "Age\n");

            // Ecrire des lignes de données
            fw.append("Tabuteau" + separateur + "Xavier" + separateur + "25\n");
            fw.append("Dupont" + separateur + "John" + separateur + "30\n");
            fw.append("Durand" + separateur + "Bob" + separateur + "22\n");
        }
        catch (IOException e) {
        	System.out.println("Erreur d'ecriture du fichier CSV.");
        }
        
        System.out.println("Ecriture fichier CSV reussie.");
    }

    private static List<String[]> lectureCSV(String filePath) {
        List<String[]> donnees = new ArrayList<>();
        System.out.println("Lecture fichier CSV en cours ...");
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Lire chaque ligne du fichier CSV
            while ((line = br.readLine()) != null) {
                // Divise la ligne en tableau de chaînes en utilisant le separateur
                String[] ligne = line.split(separateur);
                donnees.add(ligne);
            }
        }
        catch (IOException e) {
           System.out.println("Erreur de lecture du fichier CSV.");
        }

        System.out.println("Lecture fichier CSV reussie.");
        return donnees;
    }
}
