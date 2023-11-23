package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select c from User c where c.account.id= :id")
    User findByAccountId(int id);
}
