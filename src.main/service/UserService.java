package service;

import entity.User;
import exception.UserNotFoundException;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class UserService {

    /**
     * Retrieve a user by ID from array of Users
     * @param id
     * @param users
     * @return
     * @throws UserNotFoundException if user not found, could differ based on the business logic
     */
    public User findUserById(long id, User[] users) throws UserNotFoundException {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        throw new UserNotFoundException("User not found with ID: " + id);
    }


    /**
     * Determine how many days it has been since a user was created
     * @param user
     * @return the number of days elapsed since the creation of a user
     */
    public int daysSinceCreation(User user){
        if(user == null)
            throw new IllegalArgumentException("User cannot be null");
        ZonedDateTime dateOfCreation = user.getDateOfCreation();
        ZonedDateTime now = ZonedDateTime.now();
        long daysElapsed = ChronoUnit.DAYS.between(dateOfCreation, now);
        return (int)daysElapsed;
    }

    /**
     *
     * @param users
     */
    public void sortUsersByLastName(User[] users){
        //streams or Arrays.sort method could be used if admitted
        //Arrays.sort(users, Comparator.comparing(u -> u.getLastName()));
        //users = Arrays.stream(users)
        //                .sorted(Comparator.comparing(u -> u.getLastName()))
        //                .toArray(User[]::new);
        //Implementation of the quicksort algorithm to sort the users
        quickSortUsersByLastName(users, 0, users.length-1);
    }

    /**
     * Sort the users using the divide and conquer approach of the quicksort algorithm
     * @param users
     * @param startIndex start index of the subarray
     * @param endIndex end index of the subarray
     */
    public void quickSortUsersByLastName(User[] users, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        int pivotIndex = partition(users, startIndex, endIndex);
        quickSortUsersByLastName(users, startIndex, pivotIndex-1);
        quickSortUsersByLastName(users, pivotIndex+1, endIndex);
    }

    /**
     * partition the array
     * @param users
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int partition(User[] users, int startIndex, int endIndex) {
        //consider the last element as pivot element
        String lastName = users[endIndex].getLastName();
        int i = startIndex - 1;
        for(int j = startIndex; j < endIndex; j++){
            //swap current user if lexicographically lower than current pivot
            if(lastName.compareTo(users[j].getLastName()) > 0){
                i++;
                User tmpUser = users[i];
                users[i] = users[j];
                users[j] = tmpUser;
            }
        }
        // position the pivot element at the correct position
        User tmpUser = users[i + 1];
        users[i + 1] = users[endIndex];
        users[endIndex] = tmpUser;
        return i + 1;
    }

}
