package birch.blue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birch.blue.model.Authority;
import birch.blue.model.User;
import birch.blue.services.IUserManagementService;

@CrossOrigin("*") // (origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

	@Autowired
	IUserManagementService userManagementService;
	
	@GetMapping(path="/version")
	public String version()
	{
		return "v 1.0";
	}
	
	@GetMapping(path="/hello")
	public String helloAdmin()
	{
		return "this is message for admin *****";
	}


	@GetMapping(path="/usermanagement/roles")
	List<Authority> getListOfRoles()
	{
		return userManagementService.getListOfAuthorities();
	}
	
	@GetMapping(path="/usermanagement/users")
	List<User> getListOfUsers()
	{
		return userManagementService.getListOfUsers();
	}
	
	@PostMapping(path="/usermanagement/user/save")
	void updateUser(@RequestBody User user)
	{
		userManagementService.updateUser(user);
		
	}
	
	@GetMapping(path="/usermanagement/user/delete/{id}")
	void deleteUser(@PathVariable Integer id)
	{
		userManagementService.deleteUser(id);
	}
	

}

