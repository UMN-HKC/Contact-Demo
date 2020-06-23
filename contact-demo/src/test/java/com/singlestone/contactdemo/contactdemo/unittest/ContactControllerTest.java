package com.singlestone.contactdemo.contactdemo.unittest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singlestone.contactdemo.contactdemo.controllers.ContactController;
import com.singlestone.contactdemo.contactdemo.models.Address;
import com.singlestone.contactdemo.contactdemo.models.Contact;
import com.singlestone.contactdemo.contactdemo.models.Name;
import com.singlestone.contactdemo.contactdemo.models.Phone;
import com.singlestone.contactdemo.contactdemo.services.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private ContactService service;

    @Test
    public void findAllContactsTest() throws Exception {

        Set<Phone> phones1 = new HashSet<>();
        phones1.add(new Phone("mobile", "123-456-7891"));
        Contact contact1 = new Contact(new Name("Kenneth", "n/a", "Lai"), new Address("angel street", "angles", "CA", "10101"), phones1, "kenneth@gmail.com");

        Set<Phone> phones2 = new HashSet<>();
        phones2.add(new Phone("work", "822-102-1020"));
        Contact contact2 = new Contact(new Name("Fung", "LLLL", "Wang"), new Address("devil street", "devil", "CA", "10221"), phones2, "Fung@gmail.com");

        List<Contact> list = new ArrayList<>();
        list.add(contact1);
        list.add(contact2);

        when(service.findAll()).thenReturn(list);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/contacts");
        mock.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void findContactByIdTest() throws Exception {
        Set<Phone> phoneSet1 = new HashSet<>();
        phoneSet1.add(new Phone("mobile", "123-456-7891"));
        Contact contact1 = new Contact(new Name("Kenneth", "n/a", "Lai"), new Address("angel street", "angles", "CA", "10101"), phoneSet1, "kenneth@gmail.com");

        when(service.findById(1l)).thenReturn(contact1);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/contacts/{id}", 1l);
        mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("kenneth@gmail.com"));
    }

    @Test
    public void createNewContactTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Phone> phoneSet1 = new HashSet<>();
        phoneSet1.add(new Phone("mobile", "123-456-7891"));
        Contact contact1 = new Contact(new Name("Kenneth", "n/a", "Lai"), new Address("angel street", "angles", "CA", "10101"), phoneSet1, "kenneth@gmail.com");


        doNothing().when(service).addContact(contact1);
        mock.perform(post("/contacts/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact1)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateContactByIdTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<Phone> phoneSet1 = new HashSet<>();
        phoneSet1.add(new Phone("mobile", "123-456-7891"));
        Contact contact1 = new Contact(new Name("Kenneth", "n/a", "Lai"), new Address("angel street", "angles", "CA", "10101"), phoneSet1, "kenneth@gmail.com");

        doNothing().when(service).updateById(1L, contact1);

        mock.perform(MockMvcRequestBuilders
                .put("/contacts/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact1)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        doNothing().when(service).deleteById(1L);

        mock.perform(delete("/contacts/{id}", 1L))
                .andExpect(status().isOk());
    }
}
