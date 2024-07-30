package erd.exmaple.erd.example.domain.controller;

<<<<<<< HEAD
import erd.exmaple.erd.example.domain.dto.UserDTO;
import erd.exmaple.erd.example.domain.service.UserService.UserServiceSocial;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

=======
import erd.exmaple.erd.example.domain.dto.ExhibitionDTO;
import erd.exmaple.erd.example.domain.dto.PopupStoreDTO;
import erd.exmaple.erd.example.domain.dto.UserDTO;
import erd.exmaple.erd.example.domain.enums.Region;
import erd.exmaple.erd.example.domain.service.RegionService;
import erd.exmaple.erd.example.domain.service.UserService.UserServiceSocial;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
@RestController
@RequiredArgsConstructor
@RequestMapping("/Moe")
public class MoeController {

    private static final Logger log = LoggerFactory.getLogger(MoeController.class);

    private final UserServiceSocial userServiceSocial;
<<<<<<< HEAD
=======
    private final RegionService regionService;
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33

    @GetMapping
    public String redirectToLogin() {
        log.info("Redirecting to login page from Moe");
        return "redirect:/user/login";
    }

    @PostMapping("/main")
    @ResponseBody
    public UserDTO login(@RequestBody Long id) {
        UserDTO userDto = userServiceSocial.findUserById(id);
        log.info("Main page accessed by user id: {}", userDto.getId());
        return userDto;
    }
<<<<<<< HEAD
=======

    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        // 세션에서 필터링 조건을 가져옴
        Region region = (Region) session.getAttribute("filterRegion");
        String district = (String) session.getAttribute("filterDistrict");

        List<ExhibitionDTO> exhibitions;
        List<PopupStoreDTO> popupStores;

        if (region == null && district == null) {
            exhibitions = regionService.getAllExhibitions();
            popupStores = regionService.getAllPopupStores();
        } else if (region != null && (district == null || district.isEmpty())) {
            exhibitions = regionService.getExhibitionsByRegion(region);
            popupStores = regionService.getPopupStoresByRegion(region);
        } else {
            exhibitions = regionService.getExhibitionsByDistrict(district);
            popupStores = regionService.getPopupStoresByDistrict(district);
        }

        model.addAttribute("exhibitions", exhibitions);
        model.addAttribute("popupStores", popupStores);

        return "main";
    }
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
}

