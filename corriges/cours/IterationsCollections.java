/**
 * Exemple sur l'iteration de collection
 */

package corriges.cours;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Classe principale
public class IterationsCollections {
    public static void main(String[] args) {
        // LIST
        // Creation d'une liste de type List
        List<String> maListe = new ArrayList<>();
        maListe.add("valeur 1");
        maListe.add(1, "valeur 2");
        maListe.add(0, "valeur 3");
        maListe.add("valeur 1");
        
        
        // Utilisation de l'interface Iterator
        System.out.println("Parcours de la liste avec iterateur");
        
        // Recuperation d'un "Iterator" sur la liste voulue
        Iterator<String> monIterateur = maListe.iterator();
        
        // Parcours de la liste. La methode hasNext() verifie s'il y a encore un element dans la liste
        while (monIterateur.hasNext()) {
        	// La methode next() passe a l'element suivant de la liste
        	String element = monIterateur.next();
        	System.out.println(element);
        }
        
        // Parcours de la liste avec une boucle for classique 
        System.out.println("Parcours de la liste avec boucle for classique");
        
        for (int monIndice = 0; monIndice < maListe.size(); monIndice++) {
        	String monElement = maListe.get(monIndice);
        	System.out.println(monElement);
        }
        
        // Parcours de la liste avec une boucle for "intelligente"
        System.out.println("Parcours de la liste avec boucle for intelligente");
        
        for (String elem : maListe) {
        	System.out.println(elem);
        }
        
        // Parcours de la liste avec une methode forEach() 
        // La fonction donnee dans forEach est une fonction lambda.
        System.out.println("Parcours de la liste avec methode forEach");
        maListe.forEach(e -> System.out.println(e));
        
        // Parcours de la liste avec une reference de methode
        System.out.println("Parcours de la liste avec reference de methode");
        maListe.forEach(System.out::println);
    }
}