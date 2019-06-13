package org.fasttrackit.agendaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.agendaapi.domain.Contact;
import org.fasttrackit.agendaapi.exception.ResourceNotFoundException;
import org.fasttrackit.agendaapi.repository.ContactRepository;
import org.fasttrackit.agendaapi.transfer.contact.CreateContactRequest;
import org.fasttrackit.agendaapi.transfer.contact.GetContactRequest;
import org.fasttrackit.agendaapi.transfer.contact.UpdateContactRequest;
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
        LOGGER.info("Creating domain {}", request);

        Contact contact = objectMapper.convertValue(request, Contact.class);

        LOGGER.info("Contact created {}", request);
        return contactRepository.save(contact);
    }

    public Contact getContact(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving domain {}", id);
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact " + id + " does not exist"));
    }

    public Contact updateContact(long id, UpdateContactRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating domain {} with {}", id, request);

        Contact contact = getContact(id);
        BeanUtils.copyProperties(request, contact);

        LOGGER.info("Contact {} was updated with {}", id, request);
        return contactRepository.save(contact);
    }

    public void deleteContact(long id){
        LOGGER.info("Deleting domain {}", id);
        contactRepository.deleteById(id);
        LOGGER.info("Deleted domain {}", id);
    }

    public Page<Contact> getContact(GetContactRequest request, Pageable pageable) {
        LOGGER.info("Retrieving product {}", request);

        if (request.getPartialFirstName() != null && request.getPartialPhoneNumber() != null) {
            return contactRepository.findByFirstNameContainingAndPhoneNumberStartingWith(request.getPartialFirstName(), request.getPartialPhoneNumber(), pageable);
        }
        else if (request.getPartialFirstName() != null) {
            return contactRepository.findByFirstNameContaining(request.getPartialFirstName(), pageable);
        }
        else if (request.getPartialLastName() != null) {
            return contactRepository.findByLastNameContaining(request.getPartialLastName(), pageable);
        }
        else if (request.getPartialNickName() != null) {
            return contactRepository.findByNickNameContaining(request.getPartialNickName(), pageable);
        }
        else if (request.getPartialPhoneNumber() != null) {
            return contactRepository.findByPhoneNumberStartingWith(request.getPartialPhoneNumber(), pageable);
        }
        else if (request.getPartialId() != null) {
            return contactRepository.findByIdContaining(request.getPartialId(), pageable);
        }
        else if (request.getPartialAge() != null) {
            return contactRepository.findByAgeContaining(request.getPartialAge(), pageable);
        }
        else if (request.getPartialDescription() != null) {
            return contactRepository.findByDescriptionContaining(request.getPartialDescription(), pageable);
        }
        return contactRepository.findAll(pageable);
    }


}
