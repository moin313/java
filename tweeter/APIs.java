package tweeter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tweeter.Tweets.Post;
import tweeter.Tweets.PostDao;
import tweeter.User.User;
import tweeter.User.UserDao;
import tweeter.follower.FollowerDao;

import java.util.*;

@Controller
public class APIs {
    @Autowired
    UserDao userDao;
    @Autowired
    PostDao postDao;
    @Autowired
    FollowerDao followerDao;


    /*--------------------------------------------
    GENERAL UTILITY METHOD
     ---------------------------------------------*/
    private ModelAndView errorMessageModelAndView(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("message", message);
        return modelAndView;
    }


    /*-------------------------------------------
      ADD NEW USER FORM FOR TAKING INPUT FROM USER
      AND ADD NEW USER WILL GET USER INPUT FROM THE
      FORM AND REGISTER THAT USER BY STORING DETAILS
      IN DATABASE.
    ---------------------------------------------- */
    @GetMapping("/addNewUserForm")
    public ModelAndView addUserForm() {
        ModelAndView modelAndView = new ModelAndView("addNewUser");
        return modelAndView;
    }

    @PostMapping(value = "/addNewUser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addNewUser(@RequestBody MultiValueMap<String, String> formData) {
        if (userDao.isUserExist(formData.get("email").get(0))) {
            System.out.println("User Already exist");
        } else {
            System.out.println("user registered successfully");
            userDao.addNewUser(formData);
        }

        ModelAndView modelAndView = new ModelAndView("addNewUser");
        return modelAndView;
    }


    /*-------------------------------------------
      LOGIN FORM TAKES INPUT FROM USER AND SEND
      IT TO VALIDATE METHOD.
    ---------------------------------------------- */
    @GetMapping("/tweeterLoginForm")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("tweeterLogin");
        return modelAndView;
    }

    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView validate(@RequestBody MultiValueMap<String, String> formData) {
        if (!userDao.isValidUser(formData)) {
            return errorMessageModelAndView("Wrong credentials");
        }
        ModelAndView modelAndView = getUserDetails();
        return modelAndView;
    }


    /*----------------------------------------------------------
        CREATE POST WILL FETCH CONTENT FROM USER AND POST TO
        addPost METHOD.
    ----------------------------------------------------------*/
    @GetMapping("/createPost")
    public ModelAndView createPost() {
        ModelAndView modelAndView = new ModelAndView("createPost");
        return modelAndView;
    }

    @PostMapping(value = "/addPost", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addPost(@RequestBody MultiValueMap<String, String> formData) {
        String email = userDao.getLoggedInUserEmail();
        if (email == null) {
            return errorMessageModelAndView("Please login first");
        }
        System.out.println("add post 94 " + email);
        postDao.create(formData, email);
        ModelAndView modelAndView = getUserDetails();
        return modelAndView;
    }

    /*----------------------------------------------------------
      DISPLAY ALL USER
    ----------------------------------------------------------*/
    public List<User> getAllUsers() {
        List<User> users = userDao.readAll();
        if (users == null) {
            return null;
        }
        return users;
    }


    /*--------------------------------------------------------------------------
    EDIT AN EXISTING POST
    --------------------------------------------------------------------------*/
    @PutMapping("/editPost")
    @ResponseBody
    private ResponseEntity<String> editPost(@RequestBody Post post) {
        ResponseEntity<String> responseEntity = null;
        if (postDao.update(post)) {
            responseEntity = new ResponseEntity<>("Post updated successfully", HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity<>("No post found to update ", HttpStatus.OK);
        }
        return responseEntity;
    }

    /*--------------------------------------------------------------------------
    DELETE AN EXISTING POST
    --------------------------------------------------------------------------*/
    @DeleteMapping("/deletePost")
    @ResponseBody
    private ResponseEntity<String> deletePost(@RequestParam int postid) {
        ResponseEntity<String> responseEntity = null;
        if (postDao.delete(postid)) {
            responseEntity = new ResponseEntity<>("Post deleted successfully", HttpStatus.BAD_REQUEST);
        } else {
            responseEntity = new ResponseEntity<>("No post to delete ", HttpStatus.OK);
        }
        return responseEntity;
    }


    /*--------------------------------------------------------------------------
    DISPLAY USER DETAILS
    * ---------------------------------------------------------------------------*/
    @GetMapping("/displayUserDetails")
    public ModelAndView getUserDetails() {
        ModelAndView modelAndView = new ModelAndView("users");

        String email = userDao.getLoggedInUserEmail();
        User user = userDao.getUserProfile(email);
        modelAndView.getModel().put("Name", user.getName());
        modelAndView.getModel().put("Email", user.getEmail());


        modelAndView.getModel().put("Follower", user.getFollowers().size());

        List<Post> posts = postDao.readById(email);
        modelAndView.getModel().put("posts", posts);
        modelAndView.getModel().put("users", getAllUsers());
        return modelAndView;
    }


    @PostMapping(value = "/addFollower", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView follow(@RequestBody MultiValueMap<String, String> formData) {
        String followTo = formData.get("email").get(0);
        followerDao.follow(followTo);
        return getUserDetails();
    }

    @PostMapping(value = "/unFollow", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView unFollow(@RequestBody MultiValueMap<String, String> formData) {
        String unFollowTo = formData.get("email").get(0);
        System.out.println("unfollow to "+ unFollowTo);
        followerDao.unFollow(unFollowTo);
        return getUserDetails();
    }
}
