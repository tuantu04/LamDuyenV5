package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	List<Order> findAllByUserId(Long user_id);

	@Query("SELECT o FROM Order o WHERE o.fullname LIKE %:keyword% OR o.email LIKE %:keyword%")
	Page<Order> findByFullnameContainingOrEmailContaining(@Param("keyword") String keyword, Pageable pageable);

	Order findById(int id);
	
	@Query(value="Select * From `order` o ORDER BY o.id DESC LIMIT 5;",nativeQuery = true)
	List<Order> findTop5RecentOrder();

	@Query(value="SELECT DISTINCT o.user_id FROM `order` o ORDER BY o.user_id DESC LIMIT 5", nativeQuery = true)
	List<String> findTop5RecentCustomer();

	Page<Order> findAll(Pageable pageable);

	void deleteById(int id);
	
	
	@Query(value="select * from `order` o where o.payment_method = ?1",nativeQuery = true)
	List<Order> findAllByPayment_Method(String payment_Method);
	
	@Query(value="Select * From `order` o where o.payment_method = ?1 ORDER BY o.id DESC LIMIT 5;",nativeQuery = true)
	List<Order> findTop5OrderByPaymentMethod(String payment_method);

	@Query("SELECT o FROM Order o ORDER BY o.booking_Date DESC")
	Page<Order> findAllOrderByBookingDateDesc(Pageable pageable);
}
