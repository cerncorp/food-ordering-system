package com.food.ordering.system.order.service.domain.ports.output.reporitory;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<CustomerRepository> findCustomer(UUID customerId);
}
