package org.example.calorie_tracker.model.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "weight")
    private int weight;
    @Column(name = "height")
    private int height;
    @Column(name = "gender")
    private char gender;
    @Column(name = "goal_id")
    private long goalId;

    public User(Builder builder) {
        this.gender = builder.gender;
        this.height = builder.height;
        this.weight = builder.weight;
        this.age = builder.age;
        this.name = builder.name;
        this.email = builder.email;
        this.goalId = builder.goalId;
    }

    public User() {
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String email;
        private String name;
        private int age;
        private int weight;
        private int height;
        private char gender;
        private long goalId;

        public Builder() {
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setWeight(int weight){
            this.weight = weight;
            return this;
        }
        public Builder setHeight(int height){
            this.height = height;
            return this;
        }
        public Builder setGender(char gender){
            this.gender = gender;
            return this;
        }
        public Builder setGoalId(long goalId){
            this.goalId = goalId;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }
}
