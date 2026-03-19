package corriges.exercices.JDBC.Solution3_avance.main;

import java.util.Scanner;

public class Util {
    public static String question(String question) {
        Scanner clavier = new Scanner(System.in);
        String entreeClavier = "";
        
        System.out.print(question);
        
        entreeClavier = clavier.nextLine();
        clavier.close();
        
        return entreeClavier; 
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> T verifSaisieNombre(String message, Class<T> type) {
        T nombre = null;

        while (nombre == null) {
            String reponse = question(message);

            try {
                switch (type.getSimpleName()) {
                    case "Integer"  -> nombre = (T) Integer.valueOf(reponse);
                    case "Long"     -> nombre = (T) Long.valueOf(reponse);
                    case "Double"   -> nombre = (T) Double.valueOf(reponse);
                    case "Float"    -> nombre = (T) Float.valueOf(reponse);
                    default         -> throw new IllegalArgumentException();
                }
            }
            catch (IllegalArgumentException e) {
                switch (type.getSimpleName()) {
                    case "Integer"  -> System.out.println("Veuillez saisir un nombre entier");
                    case "Long"     -> System.out.println("Veuillez saisir un nombre long");
                    case "Float"    -> System.out.println("Veuillez saisir un nombre float");
                    case "Double"   -> System.out.println("Veuillez saisir un nombre double");
                    default         -> System.out.println("Veuillez saisie un nombre valide.");
                }
            }
        }
        
        return nombre;
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
