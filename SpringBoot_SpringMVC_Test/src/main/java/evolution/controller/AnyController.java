package evolution.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evolution.controller.dto.AnyDto;

@RestController
public class AnyController {
	@GetMapping("/get")
	public AnyDto get() {
		AnyDto anyDto = new AnyDto();
		anyDto.setName("Chen");
		anyDto.setAge(27);
		return anyDto;
	}
	
	@PostMapping("/post")
	public AnyDto post(@RequestBody AnyDto anyDto) {
		return anyDto;
	}
	
	@RequestMapping("/http")// Maps all HTTP operations by default.
	public void http() {
		
	}
}
