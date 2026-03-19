/**
 * Exemple sur les Fichiers
 */

package corriges.cours;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import java.util.stream.Collectors;

/** 
 * Classe pour ecrire un objet dans un fichier binaire.
 */
class Point implements Serializable {
	private static final long serialVersionUID = 4742924545474768166L;
	private int x;
	private int y;

	// Constructeurs.
	Point() {
		this.x = 0;
		this.y = 0;
	}
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Getters.
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}

/**
 * Classe principale.
 */
public class FichiersIO {
	// Constante pour definir le dossier de stockage.
	private static final String chemin = "./src/corriges/cours/Fichiers/";
	
	// Creation d'un dossier s'il n'existe pas.
	public static void creationDossier() {
		File f = new File(chemin);
		
		// Si le chemin n'existe pas
		if (!f.exists()) {
			if (f.mkdirs()) System.out.println("Dossier cree.\n");
			else System.out.println("Probleme a la creation du dossier.\n");
		}
	}

	// Lecture du chemin d'un fichier.
	public static void lectureChemin() {
		// Creation d'un objet File.
		File ft = new File(chemin + "MonFichier.txt");
		
		System.out.println("lecture infos emplacements du fichier");		
		// Utilisation des methodes de la classe File.
    	System.out.println(ft.getName());
    	System.out.println(ft.getParent());
    	System.out.println(ft.getPath());
    	
    	// Acces disque pour obtenir le chemin complet.
    	try {
    		System.out.println(ft.getCanonicalPath());
    	}
    	catch (IOException ioe) {
    		System.out.println("probleme acces chemin reel.");
    	}
    	
    	System.out.println();
	}
	
	// Ecriture d'un fichier texte avec BufferedWriter. 
	public static void ecritureBufferedFichierTexte() {
		// Creation d'un objet File.
		File ft = new File(chemin + "MonFichier.txt");
		System.out.println("Ecriture Buffered Fichier Texte");
		
		// try with ressource lance un flush() à la fermeture de la ressource
		// true signifie en mode ajout au fichier existant
		// par defaut c'est false, on ecrase le fichier existant.
		try (Writer w = new FileWriter(ft, true);
			 BufferedWriter bw = new BufferedWriter(w)) {
			// Ecrit le texte.
			bw.write("Hello World");
			// Fait un retour chariot.
			bw.newLine();
		}
		catch (IOException ioe) {
			System.out.println("Erreur ecriture fichier texte buffered.");
		}
	}
	
	// Lecture d'un fichier texte.
	public static void lectureFichierTexte() {
		// Creation d'un objet File.
		File ft = new File(chemin + "MonFichier.txt");
		System.out.println("lecture fichier texte.");
		// Creer un objet de lecture de fichier texte.
		try (Reader r = new FileReader(ft)){
			char[] chars = new char[1024];
			
			// StringBuilder est une classe qui enveloppe un tableau de caracteres
			// on peut rajouter des caracteres autant qu'on veut.
			// StringBuilder s'occupe de gerer l'espace en memoire necessaire.
			// Comme une List<char>.
			StringBuilder sb = new StringBuilder();
			
			// Lecture de 1024 caractères ou moins.
			int n = r.read(chars);
			
			// S'il reste des cractères à lire.
			while (n > -1) {
				// Ajoute les n caratactère lu de ma variable chars.
				sb.append(chars, 0, n);
				// Lecture de 1024 caractères ou moins.
				n = r.read(chars);
			}
			// Affiche le contenu du StringBuffer grace a toString().
			System.out.println(sb.toString());
		}
		catch (IOException e) {
			System.out.println("erreur lecture fichier texte.");
		}
	}
	
