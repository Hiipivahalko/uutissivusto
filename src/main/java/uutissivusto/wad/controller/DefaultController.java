package uutissivusto.wad.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uutissivusto.wad.domain.Account;
import uutissivusto.wad.repository.AccountRepository;

@Controller
public class DefaultController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        if (accountRepository.findByUsername("admin") != null) {
            return;
        }

        Account user = new Account();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));

        accountRepository.save(user);

    }

    @GetMapping("/")
    public String defaultmethod() {

        return "redirect:/frontpage";
    }
    
    @GetMapping("/getIn")
    public String login() {
        return "redirect:/frontpage";
    }
    
    /*@GetMapping("/logout")
    public String logout() {
        return "redirect:/frontpage";
    }*/

}
