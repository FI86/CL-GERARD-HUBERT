/**
 * Exercice sur l'API Date
 * 
 * 1. Ecrire un programme qui demande a l'utilisateur
 * de saisir sa date de naissance au format anglais (AAAA-MM-JJ)
 * et qui calcule puis affiche son age en annees.
 * 2. Essayer de le faire la meme chose avec une conversion
 * au format francais (JJ/MM/AAAA).
 */
package corriges.exercices.api_date;

// Imports
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe principale
 */
public class ExApiDate {
	// Enumeration de langues.
	enum Langue {EN, FR}
	
	// Constantes de formattage de date.
    private static final DateTimeFormatter formatDateEn = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    private static final DateTimeFormatter formatDateFr = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
    
    // Methode pour verifier le format de la saisie et la validite de la date
    // dans le calendrier avec une expression reguliere.
    public static boolean formatDateValide(Langue langue, String dateStr) {
    	String regex = "";
    	
    	switch (langue) {
    		case EN -> regex = "^(?:(?:\\d{1,4})-(?:0?[13578]|1[02])-(?:0?[1-9]|[12]\\d|3[01]))$|^(?:(?:\\d{1,4})-(?:0?[469]|11)-(?:0?[1-9]|[12]\\d|30))$|^(?:(?:\\d{1,4})-0?2-(?:0?[1-9]|1\\d|2[0-8]))$|^(?:(?:(?:\\d{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:(?:[02468][048]|[13579][26])00)))-(?:0?2)-29)$";
    		default	-> regex = "^(?:0?[1-9]|1\\d|2[0-8])/(?:0?[1-9]|1[0-2])/(?:\\d{1,4})$|^(?:29|30)/(?:0?[13-9]|1[0-2])/(?:\\d{1,4})$|^30/0?2/(?:\\d{1,4})$|^29/0?2/(?:(?:\\d{1,4})-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1\\d|2[0-8])|\\d{2}(?:[2468][048]|[13579][26])-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1\\d|2[0-9])|\\d{4}-0?2-(?:0?[1-9]|1\\d|2[0-9]))$";
    	}
    	
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateStr);
        // Retourne true of false.
        return matcher.matches();
    }
    
    // Methode pour verifier et convertir la saisie de date en LocalDate.
    public static LocalDate conversionFormatDate(Langue langue, String dateStr) {
    	LocalDate date = null;
    	// try inutile si le regex est bon.
        try {
            // Converti dateStr en LocalDate au format voulu.
        	switch (langue) {
	        	case EN -> date = LocalDate.parse(dateStr, formatDateEn);
	        	default -> date = LocalDate.parse(dateStr, formatDateFr);
        	}
        }
        catch (Exception e) {
        	System.out.println("La date n'existe pas.");
        }
        
        // Retourner null si le format n'est pas valide.
        return date;
    }
    
	// Methode principale
	public static void main(String[] args) {
		// Constante pour choisir la langue.
		final Langue langue = Langue.EN;
        // Recuperation de la date d'aujourd'hui.
        LocalDate dateActuelle = LocalDate.now();
        // Date de naissance.
        LocalDate dateNaissance = null;
        // Reponse du scanner.
        String reponse = "";
        // Creation d'un objet Scanner pour la saisie utilisateur.
        try (Scanner scanner = new Scanner(System.in);) {
	        do {
		    	// Saisie de la date de naissance.
	        	switch (langue) {
		        	case EN -> System.out.print("Veuillez saisir votre date de naissance (format AAAA-MM-JJ) : ");
		        	default -> System.out.print("Veuillez saisir votre date de naissance (format JJ/MM/AAAA) : ");
	        	}
	        	
		        reponse = scanner.nextLine();
	        }
	        while (!formatDateValide(langue, reponse));
        }        
        catch (Exception e ) {
        	System.out.println("Une erreur est survenue.");
        }
        
        // Conversion de la saisie en LocalDate.
        dateNaissance = conversionFormatDate(langue, reponse);
        
        // Si la date est valide (inutile si le regex est bon).
        if (dateNaissance != null) {
	        // Calcul de l'age.
	        int age = Period.between(dateNaissance, dateActuelle).getYears();
	        // Affichage du resultat.
	        System.out.println("Vous avez " + age + " ans.");
	        System.out.println("Nombre de jour : " + ChronoUnit.DAYS.between(dateNaissance, dateActuelle));
	        System.out.println("Fin du programme.");
        }
	}
}
