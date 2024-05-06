package springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springboot.model.User;
import springboot.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUser(Model model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "one_user";
    }

    @GetMapping("/create")
    public String newPerson(@ModelAttribute("user") User user) {
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setId(id);
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(Model model, @PathVariable("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("allowDelete", true);
        model.addAttribute("user", user);
        return "one_user";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

}