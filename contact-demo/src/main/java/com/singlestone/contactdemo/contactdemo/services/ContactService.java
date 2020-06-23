package com.singlestone.contactdemo.contactdemo.services;

import com.singlestone.contactdemo.contactdemo.models.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(Long id);

    void addContact(Contact contact);

    void updateById(Long id, Contact contact);

    void deleteById(Long id);
}
