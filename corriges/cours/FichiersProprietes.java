/**
 * Exemple sur les proprietes. 
 */

package corriges.cours;

import java.io.*;
import java.util.Properties;

/**
 * Classe principale.
 */
public class FichiersProprietes {
	// Constante pour definir le dossier de stockage.
	private static final String chemin = "./src/corriges/cours/Fichiers/";
	
	// Creation d'un dossier s'il n'existe pas.
	public static void creationDossier() {
		File f = new File(chemin);
		
		// Si le chemin n'existe pas.
		if (!f.exists()) {
			if (f.mkdirs()) System.out.println("Dossier cree.\n");
			else System.out.println("Probleme a la creation du dossier.\n");
		}
	}
	
	// Ecriture de proprietes.
	public static void ecriturePropriete() {
		try (OutputStream os = new FileOutputStream(chemin + "maConfig.properties");
			 OutputStream osXML = new FileOutputStream(chemin + "maConfigXML.properties");) {
			System.out.println("Ecriture de proprietes");
			// Creation d'un objet proporiete.
			Properties p = new Properties();

			// Definition des proprietes.
		    p.setProperty("version", "1.0");
		    p.setProperty("nomProjet", "Exemple de fichier de proprietes");
		    p.setProperty("date", "2024/02/25");
		    
		    // Ecriture des proprietes et d'un commentaire.
		    p.store(os, "Les proprietes sont stockées ici.");
		    // Ecriture des proprietes en XML et d'un commentaire.
		    p.storeToXML(osXML, "Les proprietes sont stockées ici.");
		    
		    // Affichage des prorietes.
		    System.out.println(p);
		    System.out.println("Nombre de proprietes : " + p.size());
		}	
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// Lecture des proprietes.
	public static void lecturePropriete() {
	    try (InputStream is = new FileInputStream(chemin + "maConfig.properties");
	    	 InputStream isXML = new FileInputStream(chemin + "maConfigXML.properties");) {
	    	System.out.println("Lecture de proprietes");
	        // Creation d'un objet propriete.
	    	Properties p = new Properties();
	        // Chargement des proprietes a partir du fichier.
	        p.load(is);
	        // Chargement des proprietes a partir du fichier en XML.
	        p.loadFromXML(isXML);
	        
	        // Obtention et affichage de la propriete date.
	        System.out.println(p.getProperty("date"));
	        // Obtention et affichage de toutes les proprietes.
	        p.list(System.out);
	    }
	    catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	
	//  Methode principale.
	public static void main(String[] args) {
		creationDossier();
		ecriturePropriete();
		System.out.println();
		lecturePropriete();
	}
}
