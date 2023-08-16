package rikkei.academythuchanh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academythuchanh.model.Customer;
import rikkei.academythuchanh.service.CustomerServiceIMPL;
import rikkei.academythuchanh.service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/customer"})
public class CustomerController {
    private final ICustomerService customerService = new CustomerServiceIMPL();

    @GetMapping
    public String index(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }

    @PostMapping("/customer/save")
    public String save(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer); // Assuming you have a save method in your service
        return "redirect:/customer"; // Redirect to the customer list page
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/view"; // Show the customer view page
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customer"; // Redirect to the customer list page
    }
}
