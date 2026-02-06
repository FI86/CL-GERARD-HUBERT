/**
 * Exemple sur les Fichiers
 */

package cours_exercices.cours;

/** 
 * Classe pour ecrire un objet dans un fichier binaire.
 */
	// Constructeurs.
	
	// Getters.


/**
 * Classe principale.
 */
public class FichiersIO {
	// Constante pour definir le dossier de stockage.
	
	// Creation d'un dossier s'il n'existe pas.
		
		// si le chemin n'existe pas


	// Lecture du chemin d'un fichier.
		// Creation d'un objet File.
	
		// Utilisation des methodes de la classe File.
    	
    	// Acces disque pour obtenir le chemin complet.

	
	// Ecriture d'un fichier texte avec BufferedWriter. 
		// Creation d'un objet File.
		
		// try with ressource lance un flush() à la fermeture de la ressource
		// true signifie en mode ajout au fichier existant
		// par defaut c'est false, on ecrase le fichier existant.

			// Ecrit le texte.
			// Fait un retour chariot.

	
	// Lecture d'un fichier texte.
		// Creation d'un objet File.

		// Creer un objet de lecture de fichier texte.
			
			// StringBuilder est une classe qui enveloppe un tableau de caracteres
			// on peut rajouter des caracteres autant qu'on veut.
			// StringBuilder s'occupe de gerer l'espace en memoire necessaire.
			// Comme une List<char>.
			
			// Lecture de 1024 caractères ou moins.
			
			// S'il reste des cractères à lire.
				// Ajoute les n caratactère lu de ma variable chars.
				// Lecture de 1024 caractères ou moins.

			// Affiche le contenu du StringBuffer grace a toString().

	
	// Lecture d'un fichier texte avec BufferedReader.
        // Creation d'un objet File.

        // Creer un objet de lecture de fichier texte.
            // Lecture de la premiere ligne.

			// Initialisation a un apres la premiere ligne.
            
            // Lecture des lignes restantes.
            // Sans Stream.

            // Avec Stream retourne vide si lnr est lue avant.
            
            // Lecture du nombre total de lignes a la fin de la boucle.
	
	// Ecriture d'un fichier binaire.
			// Creation d'un tableau de byte à partir d'un texte.
			
			// Ecriture de tableau dans le fichier.

	
	// Ecriture d'un fichier binaire avec decorateur BufferedOutputStream.
			// Creation d'un tableau de byte encode en UTF-8 à partir d'un texte.
			// UTF-8 est la valeur par defaut de l'encodage.
			
			// Ecriture de tableau dans le fichier.

	
	// Lecture d'un fichier binaire avec decorateur BufferedInputStream.
			
			// Creation d'un tableau de byte.
			
			// Je lis les 1024 premiers octets. 
			
			// On continue s'il en reste.

				// J'ecrit les octets dans le ByteArraysOutputStream.

				// Je lis les octets suivants. 

			// Je flush les derniers octets.

			// Je copie dans fichierLu le contenu du baos.
			
			// Conversion de fichierLu en bytes vers un String pour pouvoir l'afficher
			// en utilisant l'encodage standard UTF-8.

	
	// Ecriture d'un fichier binaire avec decorateur DataOutputStream.
			// Ecriture dans le fichier avec DataOutputStream.

			// Ecriture avec BufferedOutStream.

	
	// Lecture d'un fichier binaire avec decorateur DataInputStream.

			// ByteArrayOutputStream a son equivalent Texte : CharArrayOutputStream.
			
			// Creation d'un tableau de byte pour recuperer la parti buffered.
			
			// Lecture et affichage du int.
			
			// Lecture et affichage du string.
			
			// Je lis les 1024 premiers octets du buffered.
			
			// On continue s'il en reste.
				// J'ecrit les octets dans le ByteArraysOutputStream.
				
				// Je lis les octets suivants.

			// Je flush les derniers octets.

			// Je copie dans fichierLu le contenu du baos.

			
			// Conversion de fichierLu en bytes vers un String pour pouvoir l'afficher
			// en utilisant l'encodage standard UTF-8.

	
	// Ecriture d'objet dans un fichier binaire avec decorateur ObjectOutputStream.
			// Creation d'un objet.
			
			// Ecriture de l'objet dans le fichier avec ObjectOutputStream.

	
	// Lecture d'objet a partir d'un fichier binaire avec decorateur ObjectInputStream.
			// Creation d'un objet.
			
			// Lecture et affichage de l'objet Point.
			// Retourne ClassNotFoundException si la classe retournee est fausse.

	
	// Methode principale. 
	public static void main(String[] args) {

	}
}
