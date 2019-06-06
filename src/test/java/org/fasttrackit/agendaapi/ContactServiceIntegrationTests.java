package org.fasttrackit.agendaapi;

import org.fasttrackit.agendaapi.contact.Contact;
import org.fasttrackit.agendaapi.exception.ResourceNotFoundException;
import org.fasttrackit.agendaapi.service.ContactService;
import org.fasttrackit.agendaapi.transfer.CreateContactRequest;
import org.fasttrackit.agendaapi.transfer.UpdateContactRequest;
import org.hamcrest.text.IsEmptyString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.support.NullValue;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.constraints.Null;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.isEmptyOrNullString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceIntegrationTests {
    @Autowired
    private ContactService contactService;

	@Test
	public void testCreateContact_whenValidRequest_thenReturnCreatedContact() {
        createContact();
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateContact_whenMissingMandatoryProperties_thenThrowException(){
        CreateContactRequest request = new CreateContactRequest();

        contactService.createContact(request);
    }

    @Test
    public void testGetContact_whenExistingId_ThenReturnContact() throws ResourceNotFoundException {
        Contact createdContact = createContact();

        Contact contact = contactService.getContact(createdContact.getId());

        assertThat(contact, notNullValue());
        assertThat(contact.getId(), is(createdContact.getId()));
        assertThat(contact.getFirstName(), is(createdContact.getFirstName()));
        assertThat(contact.getLastName(), is(createdContact.getLastName()));
        assertThat(contact.getNickName(), is(createdContact.getNickName()));
        assertThat(contact.getPhoneNumber(), is(createdContact.getPhoneNumber()));
        assertThat(contact.getAge(), is(createdContact.getAge()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void  testGetContact_whenNonExistingId_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        contactService.getContact(9999L);
    }

    @Test
    public void testUpdateContact_whenValidRequest_thenReturnUpdatedContact() throws ResourceNotFoundException {
        Contact createdContact = createContact();

        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName(createdContact.getFirstName() + "_Updated");
        request.setLastName(createdContact.getLastName() + "_Updated");
        request.setNickName("");
        request.setPhoneNumber(createdContact.getPhoneNumber() + 10);
        request.setAge(createdContact.getAge() + 10);

        Contact updatedContact = contactService.updateContact(createdContact.getId(), request);

        assertThat(updatedContact, notNullValue());
        assertThat(updatedContact.getId(), is(createdContact.getId()));
        assertThat(updatedContact.getFirstName(), not(is(createdContact.getFirstName())));
        assertThat(updatedContact.getFirstName(), is(request.getFirstName()));
        assertThat(updatedContact.getLastName(), not(is(createdContact.getLastName())));
        assertThat(updatedContact.getLastName(), is(request.getLastName()));
        assertThat(updatedContact.getNickName(), not(is(createdContact.getNickName())));
        assertThat(updatedContact.getNickName(), is(request.getNickName()));
        assertThat(updatedContact.getPhoneNumber(), not(is(createdContact.getPhoneNumber())));
        assertThat(updatedContact.getPhoneNumber(), is(request.getPhoneNumber()));
        assertThat(updatedContact.getAge(), not(is(createdContact.getAge())));
        assertThat(updatedContact.getAge(), is(request.getAge()));



        Contact contact = contactService.getContact(createdContact.getId());
        assertThat(contact, notNullValue());
        assertThat(contact.getFirstName(), not(is(createdContact.getFirstName())));
        assertThat(contact.getFirstName(), is(updatedContact.getFirstName()));
        assertThat(contact.getLastName(), not(is(createdContact.getLastName())));
        assertThat(contact.getLastName(), is(updatedContact.getLastName()));
        assertThat(contact.getNickName(), not(is(createdContact.getNickName())));
        assertThat(contact.getNickName(), is(updatedContact.getNickName()));
        assertThat(contact.getPhoneNumber(), not(is(createdContact.getPhoneNumber())));
        assertThat(contact.getPhoneNumber(), is(updatedContact.getPhoneNumber()));
        assertThat(contact.getAge(), not(is(createdContact.getAge())));
        assertThat(contact.getAge(), is(updatedContact.getAge()));
    }



    private Contact createContact() {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("Ion");
        request.setLastName("Branzescu");
        request.setNickName("Bobi");
        request.setPhoneNumber(0721234123L);
        request.setAge(22);


        Contact createdContact = contactService.createContact(request);

        assertThat(createdContact, notNullValue());
        assertThat(createdContact.getId(), greaterThan(0L));
        assertThat(createdContact.getFirstName(), is(request.getFirstName()));
        assertThat(createdContact.getLastName(), is(request.getLastName()));
        assertThat(createdContact.getNickName(), is(request.getNickName()));
        assertThat(createdContact.getPhoneNumber(), is(request.getPhoneNumber()));
        assertThat(createdContact.getAge(), is(request.getAge()));
        assertThat(createdContact.getDescription(), isEmptyOrNullString());
        assertThat(createdContact.getImagePath(), isEmptyOrNullString());


        return createdContact;
    }
}
