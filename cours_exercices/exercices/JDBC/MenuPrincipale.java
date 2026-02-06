/**
 * Exercice JDBC :
 * 
 * Ecrire un programme divise en plusieurs fichiers et dossiers.
 * 
 * Un dossier dao pour la partie de code accedant a la BDD.
 * Un dossier modele pour les objets utilises sous forme de JavaBean.
 * Un dossier main pour le programme principal gerant les menus.
 * Vous pouvez creer d'autres dossiers si vous le juger necessaire.
 * 
 * Creer des fichiers differents selon les besoin,
 * et importer les dans les autres fichiers si necessaire.
 * 
 * Description : 
 * 
 * Creer la base de donnees MySQL si elle n'existe pas. Nommee la comme vous le souhaite.
 * Connectez vous a la BDD et creer les tables de la BDD si elles n'existent pas.
 * Ces tables sont : Utilisateurs, Clients, Fournisseurs et Articles.
 * 
 * La table Utilisateurs correpond aux utilisateurs du programme dans une entreprise.
 * Les Utilisateurs ont un id, un numero d'employe unique, un nom, un prenom, un email
 * un login et un mot de passe.
 * 
 * La table Clients cotiendra les client de l'entreprise. Ils ont un id, un numero unique,
 * un nom, un prenom, un email et une adresse.
 * 
 * La table Fournisseurs correspond aux fournisseurs de l'entreprise.
 * Ils ont un id, un numéro unique, un nom, un email et une adresse.
 * 
 * La table Article correspond aux articles achete au fournisseurs et vendu aux clients.
 * Ils ont un id, un numero unique, un champs indiquant si c'est un article acheter ou vendu, un nom, une description.
 * 
 * les operations a faire sur les toutes tables de la BDD et a decrire dans les menus sont :
 * la lectures complete.
 * la lecture d'un enregistrement selon l'id.
 * l'ecriture.
 * la modification.
 * la suppression.
 * 
 * Pour les menus, faites un premier menu pour choisir la table sur laquelle on veux faire une operation.
 * Puis un sous-menu corresondant aux differentes operations.
 * 
 * Faites les controles necesaires sur les operations pour eviter les incoherences et les erreur SQL.
 * 
 * Tous les champs de toutes les tables sont obligatoires.
 * 
 * Faire les javadoc pour toutes les methodes et classes.
 */

package cours_exercices.exercices.JDBC;

/**
 * @author Twixy
 */
public class MenuPrincipale {
    
}
