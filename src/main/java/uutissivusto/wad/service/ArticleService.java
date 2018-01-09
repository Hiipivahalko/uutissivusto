package uutissivusto.wad.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.Category;
import uutissivusto.wad.domain.FileObject;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.repository.CategoryRepository;
import uutissivusto.wad.repository.FileObjectRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileObjectRepository fileObjectRepository;

    public Article getOne(Long id) {
        return articleRepository.getOne(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article createArticle(String headline, String lead, String text, String writer) {
        Article article = new Article();
        article.setHeadline(headline);
        article.setLead(lead);
        article.setText(text);
        article.setWriter(writer);
        article.setDate(LocalDateTime.now());
        articleRepository.save(article);

        return articleRepository.findByHeadline(headline);
    }

    public void edit(Article article, String headline, String lead, String text, String writer) {
        article.setHeadline(headline);
        article.setLead(lead);
        article.setText(text);
        article.setWriter(writer);
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {

        Article article = articleRepository.getOne(id);
        Category category = article.getCategory();
        category.getArticles().remove(article);
        categoryRepository.save(category);
        FileObject first = fileObjectRepository.getOne(article.getFileObject().getId() + 1);
        FileObject second = fileObjectRepository.getOne(article.getFileObject().getId());
        articleRepository.delete(article);

    }
}
