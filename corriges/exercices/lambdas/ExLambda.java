/**
 * Exercice sur les lambda
 * Triez une liste de personnes par age a l'aide de lambdas
 * Creer une classe Personne avec deux attributs finaux : 
 *      - nom de type String
 *      - age de type int
 * En utilisant une expression lambda,
 * afficher la liste des personnes en fonction de leur age par ordre croissant et decroissant.
 */

package corriges.exercices.lambdas;

import java.util.ArrayList;
import java.util.List;

// Classe Personne
class Personne {
    private final String nom;
    private final int age;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Personne : Nom = " + this.nom + " - Age = " + this.age;
    }
}

// Class ExLambda
public class ExLambda {
    public static void main(String[] args) {
        // Creation d'une liste de personnes
        List<Personne> personnes = new ArrayList<>();
        personnes.add(new Personne("Alice", 30));
        personnes.add(new Personne("Bob", 25));
        personnes.add(new Personne("Charlie", 35));
        personnes.add(new Personne("David", 28));

        // Tri sur le nom
        personnes.sort((p1, p2) -> p1.getNom().compareTo(p2.getNom()));
        
        // Tri la liste de personnes par age croissant
        personnes.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        // Affiche la liste avec un ForEach et une lambda.
        System.out.println("Liste par age croissant :");
        personnes.forEach(x -> System.out.println(x));
        
        // Saut de ligne
        System.out.println();
        
        // Tri la liste de personnes par age decroissant
        personnes.sort((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge()));
        
        // Affiche la liste avec un ForEach.
        System.out.println("Liste par age decroissant :");
        personnes.forEach(System.out::println);
    }
}
