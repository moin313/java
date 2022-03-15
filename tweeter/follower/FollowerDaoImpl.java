package tweeter.follower;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tweeter.Tweets.Post;
import tweeter.User.User;
import tweeter.User.UserDao;

import javax.persistence.Query;
import java.util.List;

@Component
public class FollowerDaoImpl implements FollowerDao{
    @Autowired
    Session session;
    @Autowired
    UserDao userDao;

    @Override
    public List<Follower> getAll() {
        try{
            return session.createQuery("from Follower", Follower.class).getResultList();
        } catch (Exception e){
            System.out.println("EXCEPTION IN READING ALL FOLLOWER");
        }
        return null;
    }

    @Override
    public List<User> getFollowers(String email) {
        try{
            Query query = session.createQuery("from Follower where follow_to_email=:fToEmail");
            query.setParameter("f_email", email);
            return query.getResultList();
        }catch (Exception e){
            System.out.println("EXCEPTION IN GETTING FOLLOWER OF A USER");
        }
        return null;
    }

    @Override
    public boolean follow(String followTo) {
        String loggedInUserEmail = userDao.getLoggedInUserEmail();
        Follower follower = new Follower();

        try{
            boolean check = getAll().stream().anyMatch(followers -> followers.getFollower_email().equals(loggedInUserEmail) && followers.getFollowTo().getEmail().equals(followTo));
            if (check == true)
                return false;
            if(loggedInUserEmail.equals(followTo)) {
                System.out.println("User can't follow self");
                return false;
            }

            follower.setFollowTo(userDao.getUserProfile(followTo));
            follower.setFollower_email(loggedInUserEmail);
            session.beginTransaction();
            session.persist(follower);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println();
        }
        return false;
    }

    @Override
    public boolean unFollow(String email) {
        String loggedInUserEmail = userDao.getLoggedInUserEmail();
        System.out.println("loggedInUserEmail    "+loggedInUserEmail+"   unfollow to -> "+email);
        try{
            Query query = session.createQuery("from Follower where follower_email=:f_email and follow_to_email=:fToEmail");
            query.setParameter("f_email", loggedInUserEmail);
            query.setParameter("fToEmail", email);
            Follower follower = (Follower) query.getSingleResult();
            System.out.println("unfollow in fri "+follower);

            session.beginTransaction();
            session.remove(follower);
            session.getTransaction().commit();

            return true;
        } catch (Exception e){
            System.out.println("EXCEPTION IN UN FOLLOW "+e);
        }
        return false;
    }
}
