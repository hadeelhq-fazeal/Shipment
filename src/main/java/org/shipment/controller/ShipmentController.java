package org.shipment.controller;

import org.shipment.dto.ShipmentDTO;
import org.shipment.response.MessageResponse;
import org.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    @Qualifier("fedex")
    private ShipmentService fedExShipmentService;

    @Autowired
    @Qualifier("ups")
    private ShipmentService upsShipmentService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> createShipment(@Validated @RequestBody ShipmentDTO shipmentDTO) {
        switch (shipmentDTO.getCarrierId().toLowerCase()) {
            case "fedex":
                return ResponseEntity.ok(fedExShipmentService.createShipment(shipmentDTO));
            case "ups":
                return ResponseEntity.ok(upsShipmentService.createShipment(shipmentDTO));
            default:
                return ResponseEntity.badRequest().body(MessageResponse.builder()
                                .message("Invalid carrier ID")
                        .build());
        }
    }
}
