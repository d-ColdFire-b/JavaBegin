package repository;

import entity.Person;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class DirectoryPersonRepository implements PersonRepository {


    File dir;

    public DirectoryPersonRepository(File dir) {
        this.dir = dir;

        if (!dir.isDirectory()) {
            try {
                dir.mkdir();
            }
            catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

    }

    @Override
    public void save(Person person) throws IOException {
        File dirId = new File(dir.getPath() + person.getId());
        person.saveTo(dirId, person);

    }

    @Override
    public Person load(int id) throws IOException {
        Person person = new Person(id);
        File dirId = new File(dir.getPath() + id);
        person.loadFrom(dirId);
        return person;
    }
}
