package rikkei.academythuchanh.service;

import rikkei.academythuchanh.model.Customer;
import rikkei.academythuchanh.service.ICustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceIMPL implements ICustomerService {
    private static final Map<Integer, Customer> customers;

    static {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "Chinh", "chinhnd2@rikkei.academy", "Hanoi"));
        customers.put(2, new Customer(2, "Dung", "dung@rikkei.academy", "Danang"));
        customers.put(3, new Customer(3, "Hai", "dung@rikkei.academy", "Saigon"));
        customers.put(4, new Customer(4, "Diep", "diep@rikkei.academy", "Beijin"));
        customers.put(5, new Customer(5, "Hoai", "hoai@rikkei.academy", "Miami"));
        customers.put(6, new Customer(6, "Linh", "linh@rikkei.academy", "Newyork"));
    }

    //    private int generateNewId() {
//        return customers.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
//    }
    private int generateNewId() {
        int maxId = 0;
        for (Integer key : customers.keySet()) {
            if (key > maxId) {
                maxId = key;
            }
        }
        return maxId + 1;
    }

    @Override
    public void save(Customer customer) {
        customer.setId(generateNewId());
        customers.put(customer.getId(), customer);
    }


    @Override

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }


    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customers.put(id, customer);
    }

    @Override
    public void remove(int id) {
        customers.remove(id);
    }

}
