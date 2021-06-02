package com.app.contactInfo.service;

import com.app.contactInfo.entity.ContactEntity;
import com.app.contactInfo.model.Contact;
import com.app.contactInfo.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ContactServiceImpl  implements  ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactEntity contactEntity;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Contact addContact(Contact contact) {
      //  validate(contact);

       contactEntity = contactDtoToContactEntity(contact);

        contactEntity = contactRepository.save(contactEntity);

       return  contactEntityToContactDto(contactEntity);
    }

    @Override
    public void removeContact(Long id) throws Exception {
        ContactEntity contactEntity = contactRepository.findById(id)
                .orElseThrow(()->new Exception("Employee not found for id :: "+id));

             contactRepository.delete(contactEntity);


    }

    @Override
    public Contact editContact(Long id, Contact contact) throws Exception{
       // validate(contact);
       ContactEntity contactEntity = contactRepository.findById(id)
               .orElseThrow(()->new Exception("Employee not found for id :: "+id));
        Contact nc = new Contact();
        contactEntity.setFirstName(contact.getFirstName());
        contactEntity.setLastName(contact.getLastName());
        contactEntity.setEmail(contact.getEmail());
        contactEntity.setPhoneNumber(contact.getPhoneNumber());
        contactEntity.setStatus(contact.getStatus());
       contactEntity = contactRepository.save(contactEntity);

       return contactEntityToContactDto(contactEntity);


       /* employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);*/


    }

    @Override
    public List<Contact> getAllContacts() {
       List<ContactEntity> list = contactRepository.findAll();
       List<Contact> contactList = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                contactList.add(contactEntityToContactDto(list.get(i)));
            }
            return contactList ;
    }

    @Override
    public Contact getContact(long id) throws Exception {
        ContactEntity contactEntity = contactRepository.findById(id)
                .orElseThrow(()->new Exception("Employee not found for id :: "+id));

        Contact contact = contactEntityToContactDto(contactEntity);

        return contact;
    }

    public  ContactEntity contactDtoToContactEntity (Contact contact){
        ContactEntity contactEntity = mapper.map(contact,ContactEntity.class);
        return contactEntity;
    }

    public Contact contactEntityToContactDto (ContactEntity contactEntity){
            Contact contact = mapper.map(contactEntity,Contact.class);
            return  contact;
    }

    private void validate(Contact contact){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        if(violations.size()>0){
            throw  new ConstraintDefinitionException((Throwable) violations);
        }

    }
}
