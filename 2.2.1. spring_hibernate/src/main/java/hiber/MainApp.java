package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Петр", "Кондрашов", " user1@gmail.ru "),
              new Car("Octavia", 7));
      userService.add(new User("Светлана", "Горячева", " user2@gmail.ru "),
              new Car("Golf", 6));
      userService.add(new User(" Иван ", "Теплицкий", "user3@gmail.ru"),
              new Car("Camry", 150));
      userService.add(new User("Семен", "Базылев", " user4@gmail.ru "),
              new Car("Laguna", 4));
      System.out.println("==================================================================");

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println();
         System.out.println(user.toString());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car info =" + user.getCar());
      }

      userService.getUser("Camry", 150);
      userService.getUser("Octavia", 7);

      context.close();
   }
}
