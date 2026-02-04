package com.example.contact_manager_api.mapper;

import com.example.contact_manager_api.dto.request.ContactCreateRequestDto;
import com.example.contact_manager_api.dto.response.ContactResponseDto;
import com.example.contact_manager_api.dto.update.ContactUpdateRequestDto;
import com.example.contact_manager_api.model.entity.Contact;
import org.springframework.stereotype.Component;


@Component
public class ContactMapper {


    public Contact toEntity(ContactCreateRequestDto dto)
    {
        System.out.println(dto.getFirstName());
        Contact contact=new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setIsActive(dto.getIsActive());
        contact.setCategory(dto.getCategory());
        return contact;
    }

    public ContactResponseDto toDto(Contact contact)
    {
        ContactResponseDto dto=new ContactResponseDto();
        dto.setId(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setPhone(contact.getPhone());
        dto.setEmail(contact.getEmail());
        dto.setIsActive(contact.getIsActive());

        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());

        return dto;
    }


    public Contact updateEntity(Contact contact,ContactUpdateRequestDto dto)
    {
        if(dto==null)
        {
            return null;
        }

        if(dto.getFirstName()!=null)
        {
            contact.setFirstName(dto.getFirstName());
        }

        if(dto.getLastName()!=null)
        {
            contact.setLastName(dto.getLastName());
        }

        if (dto.getPhone()!=null)
        {
            contact.setPhone(dto.getPhone());
        }

        if(dto.getEmail()!=null)
        {
            contact.setEmail(dto.getEmail());
        }

        if(dto.getIsActive()!=null)
        {
            contact.setIsActive(dto.getIsActive());
        }

        if(dto.getCategory()!=null)
        {
            contact.setCategory(dto.getCategory());
        }
        return contact;
    }
}
