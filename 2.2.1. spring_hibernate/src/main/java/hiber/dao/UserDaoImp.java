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

      String query = "FROM User WHERE users_id in " +
                     "(select id FROM Car WHERE model = :model and series = :series)";

      userslist = sessionFactory.getCurrentSession().createQuery(query)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();

      System.out.println("Результат вывода пользователя по модели и серии автомобиля: ");

      userslist.forEach(System.out::println);
      System.out.println();

      return userslist;
   }
}
