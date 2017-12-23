
package uutissivusto.wad.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FileObject extends AbstractPersistable<Long> {

    @OneToOne(mappedBy = "fileObject")
    private Article news;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    private String name;
    private int contentLenght;
    private String contentType;
    private int size;
    
}
