package bstu.kachanova.javaproject.demo.rest;

import bstu.kachanova.javaproject.demo.dto.NameRequest;
import org.springframework.validation.BindingResult;
import bstu.kachanova.javaproject.demo.dto.BookRequestNoIdRent;
import bstu.kachanova.javaproject.demo.dto.BookRequestNoRent;
import bstu.kachanova.javaproject.demo.exception.ControllerException;
import bstu.kachanova.javaproject.demo.exception.RepositoryException;
import bstu.kachanova.javaproject.demo.exception.ServiceException;
import bstu.kachanova.javaproject.demo.models.RentForm;
import bstu.kachanova.javaproject.demo.models.Scooter;
import bstu.kachanova.javaproject.demo.repository.UserRentFormRepository;
import bstu.kachanova.javaproject.demo.service.BookService;
import bstu.kachanova.javaproject.demo.validator.RentValidator;
import bstu.kachanova.javaproject.demo.service.UserRentFormService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class BookRestController {
    @Autowired
    private RentValidator rentValidator;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserRentFormService userRentFormService;
    @Autowired
    private UserRentFormRepository userRentFormRepository;

    private static final Logger logger = Logger.getLogger(BookRestController.class);

    @PostMapping("/admin/createScooter")
    public ResponseEntity<?> createScooter(@RequestBody @Validated BookRequestNoIdRent bookRequestNoIdRent, BindingResult bindingResult) throws ControllerException {
        Scooter stuff = new Scooter(
                bookRequestNoIdRent.getName(),
                bookRequestNoIdRent.getDescription(),
                bookRequestNoIdRent.getCost(),
                bookRequestNoIdRent.getExpirationDate()
        );
        try {
            Scooter test=new Scooter();
            test.setCost(15);
            rentValidator.validate2(test,bindingResult);
            bookService.create(stuff);
            return new ResponseEntity<>(stuff, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @DeleteMapping("/admin/deleteScooterByNameA")
    public ResponseEntity<?> deleteScooterByNameA(@RequestBody NameRequest nameRequest) throws ControllerException {
        try {
            Scooter man = bookService.getByName(nameRequest.getName());
            bookService.deleteByName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PutMapping("/admin/updateScooter")
    public ResponseEntity<?> updateScooter(@RequestBody BookRequestNoRent BookRequestNoRent)throws ControllerException {
        try {
            Scooter man = bookService.getById( BookRequestNoRent.getId());
            bookService.updateBookById(
                    BookRequestNoRent.getId(),
                    BookRequestNoRent.getName(),
                    BookRequestNoRent.getDescription(),
                    BookRequestNoRent.getCost()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
    @DeleteMapping("/user/deleteScooterByNameU")
    public ResponseEntity<?> deleteScooterByNameU(@RequestBody NameRequest nameRequest)throws ControllerException {

        try {
            Scooter man = bookService.getByName(nameRequest.getName());
            userRentFormRepository.deleteByUserName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException | RepositoryException e ) {
            throw new ControllerException(e);

        }


    }
    @GetMapping("/admin/getAllScootersForAdmin")
    public ResponseEntity<?> getAllScootersForAdmin() throws ControllerException{
        try {
            return new ResponseEntity<>(bookService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {

            throw new ControllerException(e);

        }
    }
    @PostMapping("/admin/isScooterExistByName")
    public ResponseEntity<?> isScooterExistByName(@RequestBody NameRequest nameRequest) throws ControllerException{
        try {
            if(!bookService.existsByName(nameRequest.getName())){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }

    }
    @GetMapping("/user/userGetScooterByName/{name}")
    public ResponseEntity<?> userGetScooterByName(@PathVariable(name = "name")String name)throws ControllerException {
        Scooter stuff = null;
        try {
            stuff = bookService.getByName(name);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("admin/adminGetScooterByName/{name}")
    public ResponseEntity<?> adminGetScooterByName(@PathVariable(name = "name")String name) throws ParseException, ControllerException {
        Scooter stuff = null;
        try {
            stuff = bookService.getByName(name);
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(stuff.getExpirationDate());
            System.out.println(date);
            stuff.setExpirationDate(simpleDateFormat.parse(date));
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occured");
            throw new ControllerException(e);

        }

    }
    @GetMapping("/user/getAllScootersForUser")
    public ResponseEntity<?> getAllScootersForUser()throws ControllerException {
        try {
            return new ResponseEntity<>(bookService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
}

