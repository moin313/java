package day15;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Root {

    private Map<String,String> userlist =new HashMap<>();
    private Map<String,List<Tweet>> tweets = new HashMap<>();

    @PostMapping("/tweet")
    public ResponseEntity<String>tweet(@RequestBody Tweet tweet){
        System.out.println(tweet.toString());
        ResponseEntity<String> responseEntity=null;
        String email = tweet.getEmail();
        String name=tweet.getName();
        if(tweets.containsKey(email)&tweets.containsKey(name)) {
            tweets.get(email).add(tweet);
            tweets.get(name).add(tweet);
            responseEntity=new ResponseEntity<>("Tweet uploaded successfully",HttpStatus.OK);
        }else {
            responseEntity=new ResponseEntity<>("User does not exist please create account first ",HttpStatus.OK);
        }
        return responseEntity;
    }

    //GET all the tweets of the user
    @GetMapping("/alltweets")
    public ResponseEntity<List<Tweet>> getTweet(@RequestParam String email){
        ResponseEntity<List<Tweet>> responseEntity=null;
        if(userlist.containsKey(email)){
            responseEntity =new ResponseEntity<List<Tweet>>((List<Tweet>) tweets.get(email),HttpStatus.OK);
        }
        else {
            responseEntity=new ResponseEntity<List<Tweet>>((List<Tweet>) tweets.get(email),HttpStatus.OK);
        }
        return responseEntity;
    }


    private boolean containsInvalidChars(String name) {
        for (int i=0; name.length() > i; i++) {
            if((name.charAt(i) > 64 && name.charAt(i) < 91) || (name.charAt(i) > 96 && name.charAt(i) < 123) || name.charAt(i) == 32)
                continue;
            return false;
        }
        return true;
    }


    @PostMapping("/create")
    ResponseEntity<String> getTweets(@RequestBody Map<String,String> requestBodyMap){
        ResponseEntity<String> responseEntity=null;
        String email = requestBodyMap.get("email");
        String name = requestBodyMap.get("name");
        String pass = requestBodyMap.get("pass");
        if(!containsInvalidChars(name) && pass != null){
            responseEntity=new ResponseEntity<>("Name is not valid ",HttpStatus.OK);
            return responseEntity;
        }
        if(userlist.containsKey(email))
            responseEntity=new ResponseEntity<>("User already exist",HttpStatus.OK);
        else {
            userlist.put(email,name);
            List<Tweet> list=new ArrayList<>();
            tweets.put(email,list);
            responseEntity=new ResponseEntity<>("User Created Successfully ",HttpStatus.OK);
        }

        return responseEntity;
    }

    //    User can fetch account details --> GET
    @GetMapping("/fetch")
    ResponseEntity<String> getAccDetails(){
        ResponseEntity<String> responseEntity=null;
        if(userlist.size()==0)
            responseEntity=new ResponseEntity<>("NO user found",HttpStatus.NOT_FOUND);
        else
            responseEntity=new ResponseEntity<>(userlist.toString(),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getDetails")
    private ResponseEntity<String> getAccDetails(@RequestParam String email) {
        ResponseEntity<String> responseEntity=null;
        if(userlist.containsKey(email))
            responseEntity=new ResponseEntity<>(userlist.get(email),HttpStatus.OK);
        else
            responseEntity=new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    //    User can delete account
    @DeleteMapping("/delete")
    ResponseEntity<String > delete(@RequestBody String email){
        ResponseEntity<String > responseEntity=null;
        if(userlist.containsKey(email)){
            userlist.remove(email);
            responseEntity=new ResponseEntity<>(email+" deleted successfully",HttpStatus.OK);
        }
        else
            responseEntity=new ResponseEntity<>("user does not exists",HttpStatus.OK);
        return responseEntity;
    }

    //    User can update account details
    @PutMapping("/update")
    ResponseEntity<String> updateRecord(@RequestBody Map<String, String> requestBodyMap){
        ResponseEntity<String > responseEntity=null;
        String email = requestBodyMap.get("email");
        String name = requestBodyMap.get("name");
        String pass = requestBodyMap.get("pass");
        Boolean flag = containsInvalidChars(name);

        if(pass != null && userlist.containsKey(email)){
            if (flag == false) {
                responseEntity = new ResponseEntity<>("invalid characters", HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            String currentName= userlist.get(email);
            if(currentName == name){
                responseEntity = new ResponseEntity<>("No change is requires",HttpStatus.OK);
                return responseEntity;
            }
            else
            {
                userlist.put(email,name);
                responseEntity=new ResponseEntity<>("Successfully Updated",HttpStatus.ACCEPTED);
                return responseEntity;
            }

        }
        else {
            responseEntity=new ResponseEntity<>("No such user",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
