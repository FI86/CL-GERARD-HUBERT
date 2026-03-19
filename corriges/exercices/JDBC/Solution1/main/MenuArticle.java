package corriges.exercices.JDBC.Solution1.main;

import java.util.Scanner;
import java.util.List;

import corriges.exercices.JDBC.Solution1.modele.Article;
import corriges.exercices.JDBC.Solution1.dao.ArticleDAO;

/**
 * Gestion et affichage du menu article
 * @author Twixy
 */
class MenuArticle {
    /**
     * Affichage sur une ligne d'un article
     * @param a: article a afficher
     */
    private static void afficherArticle(Article a) {
        System.out.printf("Id : %-2s - ", a.getId());
        System.out.printf("Numero : %-4s - ", a.getNumero());
        System.out.printf("FC : %-1s - ", a.getFC());
        System.out.printf("Nom : %-20s - ", a.getNom());
        System.out.printf("Description : %-50s%n", a.getDescription());
    }
    
    /**
     * Affiche la liste des articles
     */
    private void lireArticles() {
        List<Article> liste = new ArticleDAO().lire();
        
        if (!liste.isEmpty()) {
            System.out.println();

            for (Article a : liste) {
                afficherArticle(a);
            }
        }
        else {
            System.out.println("La table Articles est vide.\n");
        }
    }    
    
    /**
     * Affiche un article specifique
     */
    private void lireArticleParId() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id de l'article voulu : ", "Erreur : Veuillez saisir un nombre entier : ");
        Article a = new ArticleDAO().lireParId(id);
        
        if (a != null) {
            System.out.println();
            afficherArticle(a);
        }
        else {
            System.out.println("L'id " + id + " n'exite pas.\n");
        }
    }
    
    /**
     * Saisie les infos d'un article
     * @return un javaBean Article a creer
     */
    private Article saisieArticle() {
        Scanner clavier = new Scanner(System.in);
        
        int numero = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez un numero : ", "Erreur : Veuillez saisir un nombre entier : ");
        System.out.print("Entrez client (c) ou fournisseur (f) : ");
        String fc = clavier.nextLine();
        System.out.print("Entrez un nom : ");
        String nom = clavier.nextLine();
        System.out.print("Entrez une description : ");
        String description = clavier.nextLine();
        
        clavier.close();
        
        return new Article(numero, fc, nom, description);
    }

    /**
     * Saisie les infos d'un article en affichant les infos deja connus
     * @return un javaBean Article a creer
     */
    private Article saisieArticle(Article a) {
        Scanner clavier = new Scanner(System.in);
        
        int numero = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez un numero (" + a.getNumero() + ") : ", "Erreur : Veuillez saisir un nombre entier : ");
        System.out.print("Entrez client (c) ou fournisseur (f) (" + a.getFC() + ") : ");
        String fc = clavier.nextLine();
        System.out.print("Entrez un nom (" + a.getNom() + ") : ");
        String nom = clavier.nextLine();
        System.out.print("Entrez une description (" + a.getDescription() + ") : ");
        String description = clavier.nextLine();
        
        if (numero <= 0) numero = a.getNumero();
        if ("".equals(fc)) fc = a.getFC();
        if ("".equals(nom)) nom = a.getNom();
        if ("".equals(description)) description = a.getDescription();
        
        clavier.close();
        
        return new Article(a.getId(), numero, fc, nom, description);
    }
    
    /**
     * Creation d'un article saisie et valide
     */
    private void creationArticle() {
        Article a = saisieArticle();
        String erreur = new ArticleDAO().validation(a);
        
        if ("".equals(erreur)) {
            if (new ArticleDAO().creer(a) == true) {
                System.out.println("Article cree avec succes.\n");
            }
        }
        else {
            System.out.println("Erreur : Article non valide.\n");
            System.out.println(erreur);
        }
    }
    
    /**
     * Suppression d'un article
     */
    private void supprimerArticle() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id de l'article a supprimer : ", "Erreur : Veuillez saisir un nombre entier : ");
        
        if (new ArticleDAO().lireParId(id) != null) {
            if (new ArticleDAO().suppressionParId(id) == true) {
                System.out.println("Article supprime.\n");
            }
        }
        else {
            System.out.println("L'id " + id + " de l'article a supprimer n'existe pas.\n");
        }
    }
    
    /**
     * Modification d'un article
     */
    private void modifierArticle() {
        int id = Utilitaires.verifSaisieInt(new Scanner(System.in), "Entrez l'id de l'article a modifier : ", "Erreur : Veuillez saisir un nombre entier : ");
        Article al = new ArticleDAO().lireParId(id);
        
        if (al != null) {
            Article a = saisieArticle(al);
            String erreur = new ArticleDAO().validation(a);
            
            if ("".equals(erreur)) {
                if (new ArticleDAO().modifier(new Article(id, a.getNumero(), a.getFC(), a.getNom(), a.getDescription())) == true) {
                    System.out.println("Article modifie.\n");
                }
            }
            else System.out.println(erreur);
        }
        else System.out.println("L'id " + id + " de l'article a modifier n'existe pas.\n");
    }
    
    /**
     * Menu Article
     */
    public void menuArticle() {
        Scanner clavier = new Scanner(System.in);
        String reponse = "";
        
        while (!"Q".equals(reponse.toUpperCase())) {
            System.out.println();
            System.out.println("Menu article");
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
                case "C" -> creationArticle();
                case "L" -> lireArticles();
                case "I" -> lireArticleParId();
                case "M" -> modifierArticle();
                case "S" -> supprimerArticle();
                case "Q" -> System.out.println();
                default  -> System.out.println("Choisissez une des options indiquees.\n");
            }
        }
        
        clavier.close();
    }
}
