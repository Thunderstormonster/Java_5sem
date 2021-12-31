package bstu.kachanova.javaproject.demo.rest;

import bstu.kachanova.javaproject.demo.dto.AuthRequest;
import bstu.kachanova.javaproject.demo.dto.AuthResponse;
import bstu.kachanova.javaproject.demo.dto.RegistrationRequest;
import bstu.kachanova.javaproject.demo.dto.UserResponse;
import bstu.kachanova.javaproject.demo.exception.ControllerException;
import bstu.kachanova.javaproject.demo.jwt.JwtProvider;
import bstu.kachanova.javaproject.demo.models.User;
import bstu.kachanova.javaproject.demo.service.MailSender;
import bstu.kachanova.javaproject.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

//это общие принципы организации взаимодействия приложения/сайта с сервером посредством протокола HTTP.

@RestController
public class MainRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private MailSender mailSender;

    private static final Logger logger = Logger.getLogger(MainRestController.class);

    //post для логирования
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws ControllerException
    {
        try{
            logger.debug("enter to login user");
            //проверка по логину и паролю
            User user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
            if(user != null)
            {
                //генерирует токин для авторизации
                String token = jwtProvider.generateToken(user.getLogin());
                //отправляет код авторизации и состояние
                AuthResponse response = new AuthResponse(token, user.getUserRole().getName());
                System.out.println(user.getUserRole().getName());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {
                throw new ControllerException("not such user");
            }
        } catch (ControllerException e) {
            logger.error("error login");

            throw new ControllerException("No such user with this credentials", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest)
    {
        logger.debug("try to register user");
        if(!userService.existsUserByLogin(registrationRequest.getLogin()))
        {
            User user = new User();
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            user.setEmail(registrationRequest.getEmail());
            user.setActive(true);
            //код активации- уникальная последовательность
            user.setActivationCode(UUID.randomUUID().toString());
            //заносим в бд user
            userService.saveUser(user);
            if(!user.getEmail().isEmpty()){
                String message = String.format("Nice to meet you, %s!\n " +
                                "Greetings, Ladies adn Gentlemen!Welcome to my Java Demo Book! Please, visit next link: http://localhost:8080/activate/%s",
                        user.getLogin(), user.getActivationCode());
                mailSender.sendMail(user.getEmail(), "Your activation code", message);
            }
            //проверка состояния запроса
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    //код активации для вывода на страницу и оповещения
    @GetMapping("/activate/{code}")
    public ModelAndView activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");

        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login2");
        return modelAndView;
    }

    //если все таки не передалось сообщение через MailSender
    @PostMapping("/authorized")
    public ResponseEntity<?> isAuthorized() throws ControllerException {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //вариант1 для вывода пользователей
    @PostMapping("/users")
    public List<User> getUsers() throws ControllerException {
        try {
            logger.debug("getting all users");

            return userService.findAll();
        } catch (Exception e) {
            logger.error("error get all users");

            throw new ControllerException("getUsers", e);
        }
    }

    //вариант2 для вывода пользователей
    @PostMapping("/getUser")
    public UserResponse getUser(@RequestHeader(name = "Authorisation") String jwt) throws ControllerException {
        try {

            String userName = jwtProvider.getLoginFromToken(jwt.substring(7));
            User user = userService.findByLogin(userName);

            return new UserResponse(user.getId(), user.getLogin(), user.getUserRole().getName());
        } catch (Exception e) {
            throw new ControllerException("getUser", e);
        }
    }
}

