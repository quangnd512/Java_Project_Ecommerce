package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.UserReponsitory;
import com.project.shopapp.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final UserReponsitory userReponsitory;
    private final OrderRepository orderRepository;
    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) throws Exception {
        // Tìm xem userId có tồn tại hay không
        User user = userReponsitory
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Khong tin thay user voi id = "+orderDTO.getUserId()));

        // convert orderDTO => Order
        // Dùng thư viện model mapper
        return null;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrders(Long userId) {
        return null;
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {

    }
}