	// Lecture d'un fichier texte avec BufferedReader.
	public static void lectureBufferedFichierTexte() {
        // Creation d'un objet File.
        File ft = new File(chemin + "MonFichier.txt");
        System.out.println("lecture Buffered fichier texte.");

        // Creer un objet de lecture de fichier texte.
        try (Reader r = new FileReader(ft);
             BufferedReader br = new BufferedReader(r); 
             LineNumberReader lnr = new LineNumberReader(br)) {

            // Lecture de la premiere ligne.
            System.out.println(lnr.readLine());
            // Initialisation a un apres la premiere ligne.
            lnr.setLineNumber(1); 
            
            // Lecture des lignes restantes.
            // Sans Stream.
            StringBuilder sb = new StringBuilder();
            String line;
            
            while ((line = lnr.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
             }
            
            System.out.println(sb.toString());
            
            // Avec Stream retourne vide si lnr est lue avant.
            System.out.println(lnr.lines().collect(Collectors.joining(System.lineSeparator())));
            
            // Lecture du nombre total de lignes a la fin de la boucle.
            System.out.println("Nombre total de lignes lues : " + lnr.getLineNumber());
        }
        catch (IOException ioe) {
            System.out.println("erreur lecture fichier texte Buffered.");
        }
	}
	
	// Ecriture d'un fichier binaire.
	public static void ecritureFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaire.bin");
		System.out.println("Ecriture Fichier Binaire");
		
		try (FileOutputStream fos = new FileOutputStream(fb)) {
			// Creation d'un tableau de byte à partir d'un texte.
			byte[] dataBytes = "mon texte\n".getBytes();
			// Ecriture de tableau dans le fichier.
			fos.write(dataBytes);
		}
		catch (IOException ioe) {
			System.out.println("erreur ecriture fichier binaire.");
		}
	}
	
	// Ecriture d'un fichier binaire avec decorateur BufferedOutputStream.
	public static void ecritureBufferedFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaire.bin");
		System.out.println("Ecriture Buffered Fichier Binaire");
		
