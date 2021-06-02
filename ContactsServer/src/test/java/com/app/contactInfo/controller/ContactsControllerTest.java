package com.app.contactInfo.controller;

import com.app.contactInfo.model.Contact;
import com.app.contactInfo.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactsController.class)
class ContactsControllerTest {


    @MockBean
    private ContactService contactService;

    @Autowired
    private MockMvc mockMvc;


    private Contact getContactSample(){
        Contact contact = new Contact("1","John","Smith",
                "abc@xy.com","12121212","Active");
        return contact;

    }

    private List<Contact> getAllContactsSample(){
        List<Contact> list = Arrays.asList(new Contact("1","John","Smith",
                "abc@xy.com","12121212","Active"),
                new Contact("2","Angela","Cracl",
                        "abc@xy.com","2343434","Active"),
                new Contact("3","Vincent","Flanh",
                        "abasw1@xy.com","565656","Active"));
        return list;
    }

    @Test
    void getContactById() throws Exception {
        Contact nc =getContactSample();
    Mockito.when(contactService.getContact(1)).thenReturn(nc);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/contacts/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
     //  assert(result.getResponse().)
        System.out.println("==>"+result.getResponse().getContentAsString());
        assertEquals(200,result.getResponse().getStatus());
     //  String expected ={"id":"1","firstName":"John","lastName":"Smith","email":"abc@xy.com","phoneNumber":"12121212","status":"Active"}
      //  assert(result.getResponse().)
      //  JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

    }



    @Test
    void editContact() throws Exception {
        Contact nc =getContactSample();
        Mockito.when(contactService.editContact(new Long(1),nc)).thenReturn(nc);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/contacts/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //  assert(result.getResponse().)
        assertEquals(200,result.getResponse().getStatus());

    }



    @Test
    void getAllContacts() throws Exception {
        List<Contact> list = getAllContactsSample();
        Mockito.when(contactService.getAllContacts()).thenReturn(list);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/contacts").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }
}