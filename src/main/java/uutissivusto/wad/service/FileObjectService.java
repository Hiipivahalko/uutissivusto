
package uutissivusto.wad.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.domain.FileObject;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.repository.FileObjectRepository;


@Service
public class FileObjectService {
    
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    @Autowired
    private ArticleRepository articleRepository;
    
    public byte[] getFileObject(Long id) {
        
        if (fileObjectRepository.existsById(id)) {
            return fileObjectRepository.getOne(id).getContent();
        }
        return null;
    }
    
    public void save(MultipartFile file, Article article) throws IOException {
        
        if (file.getContentType().equals("image/png")) {
            FileObject fo = new FileObject();
            fo.setContent(file.getBytes());
            fo.setName(file.getName());
            fo.setContentType(file.getContentType());
            fo.setSize(file.getSize());
            fo.setNews(article);
            article.setFileObject(fo);
            articleRepository.save(article);
            fileObjectRepository.save(fo);
        }
    }
    
    public void updateImage(MultipartFile file, Long id) throws IOException {
        Article article = articleRepository.getOne(id);
        
        if (file.getContentType().equals("image/png")) {
            
            //deleteFileObject(article.getFileObject());
            
            FileObject fo = article.getFileObject();
            fo.setContent(file.getBytes());
            fo.setName(file.getName());
            fo.setContentType(file.getContentType());
            fo.setSize(file.getSize());
            fo.setNews(article);
            article.setFileObject(fo);
            articleRepository.save(article);
            fileObjectRepository.save(fo);
        }
    }
    
    public void deleteFileObject(FileObject fo) {
        fileObjectRepository.delete(fo);
    }
    
    
}
