package corriges.cours;

import java.util.Scanner;

public class Scanners {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        
         while (true) {
        	 // Affiche la question sans revenir a la ligne grace a print au lieu de println
            System.out.print("Entrez des entiers (autre chose pour arrêter) : ");
            // Vide le buffer et affiche ce buffer.
            System.out.flush();
            
            // Attend une saisie et verifie si c'est un int. 
            if (sc.hasNextInt()) {
            	// Si c'est un int on recupere sa valeur et on l'affiche.
                n = sc.nextInt();
                System.out.println("Vous avez entré : " + n);
                System.out.flush();
            }
            else {
            	// Sinon, on sort de la boucle
                break;
            }
        }

        System.out.println("Fin de la lecture.");
        // Fermeture du Scanner.
        sc.close();
    }
}
