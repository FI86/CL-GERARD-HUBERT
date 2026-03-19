package corriges.exercices.JDBC.Solution1.main;

import java.util.Scanner;

/**
 * Methodes utilitaires reutilisables
 * @author Twixy
 */
public class Utilitaires {
    /**
     * Verifie si ce qui est saisie est bien un integer et recommence si incorrecte
     * @param clavier : saisie au clavier
     * @param messageOk : message si la saisie est Ok
     * @param messageErreur : message si la saisie est Nok
     * @return la saisie correcte
     */
    public static int verifSaisieInt(Scanner clavier, String messageOk, String messageErreur) {
        System.out.print(messageOk);

        while (!clavier.hasNextInt()) {
            System.out.print(messageErreur);
            clavier.nextLine(); // vidage saisie incorrect
        }

        int val = clavier.nextInt();
        clavier.nextLine(); // vidage buffer
        return val;
    }
    
    /**
     * Verifie si ce qui est saisie est bien un float et recommence si incorrecte
     * @param clavier : saisie au clavier
     * @param messageOk : message si la saisie est Ok
     * @param messageErreur : message si la saisie est Nok
     * @return la saisie correcte
     */
    public static float verifSaisieFloat(Scanner clavier, String messageOk, String messageErreur) {
        System.out.print(messageOk);

        while (!clavier.hasNextFloat()) {
            System.out.print(messageErreur);
            clavier.nextLine(); // vidage saisie incorrect
        }

        float val = clavier.nextFloat();
        clavier.nextLine(); // vidage buffer
        return val;
    }
    
    /**
     * Verifie si ce qui est saisie est bien un double et recommence si incorrecte
     * @param clavier : saisie au clavier
     * @param messageOk : message si la saisie est Ok
     * @param messageErreur : message si la saisie est Nok
     * @return : retourne la saisie correcte
     */
    public static double verifSaisieDouble(Scanner clavier, String messageOk, String messageErreur) {
        System.out.print(messageOk);

        while (!clavier.hasNextDouble()) {
            System.out.print(messageErreur);
            clavier.nextLine(); // vidage saisie incorrect
        }

        double val = clavier.nextDouble();
        clavier.nextLine(); // vidage buffer
        return val;
    }
    
    /**
     * @param chaine : chaine de caractere
     * @return : retourne la phrase avec des majuscules au debut de chaque mots
     */
    public static String premiereLettreMajusculeChaqueMots(String chaine) {
        if (chaine == null || chaine.isEmpty()) return chaine;
        
        StringBuilder result = new StringBuilder();
        // Decoupe en tableau de mots
        String[] mots = chaine.split("\\s+");
        
        for (String mot : mots) {
            if (!mot.isEmpty()) {
                result.append(Character.toUpperCase(mot.charAt(0)))
                    .append(mot.substring(1).toLowerCase());
            }
            result.append(" ");
        }

        // Suppression des espaces en trop
        return result.toString().trim();
    }
}
