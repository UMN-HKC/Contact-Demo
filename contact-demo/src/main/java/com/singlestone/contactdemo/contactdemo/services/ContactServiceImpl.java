package com.singlestone.contactdemo.contactdemo.services;

import com.singlestone.contactdemo.contactdemo.dao.ContactRepository;
import com.singlestone.contactdemo.contactdemo.exceptions.ContactNotFoundException;
import com.singlestone.contactdemo.contactdemo.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private static final String CONTACT_NOT_FOUND = "The contact does not exist!";
    private ContactRepository repo;

    @Autowired
    public ContactServiceImpl(ContactRepository repo) {
        this.repo = repo;
    }

    public List<Contact> findAll() {
        return repo.findAll();
    }

    public Contact findById(Long id) {
        Optional<Contact> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        else {
            throw new ContactNotFoundException(CONTACT_NOT_FOUND);
        }

    }

    @Override
    public void addContact(Contact contact) {
        repo.save(contact);
    }

    @Override
    public void updateById(Long id, Contact contact) {

        Optional<Contact> optional = repo.findById(id);
        if (optional.isPresent()) {
            contact.setId(id);
            repo.save(contact);
        }
        else {
            throw new ContactNotFoundException(CONTACT_NOT_FOUND);
        }

    }

    @Override
    public void deleteById(Long id) {
        Optional<Contact> optional = repo.findById(id);
        if (optional.isPresent()) {
            repo.deleteById(id);
        }
        else {
            throw new ContactNotFoundException(CONTACT_NOT_FOUND);
        }
    }
}
