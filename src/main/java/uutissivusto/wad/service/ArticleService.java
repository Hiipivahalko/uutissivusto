
package uutissivusto.wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uutissivusto.wad.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private  ArticleRepository articleRepository;
    
    
    
}
