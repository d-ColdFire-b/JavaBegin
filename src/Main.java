import entity.Person;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {



        String saveFileDirPath = "saves";
        File saveFileDir = new File(saveFileDirPath);

        Person person = new Person(1);


        if (!saveFileDir.exists()) {
            saveFileDir.mkdir();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            String answer;
            while (true) {
                System.out.println("Load data from file? (Yes / No)");
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("YES") || answer.equalsIgnoreCase("NO")) {
                    break;
                }
                System.out.println("Unreadable answer, try again");
            }
            String fileName;
//          Load from file
            if (answer.equalsIgnoreCase("YES")) {

                fileName = isFileNameOk(scanner);

                File saveFile = new File(saveFileDirPath + "/" + fileName + ".txt");

                person.printDetails(person.loadFrom(saveFile));


//          Do NOT load from file
            } else {

                fileName = isFileNameOk(scanner);

                File saveFile = new File(saveFileDirPath + "/" + fileName + ".txt");

                person.saveTo(saveFile, getInfo(scanner));


            }

        }


    }


    public static String isFileNameOk(Scanner scanner) {

        String fileName;
        while (true) {
            System.out.println("Enter file name (W/out extension):");
            fileName = scanner.nextLine();
            if (!fileName.trim().isEmpty()) {
                break;
            }
            System.out.println("Empty or White space-only name entered, try again!");
        }
        return fileName;
    }

    public static Person getInfo (Scanner scanner){

        boolean loop;
        String toCheck;
        while (true){
            loop = false;
            System.out.println("Enter person's ID");
            toCheck = scanner.nextLine();
            if (toCheck.trim().isEmpty()){
                System.out.println("Empty ID, try again");
                continue;
            }
            for (int i = 0; i < toCheck.trim().toCharArray().length ; i++) {
                if (!Character.isDigit(toCheck.trim().charAt(i))) {
                    System.out.println("It's not digit you bastard!");
                    loop = true;
                }
            }
            if (!loop) {
                break;
            }
        }


        Person person = new Person(Integer.parseInt(toCheck));

        System.out.println("Enter Name:");
        person.setName(scanner.nextLine());
        System.out.println("Enter Surname:");
        person.setSurname(scanner.nextLine());
        System.out.println("Enter Gender:");
        person.setGender(scanner.nextLine());


        return person;
    }

}