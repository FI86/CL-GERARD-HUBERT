/**
 * Exercice sur les records
 * 
 * Creer un record Etudiant pour representer les informations d'un etudiant.
 * Les informations pour un etudiant sont les suivantes :
 *      - nom
 *      - numeroEtudiant
 *      - moyenne
 * 
 * Implementer une classe GestionEtudiants qui contient une liste d'etudiants et les methodes suivantes :
 *      ajouterEtudiant         : Ajoute un etudiant a la liste.
 *      afficherEtudiants       : Affiche les informations de tous les etudiants.
 *      rechercherEtudiant      : Recherche un etudiant par son numero et affiche ses informations.
 *      calculerMoyenneGenerale : Calcul et affiche la moyenne generale de tous les etudiants.
 */

package corriges.exercices.records;

import java.util.ArrayList;
import java.util.List;

// Classe de type record
record Etudiant(String nom, int numeroEtudiant, double moyenne) {}

// Classe GestionEtudiants
class GestionEtudiants {
    private final List<Etudiant> listeEtudiants = new ArrayList<>();

    public void ajouterEtudiant(Etudiant etudiant) {
        listeEtudiants.add(etudiant);
    }

    public void afficherEtudiants() {
        System.out.println("Liste des etudiants :");
        listeEtudiants.forEach(System.out::println);
    }

    public void rechercherEtudiant(int numeroEtudiant) {
        for (Etudiant etudiant : listeEtudiants) {
            if (etudiant.numeroEtudiant() == numeroEtudiant) {
                System.out.println("Informations de l'etudiant avec le numero " + numeroEtudiant + " : " + etudiant);
                // le return seul permet de quitter la methode une fois l'information trouvee et affichee
                return;
            }
        }
        
        System.out.println("Aucun etudiant trouve avec le numero " + numeroEtudiant + ".");
    }

    public void calculerMoyenneGenerale() {
        // double sommeMoyennes = listeEtudiants.stream().mapToDouble(Etudiant::moyenne).sum();
        // double moyenneGenerale = sommeMoyennes / listeEtudiants.size();
        double moyenneGenerale = listeEtudiants.stream().mapToDouble(Etudiant::moyenne).average().orElse(0.0);

        // affichage moyenne
        System.out.println("Moyenne generale de tous les etudiants : " + moyenneGenerale);
    }    
}

// Classe ExRecords
public class ExRecords {
    public static void main(String[] args) {
        // Creation d'une instance de GestionEtudiants.
        GestionEtudiants ge = new GestionEtudiants();

        // Ajout d'etudiants a la liste.
        ge.ajouterEtudiant(new Etudiant("Alice", 101, 85.5));
        ge.ajouterEtudiant(new Etudiant("Bob", 102, 92.0));
        ge.ajouterEtudiant(new Etudiant("Charlie", 103, 78.5));

        // Affichage de la liste des etudiants.
        ge.afficherEtudiants();
        System.out.println();

        // Recherche d'un etudiant par numero.
        ge.rechercherEtudiant(102);
        System.out.println();
        
        // Calcul de la moyenne generale.
        ge.calculerMoyenneGenerale();
    }    
}
