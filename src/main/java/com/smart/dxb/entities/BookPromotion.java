package com.smart.dxb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_PROMOTION")
public class BookPromotion {
    @Id
    @SequenceGenerator(name = "promotion_seq_id_generator", sequenceName = "seq_book_promotion", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "promotion_seq_id_generator")
    private Integer promotionId;
    private String promotionCode;
    private String classification;
    private double discount;
    private boolean isActive;

}
