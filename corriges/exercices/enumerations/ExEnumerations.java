/**
 * Exercice enumeration
 * Declarer une enumeration appelee GenreLitteraire avec les valeurs suivantes : 
 * ROMAN, SCIENCE_FICTION, POESIE, THRILLER, HISTOIRE
 * Creer une classe appelee Livre avec les attributs suivants :
 *      - titre
 *      - auteur
 *      - genre
 * Instancier quelques objets de la classe Livre avec des valeurs fictives.
 * Afficher les details de chaque livre, y compris le titre, l'auteur et le genre.
 */
package corriges.exercices.enumerations;

// Classe GenreLitteraire de type enum
enum GenreLitteraire {
    ROMAN,
    SCIENCE_FICTION,
    POESIE,
    THRILLER,
    HISTOIRE
}

// Classe Livre
class Livre {
    // Attributs
    private final String titre;
    private final String auteur;
    private final GenreLitteraire genre;

    // Constructeur
    public Livre(String titre, String auteur, GenreLitteraire genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
    }

    // Methode afficherDetails
    public void afficherDetails() {
        System.out.println("Titre  : " + this.titre);
        System.out.println("Auteur : " + this.auteur);
        System.out.println("Genre  : " + this.genre);
        System.out.println();
    }
    
    @Override
    public String toString() {
    /*	return String.format("""
    			Titre  : %s
    			Auteur : %s
    			Genre  : %s
    			""", this.titre, this.auteur, this.genre);*/
    	return """
    			Titre  : $titre
    			Auteur : $auteur
    			Genre  : $genre
    			"""
    			.replace("$titre",this.titre)
    			.replace("$auteur", this.auteur)
    			.replace("$genre", this.genre.toString());
    }
}

// Classe ExEnumeration
public class ExEnumerations {
    public static void main(String[] args) {
        // Creation des objets Livre.
        Livre livre1 = new Livre("Les Miserables", "Victor Hugo", GenreLitteraire.ROMAN);
        Livre livre2 = new Livre("Dune", "Frank Herbert", GenreLitteraire.SCIENCE_FICTION);
        Livre livre3 = new Livre("Fleurs du mal", "Charles Baudelaire", GenreLitteraire.POESIE);
        Livre livre4 = new Livre("1984", "Georges Orwell", GenreLitteraire.SCIENCE_FICTION);
        Livre livre5 = new Livre("Guerre et paix", "Leon Tolstoi", GenreLitteraire.ROMAN);
        Livre livre6 = new Livre("Le septieme templier", "Giacometti et Ravenne", GenreLitteraire.THRILLER);
        
        // Affichage des livres.
        // Solution sans redefinition de la methode toString().
        livre1.afficherDetails();
        livre2.afficherDetails();
        livre3.afficherDetails();
        livre4.afficherDetails();
        livre5.afficherDetails();
        livre6.afficherDetails();
        
        // Solution avec redefinition de la methode toString().
        System.out.println(livre1);
        System.out.println(livre2);
        System.out.println(livre3);
        System.out.println(livre4);
        System.out.println(livre5);
        System.out.println(livre6);
    }
}
