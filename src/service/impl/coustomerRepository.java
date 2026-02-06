// `src/service/impl/CustomerRepository.java`
package service.impl;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private final Map<String, Customer> customerById = new HashMap<>();

    public void save(Customer customer) {
        customerById.put(customer.getId(), customer);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerById.values());
    }

    public Customer findById(String id) {
        return customerById.get(id);
    }
}
