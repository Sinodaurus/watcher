package org.singular.error;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomErrorController {

	@RequestMapping("/generalError")
	public String generalError() {
		return "Invalid request...";
	}
}
