package org.fasttrackit.agendaapi.repository;

import org.fasttrackit.agendaapi.contact.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByFirstNameContaining(String partialFirstName, Pageable pageable);
    Page<Contact> findByLastNameContaining(String partialLastName, Pageable pageable);
    Page<Contact> findByNickNameContaining(String partialNickName, Pageable pageable);
    Page<Contact> findByIdContaining(Long partialId, Pageable pageable);
    Page<Contact> findByPhoneNumberStartingWith(Long partialPhoneNumber, Pageable pageable);
    Page<Contact> findByAgeContaining(Integer partialAge, Pageable pageable);
    Page<Contact> findByDescriptionContaining(String partialDescription, Pageable pageable);
    Page<Contact> findByFirstNameContainingAndPhoneNumberStartingWith(String partialName, Long phoneNumber, Pageable pageable);

    @Query("SELECT contact FROM Contact contact WHERE firstName LIKE '%?1'") // '%?1' primul parametru
    Page<Contact> findByPartialFirstName(String partialFirstName, Pageable pageable);
}
