package entity;

public class Person {

    int id,age;
    String gender;

    public Person(int id){
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        if (gender == "Male" || gender == "Female"){

            this.gender = gender;

        }
        else {
            this.gender = "Retard";
        }
    }
}
