package org.launchcode.controllers;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="menu")
public class MenuController {
    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Cheese Menus");

        return "/menu/index";
    }

    @RequestMapping(value="add", method= RequestMethod.GET)
    public String viewAddMenuForm(Model model){
        model.addAttribute("menu", new Menu());
        model.addAttribute("title", "Add Menu");


        return "menu/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddMenuForm(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("menu", menu);
            return "menu/add";
        }

        menuDao.save(menu);

        return "redirect:view/" + menu.getId();
    }

    @RequestMapping(value="view/{menuId}", method=RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){
        Menu menu = menuDao.findOne(menuId);

        model.addAttribute("menu", menu);
        model.addAttribute("title", menu.getName());

        return "menu/view";
    }

    @RequestMapping(value="add-item/{menuId}", method=RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId){
        Menu menu = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());

        model.addAttribute("form", form);
        model.addAttribute("title", "Add Item to Menu: " + menu.getName());

        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method=RequestMethod.POST)
    public String processAddItemForm(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        Menu m = menuDao.findOne(form.getMenuId());
        Cheese c = cheeseDao.findOne(form.getCheeseId());
        m.addCheese(c);

        menuDao.save(m);

        return "redirect:view/" + form.getMenuId();
    }
}
