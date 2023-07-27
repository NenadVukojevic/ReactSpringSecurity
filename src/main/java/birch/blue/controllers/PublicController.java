package birch.blue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birch.blue.services.IUserManagementService;

@CrossOrigin("*") // (origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/public")
public class PublicController {
	@Autowired
	IUserManagementService userManagementService;

	@GetMapping(path = "/usermanagement/user/roles/{username}")
	List<String> getUserRoles(@PathVariable String username) {
		return userManagementService.getUsersListOfAuthorities(username);
	}

}
