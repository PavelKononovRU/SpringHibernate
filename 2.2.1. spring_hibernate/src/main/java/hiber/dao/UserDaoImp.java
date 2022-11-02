package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
   @Override
   public List<User> getUser(String model, int series) {
      List<User> userslist = new ArrayList<>();

      String query = """
              select * from users u\s
              where u.users_id in (select id from car c\s
              where c.model = :modelPram and c.series = :carSeriesParam)""";

      userslist = sessionFactory.getCurrentSession().createSQLQuery(query)
              .setParameter("modelPram", model)
              .setParameter("carSeriesParam", series)
              .addEntity(User.class)
              .getResultList();

      userslist.forEach(System.out::println);

      return userslist;
   }
}
