package org.snakeinc.webapi.entity;

import jakarta.persistence.Id;
import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.sql.Time;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Player {
    @Getter
    @Id
    private final int id;
    private static AtomicInteger  nextId = new AtomicInteger();
    @Getter
    @NotBlank(message="name is mandatory")
    private String name;
    @Getter
    @Min(value=14, message="age sould be superior to 13")
    private int age;
    @Getter
    private Category category;
    @Getter
    private final Time createdAt;

    public Player(){
        this.id = nextId.getAndIncrement();
        this.name = "";
        this.age = 0;
        this.category = null;
        this.createdAt = Time.valueOf(LocalTime.now());
    }
    public Player(String name, int age) {
        this.name = name;
        this.id = nextId.getAndIncrement();
        this.age = age;
        if(this.age < 50) {
            this.category = Category.JUNIOR;
        }
        else{
            this.category = Category.SENIOR;
        }
        this.createdAt = Time.valueOf(LocalTime.now());
    }
}
