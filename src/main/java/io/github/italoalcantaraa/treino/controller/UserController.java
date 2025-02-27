package io.github.italoalcantaraa.treino.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.italoalcantaraa.treino.model.User;
import io.github.italoalcantaraa.treino.repository.UserRepository;

@Controller
public class UserController {

    private List<User> usersList = new ArrayList<>();

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("user", new User());

        return mv;
    }

    @PostMapping("/create")
    public String create(User user) {


        if(user.getId() != null && user.getId() != "") {
            System.out.println("O USUARIO DO CARALHO " + user);
            User updtUser = usersList.stream()
                .filter(e -> e.getId().equals(user.getId()))
                .findFirst()
                .get();

            usersList.set(usersList.indexOf(updtUser), user);
            userRepository.save(user);
        } else {
            user.setId(UUID.randomUUID().toString());
            userRepository.save(user);    
        }

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list");
        
        usersList = userRepository.findAll();
        mv.addObject("users", usersList);
        
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("home");

        User updtUser = usersList.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .get();

        mv.addObject("user", updtUser);
        return mv;
    } 

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        userRepository.deleteById(id);

        User removeUser = usersList.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .get();

        usersList.remove(removeUser);

        return "redirect:/list";
    }

}
