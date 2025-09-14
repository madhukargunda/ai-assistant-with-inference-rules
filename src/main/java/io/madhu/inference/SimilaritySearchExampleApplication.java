package io.madhu.inference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication(exclude = {
		org.springframework.ai.model.ollama.autoconfigure.OllamaEmbeddingAutoConfiguration.class
})
@EnableAspectJAutoProxy
public class SimilaritySearchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimilaritySearchExampleApplication.class, args);
	}
}
