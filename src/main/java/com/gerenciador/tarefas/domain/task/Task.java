package com.gerenciador.tarefas.domain.task;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.tarefas.domain.user.AppUser;

@Entity
@EntityListeners(TaskListener.class)
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotEmpty(message = "A descrição da tarefa e obrigatória")
	@Length(min = 3, max = 60, message = "O tamanho da tarefa e inválido")
	private String description;

	@NotNull(message = "A data da tarefa e obrigatória.")
	@FutureOrPresent(message = "A data da tarefa nao pode ser inferior a data atual")
	private LocalDate whenToDo;

	private Boolean done = false;

	@ManyToOne
	@JoinColumn(name = "app_user_id")
	@NotNull(message = "O usuario da tarefa e obrigatorio")
	@JsonIgnore
	private AppUser appUser;

	public Task() {
		super();
	}

	public Task(String description, LocalDate whenToDo, Boolean done, AppUser appUser) {
		super();
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
		this.appUser = appUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getWhenToDo() {
		return whenToDo;
	}

	public void setWhenToDo(LocalDate whenToDo) {
		this.whenToDo = whenToDo;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Integer getId() {
		return id;
	}
}
