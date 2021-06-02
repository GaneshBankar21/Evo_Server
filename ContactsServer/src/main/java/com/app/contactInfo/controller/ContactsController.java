package com.app.contactInfo.controller;

import com.app.contactInfo.model.Contact;
import com.app.contactInfo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class ContactsController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Long contactId)
            throws Exception {
        return  ResponseEntity.ok(contactService.getContact(contactId));

    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.addContact(contact));
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> editContact(@PathVariable(value = "id") Long ContactId,
                                                    @Valid @RequestBody Contact ContactDetails) throws Exception {
        Contact contact = contactService.editContact(ContactId,ContactDetails);

        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity  removeContact(@PathVariable(value = "id") Long contactId)
            throws Exception {
              contactService.removeContact(contactId);
        return ResponseEntity.ok("success");
    }
}
