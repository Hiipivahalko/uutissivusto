
package uutissivusto.wad.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Article extends AbstractPersistable<Long> {
    
    @NotEmpty
    private String headline;
    @NotEmpty
    private String lead;
    @NotEmpty
    private String text;
    
    private LocalDateTime date;
    
    private int views = 0;
    
    @OneToOne
    private FileObject fileObject;
    
    @ManyToMany
    private List<Writer> writers;
    
    @ManyToMany
    private List<Category> categories;
    
    
}
