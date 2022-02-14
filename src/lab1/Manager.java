/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.io.File;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author PC
 */
public class Manager extends ArrayList<Items> {
    LocalDate today = java.time.LocalDate.now();
    Scanner sc = new Scanner(System.in);
    
    String fileName = "fridge.dat", fileIce = "ice.txt";
    public String getFileName() {return fileName;}
    
    
    int ice = 0, mIce = 50;
    public int getmIce() {return mIce; }
    public int getIce() {return ice; }
    public void setIce(int ice) {this.ice = ice;}
    //Note Ice
    public void saveAll(){
        saveIce(fileIce);
        saveData(fileName);
    }
    
    
    public void noteIce(){
        loadIce(fileIce);
        System.out.println("Cubes Ice: [" + this.ice + "/" + this.mIce + "]");
        if(this.ice == 0) System.out.println("NO MORE ICE NOW, PLEASE ADD THEM!!!! ");
        else if(this.ice <= 25) System.out.println("Ice cubes are running out, let's add them !!!" + "[" +this.getIce() + "/" + this.getmIce() + "]");
    }
    
    public void addIce(){
        System.out.println("======ADD ICE=======");
        int flag = 0;
        int nIce = 0;
        if(this.ice >= this.mIce){System.out.println("Ice cubes are full !!!"); flag = 1;}
        while(flag == 0){
            nIce = getInt.get(1, "Please enter the number of ice cubes entered: ");
            if((this.ice + nIce) > this.mIce)System.out.println("Too much !!");
            else { ice += nIce; flag = 1;}
        }
        System.out.println("Add ice success. [" + this.ice + "/" + this.mIce + "]");
        saveIce(fileIce);
        
    }
    
    public void removeIce(){
        System.out.println("======REMOVE ICE=======");
        int flag = 0;
        
        int removeIce = getInt.get(1,51, "How many ice cubes do you want to take out? ");
        if(removeIce > this.ice){System.out.println("Not enough ice cubes!!! ");}
        //else if(removeIce == this.ice){setIce(0); System.out.println("Remove the ice cubes successfully.");}
        else {ice -= removeIce; System.out.println("Remove the ice cubes successfully. "); }
        saveIce(fileIce);
        //if (this.ice == 0){System.out.println("NOTE\nNO MORE ICE NOW, PLEASE ADD THEM!!!! ");}
    }
    
    // load Ice
    public void loadIce (String fName){
    //if (this.ice > 0) ice = 0;
    int loadIce = getIce();
    try {
    File f = new File(fName);
    if (!f.exists()) return;
    FileReader fr =  new FileReader(f);
    BufferedReader bf = new BufferedReader(fr);
    loadIce = Integer.parseInt(bf.readLine()) ;
    bf.close(); fr.close();
    setIce(loadIce);
    
    }
    catch(Exception e){System.out.println(e);}
    }
    // save Ice
    public void saveIce(String fName){
    //if(ice==0){System.out.println("Please add ice!!");return;}
    try {
    boolean append = true;
    File f = new File(fName);
    FileWriter fw = new FileWriter(f);
    PrintWriter pw = new PrintWriter(fw);
    pw.println(this.ice);
    pw.close(); fw.close();
    this.clear();
    }
    catch(Exception e){System.out.println(e);}}
    
    
    // Sức chứa
    private int mCapacity = 10000; 
    private int capacity ;
    public int getmCapacity() { return mCapacity; }
    public int getCapacity() { return capacity;}
    
