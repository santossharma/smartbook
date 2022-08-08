package com.smart.dxb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;


/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookDTO {
    @ApiModelProperty("Book Id")
    private Integer bookId;
    @NotEmpty
    @ApiModelProperty("Book Name")
    private String bookName;
    @NotEmpty
    @ApiModelProperty("Book Description")
    private String description;
    @NotEmpty
    @ApiModelProperty("Book Author")
    private String bookAuthor;
    @NotEmpty
    @ApiModelProperty("Book Type/Classification")
    private String classification;
    @NotEmpty
    @ApiModelProperty("Book Price (double)")
    private double bookPrice;
    @NotEmpty
    @ApiModelProperty("ISBN Number")
    private String isbn;

}
