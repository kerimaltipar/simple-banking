package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode(of = {"approvalCode", "date", "amount"})
public abstract class Transaction {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String approvalCode;

    private Date date;

    private double amount;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "account_account_number", nullable = false)
    private BankAccount account;

    protected Transaction(double amount, BankAccount account) {
        this.amount = amount;
        this.account = account;
        this.date = new Date();
    }

    public abstract void post() throws InsufficientBalanceException;
}
