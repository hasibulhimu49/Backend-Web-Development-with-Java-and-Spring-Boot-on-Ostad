package com.example.contact_manager_api.repository;

import com.example.contact_manager_api.model.entity.Contact;
import com.example.contact_manager_api.repository.projection.ContactSummary;
import com.example.contact_manager_api.repository.projection.ContactSummaryExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>, JpaSpecificationExecutor<Contact> {

    //Advanced

    List<Contact> findByFirstNameIgnoreCaseContaining(String firstname);

    long countByIsActive(Boolean isActive);

    //More Advanced

    @Query("""
select c.firstName as firstName, c.email as email from Contact c where c.category= :category
""")
    List<ContactSummary> findByCategoryWithProjection(@Param("category") String category);


    Page<ContactSummaryExtra> findByIsActive(Boolean active, Pageable pageable);



     void  deleteByPhoneStartingWith(String prefix);  // delete from Contact where phone like 'prefix%'
}
