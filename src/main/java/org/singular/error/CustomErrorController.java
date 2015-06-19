package org.singular.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomErrorController {

	@RequestMapping("/generalError")
	public ResponseEntity<String> generalError() {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
