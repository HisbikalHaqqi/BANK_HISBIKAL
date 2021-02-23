package mii.bniwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class BniWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BniWebServiceApplication.class, args);
	}
        
        @GetMapping("")
        public String home(){
            return "redirect:api/auth";
        }
}
