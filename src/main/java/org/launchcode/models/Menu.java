package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=31)
    private String name;

    @ManyToMany
    private List<Cheese> cheeses;

    public Menu(){

    }

    public Menu(String name){
        this();
        this.name = name;
    }


    public void addCheese(Cheese c){
        cheeses.add(c);
    }


    public String getName(){ return name; }
    public void setName(String s) { name = s; }
    public int getId() {return id;}
    public void setId(int i) {id = i;}
    public List<Cheese> getCheeses(){return cheeses;}
}
