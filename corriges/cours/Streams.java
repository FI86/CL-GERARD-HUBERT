/**
 * Exemple sur les stream
 */

package corriges.cours;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Classe Streams
public class Streams {
    public static void main(String... args) {
        // Creer un tableau.
        int[] tab = {1, 2, 2, 10, 5, 8, 7, 9, 3};
        Integer[] tab2 = {1, 2, 2, 10, 5, 8, 7, 9, 3};
    
        // Creer une liste de string.
        List<String> prenoms = Arrays.asList("andre", "benoit", "albert", "thierry", "alain", "jean");
    
        // Transforme une liste en stream.
        List<String> liste = new ArrayList<>();
        liste.add("Hello");
        liste.add("World");

        Stream<String> stream = liste.stream();
        
        // Operations intermediaires.
        prenoms.stream()
            .filter(p -> p.startsWith("a"))
            .filter(p -> p.length() == 5)
            .forEach(x -> System.out.println(x));

        // Transforme un stream en une collection.
        List<String> autreListe = liste.stream().collect(Collectors.toList());

        // Creer un stream du tableau de int.
        IntStream si = Arrays.stream(tab);
        
        // Creer un stream du tableau de Integer.
        Stream<Integer> si2 = Arrays.stream(tab2);
        
        // Transforme un IntStrem en Stream de Integer.
        Stream<Integer> s = si.boxed();

        // Transforme un Stream en une collection.
        List<Integer> autreListeInteger = s.collect(Collectors.toList());
        
        // Creer un stream manuellement.
        IntStream is = IntStream.of(1, 2, 3, 4, 5);
        
        // Genere un stream d'int de 1 a 4.
        IntStream is1 = IntStream.range(1, 5);
        
        // Genere un stream d'int de 1 a 5.
        IntStream is2 = IntStream.rangeClosed(1, 5);
        
        // Genere un stream selon une formule donnee.
        // Exemple les 10 premiers nombres impairs au carre.
        IntStream is3 = IntStream.iterate(1, elem -> elem + 2).limit(10).map(elem -> elem * elem);
        IntStream is4 = IntStream.iterate(1, elem -> elem + 2).limit(10).map(elem -> elem * elem);
        
        // Affiche un stream ou une collection.
        si2.forEach(elem -> System.out.print(elem + " "));
        System.out.println();    
        autreListe.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        is.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        is1.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        is2.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        is3.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        // Affiche la premiere valeur retournee par le IntStream s'il y en a une.
        // findFirst() retour un OptionalInt.
        // ifPresent() test s'il y a une valeur à afficher via findFirst(). 
        is4.findFirst().ifPresent(elem -> System.out.print(elem + " "));
        System.out.println();
        stream.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
        autreListeInteger.forEach(elem -> System.out.print(elem + " "));
        System.out.println();
    }
}