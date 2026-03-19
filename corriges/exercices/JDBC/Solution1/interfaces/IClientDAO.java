package corriges.exercices.JDBC.Solution1.interfaces;

import corriges.exercices.JDBC.Solution1.modele.Client;
import java.util.List;

/**
 *
 * Interface Client DAO
 */
public interface IClientDAO {
    public List<Client> lire();
    public Client lireParId(Integer id);
    public Client lireParNumero(Integer numero);
    public Client chercheNumeroExistant(Integer id, Integer numero);
    public boolean suppressionParId(Integer id);
    public boolean modifier(Client a);
    public boolean creer(Client a);
    public String validation(Client a);
}
