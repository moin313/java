package tweeter.Tweets;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import tweeter.User.User;
import tweeter.User.UserDao;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostDaoImpl implements PostDao{
    @Autowired
    UserDao userDao;
    @Autowired
    Session session;

    @Override
    public List<Post> readAll() {
        try{
            return session.createQuery("from post", Post.class).getResultList();
        } catch(Exception e){
            System.out.println("EXCEPTION IN readAll POST "+e);
        }
        return null;
    }

    @Override
    public boolean create(MultiValueMap<String, String> formData, String email) {
        User user = userDao.getUserProfile(email);

        Post post = new Post();
        String tweet = formData.get("tweet").get(0);
        post.setTweet(tweet);
        post.setLocalDateTime(LocalDateTime.now());
        post.setUser(user);
//        System.out.println("USER " + user+" POST"+post);
        try{
            session.beginTransaction();
            session.persist(post);
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("EXCEPTION IN POSTING TWEET "+e);
        }
        return false;
    }

    @Override
    public List<Post> readById(String email) {
        try {
            String hql = "from Post where user_email=:email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            return query.getResultList();
        } catch (Exception e){
            System.out.println("EXCEPTION IN readById POSTDAOIMPL "+e);
        }
        return null;
    }

    Post getPost(int id){
        try {
            String hql = "from Post where id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            Post post = (Post) query.getSingleResult();
            System.out.println(post.getId());
            return post;
        } catch (Exception e){
            System.out.println("EXCEPTION IN GETTING POST "+e);
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try{
            Post post = getPost(id);
            session.beginTransaction();
            session.remove(post);
            session.getTransaction().commit();

            return true;
        } catch (Exception e){
            System.out.println("EXCEPTION IN DELETING POST "+e);
            return false;
        }
    }




    @Override
    public boolean update(Post post) {
        try{
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Post SET tweet=:tweet, localDateTime=:localDateTime  WHERE id=:id");
            query.setParameter("id",post.getId());
            query.setParameter("localDateTime",LocalDateTime.now());
            query.setParameter("tweet",post.getTweet());
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println("EXCEPTION IN UPDATING POST "+e);
            return false;
        }
    }
}