package org.shipment.service.impl;

import org.shipment.constants.FedexServiceId;
import org.shipment.dto.ShipmentDTO;
import org.shipment.response.MessageResponse;
import org.shipment.service.ShipmentService;
import org.springframework.stereotype.Service;

@Service("fedex")
public class FedExShipmentService implements ShipmentService {
    @Override
    public MessageResponse createShipment(ShipmentDTO shipmentDTO) {
        if (!isValidFedexService(shipmentDTO.getServiceId())) {
            throw new IllegalArgumentException("Invalid Fedex service ID");
        }
        return MessageResponse.builder().message("Fedex shipment created successfully").build();
    }

    private boolean isValidFedexService(String serviceID){
        for (FedexServiceId fedexServiceId: FedexServiceId.values()){
            if (fedexServiceId.toString().equalsIgnoreCase(serviceID)){
                return true;
            }
        }
        return false;
    }
}
