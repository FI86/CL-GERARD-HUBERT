package corriges.exercices.JDBC.Solution1.dao;

import corriges.exercices.JDBC.Solution1.interfaces.IFournisseurDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import corriges.exercices.JDBC.Solution1.modele.Fournisseur;

/**
 * Gestion de la Table Fournisseurs dans la Base de donnees MySQL
 */
public class FournisseurDAO implements IFournisseurDAO {
    /**
     * Lire tout le contenu de la table
     * @return une liste de JavaBean Fournisseur
     */
    @Override
    public List<Fournisseur> lire() {
        List<Fournisseur> lf = new ArrayList<>();

        try (Connection conn = Connexion.connecterAvecBase(true);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Fournisseurs")) {
            
            while (rs.next()) {
                lf.add(new Fournisseur(
                        rs.getInt("id"), rs.getInt("Numero"),
                        rs.getString("nom"), rs.getString("email"),
                        rs.getString("adresse")));
            }
        }
        catch (Exception e) {
            System.out.println("Erreur Fournisseur - Lire.");
            System.out.println(e);            
        }
        
        return lf;
    }
    
    /**
     * Lire un enregistrement de la table Fournisseurs par son id
     * @param id : numero de l'enregistrement dans la table
     * @return un JavaBean Fournisseur s'il existe
     */
    @Override
    public Fournisseur lireParId(Integer id) {
        Fournisseur f = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Fournisseurs WHERE id = ?");
            ps.setInt(1, id);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                f = new Fournisseur(
                    rs.getInt("id"), rs.getInt("Numero"),
                    rs.getString("nom"), rs.getString("email"),
                    rs.getString("adresse"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Fournisseur - LireParId.");
            System.out.println(e);            
        }
        
        return f;
    }
    
    /**
     * Lire un enregistrement de la table Fournisseurs par son numero
     * @param numero : numero du fournisseur dans la table
     * @return un JavaBean Fournisseur s'il existe
     */
    @Override
    public Fournisseur lireParNumero(Integer numero) {
        Fournisseur f = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Fournisseurs WHERE numero = ?");
            ps.setInt(1, numero);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                f = new Fournisseur(
                    rs.getInt("id"), rs.getInt("Numero"),
                    rs.getString("nom"), rs.getString("email"),
                    rs.getString("adresse"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Fournisseur - LireParNumero.");
            System.out.println(e);            
        }
        
        return f;
    }
    
    /**
     * Lire un enregistrement de la table Fournisseurs par son numero
     * @param id : id de l'enregistrement 
     * @param numero : numero du fournisserur
     * @return un JavaBean Fournisseur s'il existe
     */
    @Override
    public Fournisseur chercheNumeroExistant(Integer id, Integer numero) {
        Fournisseur f = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Fournisseurs WHERE id <> ? AND numero = ?");
            ps.setInt(1, id);
            ps.setInt(2, numero);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                f = new Fournisseur(
                    rs.getInt("id"), rs.getInt("Numero"),
                    rs.getString("nom"), rs.getString("email"),
                    rs.getString("adresse"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - chercheNumeroExistant.");
            System.out.println(e);            
        }
        
        return f;
    }    
    
    /**
     * Suppression d'un Fournisseur
     * @param id : numero de l'enregistrement dans la table
     * @return true si la suppression est Ok
     */
    @Override
    public boolean suppressionParId(Integer id) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            // On supprime une occurrence
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Fournisseurs WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // On "comble le trou" s'il y en a un
            // (ce qui n'est pas toujours souhaitable)
            ps = conn.prepareStatement("UPDATE Fournisseurs SET id = id-1 WHERE id > ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // On reinitialise l'auto_increment
            // (cela permettra au prochaine occurrence de suivre la plus grande valeur du champ auto incremente enregistre dans la table)
            ps = conn.prepareStatement("ALTER TABLE Fournisseurs AUTO_INCREMENT = 0");
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erreur Clients - SuppressionParId.");
            System.out.println(e);            
            return false;
        }
    }
    
    /**
     * Modifie un fournisseur
     * @param f : Un JavaBean Fournisseur
     * @return true si la modification est Ok
     */
    @Override
    public boolean modifier(Fournisseur f) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Fournisseurs SET id = ?, numero = ?, nom = ?, email = ?, adresse = ? WHERE id = ?");
            ps.setInt(1, f.getId());
            ps.setInt(2, f.getNumero());
            ps.setString(3, f.getNom());
            ps.setString(4, f.getEmail());
            ps.setString(5, f.getAdresse());
            ps.setString(6, f.getId().toString());            
            ps.executeUpdate();
            return true;
	}
        catch (Exception e) {
            System.out.println("Erreur Fournisseur - Modifier.");
            System.out.println(e);            
            return false;
        }
    }
    
     /**
     * Creer un fournisseur
     * @param f : un JavaBean Fournisseur
     * @return true si la création est Ok
     */
    @Override
    public boolean creer(Fournisseur f) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Fournisseurs VALUES (DEFAULT, ?, ?, ?, ?)");
            ps.setInt(1, f.getNumero());
            ps.setString(2, f.getNom());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getAdresse());
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erreur Fournisseurs - Creer.");
            System.out.println(e);            
            return false;
        }
    }
    
    /**
     * Controle si les parametres sont correct
     * @param f : JavaBean Fournisseur
     * @return true si la verification est Ok
     */
    @Override
    public String validation(Fournisseur f) {
        if (f.getNumero() <= 0) return "Le numero doit etre un entier positif.";
        if ("".equals(f.getNom())) return "Le nom ne doit pas etre vide.";
        if (f.getEmail() == null || "".equals(f.getEmail())) return "L'email ne doit pas etre vide.";
        if (!f.getEmail().contains("@")) return "L'email doit contenir un @.";
        if ("".equals(f.getAdresse())) return "L'adresse ne doit pas etre vide.";
        if (chercheNumeroExistant(f.getId(), f.getNumero()) != null) return "Le numero de l'article existe deja.";
        return "";
    }
}