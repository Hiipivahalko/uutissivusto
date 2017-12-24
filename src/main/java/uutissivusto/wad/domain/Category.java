
package uutissivusto.wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends AbstractPersistable<Long> {

    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;
    
    private String name;
}
