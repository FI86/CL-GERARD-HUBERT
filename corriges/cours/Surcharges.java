/**
 * Exemple sur les surcharges
 */

package corriges.cours;

// Classe Imprimante avec surcharge de la methode imprimer()
class Imprimante {
	public void imprimer() {
		System.out.println("Page de test");
	}
	
	public void imprimer(String document1, String document2) {
        System.out.println("Imprimer1");
        System.out.println(document1 + " " + document2);
	}
	
    public void imprimer(String document, Integer nombre) {
        System.out.println("Imprimer2");
        
        for (int indice = 0; indice < nombre; indice++) {
            System.out.println(document);
        }
    }
    
    public void imprimer(String... document1) {
        System.out.println("Imprimer3");
        
        for (String doc : document1) {
            System.out.println(doc);
        }
    }
}


// Classe principale
public class Surcharges {
    public static void main(String[] args) {
        Imprimante imprimante = new Imprimante();
        
        imprimante.imprimer();
        
        // Fonctionne avec String[] et String...
        imprimante.imprimer(new String[] {"doc1", "doc2", "doc3", "doc4"});
        
        // Ne fonctionne qu'avec String...
        // Avec String... il n' y a pas de limite en nombre d'arguments
        // et les argument sont accessible sous forme de tableau classique
        // dans la methode ayant le parametre avec les ...
        imprimante.imprimer("document1");
        imprimante.imprimer("document7", "doucment8", "document9");

        imprimante.imprimer("document2", "document3");
        imprimante.imprimer("document4", "document5");
        imprimante.imprimer("document6", 3);
    }
}