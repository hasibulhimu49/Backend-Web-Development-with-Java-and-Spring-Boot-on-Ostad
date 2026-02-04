package com.example.contact_manager_api.controller;

import com.example.contact_manager_api.dto.request.ContactCreateRequestDto;
import com.example.contact_manager_api.dto.response.ContactResponseDto;
import com.example.contact_manager_api.dto.update.ContactUpdateRequestDto;
import com.example.contact_manager_api.repository.projection.ContactSummary;
import com.example.contact_manager_api.repository.projection.ContactSummaryExtra;
import com.example.contact_manager_api.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {


   private final ContactService service;

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContact(@RequestBody ContactCreateRequestDto dto)
    {
        ContactResponseDto responseDto=service.createContact(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getAllContact()
    {
        return ResponseEntity.ok(service.getAllContact());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> getContactById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getContactById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDto> updateContact(@PathVariable Long id, @RequestBody ContactUpdateRequestDto dto)
    {
        return ResponseEntity.ok(service.updateContact(id,dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id)
    {
       service.deleteContact(id);
       return ResponseEntity.noContent().build();
    }


    //Aadvanced

    @GetMapping("/count")
    public long countInActive()
    {
        return service.countInActive(false);
    }

    @GetMapping("/search1")
    public ResponseEntity<List<ContactResponseDto>> getByFirstname(@RequestParam String name)
    {
        return ResponseEntity.ok(service.getByName(name));
    }



   /*
    @GetMapping("/search")
    public ResponseEntity<List<ContactResponseDto>> searchContact(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
            )
    {

        if(name!=null&& category==null)
        {
            return ResponseEntity.ok(service.getByName(name));
        }

        if(category!=null&&name==null)
        {
            return ResponseEntity.ok(service.getByCategory(category));
        }

        return ResponseEntity.badRequest().body("Provide either name or category");

    }

    */




    //More Advanced
    @GetMapping("/search2")
    public List<ContactSummary> getByCategory(String category)
    {
        return service.getByCategory(category);
    }



    @GetMapping("/active")
    public Page<ContactSummaryExtra> getByActive(@RequestParam Boolean status, Pageable pageable)
    {
        return service.getByActive(status,pageable);
    }


    @DeleteMapping("/delete-by-prefix")
    public ResponseEntity<String> deleteByPhone(@RequestParam String prefix)
    {
        service.deleteByPhonePrefix(prefix);
        return ResponseEntity.ok("Deleted all contacts starting with "+prefix);
    }


    @PostMapping("/mass")
    public ResponseEntity<List<ContactResponseDto>> createMultipleContact(@RequestBody ContactCreateRequestDto[] dtos)
    {
        List<ContactResponseDto> responseDtos= service.createMultipleContact(dtos);
        return ResponseEntity.ok(responseDtos);
    }


    @GetMapping("/specification")
    public ResponseEntity<Page<ContactResponseDto>> search(
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String category,
            Pageable pageable
    )
    {
        return ResponseEntity.ok(service.search(firstname,lastname,phone,email,isActive,category,pageable));
    }




}
