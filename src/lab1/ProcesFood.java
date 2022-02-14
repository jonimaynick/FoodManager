/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class ProcesFood implements Serializable{
    private String name;
    private int ingredientNumber;
    private String repice[] = new String[ingredientNumber];
    private int quantity[] = new int[ingredientNumber];

    public ProcesFood(String name, int ingredientNumber, String[] repice, int[] quatity) {
        this.name = name;
        this.ingredientNumber = ingredientNumber;
        this.repice = repice;
        this.quantity = quatity;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIngredientNumber() {
        return ingredientNumber;
    }

    public void setIngredientNumber(int ingredientNumber) {
        this.ingredientNumber = ingredientNumber;
    }

    public String[] getRepice() {
        return repice;
    }

    public void setRepice(String[] repice) {
        this.repice = repice;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProcesFood{" + "name=" + name + '}';
    }
    
    
}
