package entity;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.Scanner;

public class Person {

    int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
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

    public static void saveTo(File file, Person person) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            try (PrintWriter writer = new PrintWriter(stream)) {
                writer.println(person.id);
                writer.println(person.name);
                writer.println(person.surname);
                writer.println(person.gender);
            }


        }
    }


    public static Person loadFrom(File file) throws IOException {


        try (FileInputStream stream = new FileInputStream(file)) {
            try (Scanner scanner = new Scanner(stream)) {
                Person person = new Person(scanner.nextInt());
                scanner.nextLine();
                person.setName(scanner.nextLine());
                person.setSurname(scanner.nextLine());
                person.setGender(scanner.nextLine());
                return person;
            }
        }
    }


    public void printDetails(Person person) {

        System.out.println("Id = " + person.id);
        System.out.println("Name = " + person.name);
        System.out.println("Surname = " + person.surname);
        System.out.println("Gender = " + person.gender);


    }


}
