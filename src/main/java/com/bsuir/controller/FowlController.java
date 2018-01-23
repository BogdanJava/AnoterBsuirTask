package com.bsuir.controller;

import com.bsuir.model.Fowl;
import com.bsuir.service.FowlService;
import com.bsuir.validation.FowlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс, в котором мы указываем "маппинги" нашего приложения - адреса, по которым может обращаться пользователь.
 * 1){@link RequestMapping} @RequestMapping - в эту аннотацию мы передаём маппинг и указываем Http-метод.
 * 2){@link Controller} @Controller - компонент, а также слой MVC-приложения
 * 3) Возвращаемое значение методов контроллера.
 * В нашем случае методы возвращают или тип String, или объект ModelAndView. В случае со String всё просто -
 * мы указываем имя jsp-файла, который хотим отобразить. При использовании ModelAndView мы можем не только
 * адресовать наш запрос на страницу, но и добавить объекты, которые мы сможем использовать на jsp-странице.
 */

@Controller
public class FowlController {

    //Объект для логирования
    private static final Logger logger = LoggerFactory.getLogger(FowlController.class);

    //Объект-сервис. Содержит методы для работы с бизнес-логикой приложения
    @Autowired
    private FowlService fowlService;

    //Объект-валидатор. Используется для валидации приходящего с формы объекта класса Fowl. Если валидация
    //в объект BindingResult заносятся сообщения об ошибках, которые выводятся на форме
    @Autowired
    private FowlValidator fowlValidator;

    @RequestMapping(value = {"/welcome", "/"}, method = RequestMethod.GET)
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addNew");
        modelAndView.addObject("fowl", new Fowl());

        return modelAndView;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(){
        return "error";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAll");

        List<Fowl> fowlList = fowlService.getAll();
        modelAndView.addObject("fowlList", fowlList);
        modelAndView.addObject("fowl", new Fowl());

        return modelAndView;
    }

    /**
     * Объект с формы можно получить 2 способами: через @RequestParam или @ModelAttribute.
     * 1) RequestParam - использование стандартное html-формы.
     * 2) ModelAttribute - использование SpringMVC-формы со специальным префиксом "form"
     * В нём мы связываем java-объект и input'ы нашей формы.
     *
     * В приложении продемонстрировано оба варианта обработки формы
     */

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam String name, @RequestParam String number,
                       @RequestParam String id, @RequestParam String description){

        Fowl fowl = new Fowl(Integer.parseInt(id), name, description, Integer.parseInt(number));
        fowlService.update(fowl);

        return "redirect:/show";
    }

    /**
     * Непосредственно из базы объект не удаляется, мы просто выставляем поле deleted = 1
     */

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("deleteId") List<String> deleteIds){
        fowlService.delete(deleteIds);
        return "redirect:/show";
    }

    /**
     * Так называемый "флэш-атрибут" - атрибут, который будет доступен на странице только
     * после редиректа. При перезагрузке страницы он пропадает из области видимости.
     *
     * после @ModelAttribute всегда объявляется параметр типа BindingResult, который может содержать в себе ошибки
     * заполнения формы пользователем.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("fowl") Fowl fowl, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){
        fowlValidator.validate(fowl, bindingResult);

        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()){
           modelAndView.setViewName("addNew");
           return modelAndView;
        }
        try {
            fowlService.save(fowl);
        } catch (SQLException | IOException e){
            logger.error("Exception occurred: ", e);
            RedirectView redirectView = new RedirectView();
            redirectView.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            redirectView.setUrl("error");
            modelAndView.setView(redirectView);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return modelAndView;
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        redirectAttributes.addFlashAttribute("message", "New fowl's been successfully added");
        redirectView.setUrl("show");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

}
