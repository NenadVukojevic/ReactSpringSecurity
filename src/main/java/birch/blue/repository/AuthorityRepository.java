package birch.blue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import birch.blue.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	Authority findByRoleName(String roleName);

}
