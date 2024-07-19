package erd.exmaple.erd.example.domain.controller;

import erd.exmaple.erd.example.domain.dto.UserDTO;
import erd.exmaple.erd.example.domain.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Moe")
public class MoeController {

    private static final Logger log = LoggerFactory.getLogger(MoeController.class);

    private final UserService userService;

    @GetMapping
    public String redirectToLogin() {
        log.info("Redirecting to login page from Moe");
        return "redirect:/user/login";
    }

    @PostMapping("/main")
    @ResponseBody
    public UserDTO login(@RequestBody Long id) {
        UserDTO userDto = userService.findUserById(id);
        log.info("Main page accessed by user id: {}", userDto.getId());
        return userDto;
    }
}







