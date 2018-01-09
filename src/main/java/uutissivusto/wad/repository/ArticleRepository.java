
package uutissivusto.wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutissivusto.wad.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    
    public Article findByHeadline(String headline);
    
}
