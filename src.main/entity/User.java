package entity;

import java.time.ZonedDateTime;

public class User {

    private static long id;
    private String firstName;
    private String lastName;
    //For the sake of multi zoned users, the ZonedDateTime id used to keep user's local date, local time and time zone
    private ZonedDateTime dateOfCreation;

    public User(String firstName, String lastName) {
        // This could be generated using the java.util library UUID (Universal Unique ID)
        this.id = generateNextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfCreation = ZonedDateTime.now();
    }


    private static long nextId = 1;
    private static long generateNextId(){
        return nextId++;
    }

    public static long getId() {
        return id;
    }

    //Just for test purpose
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ZonedDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(ZonedDateTime dateOfCreation) {
        this.dateOfCreation =  dateOfCreation;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
