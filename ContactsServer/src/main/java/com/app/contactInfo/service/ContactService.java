package com.app.contactInfo.service;

import com.app.contactInfo.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    public Contact addContact(Contact contact);

    public void removeContact(Long id) throws Exception;

    public  Contact editContact(Long id, Contact contact) throws Exception;

    public List<Contact> getAllContacts();

    public Contact getContact(long id) throws Exception;





}
