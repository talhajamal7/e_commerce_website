package com.bsse5a.EcommerceWeb.respositories;

import com.bsse5a.EcommerceWeb.models.Product;
import com.bsse5a.EcommerceWeb.models.enums.GymEquipmentCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByGymEquipmentCategories(GymEquipmentCategories category);

    @Query("""
        SELECT p FROM Product p
        WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    List<Product> searchProducts(@Param("query") String query);

}
