package uutissivusto.wad.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uutissivusto.wad.domain.Article;
import uutissivusto.wad.repository.ArticleRepository;
import uutissivusto.wad.service.ArticleService;
import uutissivusto.wad.service.CategoryService;
import uutissivusto.wad.service.FileObjectService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private FileObjectService fileObjectService;
    
    private boolean logget = false;

    //Show frontpage
    @GetMapping("/frontpage")
    public String frontpage(Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == null) {
            logget = false;
        } else {
            logget = true;
        }
        
        model.addAttribute("auth", auth);
        
        List<Article> weeklyNewest = articleService.mostViewed(5);
        model.addAttribute("viewed", weeklyNewest);

        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "date");
        model.addAttribute("newest", articleRepository.findAll(pageable));

        model.addAttribute("categories", categoryService.findAll());

        return "frontpage";
    }

    @GetMapping("/article/new")
    public String newArticle(Model model) {

        model.addAttribute("categories", categoryService.findAll());

        return "createArticle";
    }

    @PostMapping("/article/new")
    public String createArticle(@RequestParam String headline, @RequestParam String lead, @RequestParam String text,
            @RequestParam Long categoryId, @RequestParam String writer, @RequestParam("file") MultipartFile file) throws IOException {

        if (file.getContentType().equals("image/png")) {
            Article article = new Article();
            article = articleService.createArticle(headline, lead, text, writer);
            categoryService.addCategoryToArticle(article.getId(), categoryId);
            fileObjectService.save(file, article);
        }

        //writerService.addWriter(article, writer);
        return "redirect:/frontpage";
    }

    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            articleService.addView(id);
        }

        model.addAttribute("article", articleService.getOne(id));

        return "article";
    }

    @GetMapping("/article/edit/{id}")
    public String editArticle(Model model, @PathVariable Long id) {

        Article article = articleService.getOne(id);
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.findAll());

        return "edit";
    }

    @PostMapping("/article/edit/{id}")
    public String postEdit(@PathVariable Long id, @RequestParam String headline, @RequestParam String lead, @RequestParam String text,
            @RequestParam String writer, @RequestParam("file") MultipartFile file, @RequestParam Long categoryId) throws IOException {

        Article article = articleService.getOne(id);
        articleService.edit(article, headline, lead, text, writer);
        if (file != null && file.getContentType().equals("image/png")) {
            fileObjectService.updateImage(file, id);
        }

        categoryService.editCategory(id, categoryId);

        return "redirect:/article/" + id;
    }

    @DeleteMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {

        //ategoryService.deleteArticleFromCategory(id);
        //fileObjectService.deleteFileObject(articleService.getOne(id).getFileObject());
        articleService.deleteArticle(id);

        return "redirect:/frontpage";
    }
    
    @GetMapping("/kokeilu")
    public String kokeilu() {
        return "kokeilu";
    }
    
    
    
}
