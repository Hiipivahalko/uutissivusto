package uutissivusto.wad.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.PostObject;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.repository.CategoryRepository;
import uutissivusto.wad.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    //Show frontpage
    @GetMapping("/frontpage")
    public String frontpage(Model model) {
        
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        
        return "frontpage";
    }

    //add new article
    @GetMapping("/addArticle")
    public String addArticle(@ModelAttribute PostObject postObject) {

        return "addArticle";
    }

    //post method for adding
    @PostMapping("/addArticle")
    public String postArticle(@Valid @ModelAttribute PostObject postObject, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "frontpage";
        }

        articleService.createArticle(postObject);

        return "redirect:/frontpage";
    }

    //show article
    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable Long id) {

        model.addAttribute("article", articleRepository.getOne(id));

        return "article";
    }

    //get change to edit article
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {

        model.addAttribute("article", articleRepository.getOne(id));

        return "edit";
    }

    //do the editing
    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, @RequestParam String headline,
            @RequestParam String lead, @RequestParam String text) {

        articleService.edit(id, headline, lead, text);

        return "redirect:/article/" + id;
    }

    //delete article
    @DeleteMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Long id) {

        articleService.delete(id);

        return "redirect:/frontpage";
    }
    
    
    
    

}