    public void sCapacity(){
        this.capacity = countCapacity();
    }
    
    
    //I/O
    public void loadData(String fName){
        if(this.size() > 0) this.clear();
        try {
            File f = new File(fName);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Items b;
            while ((b=(Items)(fo.readObject())) != null){
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
            for (Items b : this) fo.writeObject(b);
            fo.close();f.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    

    
    // count capacity
    public int countCapacity(){
    int capacity = 0;
    for (int i = 0; i < this.size(); i++){
        capacity += this.get(i).getAmount();
    }
    return capacity;
    } 
    //add Type
    private String addType(){
    System.out.println("==========Type of items===========");
    String type = null;
    String ms = "Mask", rf = "Raw food", ac = "Acoholic", dr = "Drinks", sn = "Snacks", pf = "Processed food", vg = "Vegetable "; 
    int nType = getInt.get(1, 8, "What type of items ?\n1." + ms + "\n2." + rf + "\n3." + ac + "\n4." + dr + "\n5." + sn + "\n6." + pf + "\n7." + vg + "\n8.Other.\nPlease enter your choose (1->8):");
    if (nType == 8){
        System.out.println("What type of the items :");
        type = sc.nextLine().toUpperCase().trim();
    }
    else{
        if (nType == 1) type = ms;
        else if (nType == 2) type = rf;
        else if (nType == 3) type = ac;
        else if (nType == 4) type = dr;
        else if (nType == 5) type = sn;
        else if (nType == 6) type = pf;
        else type = vg;
    }
    return type;
    }
    
    // check days
    private boolean dayValid(Date date){
        int flag = 0;
        if (date.year % 400 == 0) flag = 1;
        
        else if (date.year % 4 ==0 && date.year % 100 != 0)flag = 1; 
        if(date.month < 1 || date.month > 12) {System.out.println("The day " + date.toString() + " is not valid!!"); return false;}
        else if (date.month == 1 || date.month == 3 || date.month == 5 || date.month == 7 || date.month == 8 || date.month == 10 || date.month == 12){
            if (date.day < 1 || date.day > 31) {System.out.println("The day " + date.toString() + " is not valid!!"); return false;}
        }
        else if (date.month == 2){
            if(flag == 0){
                if(date.day < 1 || date.day > 28){System.out.println("The day " + date.toString() + " is not valid!!"); return false;}
            }
            else if(flag == 1){
                if(date.day < 1 || date.day > 29) {System.out.println("The day " + date.toString() + " is not valid!!"); return false;}
            }
        }
        else if(date.month == 4 || date.month == 6 || date.month == 9 || date.month == 11){
            if(date.day < 1 || date.day > 31) {System.out.println("The day " + date.toString() + " is not valid!!"); return false;}
        }
        
        return true;
    }
    
    // add date
    private Date addDate(){
    System.out.println("==========Date of items===========");
    
    Date check = new Date(0, 0, 0);
    int flag = 1;
    
    while (flag == 1){
        check.setDay(getInt.get(1, 31, "Input Date day(1 -> 31):"));
        check.setMonth(getInt.get(1, 12, "Input Date month(1 -> 12):"));
        check.setYear(getInt.get(today.getYear(), (today.getYear() + 5), "Enter Date year: "));
        
        if (check.getYear() == today.getYear() && check.getMonth() < today.getMonthValue()){
            System.out.println("You have entered the wrong expiration date, or maybe your food has expired, please check again !!");
        }
        else if (check.getYear() == today.getYear() && check.getMonth() == today.getMonthValue() && check.getDay() <= today.getDayOfMonth()){
            System.out.println("You have entered the wrong expiration date, or maybe your food has expired, please check again !!");   
        }
        else flag = 0;
        
        if(dayValid(check) != true){System.out.println("It not have the day: " + check.getDay() + "/" + check.getMonth() + "/"+ check.getYear() + " please enter again"); flag = 1;}
    }
    return check;
    }
    
    //addAmount
    private int addAmount(){
    System.out.println("==========Amount of items===========");
    int amount = 0, flag = 1;
    sCapacity();
    while(flag == 1){
    System.out.println("The capacity of the current refrigerator is:" + capacity +"/" + mCapacity);
    amount = getInt.get(1, "Please enter amount of items(gam): ");
    if((amount + capacity) > mCapacity){
        System.out.println("The refrigerator does not have enough space, please check the amount again!!");
    }
    else {
        capacity += amount;
        flag = 0;
    }}
    return amount;
    }
    
    // print item
    public void printOne(int i){
        //System.out.println(this.get(i).getCode() + "\t|\t\t" + this.get(i).getName() + "\t\t|\t" + this.get(i).getType() + "\t|\t" + this.get(i).getDate() + "\t|\t" + this.get(i).getPlace() + "\t|\t" + this.get(i).getAmount() + "\t|\t" + this.get(i).getPrice());   
        System.out.printf("%-10s",this.get(i).getCode());System.out.print("|");
        System.out.printf("%-30s",this.get(i).getName());System.out.print("|");
        System.out.printf("%-17s",this.get(i).getType());System.out.print("|");
        System.out.printf("%-17s",this.get(i).getDate());System.out.print("|");
        System.out.printf("%-20s",this.get(i).getPlace());System.out.print("|");
        System.out.printf("%-10s",this.get(i).getAmount());System.out.print("|");
        System.out.printf("%-14s",this.get(i).getPrice());System.out.print("|");
        System.out.println("");
    }
    //print tabble
    public void printTable(){
        //System.out.println("Code" + "\t|\t\t" + "Name" + "\t\t|\t" + "Type" + "\t|\t" + "Date" + "\t|\t" + "Place" + "\t|\t" + "Amount" + "\t|\t" + "Price");
        System.out.printf("%-10s","Code");System.out.print("|");
        System.out.printf("%-30s","Name");System.out.print("|");
        System.out.printf("%-17s","Type");System.out.print("|");
        System.out.printf("%-17s","Date");System.out.print("|");
        System.out.printf("%-20s","Place");System.out.print("|");
        System.out.printf("%-10s","Amount");System.out.print("|");
        System.out.printf("%-14s","Price");System.out.print("|");
        System.out.println("");
    }
    // find code
    private int find(String aCode){
       for (int i=0; i<this.size(); i++)
       if (this.get(i).getCode().equals(aCode)) return i;
       return -1;
   }
    //add Code
    private String addCode(int Plc){
    System.out.println("==========Code of items===========");
    String sCode = null;
    String up = "FC", down = "CC";
    boolean duplicated = true;    
    if(Plc == 1)System.out.println("So you want to put in freezer compartment !!");
    else System.out.println("So you want to put in cooler compartment !!");
    
    while(duplicated){
        int nCode = getInt.get(0, 500, "Please input the number of code items(must be > 0 and < 500): ");
        if(Plc == 1) sCode = up + nCode;
        else sCode = down + nCode;
        duplicated  = !codeValid(sCode);  
        if (duplicated) System.out.println("The code is duplicated. Please try another number");
    }
    return sCode;
    }
    
    public boolean nameValid(String sName){
    boolean name = true;
        for(int i = 0 ; i < this.size(); i++){
            if(sName.equals(this.get(i).getName())) return name = false;
        }
        return name;
    }
    
    public String addName(){
        System.out.println("Please enter name of item: ");
        int flag = 0;
        String name = null;
        while(flag == 0){
            name = sc.nextLine().toUpperCase().trim();  
            if(name.isEmpty()){
                System.out.println("Name can not to blank!");
            }
            else if(nameValid(name) == false){
                System.out.println("This name is existed!!");
            }
            else {
            flag = 1;
            }
        }
        return name;
    }
    //add=================================================================================================
    public void addItem(){
    String nCode, nName, nType, nPlace;
    int nAmount, nPrice;
    Date nDate;
    
    System.out.println("Enter New Item Details:");
    int fPlace = getInt.get(1, 2, "What place you put in fridge?(1 or 2)\n1.Freezer compartment \n2.Cooler compartment\nInput the numbers: ");
    if(fPlace == 1) nPlace = "Freezer compartment";
    else nPlace = "Cooler compartment";
    nCode = addCode(fPlace);
    
    nName = addName();
    
    
    nType = addType();
    nDate = addDate();
    nAmount = addAmount();
    nPrice = getInt.get(0, "How much does this item cost: ");
    this.add(new Items(nCode,nName ,nType, nDate, nPlace, nAmount, nPrice));
    saveData(fileName);
    }
    //search Items
    public void searchItems (){
        System.out.println("Enter the name of items need to find: ");
        String fName =  sc.nextLine().toUpperCase().trim();
        boolean exist = false;
 
        System.out.println("List items found:");
        printTable();
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getName().indexOf(fName) != -1)
            {
                printOne(i);
                exist = true;
            }
        }
        
        if (exist = false) {
            System.out.println("\nHave no any items.");
        }
    }
    
    public void searchByAmount(){
        int flag = 1, num = 0;
        
        if(this.size() == 0){System.out.println("Nothing to find"); return;}
        
        int find = getInt.get(1, 10000, "Please enter the amount: ");
        printTable();
        for(int i = 0; i < this.size(); i++){
            if(this.get(i).getAmount() <= find){
                printOne(i);
                num++;
                flag = 0;
            }
        }
        /*
        if(flag == 0){
            
            int arr[] = new int[num];
            for(int i = 0; i < arr.length; i++){
            printOne(arr[i]);
        }       
        }*/
        
        if(flag == 1){
            System.out.println("Don't have items with amount <= " + find);
        }
    }
    
    
    //remove items
    public void removeItems(){
        if(this.size() == 0){
            System.out.println("Nothing to delete !!");
            return;
        }
        /*
        else if(this.size() == 1){
            int choice = getInt.get(1, 2, "There are only " + this.get(0).getName() + " in the fridge do you want to delete this ?\n1.Yes\n2.No\nEnter your choice: ");
            if(choice == 1){
                String dele = this.get(0).getName();
                this.remove(0);
                System.out.println("The items " + dele + " is deleted.");
                return;
            }
            else{System.out.println(this.get(0).getName() + " is still in the closet!!");return;}
        }*/
        
        System.out.println("Enter the code want to remove: ");
        String code = sc.nextLine().toUpperCase().trim();
        int pos = find(code);
        if (pos < 0) System.out.println("The items with code " + code + " is not exist!!");
        else {
            int sure = getInt.get(1, 2, "Found " + this.get(pos).getName() +"!!!\nDo you want to delete ?\n1.Yes\n2.No\nEnter your choice: ");
            if(sure == 1){
                System.out.println("The items " + this.get(pos).getName() + " is deleted.");
                this.remove(pos);
            }
            else {
                System.out.println(this.get(pos).getName() + " is still in the closet!!");
                return;
            }
        }
        saveData(fileName);
    }
    //sort date
    /*
    public void sortDate(){
    ArrayList<Items> sDate = this;
    int arr[] = new int[sDate.size()];

    
    for(int i = 0; i < sDate.size(); i++){
       arr[i] = countDate(sDate.get(i).getDate());
    }
    sortMore(arr);
    
    for(int i = 0; i < arr.length; i++){

        for (int j = 0; j <  sDate.size() ; j++){
            if(countDate(this.get(i).getDate()) == arr[j]){
                this.set(i, sDate.get(j));
                sDate.remove(j);
            }
        }
    }
    }*/
    public void sortDate(){
        Items it = new Items("", "", "", new Date(0, 0, 0), "", 0, 0);
        for(int i = 0; i < this.size() - 1;i++){
            for(int j = i + 1; j < this.size(); j++){
                if(countDate(this.get(i).getDate()) > countDate(this.get(j).getDate())){
                    it = this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, it);
                }
            }
        }
    }
    
