package com.example.contact_manager_api.service;

import com.example.contact_manager_api.dto.request.ContactCreateRequestDto;
import com.example.contact_manager_api.dto.response.ContactResponseDto;
import com.example.contact_manager_api.dto.update.ContactUpdateRequestDto;
import com.example.contact_manager_api.mapper.ContactMapper;
import com.example.contact_manager_api.model.entity.Contact;
import com.example.contact_manager_api.repository.ContactRepository;
import com.example.contact_manager_api.repository.projection.ContactSummary;
import com.example.contact_manager_api.repository.projection.ContactSummaryExtra;
import com.example.contact_manager_api.repository.specification.ContactSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService{

    private final ContactRepository repository;
    private final ContactMapper mapper;


    @Transactional
    public ContactResponseDto createContact(ContactCreateRequestDto dto){
        Contact contact=mapper.toEntity(dto);
        Contact saveContact=repository.save(contact);
        return mapper.toDto(saveContact);
    }



    public List<ContactResponseDto> getAllContact(){
        return repository.findAll().stream().map((c)->mapper.toDto(c)).toList();
    }


    public ContactResponseDto getContactById(Long id)
    {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }



    public ContactResponseDto updateContact(Long id, ContactUpdateRequestDto dto)
    {
        Contact contact=repository.findById(id).orElseThrow();
        mapper.updateEntity(contact,dto);
        Contact saved=repository.save(contact);
        return mapper.toDto(saved);
    }


    public void deleteContact(Long id)
    {
        repository.deleteById(id);
    }

    //Advanced

    public List<ContactResponseDto> getByName(String name)
    {
        return repository.findByFirstNameIgnoreCaseContaining(name).stream().map(mapper::toDto).toList();
    }

    public long countInActive(Boolean isactive)
    {
       long activecount =repository.countByIsActive(isactive);
       return activecount;
    }

    //More Advanced
    public List<ContactSummary> getByCategory(String category)
    {
        return repository.findByCategoryWithProjection(category);

    }

    public Page<ContactSummaryExtra> getByActive(Boolean isActive, Pageable pageable)
    {
        return repository.findByIsActive(isActive,pageable);
    }



    @Transactional
    public void deleteByPhonePrefix(String prefix)
    {
        repository.deleteByPhoneStartingWith(prefix);
    }


    public List<ContactResponseDto> createMultipleContact(ContactCreateRequestDto[] dtos)
    {
        List<Contact> contacts = Arrays.stream(dtos)
                .map(mapper::toEntity)  // DTO → Entity
                .toList();

        List<Contact>savedContacts=repository.saveAll(contacts);
        return savedContacts.stream().map(mapper::toDto).toList();


    }


    public Page<ContactResponseDto> search(String firstname,String lastname,String phone,
                                           String email, Boolean isActive,String category,Pageable pageable)
    {

        Specification<Contact> spec=Specification.where(ContactSpecification.
                hasFirstName(firstname)).and(ContactSpecification.hasLastName(lastname)).
                and(ContactSpecification.hasPhone(phone)).and(ContactSpecification.hasEmail(email)).
                and(ContactSpecification.isActive(isActive)).and(ContactSpecification.hasCategory(category));

        return repository.findAll(spec,pageable).map(mapper::toDto);

    }



}








