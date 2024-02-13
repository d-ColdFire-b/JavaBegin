package repository;

import entity.Person;

import java.io.IOException;

public interface PersonRepository {

    public void save(Person person) throws IOException;


    public Person load(int id) throws IOException;


}