    // check code
    public boolean codeValid(String code){
    for (int i = 0; i < this.size(); i++) if (code.equals(this.get(i).getCode())) return false;
    //for (int i = 0; i < this.size(); i++) {if (this.get(i).getCode().equals(code) == true) return false;}
    return true;
    }
    
    // change amount
    public void changeAmount(){
        if(this.isEmpty()){
            System.out.println("Nothing to update!");
            return;
        }
        System.out.println("Enter the code want to update: ");
        String code = sc.nextLine().toUpperCase().trim();
        int pos = find(code);
        if (pos < 0) {System.out.println("The items with code " + code + " is not exist!!"); return;}
        int choose = getInt.get(1, 2, "Do you want to add more or put out the " + this.get(pos).getName() + " ?\n1.Add more\n2.Put out\nEnter your choose: ");
        if (choose == 1){
            int add = getInt.get(1, getmCapacity(), "How much you want to add: ");
            if((add + getCapacity()) > getmCapacity()){
                System.out.println("The fridge is not enought!! " + getCapacity() + "/" + getmCapacity());
            }
            else {this.get(pos).setAmount(this.get(pos).getAmount() + add); capacity += add; System.out.println("Add sucess");}
        }
        else {
            int remo = getInt.get(1, getmCapacity(), "How much you want to remove: ");
            if(remo >= this.get(pos).getAmount()){
                System.out.println("You better delete all items!");
            }
            else {this.get(pos).setAmount(this.get(pos).getAmount() - remo); capacity -= remo; System.out.println("Sucess");}
        }
        saveData(fileName);
    }
    
    
    //count Date
    public int countDate(Date date){
    int num = 0;
    num = ( date.getDay() * 100 / 31) + ((date.getMonth() * 100)- 100 ) + (date.getYear() * 1200);
    
    //num = ( date.getDay() + (date.getDay() * 32)) / 2;
    //num = num + (date.getYear() * 10000);
    //num *= 10;
    //System.out.println(num);
    return num;
    }
    
