package com.gerenciador.tarefas.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gerenciador.tarefas.domain.task.Task;
import com.gerenciador.tarefas.domain.task.TaskRepository;
import com.gerenciador.tarefas.domain.user.AppUser;
import com.gerenciador.tarefas.domain.user.AppUserRepository;

@Component
public class InsertTestData {

	private TaskRepository taskRepository;
	private AppUserRepository appUserRepository;

	@Autowired
	public InsertTestData(TaskRepository taskRepository, AppUserRepository appUserRepository) {
		this.taskRepository = taskRepository;
		this.appUserRepository = appUserRepository;
	}

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		AppUser appUser1 = new AppUser("thiago", encoder.encode("abc"), "Thiago Alves");
		appUserRepository.save(appUser1);

		AppUser appUser2 = new AppUser("heitor", encoder.encode("cba"), "Heitor Corrêa");
		appUserRepository.save(appUser2);

		LocalDate baseDate = LocalDate.parse("2025-02-01");

		for (int i = 1; i <= 5; i++) {
			Task task = new Task(String.format("Tarefa do %s #%d", appUser1.getUsername(), i), baseDate.plusDays(i),
					false, appUser1);
			taskRepository.save(task);
		}

		for (int i = 1; i <= 5; i++) {
			Task task = new Task(String.format("Tarefa do %s #%d", appUser2.getUsername(), i), baseDate.plusDays(i),
					false, appUser2);
			taskRepository.save(task);
		}
	}

}
