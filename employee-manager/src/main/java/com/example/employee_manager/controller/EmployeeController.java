 package com.example.employee_manager.controller;

import com.example.employee_manager.model.Employee;
import com.example.employee_manager.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/")
    public String home() {
        return "redirect:/employees";
    }

    // READ
    @GetMapping("/employees")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("employees", service.search(keyword));
        model.addAttribute("employee", new Employee());
        model.addAttribute("keyword", keyword);
        return "employees";
    }

    // CREATE
    @PostMapping("/employees")
    public String create(@Valid @ModelAttribute("employee") Employee employee,
                         BindingResult result, Model model, RedirectAttributes ra) {
        if (service.emailExists(employee.getEmail())) {
            result.rejectValue("email", "duplicate", "This email is already registered");
        }
        if (result.hasErrors()) {
            model.addAttribute("employees", service.search(null));
            return "employees";
        }
        service.save(employee);
        ra.addFlashAttribute("message", "Employee saved successfully");
        return "redirect:/employees";
    }

    // Show edit form
    @GetMapping("/employees/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return service.findById(id).map(e -> {
            model.addAttribute("employee", e);
            return "editemployee";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Employee not found");
            return "redirect:/employees";
        });
    }

    // UPDATE
    @PostMapping("/employees/{id}/update")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("employee") Employee employee,
                         BindingResult result, RedirectAttributes ra) {
        if (service.emailUsedByAnother(employee.getEmail(), id)) {
            result.rejectValue("email", "duplicate", "This email is already registered");
        }