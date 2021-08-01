package com.gerenciador.tarefas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.gerenciador.tarefas.domain.task.Task;

@SpringBootApplication
public class GerenciadorDeTarefasApplication implements RepositoryRestConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(GerenciadorDeTarefasApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeTarefasApplication.class, args);
		logger.info("Gerenciador de Tarefas Iniciado!");
	}

	// Poderiamos criar uma nova classe anotar com @Configure e implementar a
	// configuracao la.
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Task.class);

		// CORS Config
		config.getCorsRegistry().addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");

		logger.info("Repository CORS Setup... OK!");
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	// Configurar Eventos de Validacao (Momento em que a validacao vai ser
	// executada)
	// As menssagens de erro de validacao ja vao voltar tratadas em um array de
	// errors(sem stacktrace, etc..)
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		Validator validator = validator();
		validatingListener.addValidator("beforeCreate", validator);
		validatingListener.addValidator("beforeSave", validator);

		logger.info("Configure Validator... OK!");
	}

}