		try (FileOutputStream fos = new FileOutputStream(fb, true);
			 BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			// Creation d'un tableau de byte encode en UTF-8 à partir d'un texte.
			// UTF-8 est la valeur par defaut de l'encodage.
			byte[] dataBytes = "mes donnees".getBytes(StandardCharsets.UTF_8);
			// Ecriture de tableau dans le fichier.
			bos.write(dataBytes);
		}
		catch (IOException ioe) {
			System.out.println("erreur ecriture fichier binaire Buffered.");
		}		
	}
	
	// Lecture d'un fichier binaire avec decorateur BufferedInputStream.
	public static void lectureBufferedFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaire.bin");
		System.out.println("Lecture Buffered Fichier Binaire");
		byte[] fichierLu;
		
		try (FileInputStream fis = new FileInputStream(fb);
			 BufferedInputStream bis = new BufferedInputStream(fis);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			
			// Creation d'un tableau de byte.
			byte[] dataBytes = new byte[1024];
			// Je lis les 1024 premiers octets. 
			int count = bis.read(dataBytes);
			
			// On continue s'il en reste.
			while (count != -1) {
				// J'ecrit les octets dans le ByteArraysOutputStream.
				baos.write(dataBytes, 0, count);
				// Je lis les octets suivants. 
				count = bis.read(dataBytes);
			}
			// Je flush les derniers octets.
			baos.flush();
			// Je copie dans fichierLu le contenu du baos.
			fichierLu = baos.toByteArray();
			
			// Conversion de fichierLu en bytes vers un String pour pouvoir l'afficher
			// en utilisant l'encodage standard UTF-8.
			String contenuFichier = new String(fichierLu, StandardCharsets.UTF_8);
	        System.out.println(contenuFichier);
	        }
		catch (IOException ioe) {
			System.out.println("erreur lecture fichier binaire Buffered.");
		}	
	}
	
	// Ecriture d'un fichier binaire avec decorateur DataOutputStream.
	public static void ecritureDataOutputStreamFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaireData.bin");
		System.out.println("Ecriture Data Fichier Binaire");
		
		try (FileOutputStream fos = new FileOutputStream(fb);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);
			 DataOutputStream dos = new DataOutputStream(bos)) {
			// Ecriture dans le fichier avec DataOutputStream.
			dos.writeInt(12);
			dos.writeUTF("string dos");
			// Ecriture avec BufferedOutStream.
			bos.write("string bytes bos".getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException ioe) {
			System.out.println("erreur ecriture fichier binaire Data.");
		}
	}
	
	// Lecture d'un fichier binaire avec decorateur DataInputStream.
	public static void lectureDataInputStreamFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaireData.bin");
		System.out.println("Lecture Data Fichier Binaire");
		byte[] fichierLu;
		
		try (FileInputStream fis = new FileInputStream(fb);
			 BufferedInputStream bis = new BufferedInputStream(fis);
			 DataInputStream dis = new DataInputStream(bis);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			// ByteArrayOutputStream a son equivalent Texte : CharArrayOutputStream.
			
			// Creation d'un tableau de byte pour recuperer la parti buffered.
			byte[] dataBytes = new byte[1024];
			
			// Lecture et affichage du int.
			System.out.println(dis.readInt());
			
			// Lecture et affichage du string.
			System.out.println(dis.readUTF());
			
			// Je lis les 1024 premiers octets du buffered.
			int count = bis.read(dataBytes);
			
			// On continue s'il en reste.
			while (count != -1) {
				// J'ecrit les octets dans le ByteArraysOutputStream.
				baos.write(dataBytes, 0, count);
				// Je lis les octets suivants.
				count = bis.read(dataBytes);
			}
			// Je flush les derniers octets.
			baos.flush();
			// Je copie dans fichierLu le contenu du baos.
			fichierLu = baos.toByteArray();
			
			// Conversion de fichierLu en bytes vers un String pour pouvoir l'afficher
			// en utilisant l'encodage standard UTF-8.
			String contenuFichier = new String(fichierLu, StandardCharsets.UTF_8);
	        System.out.println(contenuFichier);
	        }
		catch (IOException ioe) {
			System.out.println("erreur lecture fichier binaire Data.");
		}
	}
	
	// Ecriture d'objet dans un fichier binaire avec decorateur ObjectOutputStream (Serialisation).
	public static void ecritureObjectOutputStreamFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaireObjet.bin");
		System.out.println("Ecriture Object Fichier Binaire");
		
		try (FileOutputStream fos = new FileOutputStream(fb);
			 ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			
			// Creation d'un objet.
			Point p = new Point(10, 5);
			
			// Ecriture de l'objet dans le fichier avec ObjectOutputStream.
			oos.writeObject(p);
		}
		catch (IOException ioe) {
			System.out.println("erreur ecriture fichier binaire objet.");
		}
	}
	
	// Lecture d'objet a partir d'un fichier binaire avec decorateur ObjectInputStream (Deserialisation).
	public static void lectureObjectInputStreamFichierBinaire() {
		File fb = new File(chemin + "MonFichierBinaireObjet.bin");
		System.out.println("Lecture Data Fichier Binaire");
		
		try (FileInputStream fis = new FileInputStream(fb);
			 ObjectInputStream ois = new ObjectInputStream(fis);) {
			// Creation d'un objet.
			Point p = new Point();
			
			// Lecture et affichage de l'objet Point.
			// Retourne ClassNotFoundException si la classe retournee est fausse.
			p = (Point) ois.readObject();
			System.out.println("Point : x = " + p.getX());
			System.out.println("Point : y = " + p.getY());
	        }
		catch (IOException | ClassNotFoundException e) {
			System.out.println("erreur lecture fichier binaire objet.");
		}
	}
	
	// Methode principale. 
	public static void main(String[] args) {
		creationDossier();
		ecritureBufferedFichierTexte();
		lectureChemin();
		lectureFichierTexte();
		lectureBufferedFichierTexte();
		ecritureFichierBinaire();
		ecritureBufferedFichierBinaire();
		lectureBufferedFichierBinaire();
		ecritureDataOutputStreamFichierBinaire();
		lectureDataInputStreamFichierBinaire();
		ecritureObjectOutputStreamFichierBinaire();
		lectureObjectInputStreamFichierBinaire();
	}
}
