package repository;

import entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectoryProductRepository implements Repository<Product>{

    File dir = new File("Products");


    public  DirectoryProductRepository(){}
    public DirectoryProductRepository(File dir){
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
    public void save(Product product) throws IOException {

        File dirId = new File(dir.getPath() + "/" + product.getId());
        try (FileOutputStream stream = new FileOutputStream(dirId)) {
            try (PrintWriter writer = new PrintWriter(stream)) {
                writer.println(product.getId());
                writer.println(product.getName());
                writer.println(product.getPrice());
            }
        }
    }

    @Override
    public Product load(int id) throws IOException {
        File dirId = new File(dir.getPath() + "/" + id);
        try {
            try (FileInputStream stream = new FileInputStream(dirId)) {
                try (Scanner scanner = new Scanner(stream)) {
                    Product product = new Product(scanner.nextInt());
                    scanner.nextLine();
                    product.setName(scanner.nextLine());
                    product.setPrice(scanner.nextDouble());
                    return product;
                }
            }
        } catch (Exception e) {
            System.out.println("No saves in repo for product Id - " + id);
        }

        return null;
    }

    @Override
    public List<Product> load(List<Integer> ids) throws IOException {
        List<Product> list = new ArrayList<>();

        for (Integer id : ids) {
            list.add(load(id));
        }

        return list;
    }
}
