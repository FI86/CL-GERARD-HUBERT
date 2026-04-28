/**
 * Exemple sur l'heritage
 */

package corriges.cours;

// Classe Compte simple
class Compte {
    // Proprietes
    private Double solde;
    private String proprietaire;
    
    // Constructeur
    public Compte(Double solde, String proprietaire) {
        this.solde = solde;
        this.proprietaire = proprietaire;
    }
    
    // Getters
    public String getProprietaire() {
        return this.proprietaire;
    }

    public Double getSolde() {
        return this.solde;
    }
    
    // Setters
    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
    
    // Methode d'affichage
    public String getElements() {
        return "Proprietaire : " + this.proprietaire + " - solde : " + this.solde;
    }
    
    @Override
    public String toString() {
    	return this.getElements().toString();
    } 
}

// Classe CompteBancaire heritant de Compte
class CompteBancaire extends Compte {
    // Propriete
	private String nomBanque;
	
    // Constructeurs
	public CompteBancaire() {
		super(100.0, "Xavier");
		this.nomBanque = "Ma Banque";
	}
	
	
    public CompteBancaire(Double solde, String proprietaire, String nomBanque) {
        // Appel le constructeur de la classe mere
        super(solde, proprietaire);
        this.nomBanque = nomBanque;
    }
    
    // Getters
    public String getNomBanque() {
        return this.nomBanque;
    }    
    
    // Setters
    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
    
    @Override
    public String getElements() {
    	return super.getElements() + " - Nom de la banque : " + this.nomBanque;
    }
    
    @Override
    public String toString() {
    	return this.getElements().toString();
    }
}

// Classe principale
public class Heritage {
    public static void main(String[] args) {
    	Compte c = new Compte(150.0, "toto");
    	CompteBancaire cb = new CompteBancaire(100.0, "Xavier", "Ma Banque");
    	
    	cb.setProprietaire("Yo");
    	cb.setSolde(1500.0);
    	
    	System.out.println(c.getElements());
    	System.out.println(cb.getElements());
    	
        // Avec la redefinition de la methode toString,
        // on peut afficher sans appeler explicitement la methode.
    	System.out.println(c);
    	System.out.println(cb);
    }
}