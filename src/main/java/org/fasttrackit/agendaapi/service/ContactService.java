package org.fasttrackit.agendaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.agendaapi.contact.Contact;
import org.fasttrackit.agendaapi.exception.ResourceNotFoundException;
import org.fasttrackit.agendaapi.repository.ContactRepository;
import org.fasttrackit.agendaapi.transfer.CreateContactRequest;
import org.fasttrackit.agendaapi.transfer.GetContactRequest;
import org.fasttrackit.agendaapi.transfer.UpdateContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ObjectMapper objectMapper) {
        this.contactRepository = contactRepository;
        this.objectMapper = objectMapper;
    }

    public Contact createContact(CreateContactRequest request){
        LOGGER.info("Creating contact {}", request);

        Contact contact = objectMapper.convertValue(request, Contact.class);

        LOGGER.info("Contact created {}", request);
        return contactRepository.save(contact);
    }

    public Contact getContact(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving contact {}", id);
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact " + id + " does not exist"));
    }

    public Contact updateContact(long id, UpdateContactRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating contact {} with {}", id, request);

        Contact contact = getContact(id);
        BeanUtils.copyProperties(request, contact);

        LOGGER.info("Contact {} was updated with {}", id, request);
        return contactRepository.save(contact);
    }

    public void deleteContact(long id){
        LOGGER.info("Deleting contact {}", id);
        contactRepository.deleteById(id);
        LOGGER.info("Deleted contact {}", id);
    }

    public Page<Contact> getContact(GetContactRequest request, Pageable pageable) {
        LOGGER.info("Retrieving product {}", request);

        if (request.getPartialFirstName() != null && request.getPhoneNumber() != null) {
            return contactRepository.findByFirstNameContainingAndPhoneNumberStartingWith(request.getPartialFirstName(), request.getPhoneNumber(), pageable);
        }
        else if (request.getPartialFirstName() != null) {
            return contactRepository.findByFirstNameContaining(request.getPartialFirstName(), pageable);
        }
        return contactRepository.findAll(pageable);
    }


}
