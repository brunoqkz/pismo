package com.github.brunoqkz.pismo.model;


import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity(name = "Transactions")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Transaction_ID")
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "Account_ID")
  private Account account;

  @OneToOne(optional = false)
  @JoinColumn(name = "OperationType_ID")
  private OperationType operationType;

  @Column(name = "Amount")
  private Double amount;

  @Column(name = "EventDate")
  private final LocalDateTime eventDate = LocalDateTime.now();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Transaction that = (Transaction) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
