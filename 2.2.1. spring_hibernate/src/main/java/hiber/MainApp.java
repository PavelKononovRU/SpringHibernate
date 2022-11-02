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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"),
              new Car("Octavia", 7));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"),
              new Car("GOlf", 6));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"),
              new Car("Camry", 150));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"),
              new Car("Laguna", 4));

      userService.getUser("Camry", 170);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car info =" + user.getCar());
      }

      context.close();
   }
}
