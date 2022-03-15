package tweeter.follower;

import tweeter.User.User;

import java.util.List;

public interface FollowerDao {
    List<Follower> getAll();
    List<User> getFollowers(String email);
    boolean follow(String FollowTo);
    boolean unFollow(String email);

}
