package org.example.calorie_tracker.model.dish.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "calorie")
    private int calorie;
    @Column(name = "protein")
    private int protein;
    @Column(name = "fat")
    private int fat;
    @Column(name = "carbohydrate")
    private int carbohydrate;
    @Column(name = "date")
    private Date date;
    @Column(name = "user_email")
    private String userEmail;

    public Dish() {
    }

    public Dish(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.calorie = builder.calorie;
        this.protein = builder.protein;
        this.fat = builder.fat;
        this.carbohydrate = builder.carbohydrate;
        this.date = builder.date;
        this.userEmail = builder.userEmail;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String name;
        private int calorie;
        private int protein;
        private int fat;
        private int carbohydrate;
        private Date date;
        private String userEmail;

        private Builder() {
        }

        public Builder setId(long id){
            this.id = id;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setCalorie(int calorie){
            this.calorie = calorie;
            return this;
        }
        public Builder setProtein(int protein){
            this.protein = protein;
            return this;
        }
        public Builder setFat(int fat){
            this.fat = fat;
            return this;
        }
        public Builder setCarbohydrate(int carbohydrate){
            this.carbohydrate = carbohydrate;
            return this;
        }
        public Builder setDate(Date date){
            this.date = date;
            return this;
        }
        public Builder setUserEmail(String email){
            this.userEmail = email;
            return this;
        }
        public Dish build(){
            return new Dish(this);
        }
    }
}
