package tweeter.User;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface UserDao {
    List<User> readAll();

    boolean addNewUser(MultiValueMap<String, String> formData);

    boolean isUserExist(String email);

    boolean isValidUser(MultiValueMap<String, String> formData);

    public String getLoggedInUserEmail();

    User getUserProfile(String email);
}
