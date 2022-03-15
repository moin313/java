package tweeter.Tweets;

import tweeter.User.User;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tweets_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tweet;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    public Post(){}

    public Post(int id, String tweet, User user) {
        this.id = id;
        this.tweet = tweet;
        this.localDateTime = LocalDateTime.now();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
