package org.fasttrackit.agendaapi.repository;

import org.fasttrackit.agendaapi.contact.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByFirstNameContaining(String partialFirstName, Pageable pageable);
    Page<Contact> findByFirstNameContainingAndPhoneNumberContaining(String partialName, long phoneNumber, Pageable pageable);

    @Query("SELECT contact FROM Contact product WHERE firstName LIKE '%?1'") // '%?1' primul parametru
    Page<Contact> findByPartialFirstName(String partialFirstName, Pageable pageable);
}
