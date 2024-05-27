package org.shipment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDTO {
    @NotBlank(message = "Carrier ID is required.")
    private String carrierId;

    @NotBlank(message = "Service ID is required.")
    private String serviceId;

    @NotNull(message = "Package details are required.")
    private PackageDetailsDTO packageDetails;
}
