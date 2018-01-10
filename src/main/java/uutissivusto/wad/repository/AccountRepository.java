
package uutissivusto.wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uutissivusto.wad.domain.Account;


public interface AccountRepository extends JpaRepository<Account, Long>{
    public Account findByUsername(String username);
}
