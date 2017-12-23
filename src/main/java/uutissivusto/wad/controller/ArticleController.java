
package uutissivusto.wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.PostObject;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.service.ArticleService;

@Controller
public class ArticleController {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private ArticleService articleService;
    
    @GetMapping("/frontpage")
    public String frontpage(Model model) {
        
        model.addAttribute("articles", articleRepository.findAll());
        
        return "frontpage";
    }
    
    @GetMapping("/addArticle")
    public String addArticle(@ModelAttribute PostObject postObject) {
        
        return "addArticle";
    }
    
    @PostMapping("/addArticle")
    public String postArticle(@Valid @ModelAttribute PostObject postObject, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "frontpage";
        }
        
        articleService.createArticle(postObject);
        
        return "redirect:/frontpage";
    }
    
    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable Long id) {
        
        model.addAttribute("article", articleRepository.getOne(id));
        
        return "article";
    }
    
}
