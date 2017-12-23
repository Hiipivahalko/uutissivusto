
package uutissivusto.wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.PostObject;
import uutissivusto.wad.repository.ArticleRepository;

@Controller
public class ArticleController {
    
    @Autowired
    private ArticleRepository ArticleRepository;
    
    @GetMapping("/frontpage")
    public String frontpage(Model model) {
        
        model.addAttribute("articles", ArticleRepository.findAll());
        
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
        
        Article article = new Article();
        article.setHeadline(postObject.getHeadline());
        article.setLead(postObject.getLead());
        article.setText(postObject.getText());
        
        ArticleRepository.save(article);
        
        
        return "redirect:/frontpage";
    }
    
}
