/**
 * Exemple sur l'API Date
 */
package corriges.cours;

// Import
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Classe principale
 */
public class API_Date {
	// Methode principale
	public static void main(String[] args) {
		// LocalDate
		// Creation d'une date (YYYY-MM-DD)
		LocalDate ld = LocalDate.of(2024, 2, 26);
		System.out.println("date definie : " + ld);
		// Creation d'une date (YYYY-MM-DD) a partir d'une string
		// attention a bien respecter le format sur deux chiffres pour mois et jour.
		ld = LocalDate.parse("2024-02-26");
		System.out.println("date definie avec string : " + ld);
		// Creation d'une date d'aujourd'hui
		ld = LocalDate.now();
		System.out.println("date aujourd'hui : " + ld);
		// annee d'une date
		int annee = ld.getYear();
		System.out.println("annee : " + annee);
		// mois d'une date
		Month mois = ld.getMonth();
		System.out.println("mois : " + mois);
		// jour en nombre d'une date
		int jourNombre = ld.getDayOfMonth();
		System.out.println("jour en nombre : " + jourNombre);
		// jour en lettre d'une date
		DayOfWeek jour = ld.getDayOfWeek();
		System.out.println("jour en lettre : " + jour);
		// nombre de jours dans le mois
		int nbJour = ld.lengthOfMonth();
		System.out.println("jour en lettre : " + nbJour);
		// annee bisextile ou pas ?
		boolean bisextile = ld.isLeapYear();
		System.out.println("annee bisextile ? : " + bisextile);
		
		// il est possible d'obtenir des infos sur date et heures avec l'enumeration ChronoField
		int a = ld.get(ChronoField.YEAR);
		int m = ld.get(ChronoField.MONTH_OF_YEAR);
		int j = ld.get(ChronoField.DAY_OF_MONTH);
		System.out.println("date : " + j + "-" + m + "-" + a);
		System.out.println();
		
		// LocalTime
		// definition d'une heure (HH:MM:SS)
		LocalTime lt = LocalTime.of(13, 30, 20);
		System.out.println("heure definie : " + lt);
		// Creation d'une date (HH:MM:SS) a partir d'une string
		// attention a bien respecter le format sur deux chiffres.
		lt = LocalTime.parse("13:45:26");
		System.out.println("heure definie avec string : " + lt);		
		// heure actuelle
		lt = LocalTime.now();
		System.out.println("heure actuelle : " + lt);
		// Obtenir les heures, minutes et seconde de l'heure
		int heure = lt.getHour();
		int minute = lt.getMinute();
		int seconde = lt.getSecond();
		System.out.println("heure : " + heure + ":" + minute + ":" + seconde);
		System.out.println();
		
		// LocalDateTime
		// definition d'une date et d'une heure
		LocalDateTime ldt = LocalDateTime.of(2024, Month.DECEMBER, 18, 13, 45, 20);
		System.out.println("date et heure : " + ldt);
		// a partir d'une date et une heure deja definie via LocalDate et LocalTime
		ldt = LocalDateTime.of(ld, lt);
		System.out.println("date et heure : " + ldt);
		// ou en utilisant atTime() sur une variable LocalDate
		ldt = ld.atTime(lt);
		System.out.println("date et heure : " + ldt);
		// ou en utilisant atDate() sur une variable LocalTime
		ldt = lt.atDate(ld);
		System.out.println("date et heure : " + ldt);
		// a partir d'une date deja definie via LocalDate et une heure manuellement
		ldt = ld.atTime(11, 45, 25);
		System.out.println("date et heure : " + ldt);
		// recuperer la date d'un LocalDateTime
		System.out.println("date du LocalDateTime : " + ldt.toLocalDate());
		// recuperer l'heure d'un LocalDateTime
		System.out.println("heure du LocalDateTime : " + ldt.toLocalTime());
		
		// Durees entre LocalTime, LocalDateTime ou Instant
		Duration d1 = Duration.between(lt, ldt.toLocalTime());
		System.out.println("duree en deux horaire en heure : " + d1.toHours());
		System.out.println("duree en deux horaire en minute : " + d1.toMinutes());
		
		// definition d'une duree
		d1 = Duration.ofMinutes(3);
		// ou avec ChronoUnit
		d1 = Duration.of(3, ChronoUnit.MINUTES);
		System.out.println("duree definie en seconde : " + d1.getSeconds());
		
		// Duree sur les dates
		// periode en le 01/01/2024 et aujourd'hui
		Period p = Period.between(LocalDate.of(2024, 01, 01), ld);
		System.out.println("periode entre deux date en jour : " + p);
	}
}
