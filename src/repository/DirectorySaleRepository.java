package repository;

import entity.Person;
import entity.Sale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectorySaleRepository implements Repository<Sale> {

    File dir = new File("sales");


    public DirectorySaleRepository() {
    }

    public DirectorySaleRepository(File dir) {
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
    public void save(Sale sale) throws IOException {
        File dirId = new File(dir.getPath() + "/" + sale.getId());
        try (FileOutputStream stream = new FileOutputStream(dirId)) {
            try (PrintWriter writer = new PrintWriter(stream)) {
                writer.println(sale.getId());
                writer.println(sale.getAmount());

                Person person = sale.getPerson();
                writer.println(person.getId());
                writer.println(person.getName());
                writer.println(person.getSurname());
                writer.println(person.getGender());
            }
        }
    }

    @Override
    public Sale load(int id) throws IOException {
        File dirId = new File(dir.getPath() + "/" + id);
        try {
            try (FileInputStream stream = new FileInputStream(dirId)) {
                try (Scanner scanner = new Scanner(stream)) {
                    Sale sale = new Sale(scanner.nextInt());
                    scanner.nextLine();
                    sale.setAmount(scanner.nextDouble());
                    scanner.nextLine();
                    int personId = scanner.nextInt();
                    Repository<Person> personRepository = new DirectoryPersonRepository();
                    try {
                        sale.setPerson(personRepository.load(personId));
                    } catch (Exception e) {
                        System.out.println("Not saved in Persons =(");
                        Person person = new Person(personId);
                        scanner.nextLine();
                        person.setName(scanner.nextLine());
                        person.setSurname(scanner.nextLine());
                        person.setGender(scanner.nextLine());
                        sale.setPerson(person);
                        personRepository.save(person); // Not anymore >=)
                    }
                    return sale;
                }
            }
        } catch (Exception e) {
            System.out.println("No saves in repo for person Id - " + id);
        }

        return null;
    }

    @Override
    public List<Sale> load(List<Integer> ids) throws IOException {


        List<Sale> saleList = new ArrayList<>();

        for (Integer id : ids) {
            saleList.add(load(id));
        }

        return saleList;

    }
}
