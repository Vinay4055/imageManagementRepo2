package com.nagarro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

//@NamedQueries({ @NamedQuery(name = "findByName", query = "from User user where user.name = :name") })
@Getter
@Setter
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	@Column(name = "userId")
	private int userId;
	@Column(name = "name", unique = true, nullable = false)
	@NotNull(message = "Name is mandatory")
	String name;
	@NotNull(message = "Name is mandatory")
	@Column(name = "password")
	String password;
	

}
