package ru.itis.repository;

import ru.itis.model.Contact;
import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();
    void save(Contact contact);
    Contact findById(int id);
}
