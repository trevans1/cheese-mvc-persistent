package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    public AddMenuItemForm(Menu m, Iterable<Cheese> cs){
        menu = m;
        cheeses = cs;
    }

    public AddMenuItemForm() { }


    public Menu getMenu(){ return menu; }
    public void setMenu(Menu m) { menu = m; }

    public Iterable<Cheese> getCheeses(){ return cheeses; }
    public void setCheeses(Iterable<Cheese> cs) { cheeses = cs; }

    public int getMenuId(){ return menuId; }
    public void setMenuId(int id){ menuId = id; }

    public int getCheeseId() { return cheeseId; }
    public void setCheeseId(int id) { cheeseId = id; }

}
