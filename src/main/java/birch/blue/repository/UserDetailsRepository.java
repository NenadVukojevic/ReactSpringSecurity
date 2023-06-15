package birch.blue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import birch.blue.model.User;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Integer> {
	User findByUserName(String userName);
}