    //print soft date
    public void printSort(){
        sortDate();
        printTable();
        for (int i = 0; i < this.size(); i++) printOne(i);
        saveData(fileName);
    }
    
    //check overdate
    public void overDate(){
        Date tDay = new Date(today.getDayOfMonth(),today.getMonthValue(),today.getYear());
        int tDayy = countDate(tDay);
        for(int i=0; i < this.size(); i++){
            int iiDay = countDate(this.get(i).getDate());
            if (tDayy == iiDay) {printOver(i, " is over date today, take it out today please !");/*System.out.println(tDayy + "/" + iiDay);*/ }
            else if (countDate(tDay) > countDate(this.get(i).getDate())) printOver(i, " is over date. Let's leave it !!");
            
        }
    }
    //print over date
    public void printOver(int i, String s){
        System.out.print("The ");
        System.out.printf("%-20s","(" + this.get(i).getName() + ")");
        System.out.print(" with date ");
        System.out.printf("%-10s","(" + this.get(i).getDate() + ")");
        System.out.print(s);
        System.out.println("");
    }
    
    //call name to check
    public String findName(int i){
        String name = this.get(i).getName();
        return name;
    }
    //call size to check
    public int findSize(){
        int size = this.size();
        return size;
    }
    //call so luon
    public int findAmount(int i){
        int amount = this.get(i).getAmount();
        return amount;
    }
    
