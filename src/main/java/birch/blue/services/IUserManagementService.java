package birch.blue.services;

import java.util.List;

import birch.blue.model.Authority;
import birch.blue.model.User;

public interface IUserManagementService {

	List<Authority> getListOfAuthorities();

	List<User> getListOfUsers();

	void updateUser(User user);

	void deleteUser(Integer id);

	List<String> getUsersListOfAuthorities(String username);
}
