
package uutissivusto.wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uutissivusto.wad.domain.Category;
import uutissivusto.wad.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public void addCategoryToFrontpage(String name) {
        
        if (name != null) {
            if (categoryRepository.findByName(name) == null) {
                Category category = new Category();
                
                category.setName(name);
                categoryRepository.save(category);
            }
        }
        
    }
    
    public Category getOneCategory(Long id) {
        
        return categoryRepository.getOne(id);
    }
    
}
