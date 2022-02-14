/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */

public class componentHandling extends ArrayList<ProcesFood>{
    Scanner sc = new Scanner(System.in);
    Manager mng = new Manager();
    
    
    public void addRepice(){
        
        int nIngredientNumber = getInt.get(1, 10, "How many materials is it made from ?\nEnter your choice: ");
        System.out.println("Finished product name: ");
        String nName = sc.nextLine().toUpperCase();
        String nRepice[] = new String[nIngredientNumber];
        for(int i = 0; i < nRepice.length; i++){
            System.out.println("The " + (i + 1) + " is: ");
            nRepice[i] = sc.nextLine().toUpperCase();
        }
        int nQua[] = new int[nIngredientNumber];
        for(int i = 0; i < nQua.length; i++){
            nQua[i] = getInt.get(1, 10000, "The " + nRepice[i] + " need: ");
        }
        this.add(new ProcesFood(nName,nIngredientNumber, nRepice, nQua));
    }
    // check name co ton tai k
    public boolean repiceIn(String i){
        
        int trr = mng.findSize();
        System.out.println(trr);
        for(int j = 0; j < mng.findSize(); j++){
            i.equals(mng.findName(j));
            return true;
        }
        return false;
    }  

    //check du list name hay k
    public int checkListName(String[] name){
        int allFlag = 1; // check tong the
        int flag = 0; // check trong vong lap
        //boolean chech = 
        //dem neu khong tim thay ten
        for(int i = 0; i < name.length; i++){
            if(repiceIn(name[i])== true);
            else flag++;
        }
        //them ten vao mang neu khong tim thay
        if(flag >= 1){    
            String arr[] = new String[flag];
            for(int i =0; i < arr.length; i++){
                for(int j=0; j < name.length; j++){
                    if(repiceIn(name[j])== false) arr[i] = name[j];
                }
            }
            System.out.println("Not enough ingredients!!\nThe missing ingredients are: ");
            for(int i = 0; i < arr.length; i++){
                System.out.print(arr[i]);
                System.out.print("\t");
                if(i == (arr.length - 1))System.out.println("");
            }
            
            allFlag = 0;
            return allFlag;
        }
        return allFlag;
    }
    // lay name valid
    public int iExist(String i){
        
        int num = 0;
            for(int j = 0; j < mng.findSize(); j++){
                i.equals(mng.findName(j));
                num = j;
            }
        return num;
    }  
    
    //check co du so luong k
    public boolean checkAmou(String[] name, int[] num){
        
        int y, flag = 0;
        boolean ans = true;
        String[] cName = new String[name.length + 1];
        int[] cNum = new int[num.length + 1];
        //mang name
        for(int i = 0; i < name.length; i++){
            for(int j = 0; j < mng.findSize(); j++ ){
                if(name[i].equals(mng.findName(j))){
                    cName[i] = mng.findName(j);
                    cNum[i] = mng.findAmount(j);
                }
            }
        }
        //
        for(int i = 0; i < num.length; i++){
            if(cNum[i] < num[i]){
                System.out.println("The " + name[i] + " is not enought!!");
                flag = 1;
            }
        }
        if(flag == 1) ans = false;
        else ans = true;  
        return ans;
    }
    
    

    
    
    public void checkRepice(){
        if (this.isEmpty()){System.out.println("Nothing to check!"); return;}
        
        for(int i = 0; i < this.size(); i++) System.out.println((i + 1)+ ". "  + this.get(i).getName());
        int num = getInt.get(1, (this.size() + 1), "What repice you want to check ?\nChoose one: ");
        num--;
        int listN = checkListName(this.get(num).getRepice());
        if(listN == 1){
            boolean ans = checkAmou(this.get(num).getRepice(), this.get(num).getQuantity());
            if(ans){System.out.println(this.get(num).getName() + " Can processing");}
        }
        
    }
    //save data
    public void loadData(String fName){
        if(this.size() > 0) this.clear();
        try {
            File f = new File(fName);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            ProcesFood b;
            while ((b=(ProcesFood)(fo.readObject())) != null){
                this.add(b);
            }
            fo.close();fi.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void saveData(String fName){
        if(this.size() == 0){
            System.out.println("Empty List!!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (ProcesFood b : this) fo.writeObject(b);
            fo.close();f.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
