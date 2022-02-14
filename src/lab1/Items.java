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
public class Items implements Serializable{
    private String code;
    private String name;
    private String type;
    private Date date;
    private String place;
    private int amount;
    private int price;
    
    //construc
    public Items(String code,String name , String type, Date date, String place, int amount, int price) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.date = date;
        this.place = place;
        this.amount = amount;
        this.price = price;
    }
    
    // geter setter

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    // toString

    @Override
    public String toString() {
        return "Items{" + "code=" + code + ", name=" + name + ", type=" + type + ", date=" + date + ", place=" + place + ", amount=" + amount + ", price=" + price + '}';
    }
}
