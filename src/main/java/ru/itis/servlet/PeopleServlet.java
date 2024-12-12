package ru.itis.servlet;

import ru.itis.model.Person;
import ru.itis.service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/people")
public class PeopleServlet extends HttpServlet {

    private PersonService personService;

    @Override
    public void init() throws ServletException {
        this.personService = (PersonService) getServletContext().getAttribute("personService");
        // Убедились, что personService не будет null, так как AppListener задаёт его
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> people = personService.getAllPeople();
        req.setAttribute("people", people);
        req.getRequestDispatcher("/jsp/people.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String bdStr = req.getParameter("birth_date");
        if (name != null && !name.isBlank() && bdStr != null && !bdStr.isBlank()) {
            Date bd = Date.valueOf(bdStr);
            Person p = Person.builder().name(name).birthDate(bd).build();
            personService.addPerson(p);
        }
        resp.sendRedirect("/people");
    }
}
