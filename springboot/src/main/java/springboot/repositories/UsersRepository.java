package springboot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
