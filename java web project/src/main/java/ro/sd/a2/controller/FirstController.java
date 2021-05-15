package ro.sd.a2.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.service.UserService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * This is user controller
 */
@Controller
@Transactional
public class FirstController
{

    private static final Logger log = LoggerFactory.getLogger(FirstController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ModelAndView showProfile()
    {
        log.info("Called /profile page");
        log.warn("A fost creat un profil nou {{ID USER}}, dar nu este complet");
        // log.error("No user having ID {{A}} was found!");

        String a = "Ana are" + "mere " + "pere";
        StringBuilder builder = new StringBuilder().append("Ana are ").append("mere si ").append("pere");

        //validation if needed
        //shall we log a little?
        ModelAndView mav = new ModelAndView();

        User user = new User("Bubu");
        user.setEmail("user@email.com");

        mav.addObject("userObj", user);
        mav.addObject("numeStudent", user.getName());
        mav.addObject("email", user.getEmail());

        // adaugi x obiecte
        mav.setViewName("profile");

        // $(  ${obj} 1  1

        //log the final outcome: Success y?
        return mav;
    }

    @GetMapping("/page")
    public ModelAndView page()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("page");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/test")
    public ModelAndView test()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");
        return mav;
    }

    /**
     * Ce face metoda?
     *
     * @param userDto - asta.....
     * @return .... MAV
     */
    @PostMapping("/addPerson")
    public ModelAndView addUser(UserDto userDto)
    {
        //log.info("Received a request to create a new user .");

        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj", userDto);
        mav.addObject("numeStudent", userDto.getName());

        String result = userService.createUser(userDto);


        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;
    }

    @PostMapping("/updatePerson")
    public ModelAndView updateUser(UserDto userDto)
    {
        log.info("received a request to update an user.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj1", userDto);
        mav.addObject("numeStudent1", userDto.getName());

        String result = userService.updateUser(userDto);

        mav.addObject("result", result);
        mav.setViewName("home");
        return mav;
    }

    @PostMapping("/deletePerson")
    public ModelAndView deleteUser(UserDto userDto)
    {
        log.info("received a request to update an user.");

        ModelAndView mav = new ModelAndView();
        mav.addObject("userObj1", userDto);
        mav.addObject("numeStudent1", userDto.getName());

        String result = userService.deleteUser(userDto);

        mav.addObject("result", result);

        mav.setViewName("home");
        return mav;
    }

    @GetMapping("/showPersons")
    public ModelAndView showUser()
    {
        ModelAndView mav = new ModelAndView();
        List<UserDto> userDtos = userService.getAllUsers();
        mav.addObject("users", userDtos);

        mav.setViewName("my-page");

        return mav;
    }

}
