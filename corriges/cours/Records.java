/**
 * Exemple de Records
 */

package corriges.cours;

import java.util.Objects;

// Classe PointSansRecords
class PointSansRecords {
    //  Attributs ne pouvant plus etre modifies apres la construction de l'instance.
    private final double x;
    private final double y;
    
    // Un constructeur appelant le second constructeur acceptant deux doubles.
    public PointSansRecords() {
        this(0, 0);
    }
    
    // Constructeur permettant de fixer l'etat initial des deux attributs de la classe.
    public PointSansRecords(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // Deux getters pour l'acces a l'etat de vos points.
    // Les setters ne sont pas disponibles afin de garantir l'aspect immuable (lecture seule) des objets.
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }

    // La methode hashCode() permet de renvoyer la valeur de hachage de l'objet sur lequel elle est invoquee.
    // Les specifications imposent une regle a respecter lors de la redefinition de ces methodes :
    // si une classe redefinit la methode equals() alors elle doit aussi redefinir la methode hashCode() et inversement.
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    // Redefinition de la methode equals.
    // Permettra de comparer deux instances Java et d'indiquer si elles sont identiques ou non.
    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof PointSansRecords && this.x == ((PointSansRecords) object).x && this.y == ((PointSansRecords) object).y);
    }
    
    // Affiche la chaine de caracteres representant un point.
    @Override public String toString() {
        return "PointSansRecords : x = " + x + " - y = " + y;
    }
}

// Classe Points de type record
record PointSimple(double x, double y) {}

// Classe PointsPerso de type record
record PointPerso(double x, double y) {
    // Definition du constructeur sans parametre manuellement
    public PointPerso() {
        this(0, 0);
    }
    
    // Creation methodePerso
    public void methodePerso() {
        // Acces aux attributs du record.
        System.out.println( "Attributs : x = " + this.x + " - y = " + this.y );
        // Acces aux methodes d'acces du record.
        System.out.println( "Getteurs : x = " + this.x() + " - y = " + this.y());
    }
}

// Classe Records
public class Records {
    public static void main(String[] args) {
        // Creation de point sans passer par les records
        PointSansRecords psd1 = new PointSansRecords(15, 20);
        PointSansRecords psd2 = new PointSansRecords(15, 20);
        System.out.println("x = " + psd1.getX());
        System.out.println("y = " + psd1.getY());
        System.out.println("Egalite : " + psd1.equals(psd2));
        System.out.println(psd1);
        System.out.println();
        
        // Utilisation du record simple
        PointSimple ps1 = new PointSimple(0, 0);
        PointSimple ps2 = new PointSimple(10, 10);
        
        System.out.println(ps1);
        System.out.println(ps2);
        System.out.println("Egalite : " + (ps1 == ps2));
        System.out.println("Hash code : " + ps2.hashCode());
        System.out.println("Getters : x = " + ps2.x() + " - y = " + ps2.y());
        System.out.println();
        
        // Utilisation du record personnalise.
        PointPerso pp1 = new PointPerso();
        PointPerso pp2 = new PointPerso(10, 10);
        
        System.out.println(pp1);
        System.out.println(pp2);
        System.out.println("Egalite : " + (pp1 == pp2));
        System.out.println("Hash code : " + pp2.hashCode());
        System.out.println("Getters : x = " + pp2.x() + " - y = " + pp2.y());
        System.out.println();
        pp1.methodePerso();
        pp2.methodePerso();
    }
}
