package corriges.exercices.JDBC.Solution1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import corriges.exercices.JDBC.Solution1.modele.Article;
import corriges.exercices.JDBC.Solution1.interfaces.IArticleDAO;

/**
 * Gestion de la Table Articles dans la Base de donnees MySQL
 */
public class ArticleDAO implements IArticleDAO {
    /**
     * Lire tout le contenu de la table
     * @return une liste de JavaBean Article
     */
    @Override
    public List<Article> lire() {
        List<Article> la = new ArrayList<>();

        try (Connection conn = Connexion.connecterAvecBase(true);
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Articles")) {
            
            while (rs.next()) {
                la.add(new Article(
                        rs.getInt("id"), rs.getInt("numero"),
                        rs.getString("fc"), rs.getString("nom"),
                        rs.getString("description")));
            }
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - Lire.");
            System.out.println(e);            
        }
        
        return la;
    }
    
    /**
     * Lire un enregistrement de la table Articles par son id
     * @param id : numero de l'enregistrement dans la table
     * @return un JavaBean Utilisateur s'il existe
     */
    @Override
    public Article lireParId(Integer id) {
        Article a = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Articles WHERE id = ?");
            ps.setInt(1, id);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                a = new Article(
                    rs.getInt("id"), rs.getInt("numero"),
                    rs.getString("fc"), rs.getString("nom"),
                    rs.getString("description"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Article - LireParId.");
            System.out.println(e);            
        }
        
        return a;
    }
    
    /**
     * Lire un enregistrement de la table Articles par son numero
     * @param numero : numero de l'article
     * @return un JavaBean Article s'il existe
     */
    @Override
    public Article lireParNumero(Integer numero) {
        Article a = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Articles WHERE numero = ?");
            ps.setInt(1, numero);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                a = new Article(
                    rs.getInt("id"), rs.getInt("numero"),
                    rs.getString("fc"), rs.getString("nom"),
                    rs.getString("description"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - LireParNumero.");
            System.out.println(e);            
        }
        
        return a;
    }
    
    /**
     * Lire un enregistrement de la table Articles par son numero
     * @param id : id de l'enregistrement 
     * @param numero : numero de l'article
     * @return un JavaBean Article s'il existe
     */
    @Override
    public Article chercheNumeroExistant(Integer id, Integer numero) {
        Article a = null;
        
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Articles WHERE id <> ? AND numero = ?");
            ps.setInt(1, id);
            ps.setInt(2, numero);
	    
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                a = new Article(
                    rs.getInt("id"), rs.getInt("numero"),
                    rs.getString("fc"), rs.getString("nom"),
                    rs.getString("description"));
            }
	    
            rs.close();
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - chercheNumeroExistant.");
            System.out.println(e);            
        }
        
        return a;
    }
    
    /**
     * Suppression d'un article
     * @param id : numero de l'enregistrement dans la table
     * @return true si la suppression est Ok
     */
    @Override
    public boolean suppressionParId(Integer id) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            // On supprime une occurrence
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Articles WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // On "comble le trou" s'il y en a un
            // (ce qui n'est pas toujours souhaitable)
            ps = conn.prepareStatement("UPDATE Articles SET id = id-1 WHERE id > ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // On reinitialise l'auto_increment
            // (cela permettra au prochaine occurrence de suivre la plus grande valeur du champ auto incremente enregistre dans la table)
            ps = conn.prepareStatement("ALTER TABLE Articles AUTO_INCREMENT = 0");
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - SuppressionParId.");
            System.out.println(e);            
            return false;
        }
    }
    
    /**
     * Modifie un article
     * @param a : Un JavaBean Article
     * @return true si la modification est Ok
     */
    @Override
    public boolean modifier(Article a) {
        try (Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Articles SET id = ?, numero = ?, fc = ?, nom = ?, description = ? WHERE id = ?");
            ps.setInt(1, a.getId());
            ps.setInt(2, a.getNumero());
            ps.setString(3, a.getFC());
            ps.setString(4, a.getNom());
            ps.setString(5, a.getDescription());
            ps.setString(6, a.getId().toString());
            ps.executeUpdate();
            return true;
	}
        catch (Exception e) {
            System.out.println("Erreur Articles - Modifier.");
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * Creer un article
     * @param a : un JavaBean Article
     * @return true si la creation est Ok
     */
    @Override
    public boolean creer(Article a) {
        try(Connection conn = Connexion.connecterAvecBase(true)) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Articles VALUES (DEFAULT, ?, ?, ?, ?)");
            ps.setInt(1, a.getNumero());
            ps.setString(2, a.getFC());
            ps.setString(3, a.getNom());
            ps.setString(4, a.getDescription());
  
            ps.executeUpdate();
            return true;
        }
        catch (Exception e) {
            System.out.println("Erreur Articles - Creer.");
            System.out.println(e);            
            return false;
        }
    }
    
    /**
     * Controle si les parametres sont correct
     * @param a : JavaBean Article
     * @return true si la verification est Ok
     */
    @Override
    public String validation(Article a) {
        if (!"F".equals(a.getFC().toUpperCase()) && !"C".equals(a.getFC().toUpperCase())) return "Le champs FC dans etre F ou C.";
        if (a.getNumero() <= 0) return "Le numero doit etre un entier positif.";
        if ("".equals(a.getNom())) return "Le nom ne doit pas etre vide.";
        if ("".equals(a.getDescription())) return "La description ne doit pas etre vide.";
        if (chercheNumeroExistant(a.getId(), a.getNumero()) != null) return "Le numero de l'article existe deja.";
        return "";
    }
}