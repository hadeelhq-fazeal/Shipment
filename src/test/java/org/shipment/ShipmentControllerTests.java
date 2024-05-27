package org.shipment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shipment.controller.ShipmentController;
import org.shipment.dto.PackageDetailsDTO;
import org.shipment.dto.ShipmentDTO;
import org.shipment.response.MessageResponse;
import org.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ShipmentControllerTests {

    @InjectMocks
    private ShipmentController shipmentController;

    @Mock
    private ShipmentService shipmentService;

    @Mock
    @Qualifier("fedex")
    private ShipmentService fedExShipmentService;

    @Mock
    @Qualifier("ups")
    private ShipmentService upsShipmentService;


    @Test
    public void testCreateFedExShipment() {
        ShipmentDTO request = new ShipmentDTO();
        request.setCarrierId("fedex");
        request.setServiceId("fedexAIR");
        request.setPackageDetails(PackageDetailsDTO.builder()
                        .height(BigDecimal.valueOf(10))
                        .length(BigDecimal.valueOf(10))
                        .width(BigDecimal.valueOf(10))
                        .weight(BigDecimal.valueOf(100))
                .build());

        when(fedExShipmentService.createShipment(request))
                .thenReturn(MessageResponse.builder()
                        .message("FedEx shipment created successfully")
                        .build());

        ResponseEntity<MessageResponse> response = shipmentController.createShipment(request);

        Assertions.assertEquals("FedEx shipment created successfully", response.getBody().getMessage());
    }

    @Test
    public void testCreateUPSShipment() {
        ShipmentDTO request = new ShipmentDTO();
        request.setCarrierId("ups");
        request.setServiceId("UPSExpress");
        request.setPackageDetails(PackageDetailsDTO.builder()
                .height(BigDecimal.valueOf(10))
                .length(BigDecimal.valueOf(10))
                .width(BigDecimal.valueOf(10))
                .weight(BigDecimal.valueOf(100))
                .build());

        when(upsShipmentService.createShipment(any(ShipmentDTO.class)))
                .thenReturn(MessageResponse.builder()
                        .message("UPS shipment created successfully")
                        .build());

        ResponseEntity<MessageResponse> response = shipmentController.createShipment(request);

        Assertions.assertEquals("UPS shipment created successfully", response.getBody().getMessage());
    }

    @Test
    public void testInvalidCarrierId() {
        ShipmentDTO request = new ShipmentDTO();
        request.setCarrierId("invalid");

        MessageResponse response = shipmentController.createShipment(request).getBody();

        Assertions.assertEquals("Invalid carrier ID", response.getMessage());
    }
}
