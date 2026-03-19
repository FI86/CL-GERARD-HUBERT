package corriges.exercices.JDBC.Solution1.main;

import java.util.Scanner;
import java.util.List;

import corriges.exercices.JDBC.Solution1.modele.Client;
import corriges.exercices.JDBC.Solution1.dao.ClientDAO;

/**
 * Gestion et affichage du menu client
 * @author Twixy
 */
class MenuClient {
    /**
     * Affichage sur une ligne d'un client
     * @param c : client a afficher
     */
    private void afficherClient(Client c) {
        System.out.printf("Id : %-2s - ", c.getId());
        System.out.printf("Numero : %-4s - ", c.getNumero());
        System.out.printf("Nom : %-15s - ", c.getNom());
        System.out.printf("Prenom : %-15s - ", c.getPrenom());
        System.out.printf("Email : %-25s - ", c.getEmail());
        System.out.printf("Adresse : %-30s%n", c.getAdresse());
    }
    
    /**
     * Affiche la liste des clients
     */
    private void lireClients() {
        List<Client> liste = new ClientDAO().lire();
        
        if (!liste.isEmpty()) {
            System.out.println();

            for (Client c : liste) {
                afficherClient(c);
            }
        }
        else {
            System.out.println("La table Clients est vide.\n");
        }
    }    
    
    /**
     * Affiche un client specifique
     */
    private void lireClientParId() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id du client voulu : ", "Erreur : Veuillez saisir un nombre entier : ");
        Client c = new ClientDAO().lireParId(id);
        
        if (c != null) {
            System.out.println();
            afficherClient(c);
        }
        else {
            System.out.println("L'id " + id + " n'exite pas.\n");
        }
    }
    
    /**
     * Saisie les infos d'un client
     * @return un javaBean Client a creer
     */
    private Client saisieClient() {
        Scanner clavier = new Scanner(System.in);
        
        int numero = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez un numero : ", "Erreur : Veuillez saisir un nombre entier : ");
        System.out.print("Entrez un nom : ");
        String nom = clavier.nextLine();
        System.out.print("Entrez un prenom : ");
        String prenom = clavier.nextLine();
        System.out.print("Entrez un email : ");
        String email = clavier.nextLine();
        System.out.print("Entrez une adresse : ");
        String adresse = clavier.nextLine();
        
        clavier.close();

        return new Client(numero, nom, prenom, email, adresse);
    }

    /**
     * Saisie les infos d'un client en affichant les infos deja connus
     * @return un javaBean Client a creer
     */
    private Client saisieClient(Client c) {
        Scanner clavier = new Scanner(System.in);
        
        int numero = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez un numero (" + c.getNumero() + ") : ", "Erreur : Veuillez saisir un nombre entier : ");
        System.out.print("Entrez un nom (" + c.getNom() + ") : ");
        String nom = clavier.nextLine();
        System.out.print("Entrez un prenom (" + c.getPrenom() + ") : ");
        String prenom = clavier.nextLine();
        System.out.print("Entrez un email (" + c.getEmail() + ") : ");
        String email = clavier.nextLine();
        System.out.print("Entrez une adresse (" + c.getAdresse() + ") : ");
        String adresse = clavier.nextLine();
        
        if (numero <= 0) numero = c.getNumero();
        if ("".equals(nom)) nom = c.getNom();
        if ("".equals(prenom)) prenom = c.getPrenom();
        if ("".equals(email)) email = c.getEmail();
        if ("".equals(adresse)) adresse = c.getAdresse();
        
        clavier.close();

        return new Client(c.getId(), numero, nom, prenom, email, adresse);
    }
    
    /**
     * Creation d'un client saisie et valide
     */
    private void creationClient() {
        Client c = saisieClient();
        String erreur = new ClientDAO().validation(c);
        
        if ("".equals(erreur)) {
            if (new ClientDAO().creer(c) == true) {
                System.out.println("Client cree avec succes.\n");
            }
        }
        else {
            System.out.println("Erreur : Client non valide.\n");
            System.out.println(erreur);
        }
    }
    
    /**
     * Suppression d'un client
     */
    private void supprimerClient() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id du client a supprimer : ", "Erreur : Veuillez saisir un nombre entier : ");
        
        if (new ClientDAO().lireParId(id) != null) {
            if (new ClientDAO().suppressionParId(id) == true) {
                System.out.println("Client supprime.\n");
            }
        }
        else System.out.println("L'id " + id + " du client a supprimer n'existe pas.\n");
    }
    
    /**
     * Modification d'un client
     */
    private void modifierClient() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id du client a modifier : ", "Erreur : Veuillez saisir un nombre entier : ");
        Client cl = new ClientDAO().lireParId(id);
        
        if (cl != null) {
            Client c = saisieClient(cl);
            String erreur = new ClientDAO().validation(c);
            
            if ("".equals(erreur)) {
                if (new ClientDAO().modifier(new Client(id, c.getNumero(), c.getNom(), c.getPrenom(), c.getEmail(), c.getAdresse())) == true) {
                    System.out.println("Client modifie.\n");
                }
            }
            else System.out.println(erreur);
        }
        else System.out.println("L'id " + id + " du client a modifier n'existe pas.\n");
    }
    
    /**
     * Menu Client
     */
    public void menuClient() {
        Scanner clavier = new Scanner(System.in);
        String reponse = "";
        
        while (!"Q".equals(reponse.toUpperCase())) {
            System.out.println();
            System.out.println("Menu client");
            System.out.println("-----------");
            System.out.println("C : Creer");
            System.out.println("L : Lire");
            System.out.println("I : Lire par id");
            System.out.println("M : Modifier");
            System.out.println("S : Supprimer");
            System.out.println("Q : Quitter");
            System.out.println("-----------");
            System.out.print("Votre choix : ");
            reponse = clavier.nextLine();

            switch (reponse.toUpperCase()) {
                case "C" -> creationClient();
                case "L" -> lireClients();
                case "I" -> lireClientParId();
                case "M" -> modifierClient();
                case "S" -> supprimerClient();
                case "Q" -> System.out.println();
                default  -> System.out.println("Choisissez une des options indiquees.\n");
            }
        }
        
        clavier.close();
    }
}
