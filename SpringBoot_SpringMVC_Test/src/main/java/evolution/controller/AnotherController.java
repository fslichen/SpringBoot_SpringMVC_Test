package evolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import evolution.controller.dto.AnyDto;
import evolution.service.AnyService;

@RestController
public class AnotherController {
	@Autowired
	private AnyService anyService;
	
	@GetMapping("/call/service")
	public AnyDto callService() {
		AnyDto anyDto = new AnyDto();
		anyDto.setName(anyService.anyMethod());
		anyDto.setAge(27);
		return anyDto;
	}
}
