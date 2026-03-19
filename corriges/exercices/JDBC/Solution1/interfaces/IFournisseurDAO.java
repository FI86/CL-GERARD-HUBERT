package corriges.exercices.JDBC.Solution1.interfaces;

import corriges.exercices.JDBC.Solution1.modele.Fournisseur;
import java.util.List;

/**
 * Interface Fournisseur DAO
 */
public interface IFournisseurDAO {
    public List<Fournisseur> lire();
    public Fournisseur lireParId(Integer id);
    public Fournisseur lireParNumero(Integer numero);
    public Fournisseur chercheNumeroExistant(Integer id, Integer numero);
    public boolean suppressionParId(Integer id);
    public boolean modifier(Fournisseur a);
    public boolean creer(Fournisseur a);
    public String validation(Fournisseur a);
}
