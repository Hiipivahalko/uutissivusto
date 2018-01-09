
package uutissivusto.wad.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    
    
    private String headline;
    
    private String lead;
    
    private String text;
    
    private LocalDateTime date;
    
    private int views = 0;
    
    private String writer;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private FileObject fileObject;
    
    @ManyToOne
    private Category category;
    
    /*@ManyToOne
    private Writer writer;
    */
    
}
