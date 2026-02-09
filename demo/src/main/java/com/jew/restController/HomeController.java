package com.jew.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jew.service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @Autowired
	HomeService homeService;

	@GetMapping("/getQueryTest") 
	public ResponseEntity<Integer> javalec() throws Exception {
		return ResponseEntity.ok().body(homeService.javaLec()); 
	}

}
