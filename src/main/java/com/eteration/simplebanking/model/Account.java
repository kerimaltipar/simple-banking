package com.eteration.simplebanking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    private String owner;

    @Id
    @GeneratedValue(generator = "uuid")
    private String accountNumber;

    private double balance;

//    private Transaction transactions;

}
