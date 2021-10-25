package com.github.brunoqkz.pismo.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity(name = "Accounts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Account_ID")
  private Long id;

  @Column(name = "Document_Number")
  private String document;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Account account = (Account) o;
    return id != null && Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
