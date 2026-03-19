package corriges.exercices.JDBC.Solution1.dao;

import corriges.exercices.JDBC.Solution1.interfaces.IUtilisateurDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import corriges.exercices.JDBC.Solution1.modele.Utilisateur;

/**
 * Gestion de la Table Utilisateurs dans la Base de donnees MySQL
 */
public class UtilisateurDAO implements IUtilisateurDAO {
    /**
     * Lire tout le contenu de la table
     * @return une liste de JavaBean Utilisateur
     */
    @Override
    public List<Utilisateur> lire() {
        List<Utilisateur> lu = new ArrayList<>();

        try (Connection conn = Connexion.connecterAvecBase(true);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Utilisateurs")) {
            
            while (rs.next()) {
                lu.add(new Utilisateur(
                        rs.getInt("id"), rs.getInt("numero"),
                        rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("login"), rs.getString("mdp")));
            }
        }
        catch (Exception e) {
            System.out.println("Erreur Utilisateur - Lire.");
            System.out.println(e);            
        }
        
        return lu;
    }
    
    /**
     * Lire un enregistrement de la table Utilisateurs par son id
     * @param id : numero de l'enregistrement dans la table
     * @return un JavaBean Utilisateur s'il existe
     */
    @Override
    public Utilisateur lireParId(Integer id) {
        Utilisateur u = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Utilisateurs WHERE id = ?");
            ps.setInt(1, id);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                u = new Utilisateur(
                    rs.getInt("id"), rs.getInt("numero"),
                    rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("login"), rs.getString("mdp"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Utilisateur - LireParId.");
            System.out.println(e);            
        }
        
        return u;
    }

    /**
     * Lire un enregistrement de la table Utilisateurs par son numero
     * @param numero : numero de l'utilisateur
     * @return un JavaBean Utilisateur s'il existe
     */
    @Override
    public Utilisateur lireParNumero(Integer numero) {
        Utilisateur u = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Utilisateurs WHERE numero = ?");
            ps.setInt(1, numero);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                u = new Utilisateur(
                    rs.getInt("Id"), rs.getInt("numero"),
                    rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("login"), rs.getString("mdp"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Utilisateur - LireParNumero.");
            System.out.println(e);            
        }
        
        return u;
    }
    
    /**
     * Lire un enregistrement de la table Utilisateurs par son numero
     * @param id : id de l'enregistrement 
     * @param numero : numero de l'utilisateur
     * @return un JavaBean Utilisateur s'il existe
     */
    @Override
    public Utilisateur chercheNumeroExistant(Integer id, Integer numero) {
        Utilisateur u = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Utilisateurs WHERE id <> ? AND numero = ?");
            ps.setInt(1, id);
            ps.setInt(2, numero);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                u = new Utilisateur(
                    rs.getInt("Id"), rs.getInt("numero"),
                    rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getString("login"), rs.getString("mdp"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - chercheNumeroExistant.");
            System.out.println(e);            
        }
        
        return u;
    }    
    
    /**
     * Suppression d'un utilisateur
     * @param id : numero de l'enregistrement dans la table
     * @return true si la suppression est Ok
     */
    @Override
    public boolean suppressionParId(Integer id) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            // On supprime une occurrence
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Utilisateurs WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // On "comble le trou" s'il y en a un
            // (ce qui n'est pas toujours souhaitable)
            ps = conn.prepareStatement("UPDATE Utilisateurs SET id = id-1 WHERE id > ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // On reinitialise l'auto_increment
            // (cela permettra au prochaine occurrence de suivre la plus grande valeur du champ auto incremente enregistre dans la table)
            ps = conn.prepareStatement("ALTER TABLE Utilisateurs AUTO_INCREMENT = 0");
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erreur Utilisateur - SuppressionParId.");
            System.out.println(e);            
            return false;
        }
    }
    
    /**
     * Modifie un utilisateur
     * @param u : Un JavaBean Utilisateur
     * @return true si la modification est Ok
     */
    @Override
    public boolean modifier(Utilisateur u) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Utilisateurs SET id = ?, numero = ?, nom = ?, prenom = ?, login = ?, email = ?, mdp = ? WHERE id = ?");
            ps.setInt(1, u.getId());
            ps.setInt(2, u.getNumero());
            ps.setString(3, u.getNom());
            ps.setString(4, u.getPrenom());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getLogin());
            ps.setString(7, u.getMdp());
            ps.setString(8, u.getId().toString());
            ps.executeUpdate();
            return true;
	}
        catch (Exception e) {
            System.out.println("Erreur Utilisateur - Modifier.");
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * Creer un utilisateur
     * @param u : un JavaBean Utilisateur
     * @return true si la creation est Ok
     */
    @Override
    public boolean creer(Utilisateur u) {
        try (Connection conn = Connexion.connecterAvecBase(true)){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Utilisateurs VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, u.getNumero());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getLogin());
            ps.setString(6, u.getMdp());
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erreur Utilisateur - Creer.");
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * Controle si les parametres sont correct
     * @param u : JavaBean Utilisateur
     * @return true si la verification est Ok
     */
    @Override
    public String validation(Utilisateur u) {
        if (u.getNumero() <= 0) return "Le numero doit etre un entier positif.";
        if ("".equals(u.getNom())) return "Le nom ne doit pas etre vide.";
        if ("".equals(u.getPrenom())) return "Le Prénom ne doit pas etre vide.";
        if (u.getEmail() == null || "".equals(u.getEmail())) return "L'email ne doit pas etre vide.";
        if (!u.getEmail().contains("@")) return "L'email doit contenir un @.";
        if ("".equals(u.getLogin())) return "Le login ne doit pas etre vide.";
        if (u.getMdp().length() < 8) return "Le mot de passe doit comporté au moins 8 caractères";
        if (chercheNumeroExistant(u.getId(), u.getNumero()) != null) return "Le numero de l'article existe déjà.";
        return "";
    }
}