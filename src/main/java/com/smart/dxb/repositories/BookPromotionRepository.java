package com.smart.dxb.repositories;

import com.smart.dxb.entities.BookPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Repository
public interface BookPromotionRepository extends JpaRepository<BookPromotion, Integer> {

    BookPromotion findByPromotionCode(String promotionCode);
    BookPromotion findByPromotionCodeAndIsActive(String promotionCode, boolean isActive);

}
