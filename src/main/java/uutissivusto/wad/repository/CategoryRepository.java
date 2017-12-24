
package uutissivusto.wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutissivusto.wad.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    public Category findByName(String name);
    
}
