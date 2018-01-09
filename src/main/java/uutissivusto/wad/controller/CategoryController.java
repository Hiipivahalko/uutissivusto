
package uutissivusto.wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutissivusto.wad.domain.Category;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.repository.CategoryRepository;
import uutissivusto.wad.service.CategoryService;

@Controller
public class CategoryController {
    
    
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/addCategoryToFrontpage")
    public String addCategoryToFrontpage(@RequestParam String name) {
        
        categoryService.createCategory(name);
        
        return "redirect:/frontpage";
    }
    
    @GetMapping("/category/{id}")
    public String category(Model model, @PathVariable Long id) {
        
        model.addAttribute("category", categoryService.getOneCategory(id));
        
        return "category";
    }
    
}
