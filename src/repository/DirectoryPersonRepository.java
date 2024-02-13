package repository;

import entity.Person;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.SortedMap;

public class DirectoryPersonRepository implements PersonRepository {


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
        person.saveTo(dirId, person);

    }

    @Override
    public Person load(int id) throws IOException {
        Person person = new Person(id);
        File dirId = new File(dir.getPath() + "/" + id);
        try {
            person.loadFrom(dirId);
        } catch (Exception e){
            System.out.println("No saves in repo for person Id - " + id);
        }

        return person;
    }
}
