package webshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import webshop.service.InitDbService;

@RequiredArgsConstructor
@SpringBootApplication
public class WebshopApplication implements CommandLineRunner {
	
	private final InitDbService initDbService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		initDbService.initDb();
	}

}
