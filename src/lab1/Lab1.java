/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.time.LocalDate;
import java.util.Scanner;
//import sun.security.krb5.internal.crypto.Des;

/**
 *
 * @author PC
 */
public class Lab1 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        LocalDate day = java.time.LocalDate.now();
        Scanner sc = new Scanner(System.in);
        Manager mn = new Manager();
        componentHandling ch = new componentHandling();
        String fileName = mn.getFileName();
        String fileRe = "fileRe.dat";

        
        
        
        Menu menu=new Menu();
        int option = 1;
            menu.add("Add cubes ice");
            menu.add("Remove cubes ice");
            menu.add("Add items");
            menu.add("Search items");
            menu.add("Change amount");
            menu.add("Remove items");
            menu.add("Sort follow date and print all");
            menu.add("Add new repice");
            menu.add("Repice check");
            menu.add("Search by amount");
            menu.add("Quit");
            //menu.add("Exercise 6");
            //menu.add("Exit");
            menu.setMax(11);
            
            
        while(option > 0 && option < 11){
            System.out.println("=====================================================");
            mn.loadData(fileName);
            mn.loadDataR(fileRe);
            mn.overDate();
            mn.sCapacity();
            mn.noteIce();
            System.out.println("Today is: " + day);
            System.out.println("Hello, the current capacity of the refrigerator is " + mn.getCapacity() + "/" + mn.getmCapacity());
            option = menu.getUserChoice();
            switch(option){
                case 1: mn.addIce();
                        break;
                case 2: mn.removeIce();
                        break;
                case 3: mn.addItem();
                        break;
                case 4: mn.searchItems();
                        break;
                case 5: mn.changeAmount();
                        break;
                case 6: mn.removeItems();
                        break;
                case 7: mn.printSort();
                        break;
                case 8: mn.addRepice();
                        mn.saveDataR(fileRe);
                        break;
                case 9: mn.checkRepice();
                        break;     
                case 10:mn.searchByAmount();
                        break;
            }
        }

    }
}
