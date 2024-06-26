package org.example.routtoproject.repository.shop;

import org.example.routtoproject.model.entity.shop.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.routtoproject.repository.shop.product
 * fileName : CartRepository
 * author : hayj6
 * date : 2024-04-22(022)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22(022)         hayj6          최초 생성
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}