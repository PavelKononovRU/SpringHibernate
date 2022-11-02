package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
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
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @SuppressWarnings("all")
   @Override
   public List<User> getUser(String model, int series) {
      List<User> userslist = null;

      String query = "select * from users u where u.users_id in (select id from car c where c.model = :modelPram and c.series = :carSeriesParam)";

      userslist = sessionFactory.getCurrentSession().createSQLQuery(query)
              .setParameter("modelPram", model)
              .setParameter("carSeriesParam", series)
              .addEntity(User.class)
              .getResultList();
      System.out.println("Должно быть начало вывода");

      for (User user : userslist) {
         System.out.println(user.toString());
      }

      System.out.println("А здесь конец");

      //userslist.forEach(System.out::println);

      return userslist;
   }
}
