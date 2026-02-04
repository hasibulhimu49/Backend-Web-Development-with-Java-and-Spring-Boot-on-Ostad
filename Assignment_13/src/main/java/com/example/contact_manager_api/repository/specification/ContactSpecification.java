package com.example.contact_manager_api.repository.specification;

import com.example.contact_manager_api.model.entity.Contact;
import org.springframework.data.jpa.domain.Specification;


public class ContactSpecification {

    public static Specification<Contact> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Contact> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null ? null : criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<Contact> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                phone == null ? null : criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Contact> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

    public static Specification<Contact> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null ? null : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Contact> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) ->
                isActive == null ? null : criteriaBuilder.equal(root.get("isActive"), isActive);
    }
}
