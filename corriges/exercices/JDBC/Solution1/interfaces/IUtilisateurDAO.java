package corriges.exercices.JDBC.Solution1.interfaces;

import corriges.exercices.JDBC.Solution1.modele.Utilisateur;
import java.util.List;

/**
 * Interface Utilisateur DAO
 */
public interface IUtilisateurDAO {
    public List<Utilisateur> lire();
    public Utilisateur lireParId(Integer id);
    public Utilisateur lireParNumero(Integer numero);
    public Utilisateur chercheNumeroExistant(Integer id, Integer numero);
    public boolean suppressionParId(Integer id);
    public boolean modifier(Utilisateur a);
    public boolean creer(Utilisateur a);
    public String validation(Utilisateur a);
}
