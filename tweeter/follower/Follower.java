package tweeter.follower;

import tweeter.User.User;

import javax.persistence.*;

@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String follower_email;

    @ManyToOne
    @JoinColumn(name = "follow_to_email")
    private User followTo;

    public Follower(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFollowTo() {
        return followTo;
    }

    public void setFollowTo(User followTo) {
        this.followTo = followTo;
    }

    public String getFollower_email() {
        return follower_email;
    }

    public void setFollower_email(String follower_email) {
        this.follower_email = follower_email;
    }

}
