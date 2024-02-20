package repository;

import entity.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectoryPersonRepository implements Repository<Person> {


    File dir;

    public DirectoryPersonRepository(File dir) {
        this.dir = dir;
        if (!dir.isDirectory()) {
            try {
                if (!dir.mkdir()) {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

    }

    @Override
    public void save(Person person) throws IOException {
        File dirId = new File(dir.getPath() + "/" + person.getId());
        saveTo(dirId, person);

    }


    public static void saveTo(File file, Person person) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            try (PrintWriter writer = new PrintWriter(stream)) {
                writer.println(person.getId());
                writer.println(person.getName());
                writer.println(person.getSurname());
                writer.println(person.getGender());
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

    @Override
    public Person load(int id) throws IOException {

        File dirId = new File(dir.getPath() + "/" + id);
        try {
            return loadFrom(dirId);
        } catch (Exception e) {
            System.out.println("No saves in repo for person Id - " + id);
        }

        return null;
    }

    @Override
    public List<Person> load(List<Integer> ids) throws IOException {

        List<Person> personList = new ArrayList<>();

        for (Integer id : ids) {
            personList.add(load(id));
        }

        return personList;
    }
}
