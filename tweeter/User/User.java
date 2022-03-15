package tweeter.User;


import tweeter.Tweets.Post;
import tweeter.follower.Follower;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tweeter_User_details")
public class User {
    @Id
    private String email;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "followTo", cascade = CascadeType.REMOVE)
    private Set<Follower> followers = new HashSet<>();

    public User(){}

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follower> followers) {
        this.followers = followers;
    }

    public String getEmail() {
    return email;
}

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
