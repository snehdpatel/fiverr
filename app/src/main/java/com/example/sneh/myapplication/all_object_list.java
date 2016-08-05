package com.example.sneh.myapplication;

import java.util.List;

/**
 * Created by starsilver on 12/11/15.
 */
public class all_object_list {

    private List<equipment_> equipment_list;
    private List<animal_class>animal_list;
    private List<worker_class> worker_list;
    private List<food_class> food_list;
    private List<medical_> medical_list;
    private List<returned_expense_class> returned_expenses_list;
    private List<down_payment_class> down_payment_list;

    private List<additional_expense_class> additional_expense_List;

    public List<additional_expense_class> getAdditional_expense_List() {
        return additional_expense_List;
    }

    public void setAdditional_expense_List(List<additional_expense_class> additional_expense_List) {
        this.additional_expense_List = additional_expense_List;
    }



    public List<down_payment_class> getDown_payment_list() {
        return down_payment_list;
    }

    public void setDown_payment_list(List<down_payment_class> down_payment_list) {
        this.down_payment_list = down_payment_list;
    }



    public List<animal_class> getAnimal_list() {
        return animal_list;
    }

    public void setAnimal_list(List<animal_class> animal_list) {
        this.animal_list = animal_list;
    }

    public List<equipment_> getEquipment_list() {
        return equipment_list;
    }

    public void setEquipment_list(List<equipment_> equipment_list) {
        this.equipment_list = equipment_list;
    }

    public List<food_class> getFood_list() {
        return food_list;
    }

    public void setFood_list(List<food_class> food_list) {
        this.food_list = food_list;
    }

    public List<medical_> getMedical_list() {
        return medical_list;
    }

    public void setMedical_list(List<medical_> medical_list) {
        this.medical_list = medical_list;
    }

    public List<worker_class> getWorker_list() {
        return worker_list;
    }

    public void setWorker_list(List<worker_class> worker_list) {
        this.worker_list = worker_list;
    }

    public List<returned_expense_class> getReturned_expenses() {
        return returned_expenses_list;
    }

    public void setReturned_expenses(List<returned_expense_class> returned_expenses) {
        this.returned_expenses_list = returned_expenses;
    }

    public int get_equipment_size(){
        return equipment_list.size();
    }

    public int get_animal_size(){
        return  animal_list.size();
    }

    public int get_worker_size(){
        return worker_list.size();
    }

    public int get_food_size(){
        return  food_list.size();
    }

    public int get_medical_size(){
        return medical_list.size();
    }

    public int get_returned_expense_size(){
        return returned_expenses_list.size();
    }

    public int get_equipment_expense(){
        int price=0;
        for(int i=0;i<equipment_list.size();i++){
            int amount=(equipment_list.get(i).getQuantity())*(equipment_list.get(i).getPrice());
            price=price+amount;
        }
        return  price;
    }
    public int get_downpayment()
    {
        int price=0;
        for(int i=0;i<down_payment_list.size();i++){
            int amount=down_payment_list.get(i).getPrice();
            price=price+amount;
        }
        return price;
    }
    public int get_animal_expense(){
        int price=0;
        for(int i=0;i<animal_list.size();i++){
            int amount=(animal_list.get(i).getPrice())*(animal_list.get(i).getQuantity());
            price=price+amount;
        }
        return price;
    }
    public int all_additional_expenses()
    {
        int price=0;
        for(int i=0;i<additional_expense_List.size();i++)
        {
            int amount=(additional_expense_List.get(i).getPrice_per_one())*(additional_expense_List.get(i).getQuantity());
            price=price+amount;
        }
        return price;
    }
    public int get_worker_expense(){
        int price=0;
        for(int i=0;i<worker_list.size();i++){
            if(worker_list.get(i).getSetactive()==1){
                int amount=worker_list.get(i).getSalary();
                price=price+amount;}
        }
        return price;
    }

    public int get_food_expense(){
        int price=0;
        for(int i=0;i<food_list.size();i++){
            int amount=(food_list.get(i).getQuantity())*(food_list.get(i).getPrice());
            price=price+amount;
        }
        return price;
    }

    public int get_medical_expense(){
        int price=0;
        for(int i=0;i<medical_list.size();i++){
            price=price+medical_list.get(i).getPrice();
        }
        return price;
    }

    public int get_returned_expense_expense(){
        int price=0;
        for(int i=0;i<returned_expenses_list.size();i++){
            int amount=(returned_expenses_list.get(i).getQuantity())*(returned_expenses_list.get(i).getPrice());
            price=price+amount;
        }
        return price;
    }

    /*public int all_expense(){
        int price=0;
        price=(this.get_equipment_expense()+this.get_animal_expense()+this.get_worker_expense()+this.get_food_expense()+this.get_medical_expense())-this.get_returned_expense_expense();
        return price;
    }*/
    public int all_expense(){
        int price=0;
        price=(this.all_additional_expenses()+this.get_equipment_expense()+this.get_animal_expense()+this.get_worker_expense()+this.get_food_expense()+this.get_medical_expense())-this.get_returned_expense_expense();
        return price;
    }

}