package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="accounts")
@Data
public class Account {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(name="number")
   private Integer number;
   @Column(name="cash")
   private Double cash;

   public Account(Integer id, Integer number, Double cash) {
      this.id = id;
      this.number = number;
      this.cash = cash;
   }
}
// todo hibernate spring jparepository(добавить методы в карс)