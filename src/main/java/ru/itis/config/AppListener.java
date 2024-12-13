package ru.itis.config;

import ru.itis.repository.impl.BirthdayRepositoryImpl;
import ru.itis.repository.impl.UserRepositoryImpl;
import ru.itis.service.BirthdayService;
import ru.itis.service.impl.BirthdayServiceImpl;
import ru.itis.service.impl.UserServiceImpl;
import ru.itis.service.UserService;
import ru.itis.mapper.impl.UserMapperImpl;
import ru.itis.repository.impl.TaskRepositoryImpl;
import ru.itis.service.impl.TaskServiceImpl;
import ru.itis.service.TaskService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserServiceImpl(
                new UserRepositoryImpl(),
                new UserMapperImpl()
        );
        sce.getServletContext().setAttribute("userService", userService);

        TaskService taskService = new TaskServiceImpl(new TaskRepositoryImpl());
        sce.getServletContext().setAttribute("taskService", taskService);

        BirthdayService birthdayService = new BirthdayServiceImpl(new BirthdayRepositoryImpl());
        sce.getServletContext().setAttribute("birthdayService", birthdayService);
    }
}
