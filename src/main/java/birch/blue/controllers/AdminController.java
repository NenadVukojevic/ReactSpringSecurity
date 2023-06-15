package birch.blue.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") // (origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/admin")
public class AdminController {

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


}

