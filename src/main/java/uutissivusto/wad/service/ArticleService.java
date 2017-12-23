
package uutissivusto.wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.PostObject;
import uutissivusto.wad.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private  ArticleRepository articleRepository;
    
    
    public void createArticle(PostObject postObject) {
        Article article = new Article();
        article.setHeadline(postObject.getHeadline());
        article.setLead(postObject.getLead());
        article.setText(postObject.getText());
        
        articleRepository.save(article);
    }
    
    public void delete(Long id) {
        Article article = articleRepository.getOne(id);
        articleRepository.delete(article);
    }
    
    public void edit(Long id, String headline, String lead, String text) {
        Article article = articleRepository.getOne(id);
        
        article.setHeadline(headline);
        article.setLead(lead);
        article.setText(text);
        articleRepository.save(article);
    }
}
