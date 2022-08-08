package com.smart.dxb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookInvoiceDTO {
    @ApiModelProperty("Total payable amount")
    private Double totalPayableAmount;
    @ApiModelProperty("Book/s in the cart")
    private List<BookDTO> books;

}
