package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select e from Account e where e.username = :username")
    Account findAccountByUsername(String username);
}
