package com.lazy.springbooks.controller;

import com.lazy.springbooks.forms.FilmForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import com.lazy.springbooks.model.Film;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class FilmController {
    private static List<Film> films =new ArrayList<Film>();
    static {
        films.add(new Film(1,"Fight club","David Fincher"));
        films.add(new Film(2,"Dumkirk","Cristopher Nolan"));
        films.add(new Film(3,"The Breakfast club","John Hughes"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/","/index"})
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called");
        return modelAndView;
    }

    @GetMapping(value = {"/allfilms"})
    public ModelAndView filmList(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filmlist");
        model.addAttribute("films", films);
        log.info("/allfilms was called");
        return modelAndView;
    }

    @GetMapping(value = {"/addfilm"})
    public ModelAndView saveFilm(Model model){
        ModelAndView modelAndView = new ModelAndView("addfilm");
        FilmForm filmForm = new FilmForm();
        model.addAttribute("filmform", filmForm);
        log.info("/addfilm was called");
        return modelAndView;
    }

    @PostMapping(value = {"/addfilm"})
    public ModelAndView saveFilm(Model model, @ModelAttribute("filmform") FilmForm filmForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filmlist");
        int id = films.size();
        String name = filmForm.getName();
        String director = filmForm.getDirector();

        if(name != null && name.length() > 0 && director != null && director.length() > 0){
            Film newFilm = new Film(id,name,director);
            films.add(newFilm);
            model.addAttribute("films", films);

            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addfilm");
        log.info("/addfilm was called");
        return modelAndView;
    }

    @GetMapping(value = {"/updatefilm"})
    public ModelAndView updateFilm(Model model){
        ModelAndView modelAndView = new ModelAndView("updatefilm");
        FilmForm filmForm = new FilmForm();
        model.addAttribute("filmform", filmForm);
        log.info("/updatefilm was called");
        return modelAndView;
    }

    @PostMapping(value = {"/updatefilm"})
    public ModelAndView updateFilm(Model model, @ModelAttribute("filmform") FilmForm filmForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filmlist");
        int id = filmForm.getId();
        String name = filmForm.getName();
        String director = filmForm.getDirector();

        if(id >= 0 && id <= films.size() && name != null && name.length() > 0 && director != null && director.length() > 0){
            Film updatedFilm = new Film(id,name,director);
            films.set(id, updatedFilm);
            model.addAttribute("films", films);

            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("updatefilm");
        log.info("/updatefilm was called");
        return modelAndView;
    }

    @GetMapping(value = {"/deletefilm"})
    public ModelAndView deletePerson(Model model){
        ModelAndView modelAndView = new ModelAndView("deletefilm");
        FilmForm filmForm = new FilmForm();
        model.addAttribute("filmform", filmForm);
        log.info("/deletefilm was called");
        return modelAndView;
    }

    @PostMapping(value = {"/deletefilm"})
    public ModelAndView deletePerson(Model model, @ModelAttribute("filmform") FilmForm filmForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("filmlist");
        int id = filmForm.getId();
        String name = filmForm.getName();
        String director = filmForm.getDirector();

        if(id >= 0 && id <= films.size()){
            Film deletedFilm = new Film(id,name,director);
            films.remove(id);
            model.addAttribute("films", films);

            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("deletefilm");
        log.info("/deletefilm was called");
        return modelAndView;
    }

}
