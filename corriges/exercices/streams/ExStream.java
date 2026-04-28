/**
 * Exercices Stream
 * 
 * Creer 3 methodes pour les choses suivantes puis executez les dans le programme principal.
 * - Calcul et affiche le nombre de fois qu'un caractere apparait dans une chaine.
 * - Filtre et affiche les mots commencant par une lettre donnee.
 * - Convertir une chaine representant une liste de nombres separes par un ";".
 *   Afficher le resultat de la conversion sous la forme d'une liste de nombres ayant une longueur fixe de 4 caracteres et separer par une "," 
 */ 
package corriges.exercices.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Exercice sur les streams
 * @author Twixy
 */
public class ExStream {
    /**
     * Calcul et affiche le nombre de fois qu'un caractere apparait dans une chaine.
     * @param str : chaine de caracteres a tester.
     * @param ch : caractere a tester dans la chaine.
     */
    private static void nbOccurrences(String str, char ch) {
        System.out.println(str.chars().filter(c -> c == ch).count());
    }
    
    /**
     * Filtre et affiche les mots commencant par une lettre donnee.
     * @param liste : une liste de mots.
     * @param lettre : la lettre avec laquelle le mot doit commencer pour etre selectionne.
     */
    private static void motsCommencantPar(List<String> liste, String lettre) {
        liste.stream().filter(x -> x.startsWith(lettre)).forEach(x -> System.out.println(x));
    }
    
    /**
     * Convertir une chaine representant une liste de nombres separes par un ";".
     * Afficher le resultat de la conversion sous la forme d'une liste de nombres ayant une longueur fixe de 4 caracteres et separer par une "," 
     */
    private static void convertNbrTableau() {
        String donnees ="14;15;8;10;7;9";
        List<String> numbers = Arrays.asList(donnees.split(";")); 
        
        System.out.println(numbers.stream()
                 .map(x -> Integer.parseInt(x))
                 .map(i -> String.format("%04d", i))
                 .collect(Collectors.joining(", ")));
    }
    
    /**
     * Met en majuscule la premiere lettre d'un mot.
     * @param mot : mot pour lequel on veut mettre une majuscule a la premiere lettre.
     * @return : le mot modifie.
     */
    public static String majusculeMot(String mot)
    {
        if (mot == null || mot.length() == 0) return mot;
        // substring(x) recupere la sous chaine a partir de la position x.
        return mot.substring(0, 1).toUpperCase() + mot.substring(1);
    }

    /**
     * Met en majuscule chaque mot d'une phrase
     * apres avoir supprimer les espaces en trops dans la phrase.
     * @param str : la phrase a modifier.
     * @return : la phrase modifee.
     */
    public static String majusculeMotsPhrase(String str)
    {
        if (str == null || str.length() == 0) return str;
        // "\\s+" : expression reguliere indiquant un espace ou plus.
        return Stream.of(str.trim().split("\\s+"))
                // la reference de methode :: remplace la lambda : mot -> ExStream.majusculeMot(mot).
                .map(ExStream::majusculeMot)
                .collect(Collectors.joining(" "));
    }

    /**
     * Programme principal
     * @param args Parametres passes en ligne de commande
     */
    public static void main(String[] args) {
        List<String> mots = Arrays.asList("calculatrice", "chat", "plaid", "chocolat", "travail", "physiologie");
        String chaine = "Ma chaine de caracteres";
        String str = "met  en   majuscule      mes   mots.";
        
        nbOccurrences(chaine, 'c');
        convertNbrTableau();
        motsCommencantPar(mots, "c");
        System.out.println(majusculeMotsPhrase(str));
    }
}
