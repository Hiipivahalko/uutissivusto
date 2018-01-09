
package uutissivusto.wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutissivusto.wad.domain.FileObject;


public interface FileObjectRepository extends JpaRepository<FileObject, Long>{
    
    
}
