package com.example.contact_manager_api.model.entity;

import com.example.contact_manager_api.model.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Contact_Table")
@Data
public class Contact extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name= "First Name")
    public String firstName;

    @Column(name = "Last Name")
    public String lastName;

    @Column
    public String phone;

    @Column
    public String email;

    @Column
    public Boolean isActive;

    @Column
    public String category;



}
