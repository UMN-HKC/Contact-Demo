package com.singlestone.contactdemo.contactdemo.controllers;

import com.singlestone.contactdemo.contactdemo.models.Contact;
import com.singlestone.contactdemo.contactdemo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/")
public class ContactController {

    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contact> findAllContacts() {
        return service.findAll();
    }

    @GetMapping(value = "/contacts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Contact findContactById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value = "/contacts", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createNewContact(@RequestBody Contact contact) {
        service.addContact(contact);
    }

    @PutMapping(value = "/contacts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateContactById(@RequestBody Contact contact, @PathVariable("id") Long id) {
        service.updateById(id, contact);
    }

    @DeleteMapping(value = "/contacts/{id}")
    public void deleteContactById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
