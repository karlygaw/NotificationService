package kz.narxoz.rabbit.rabbitreceiver.listener;

import kz.narxoz.rabbit.rabbitreceiver.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderNotificationListener {

    @RabbitListener(queues = "order.almaty")
    public void handleAlmatyOrders(OrderDTO orderDTO) {
        System.out.println("Received order for Almaty: " + orderDTO);
    }

    @RabbitListener(queues = "order.astana")
    public void handleAstanaOrders(OrderDTO orderDTO) {
        System.out.println("Received order for Astana: " + orderDTO);
    }
}
