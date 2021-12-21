package bstu.kachanova.javaproject.demo.rest;

import bstu.kachanova.javaproject.demo.dto.NameRequest;
import bstu.kachanova.javaproject.demo.dto.ScooterRequestNoIdRent;
import bstu.kachanova.javaproject.demo.dto.ScooterRequestNoRent;
import bstu.kachanova.javaproject.demo.exception.ControllerException;
import bstu.kachanova.javaproject.demo.exception.RepositoryException;
import bstu.kachanova.javaproject.demo.exception.ServiceException;
import bstu.kachanova.javaproject.demo.models.Scooter;
import bstu.kachanova.javaproject.demo.repository.UserRentFormRepository;
import bstu.kachanova.javaproject.demo.service.ScooterService;
import bstu.kachanova.javaproject.demo.service.UserRentFormService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class ScooterRestController {
    @Autowired
    private ScooterService scooterService;
    @Autowired
    private UserRentFormService userRentFormService;
    @Autowired
    private UserRentFormRepository userRentFormRepository;

    private static final Logger logger = Logger.getLogger(ScooterRestController.class);

    @PostMapping("/admin/createScooter")
    public ResponseEntity<?> createScooter(@RequestBody ScooterRequestNoIdRent scooterRequestNoIdRent) throws ControllerException {
        Scooter stuff = new Scooter(
                scooterRequestNoIdRent.getName(),
                scooterRequestNoIdRent.getDescription(),
                scooterRequestNoIdRent.getCost(),
                scooterRequestNoIdRent.getExpirationDate()
        );
        try {
            scooterService.create(stuff);
            return new ResponseEntity<>(stuff, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @DeleteMapping("/admin/deleteScooterByNameA")
    public ResponseEntity<?> deleteScooterByNameA(@RequestBody NameRequest nameRequest) throws ControllerException {
        try {
            Scooter man = scooterService.getByName(nameRequest.getName());
            scooterService.deleteByName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PutMapping("/admin/updateScooter")
    public ResponseEntity<?> updateScooter(@RequestBody ScooterRequestNoRent ScooterRequestNoRent)throws ControllerException {
        try {
            Scooter man = scooterService.getById( ScooterRequestNoRent.getId());
            scooterService.updateScooterById(
                    ScooterRequestNoRent.getId(),
                    ScooterRequestNoRent.getName(),
                    ScooterRequestNoRent.getDescription(),
                    ScooterRequestNoRent.getCost()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
    @DeleteMapping("/user/deleteScooterByNameU")
    public ResponseEntity<?> deleteScooterByNameU(@RequestBody NameRequest nameRequest)throws ControllerException {

        try {
            Scooter man = scooterService.getByName(nameRequest.getName());
            userRentFormRepository.deleteByUserName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException | RepositoryException e ) {
            throw new ControllerException(e);

        }


    }
    @GetMapping("/admin/getAllCompsForAdmin")
    public ResponseEntity<?> getAllCompsForAdmin() throws ControllerException{
        try {
            return new ResponseEntity<>(scooterService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {

            throw new ControllerException(e);

        }
    }
    @PostMapping("/admin/isScooterExistByName")
    public ResponseEntity<?> isScooterExistByName(@RequestBody NameRequest nameRequest) throws ControllerException{
        try {
            if(!scooterService.existsByName(nameRequest.getName())){
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
            stuff = scooterService.getByName(name);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("admin/adminGetScooterByName/{name}")
    public ResponseEntity<?> adminGetScooterByName(@PathVariable(name = "name")String name) throws ParseException, ControllerException {
        Scooter stuff = null;
        try {
            stuff = scooterService.getByName(name);
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
    @GetMapping("/user/getAllCompsForUser")
    public ResponseEntity<?> getAllCompsForUser()throws ControllerException {
        try {
            return new ResponseEntity<>(scooterService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
}

