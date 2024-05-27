package org.shipment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shipment.dto.PackageDetailsDTO;
import org.shipment.dto.ShipmentDTO;
import org.shipment.response.MessageResponse;
import org.shipment.service.impl.FedExShipmentService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class FedExShippingServiceTest {
    @InjectMocks
    private FedExShipmentService fedExShipmentService;


    @Test
    public void testCreateShipment_Success() {
        ShipmentDTO request = new ShipmentDTO();
        request.setCarrierId("fedex");
        request.setServiceId("fedexAIR");
        request.setPackageDetails(PackageDetailsDTO.builder()
                .height(BigDecimal.valueOf(10))
                .length(BigDecimal.valueOf(10))
                .width(BigDecimal.valueOf(10))
                .weight(BigDecimal.valueOf(100))
                .build());

        MessageResponse response = fedExShipmentService.createShipment(request);

        Assertions.assertEquals("Fedex shipment created successfully", response.getMessage());
    }

    @Test
    public void testCreateShipment_InvalidCarrierServiceID() {
        ShipmentDTO request = new ShipmentDTO();
        request.setCarrierId("fedex");
        request.setServiceId("invalidServiceID");
        request.setPackageDetails(PackageDetailsDTO.builder()
                .height(BigDecimal.valueOf(10))
                .length(BigDecimal.valueOf(10))
                .width(BigDecimal.valueOf(10))
                .weight(BigDecimal.valueOf(100))
                .build());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fedExShipmentService.createShipment(request);
        });

        String result = exception.getMessage();
        assertTrue(result.contains("Invalid Fedex service ID"));
    }
}
