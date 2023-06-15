package birch.blue.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") // (origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/user")
public class UserController {
	@GetMapping(path="/hello")
	public String helloUser()
	{
		return "this is message for user !!!!!";
	}
}
