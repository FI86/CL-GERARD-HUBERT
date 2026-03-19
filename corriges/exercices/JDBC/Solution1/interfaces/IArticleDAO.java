package corriges.exercices.JDBC.Solution1.interfaces;

import corriges.exercices.JDBC.Solution1.modele.Article;
import java.util.List;

/**
 * Interface Article DAO
 */
public interface IArticleDAO {
    public List<Article> lire();
    public Article lireParId(Integer id);
    public Article lireParNumero(Integer numero);
    public Article chercheNumeroExistant(Integer id, Integer numero);
    public boolean suppressionParId(Integer id);
    public boolean modifier(Article a);
    public boolean creer(Article a);
    public String validation(Article a);
}
