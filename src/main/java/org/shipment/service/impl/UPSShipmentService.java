package org.shipment.service.impl;

import org.shipment.constants.UpsServiceId;
import org.shipment.dto.ShipmentDTO;
import org.shipment.response.MessageResponse;
import org.shipment.service.ShipmentService;
import org.springframework.stereotype.Service;

@Service("ups")
public class UPSShipmentService implements ShipmentService {
    @Override
    public MessageResponse createShipment(ShipmentDTO shipmentDTO) {
        if (!isValidUpsService(shipmentDTO.getServiceId())) {
            throw new IllegalArgumentException("Invalid UPS service ID");
        }
        return MessageResponse.builder().message("UPS shipment created successfully").build();
    }

    private boolean isValidUpsService(String serviceID){
        for (UpsServiceId upsServiceId: UpsServiceId.values()){
            if (upsServiceId.toString().equalsIgnoreCase(serviceID)){
                return true;
            }
        }
        return false;
    }
}
