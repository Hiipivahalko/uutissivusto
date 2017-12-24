
package uutissivusto.wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uutissivusto.wad.service.CategoryService;

@Controller
public class CategoryController {
    
    
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/addCategoryToFrontpage")
    public String addCategory(@RequestParam String name) {
        
        categoryService.addCategoryToFrontpage(name);
        
        return "redirect:/frontpage";
    }
    
    @GetMapping("/category/{id}")
    public String category(Model model, @PathVariable Long id) {
        
        
        
        return "category";
    }
    
}
