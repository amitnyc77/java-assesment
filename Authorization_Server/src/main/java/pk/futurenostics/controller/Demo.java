package pk.futurenostics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class Demo {
	
	@GetMapping("/home")
	public String sayHello(){
		return ResponseEntity.ok("Welcom to the home page").getBody();
	}

}
