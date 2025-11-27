import entity.User;
import exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService userService = new UserService();
    private static User[] users;

    @BeforeAll
    public static void setup(){
        users = new User[]{
                new User("AfirstName", "ALastName"),
                new User("AalastName", "AcLastName"),
                new User("rlastName", "ZLastName"),
                new User("AblastName", "AcLastName")
        };
    }

    @Test
    public void testFindUserById_returnsUserObject() {
        User user = new User("FirstName", "LastName");
        assertNotNull(user);
        assertInstanceOf(User.class, user);
        assertEquals("FirstName", user.getFirstName());
        assertEquals("LastName", user.getLastName());
    }

    @Test
    public void testFindUserByLastName_returnsUserObject() {
        try {
            users[0].setId(1);
            User user = userService.findUserById(1, users);
            assertEquals("AfirstName", user.getFirstName());
            assertEquals("ALastName", user.getLastName());

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRetrieveUserById_withNonExistingID_throwsUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> userService.findUserById(10, users));
    }

    @Test
    public void testDaysSinceCreation_withValidUser_returnsValidResult(){
        User user = new User("FirstName", "LastName");
        user.setDateOfCreation(ZonedDateTime.of(LocalDate.of(2025, 11, 20), LocalTime.now(), ZoneId.of("America/Montreal")));
        int elapsedDays = userService.daysSinceCreation(user);
        assertEquals(6, elapsedDays);

    }

    @Test
    public void testDaysSinceCreation_withNullUser_ThowsException() {
        assertThrows(IllegalArgumentException.class, () -> userService.daysSinceCreation(null));
    }

    @Test
    public void testSortUsersByLastName_returnsSortedArrayOfUsers(){
        userService.sortUsersByLastName(users);
        assertEquals("ALastName", users[0].getLastName());
        assertEquals("ZLastName", users[3].getLastName());
    }

}
