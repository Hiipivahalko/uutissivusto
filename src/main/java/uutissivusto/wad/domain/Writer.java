
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
public class Writer extends AbstractPersistable<Long> {

    @ManyToMany(mappedBy = "writers")
    private List<Article> newss;
    
    private String name;
    
}
