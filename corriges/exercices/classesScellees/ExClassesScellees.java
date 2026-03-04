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

package corriges.exercices.classesScellees;

/**
 *  Classe scellee representant la classe de base de tous les personnages.
 */
sealed abstract class Personnage permits Guerrier, Magicien, Archer {
	// Methode pour afficher les details du personnage.
	public abstract void afficherDetails();
}


/**
 *  Classe Guerrier, sous-classe de Personnage.
 */
final class Guerrier extends Personnage {
	// Les guerriers peuvent etre de l'Alliance ou de la Horde.
	private final String faction;
	
	// Constructeur pour initialiser les attributs du guerrier.
	public Guerrier(String faction) {
		this.faction = faction;
	}

	// Implementation de la methode pour afficher les details du guerrier.
	@Override
	public void afficherDetails() {
		System.out.println("Je suis un guerrier de la faction " + faction);
	}
}


/**
 *  Classe Magicien, sous-classe de Personnage.
 */
final class Magicien extends Personnage {
	// Les magiciens peuvent etre de l'Alliance ou de la Horde.
	private final String faction;
	
	// Constructeur pour initialiser les attributs du magicien.
	public Magicien(String faction) {
		this.faction = faction;
	}

	// Implementation de la methode pour afficher les details du magicien.
	@Override
	public void afficherDetails() {
		System.out.println("Je suis un magicien de la faction " + faction);
	}
}


/**
 *  Classe Archer, sous-classe de Personnage.
 */
final class Archer extends Personnage {
	// Les archers n'appartiennent a aucune faction.
	// Pas besoin de stocker une faction pour les archers.
	// donc pas de champs supplementaire ici.
	
	// Constructeur par defaut.
	public Archer() {}
	
	// Implementation de la methode pour afficher les details de l'archer.
	@Override
	public void afficherDetails() {
		System.out.println("Je suis un archer");
	}
}


/**
 *  Classe principale.
 */
public class ExClassesScellees {
	// Methode principale.
    public static void main(String[] args) {
        // Creation de differents personnages.
        Guerrier guerrierAlliance = new Guerrier("Alliance");
        Guerrier guerrierHorde = new Guerrier("Horde");
        Magicien magicienAlliance = new Magicien("Alliance");
        Magicien magicienHorde = new Magicien("Horde");
        Archer archer = new Archer();

        // Affichage des details des personnages.
        guerrierAlliance.afficherDetails();
        guerrierHorde.afficherDetails();
        magicienAlliance.afficherDetails();
        magicienHorde.afficherDetails();
        archer.afficherDetails();
    }
}
