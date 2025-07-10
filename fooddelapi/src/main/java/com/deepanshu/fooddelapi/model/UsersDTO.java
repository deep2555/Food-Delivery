package com.deepanshu.fooddelapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*this is the user module 
 * where all the getter and setter are handle by lombook
 * taken ID auto generated
 * taking the user table name as Users*/
 


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class UsersDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String UserMail;
	
	@Column(nullable = false)
	private String userPassword;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	
	
}