    ///////////===========================================================================
    //P2
    ArrayList<ProcesFood> cH = new ArrayList<ProcesFood>();
    public void addRepice(){
        
        int nIngredientNumber = getInt.get(1, 10, "How many materials is it made from ?\nEnter your choice: ");
        System.out.println("Finished product name: ");
        String nName = sc.nextLine().toUpperCase().trim();
        String nRepice[] = new String[nIngredientNumber];
        for(int i = 0; i < nRepice.length; i++){
            System.out.println("The " + (i + 1) + " is: ");
            nRepice[i] = sc.nextLine().toUpperCase().trim();
        }
        int nQua[] = new int[nIngredientNumber];
        for(int i = 0; i < nQua.length; i++){
            nQua[i] = getInt.get(1, 10000, "The " + nRepice[i] + " need: ");
        }
        cH.add(new ProcesFood(nName, nIngredientNumber, nRepice, nQua));
    }
    // check name co ton tai k
    public boolean repiceIn(String i){
        for(int j = 0; j < findSize(); j++){
            i.equals(findName(j));
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
            for(int j = 0; j < findSize(); j++){
                if(i.equals(findName(j)))num = j;
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
            for(int j = 0; j < findSize(); j++ ){
                if(name[i].equals(findName(j))){
                    cName[i] = findName(j);
                    cNum[i] = findAmount(j);
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
        if (cH.isEmpty()){System.out.println("Nothing to check!"); return;}
        
        for(int i = 0; i < cH.size(); i++) System.out.println((i + 1)+ ". "  + cH.get(i).getName());
        int num = getInt.get(1, (cH.size() + 1), "What repice you want to check ?\nChoose one: ");
        num--;
        int listN = checkListName(cH.get(num).getRepice());
        if(listN == 1){
            boolean ans = checkAmou(cH.get(num).getRepice(), cH.get(num).getQuantity());
            if(ans){
                System.out.println(cH.get(num).getName() + " Can processing");
                System.out.println("Do you want to take: ");
                for(int i = 0; i < cH.get(num).getQuantity().length; i++){
                    //System.out.println(cH.get(num).getQuantity()[i] + "gam" + cH.get(num).getRepice()[i]);
                    System.out.printf("%-15s", cH.get(num).getQuantity()[i]);
                    System.out.printf("%-10s", "gam");
                    System.out.printf("%-20s", cH.get(num).getRepice()[i]);
                    System.out.println("");
                }
                int choose =  getInt.get(1, 2, "1.Yes\n2.No\nEnter your choose: ");
                if(choose == 1){
                    for(int i = 0; i < cH.get(num).getRepice().length; i++){
                        int remo = iExist(cH.get(num).getRepice()[i]);
                        this.get(remo).setAmount(this.get(remo).getAmount() - cH.get(num).getQuantity()[i]);
                    }
                    saveData(fileName);
                    System.out.println("Success");
                }
                else return;
            }
        }
    }
    //save data
    public void loadDataR(String fName){
        if(cH.size() > 0) cH.clear();
        try {
            File f = new File(fName);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            ProcesFood b;
            while ((b=(ProcesFood)(fo.readObject())) != null){
                cH.add(b);
            }
            fo.close();fi.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void saveDataR(String fName){
        if(cH.size() == 0){
            System.out.println("Empty List!!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (ProcesFood b : cH) fo.writeObject(b);
            fo.close();f.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
