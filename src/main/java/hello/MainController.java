package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class MainController {
    @Autowired // This means to get the bean called userRepository
               // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @PostMapping(path = "/users")
    public @ResponseBody User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping(path = "/users")
    public @ResponseBody User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(path = "/users")
    public @ResponseBody boolean deleteUser(@RequestAttribute int id) {
        try {
            userRepository.delete(new User(id));
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
