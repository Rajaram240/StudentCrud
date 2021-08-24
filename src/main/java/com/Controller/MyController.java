package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.DAO.StudentDAO;
import com.model.Student;
import com.service.StudentServiceImpl;

@Controller
public class MyController {
@Autowired
	private StudentServiceImpl service;
	
//get list of student
@GetMapping("/")
public String viewHomePage(Model model) {
    List<Student> liststudent = service.listAll();
    model.addAttribute("liststudent", liststudent);
    System.out.print("Get / ");	
    return "index";
}

@GetMapping("/new")
public String add(Model model) {
    model.addAttribute("student", new Student());
    return "new";
}

@RequestMapping(value = "/save", method = RequestMethod.POST)
public String saveStudent(@ModelAttribute("student") Student std) {
    service.save(std);
    return "redirect:/";
}

@RequestMapping("/edit/{id}")
public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
    ModelAndView mav = new ModelAndView("new");
    Student std = service.get(id);
    mav.addObject("student", std);
    return mav;
    
}
@RequestMapping("/delete/{id}")
public String deletestudent(@PathVariable(name = "id") int id) {
    service.delete(id);
    return "redirect:/";
}
}