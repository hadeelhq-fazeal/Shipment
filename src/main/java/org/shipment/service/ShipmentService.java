package org.shipment.service;

import org.shipment.dto.ShipmentDTO;
import org.shipment.response.MessageResponse;

public interface ShipmentService {
    public MessageResponse createShipment(ShipmentDTO shipmentDTO);
}
