package uutissivusto.wad.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.Category;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public Category getOneCategory(Long id) {

        return categoryRepository.getOne(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void addCategoryToArticle(Long articleId, Long categoryId) {
        Article article = articleRepository.getOne(articleId);
        Category category = categoryRepository.getOne(categoryId);
        article.setCategory(category);
        category.getArticles().add(article);
        categoryRepository.save(category);
        articleRepository.save(article);

    }

    public void createCategory(String name) {

        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    
    public void editCategory(Long id, Long categoryId) {
        Article article = articleRepository.getOne(id);
        Category category = categoryRepository.getOne(categoryId);
        Category old = article.getCategory();
        if (categoryId == old.getId()) {
            return;
        } else {
            old.getArticles().remove(article);
            categoryRepository.save(old);
            article.setCategory(category);
            articleRepository.save(article);
            category.getArticles().add(article);
            categoryRepository.save(category);
            
        }
        
    }
    
    public void deleteArticleFromCategory(Long id) {
        Article article = articleRepository.getOne(id);
        Category category = article.getCategory();
        category.getArticles().remove(article);
        categoryRepository.save(category);
        
    }
    

}
