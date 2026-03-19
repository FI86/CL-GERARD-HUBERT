package corriges.exercices.JDBC.Solution1.main;

import java.util.Scanner;
import java.util.List;

import corriges.exercices.JDBC.Solution1.modele.Fournisseur;
import corriges.exercices.JDBC.Solution1.dao.FournisseurDAO;

/**
 * Gestion et affichage du menu fournisseur
 * @author Twixy
 */
class MenuFournisseur {
    /**
     * Affichage sur une ligne d'un fournisseur
     * @param f : fournisseur a afficher
     */
    private void afficherFournisseur(Fournisseur f) {
        System.out.printf("Id : %-2s - ", f.getId());
        System.out.printf("Numero : %-4s - ", f.getNumero());
        System.out.printf("Nom : %-15s - ", f.getNom());
        System.out.printf("Email : %-25s - ", f.getEmail());
        System.out.printf("Adresse : %-30s%n", f.getAdresse());
    }
    
    /**
     * Affiche la liste des fournisseurs
     */
    private void lireFournisseurs() {
        List<Fournisseur> liste = new FournisseurDAO().lire();
        
        if (!liste.isEmpty()) {
            System.out.println();

            for (Fournisseur c : liste) {
                afficherFournisseur(c);
            }
        }
        else {
            System.out.println("La table Fournisseurs est vide.\n");
        }
    }    
    
    /**
     * Affiche un fournisseur specifique
     */
    private void lireFournisseurParId() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id du fournisseur voulu : ", "Erreur : Veuillez saisir un nombre entier : ");
        Fournisseur f = new FournisseurDAO().lireParId(id);
        
        if (f != null) {
            System.out.println();
            afficherFournisseur(f);
        }
        else {
            System.out.println("L'id " + id + " n'exite pas.\n");
        }
    }
    
    /**
     * Saisie les infos d'un fournisseur
     * @return un javaBean Fournisseur a creer
     */
    private Fournisseur saisieFournisseur() {
        Scanner clavier = new Scanner(System.in);
        
        int numero = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez un numero : ", "Erreur : Veuillez saisir un nombre entier : ");
        System.out.print("Entrez un nom : ");
        String nom = clavier.nextLine();
        System.out.print("Entrez un email : ");
        String email = clavier.nextLine();
        System.out.print("Entrez une adresse : ");
        String adresse = clavier.nextLine();
        
        clavier.close();

        return new Fournisseur(numero, nom, email, adresse);
    }

    /**
     * Saisie les infos d'un fournisseur en affichant les infos deja connus
     * @return un javaBean Fournisseur a creer
     */
    private Fournisseur saisieFournisseur(Fournisseur f) {
        Scanner clavier = new Scanner(System.in);
        
        int numero = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez un numero (" + f.getNumero() + ") : ", "Erreur : Veuillez saisir un nombre entier : ");
        System.out.print("Entrez un nom (" + f.getNom() + ") : ");
        String nom = clavier.nextLine();
        System.out.print("Entrez un email (" + f.getEmail() + ") : ");
        String email = clavier.nextLine();
        System.out.print("Entrez une adresse (" + f.getAdresse() + ") : ");
        String adresse = clavier.nextLine();
        
        if (numero <= 0) numero = f.getNumero();
        if ("".equals(nom)) nom = f.getNom();
        if ("".equals(email)) email = f.getEmail();
        if ("".equals(adresse)) adresse = f.getAdresse();
        
        clavier.close();

        return new Fournisseur(f.getId(), numero, nom, email, adresse);
    }
    
    /**
     * Cr�ation d'un fournisseur saisie et valide
     */
    private void creationFournisseur() {
        Fournisseur f = saisieFournisseur();
        String erreur = new FournisseurDAO().validation(f);
        
        if ("".equals(erreur)) {
            if (new FournisseurDAO().creer(f) == true) {
                System.out.println("Fournisseur cree avec succes.\n");
            }
        }
        else {
            System.out.println("Erreur : Fournisseur non valide.\n");
            System.out.println(erreur);            
        }
    }
    
    /**
     * Suppression d'un fournisseur
     */
    private void supprimerFournisseur() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id du fournisseur a supprimer : ", "Erreur : Veuillez saisir un nombre entier : ");
        
        if (new FournisseurDAO().lireParId(id) != null) {
            if (new FournisseurDAO().suppressionParId(id) == true) {
                System.out.println("Fournisseur supprime.\n");
            }
        }
        else {
            System.out.println("L'id " + id + " du fournisseur a supprimer n'existe pas.\n");
        }
    }
    
    /**
     * Modification d'un fournisseur
     */
    private void modifierFournisseur() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id du fournisseur a modifier : ", "Erreur : Veuillez saisir un nombre entier : ");
        Fournisseur fl = new FournisseurDAO().lireParId(id);

        if (fl != null) {
            Fournisseur f = saisieFournisseur(fl);
            String erreur = new FournisseurDAO().validation(f);
            
            if ("".equals(erreur)) {
                if (new FournisseurDAO().modifier(new Fournisseur(id, f.getNumero(), f.getNom(), f.getEmail(), f.getAdresse())) == true) {
                    System.out.println("Fournisseur modifie.\n");
                }
            }
            else System.out.println(erreur);
        }
        else System.out.println("L'id " + id + " du fournisseur a modifier n'existe pas.\n");
    }
    
    /**
     * Menu Fournisseur
     */
    public void menuFournisseur() {
        Scanner clavier = new Scanner(System.in);
        String reponse = "";
        
        while (!"Q".equals(reponse.toUpperCase())) {
            System.out.println();
            System.out.println("Menu fournisseur");
            System.out.println("----------------");
            System.out.println("C : Creer");
            System.out.println("L : Lire");
            System.out.println("I : Lire par id");
            System.out.println("M : Modifier");
            System.out.println("S : Supprimer");
            System.out.println("Q : Quitter");
            System.out.println("----------------");
            System.out.print("Votre choix : ");
            reponse = clavier.nextLine();

            switch (reponse.toUpperCase()) {
                case "C" -> creationFournisseur();
                case "L" -> lireFournisseurs();
                case "I" -> lireFournisseurParId();
                case "M" -> modifierFournisseur();
                case "S" -> supprimerFournisseur();
                case "Q" -> System.out.println();
                default  -> System.out.println("Choisissez une des options indiquees.\n");
            }
        }
        
        clavier.close();
    }
}
