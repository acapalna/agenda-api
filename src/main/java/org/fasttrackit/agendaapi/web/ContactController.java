package org.fasttrackit.agendaapi.web;

import org.fasttrackit.agendaapi.domain.Contact;
import org.fasttrackit.agendaapi.exception.ResourceNotFoundException;
import org.fasttrackit.agendaapi.service.ContactService;
import org.fasttrackit.agendaapi.transfer.contact.CreateContactRequest;
import org.fasttrackit.agendaapi.transfer.contact.GetContactRequest;
import org.fasttrackit.agendaapi.transfer.contact.UpdateContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Page<Contact>> getContacts(GetContactRequest request, Pageable pageable){
        Page<Contact> response = contactService.getContact(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody @Valid CreateContactRequest request){
        Contact contact = contactService.createContact(request);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity getContact(@PathVariable Long id) throws ResourceNotFoundException {
        Contact contact = contactService.getContact(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateContactRequest request) throws ResourceNotFoundException {
        contactService.updateContact(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
