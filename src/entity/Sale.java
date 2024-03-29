package entity;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Sale implements Iterable<Product> {

    int id;
    double amount;
    Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Sale() {
    }

    public Sale(int id) {
        this.id = id;
    }

    Map<Product, Double> products = new TreeMap<>();

    @Override
    public Iterator<Product> iterator() {
        return products.keySet().iterator();
    }
}
