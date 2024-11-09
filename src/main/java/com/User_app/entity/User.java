package com.User_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="name", length=25, nullable=false)
    private String username;
    
    @Column(name="email", length=50, nullable=false)
    private String userEmail;
    
    @Column(name="password", nullable=false)
    private String userPassword;
    
}
