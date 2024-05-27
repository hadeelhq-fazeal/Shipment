package org.shipment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDetailsDTO {
    @NotNull(message = "Width is required.")
    private BigDecimal width;

    @NotNull(message = "Height is required.")
    private BigDecimal height;

    @NotNull(message = "Length is required.")
    private BigDecimal length;

    @NotNull(message = "Weight is required.")
    private BigDecimal weight;
}
