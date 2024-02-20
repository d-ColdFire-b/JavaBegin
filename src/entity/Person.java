package entity;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

public class Person {
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    String gender, name, surname;

    public Person(int id) {
        this.id = id;
    }


    public void setGender(String gender) {
        if (gender.equals("Male") || gender.equals("Female")) {
            this.gender = gender;

        } else {
            this.gender = "Retard";
        }
    }


    public static void printDetails(Person person) {

        System.out.println("Id = " + person.id);
        System.out.println("Name = " + person.name);
        System.out.println("Surname = " + person.surname);
        System.out.println("Gender = " + person.gender);


    }


}
