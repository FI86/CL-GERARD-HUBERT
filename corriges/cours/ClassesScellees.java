/**
 * Exemple sur les classes scellees.
 */

package corriges.cours;

/**
 *  Classe fille de la classe Cercle avec heritage impossible.
 */
final class CouleurCercle extends Cercle {
	// Attibut.
	public String couleur;
	
	// Constructeur.
	public CouleurCercle(String nomForme, double rayon, String couleur) {
		super(nomForme, rayon);
		this.couleur = couleur;
	}
}

/**
 *  Classe fille Cercle non scelle.
 */
non-sealed class Cercle extends Forme {
	// Attribut.
	public double rayon;
	
	// Constructeur.
	public Cercle(String nomForme, double rayon) {
		super(nomForme);
		this.rayon = rayon;
	}
}

/**
 *  Classe mere Forme scellee. 
 */
sealed class Forme permits Cercle {
	// Attribut.
	private String nomForme;
	
	// Constructeur.
	public Forme(String nomForme) {
		this.nomForme = nomForme;
	}
	
	// Getter.
	public String getNomForme() {
		return this.nomForme;
	}
	
	// Setter.
	public void setForme(String nomForme) {
		this.nomForme = nomForme;
	}
}


/**
 *  Classe principale.
 */
public class ClassesScellees {
	// Methode principale.
	public static void main(String[] args) {
		Cercle c = new Cercle("Cercle", 15.0);
		System.out.println("nom de la forme : " + c.getNomForme());
		System.out.println("rayon du cercle : " + c.rayon);
		System.out.println();
		
		CouleurCercle cc = new CouleurCercle(c.getNomForme().toString(), c.rayon, "Bleu");
		System.out.println("nom de la forme   : " + cc.getNomForme());
		System.out.println("rayon du cercle   : " + cc.rayon);
		System.out.println("couleur du cercle : " + cc.couleur);
	}
}
