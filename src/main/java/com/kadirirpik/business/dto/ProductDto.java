package com.kadirirpik.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotBlank(message = "It cannot be left blank.")
    private String productName;
    @NotBlank(message = "It cannot be left blank.")
    private String productTrade;
    @NotBlank(message = "It cannot be left blank.")
    private String productSerialNumber;
    @DecimalMin(value = "0.0", message = "Min 0.0")
    @DecimalMax(value = "100000000.0", message = "Max 100000000.0")
    private double productPrice;

    // Kim Güncelledi ?
    @LastModifiedBy
    private String updatedBy;
    // Kim ne zaman Güncelledi ?
    @LastModifiedDate
    private java.util.Date updatedDate;
}