import entity.Person;
import repository.DirectoryPersonRepository;
import repository.PersonRepository;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {


        String saveFileDirPath = "saves";
        File saveFileDir = new File(saveFileDirPath);
        PersonRepository personRepository = new DirectoryPersonRepository(saveFileDir);

        Person person = new Person(1);


        Person.printDetails(personRepository.load(1));


//      Palindrome task
        if (false) {
            String str1 = "asadfasfsda";
            String str2 = "aasfcvxvcfsaa";
            String str3 = "aasfcvvcfsaa";

            System.out.println(isPalindrom(str1));
            System.out.println(isPalindrom(str2));
            System.out.println(isPalindrom(str3));
        }

//      countSubstring task
        if (false){
            String mainOne = "Is it hard too pee it from here?";
            String findOne = "it";

            System.out.println(countSubString(mainOne, findOne));
        }
/*



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

                fileName = isFileNameOk(scanner, false);


                File saveFile = new File(fileName);
                if (!saveFile.isDirectory()) {
                    person.printDetails(person.loadFrom(saveFile));
                } else {
                    fileName = fileName + "/" + isFileNameOk(scanner, true);
                    saveFile = new File(fileName);
                    person.printDetails(person.loadFrom(saveFile));
                }


//          Do NOT load from file
            } else {

                fileName = isFileNameOk(scanner, false);


                File saveFile = new File(fileName);

                if (!saveFile.isDirectory()) {
                    person.saveTo(saveFile, getInfo(scanner));
                } else {
                    fileName = fileName + "/" + isFileNameOk(scanner, true);
                    saveFile = new File(fileName);
                    person.saveTo(saveFile, getInfo(scanner));
                }




            }

        }
*/

    }


    public static String isFileNameOk(Scanner scanner, boolean file) {

        String fileName;
        while (true) {
            if (file) {
                System.out.println("Enter file file name:");
            } else {
                System.out.println("Enter file path:");
            }
            fileName = scanner.nextLine();
            if (!fileName.trim().isEmpty()) {
                break;
            }
            System.out.println("Empty or White space-only name entered, try again!");
        }
        return fileName;
    }

    public static Person getInfo(Scanner scanner) {

        boolean loop;
        String toCheck;
        while (true) {
            loop = false;
            System.out.println("Enter person's ID");
            toCheck = scanner.nextLine();
            if (toCheck.trim().isEmpty()) {
                System.out.println("Empty ID, try again");
                continue;
            }
            for (int i = 0; i < toCheck.trim().toCharArray().length; i++) {
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

    public static boolean isPalindrom(String str){
        char arr[] = str.toCharArray();
        for (int i = 0; i < str.toCharArray().length/2; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static int countSubString(String x, String y){
        int count = 0;
        char xArr[] = x.toCharArray();
        char yArr[] = y.toCharArray();

        for (int i = 0; i < xArr.length; i++) {
            if (xArr[i] == yArr[0]){
                count++;
                for (int j = 0; j < yArr.length; j++) {
                    if (yArr[j] != xArr[j+i]){
                        count--;
                        break;
                    }
                }

            }
        }



        return count;
    }


}