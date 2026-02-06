/**
 * Exercice Classes Scellees
 * 
 * Supposons qu'on developpe un jeu video dans lequel il existe differentes categories de personnages.
 * Il y as trois types de personnages : Guerrier, Magicien et Archer.
 * Les guerriers et les magiciens peuvent etre de deux factions differentes : Alliance et Horde.
 * Les archers, en revanche, n'ont pas de faction.
 * Pour implementer cette hierarchie de personnages, utilise des classes scellees.
 * Creer une methode afficherDetail() qui affichera un texte du genre :
 * Je suis un ... de la faction ... .
 * Pour la cartegorie n'ayant pas de faction, on se contentera de l'affichage Je suis un ... .
 */

package cours_exercices.exercices.classesScellees;

/**
 *  Classe scellee representant la classe de base de tous les personnages.
 */
	// Methode pour afficher les details du personnage.


/**
 *  Classe Guerrier, sous-classe de Personnage.
 */
	// Les guerriers peuvent etre de l'Alliance ou de la Horde.
	
	// Constructeur pour initialiser les attributs du guerrier.

	// Implementation de la methode pour afficher les details du guerrier.


/**
 *  Classe Magicien, sous-classe de Personnage.
 */
	// Les magiciens peuvent etre de l'Alliance ou de la Horde.
	
	// Constructeur pour initialiser les attributs du magicien.

	// Implementation de la methode pour afficher les details du magicien.


/**
 *  Classe Archer, sous-classe de Personnage.
 */
	// Les archers n'appartiennent a aucune faction.
	// Pas besoin de stocker une faction pour les archers.
	// donc pas de champs supplementaire ici.
	
	// Constructeur par defaut.
	
	// Implementation de la methode pour afficher les details de l'archer.


/**
 *  Classe principale.
 */
public class ExClassesScellees {
	// Methode principale.
    public static void main(String[] args) {
        // Creation de differents personnages.

        // Affichage des details des personnages.
    }
}
