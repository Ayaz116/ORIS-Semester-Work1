package ru.itis.servlet;

import ru.itis.model.Contact;
import ru.itis.repository.ContactRepository; // если нужен сервис, добавьте ContactService
import ru.itis.repository.impl.ContactRepositoryImpl; // или через AppListener настроить

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/contacts")
public class ContactsServlet extends HttpServlet {
    private ContactRepository contactRepository; // лучше ContactService, но для примера

    @Override
    public void init() throws ServletException {
        contactRepository = new ContactRepositoryImpl();
        // или через getServletContext().getAttribute("contactService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Contact> contacts = contactRepository.findAll();
        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("/jsp/contacts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String birthDateStr = req.getParameter("birth_date");
        Date bd = Date.valueOf(birthDateStr);

        Contact c = Contact.builder().name(name).birthDate(bd).build();
        contactRepository.save(c);

        resp.sendRedirect("/contacts");
    }
}
