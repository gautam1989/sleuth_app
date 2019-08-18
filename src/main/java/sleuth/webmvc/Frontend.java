package sleuth.webmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@RestController
@CrossOrigin
public class Frontend {

  @Autowired RestTemplate restTemplate;

   Logger logger = LoggerFactory.getLogger(this.getClass());

  String backendBaseUrl = System.getProperty("spring.example.backendBaseUrl", "http://localhost:9000");

  @RequestMapping("/") public String callBackend() {
    logger.info("frontend");
    return restTemplate.getForObject(backendBaseUrl + "/api", String.class);
  }

  @Bean RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(Frontend.class,
        "--spring.application.name=frontend",
        "--server.port=8081"
    );
  }
}
