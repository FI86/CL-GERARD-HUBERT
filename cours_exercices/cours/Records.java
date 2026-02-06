/**
 * Exemple de Records
 */

package cours_exercices.cours;


// Classe PointSansRecords
    //  Attributs ne pouvant plus etre modifies apres la construction de l'instance.
    
    // Un constructeur appelant le second constructeur acceptant deux doubles.
    
    // Constructeur permettant de fixer l'etat initial des deux attributs de la classe.
    
    // Deux getters pour l'acces a l'etat de vos points.
    // Les setters ne sont pas disponibles afin de garantir l'aspect immuable (lecture seule) des objets.
    
    // La methode hashCode() permet de renvoyer la valeur de hachage de l'objet sur lequel elle est invoquee.
    // Les specifications imposent une regle a respecter lors de la redefinition de ces m�thodes :
    // si une classe redefinit la methode equals() alors elle doit aussi redefinir la methode hashCode() et inversement.
    
    // Redefinition de la methode equals.
    // Permettra de comparer deux instances Java et d'indiquer si elles sont identiques ou non.
    
    // Affiche la chaine de caracteres representant un point.


// Classe PointSimple de type record


// Classe PointsPerso de type record
    // Definition du constructeur sans parametre manuellement
    
    // Definition du constructeur a deux parametres obligatoire
    // du fait d'avoir definit un constructeur manuellement
    
    // Creation methodePerso
        // Acces aux attributs du record.
        
        // Acces aux methodes d'acces du record.


// Classe Records
public class Records {
    public static void main(String[] args) {
        // Creation de point sans passer par les records
        
        // Utilisation du record simple
        
        // Utilisation du record personnalise.
    }
}
