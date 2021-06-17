package hiber.dao;


import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      List<User> usersList = session.createQuery("from User where car = (from Car where model = :model and series = :series)", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();

      if (!usersList.isEmpty()) {
         return usersList.get(0);
      }
      return null;
   }
}


// https://russianblogs.com/article/7109261529/
// http://java-online.ru/hibernate-hql.xhtml