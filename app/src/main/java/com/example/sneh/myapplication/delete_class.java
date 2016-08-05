package com.example.sneh.myapplication;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class delete_class {
    Context context;
    db_handler handler;
    DataBaseHandler handler2;
    public delete_class(Context con){
        context=con;
    }

    public void delete_cicle(cicle cicle){
        int i=0;
        handler=new db_handler(context);
        handler2 = new DataBaseHandler(context);

        List<building_class> building_list=handler.get_all_building(cicle.getCicle_id());
        List<equipment_>equipment_list=new ArrayList<>();
        List<worker_class>worker_list=new ArrayList<>();
        List<medical_>medical_list=new ArrayList<>();
        List<food_class> food_list=new ArrayList<>();
        List<consommation_>con_list=new ArrayList<>();
        List<additional_expense_class>additional_list=new ArrayList<>();
        List<down_payment_class>down_list=new ArrayList<>();
        List<returned_expense_class>return_list=new ArrayList<>();
        List<animal_class>animal_list=new ArrayList<>();
        List<death_>death_list=new ArrayList<>();
        List<egg_n>normal_egg_list=new ArrayList<>();
        List<egg_b>broken_egg_list=new ArrayList<>();
        List<notes_file>notes_list=new ArrayList<>();
        List<image_class>image_list=new ArrayList<>();
        List<temp_>temp_list=new ArrayList<>();
        for(i=0;i<building_list.size();i++){
            building_class building=building_list.get(i);
            Log.d("del_equip_before:" + String.valueOf(building_list.get(i).getBuilding_id()), String.valueOf(building_list.get(i).getFlag()));

            equipment_list.addAll(handler.get_all_equipment_by_ids(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            worker_list.addAll(handler.get_all_worker(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            medical_list.addAll(handler.get_all_medical(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            food_list.addAll(handler.getAllFoodBYIDS(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            con_list.addAll(handler.get_all_con(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            additional_list.addAll(handler.get_all_additional_expense(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            down_list.addAll(handler.get_all_down_payment(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            return_list.addAll(handler.get_all_returned_expense(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            animal_list.addAll(handler.getAllAnimal(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            death_list.addAll(handler.get_all_death(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            normal_egg_list.addAll(handler.get_all_normal_egg(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            broken_egg_list.addAll(handler.get_all_broken_egg(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            notes_list.addAll(handler2.get_all_note(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            temp_list.addAll(handler.getAllTemp(cicle.getCicle_id(), building_list.get(i).getBuilding_id()));
            image_list.addAll(handler.get_all_image(cicle.getCicle_id(),building_list.get(i).getBuilding_id()));

            if(building.getFlag()==0){
                handler.deleteBuilding(building);
            }
            else{
                building.setFlag(-1);
                handler.updateBuilding(building);
            }
            Log.d("del_build_fl_before:"+String.valueOf(building_list.get(i).getBuilding_id()),String.valueOf(building_list.get(i).getFlag()));

        }

        if(cicle.getFlag()==0){
            handler.deleteCicle(cicle);
        }
        else{
            cicle.setFlag(-1);
            handler.updateCicle(cicle);
        }
        for(i=0;i<equipment_list.size();i++){
            Log.d("del_equip_before:"+String.valueOf(equipment_list.get(i).getEquip_id()),String.valueOf(equipment_list.get(i).getFlag()));
            if(equipment_list.get(i).getFlag()==0){
                handler.deleteEquipment(equipment_list.get(i));
            }else{
                equipment_list.get(i).setFlag(-1);
                handler.updateEquipment(equipment_list.get(i));
            }
            Log.d("del_equip_after:"+String.valueOf(equipment_list.get(i).getEquip_id()),String.valueOf(equipment_list.get(i).getFlag()));

        }
        for(i=0;i<worker_list.size();i++){
            Log.d("del_worker_before:"+String.valueOf(worker_list.get(i).getWorker_id()),String.valueOf(worker_list.get(i).getFlag()));
            if(worker_list.get(i).getFlag()==0){
                handler.deleteWorker(worker_list.get(i));
            }else{
                worker_list.get(i).setFlag(-1);
                handler.updateWorker(worker_list.get(i));
            }
            Log.d("del_worker_after:"+String.valueOf(worker_list.get(i).getWorker_id()),String.valueOf(worker_list.get(i).getFlag()));

        }

        for(i=0;i<food_list.size();i++){
            Log.d("del_food_before"+String.valueOf(food_list.get(i).getFood_id()),String.valueOf(food_list.get(i).getFlag()));
            if(food_list.get(i).getFlag()==0){
                handler.deleteFood(food_list.get(i));
            }else{
                food_list.get(i).setFlag(-1);
                handler.updateFood(food_list.get(i));
            }
            Log.d("del_food_after:"+String.valueOf(food_list.get(i).getFood_id()),String.valueOf(food_list.get(i).getFlag()));

        }

        for(i=0;i<medical_list.size()&& medical_list.size()>0;i++){
            Log.d("del_medical_before"+String.valueOf(medical_list.get(i).getMedical_id()),String.valueOf(medical_list.get(i).getFlag()));
            if(medical_list.get(i).getFlag()==0){
                handler.deleteMedical(medical_list.get(i));
            }else{
                medical_list.get(i).setFlag(-1);
                handler.updateMedical(medical_list.get(i));
            }
            Log.d("del_medical_after:"+String.valueOf(medical_list.get(i).getMedical_id()),String.valueOf(medical_list.get(i).getFlag()));

        }

        for(i=0;i<con_list.size();i++){
            Log.d("del_con_before"+String.valueOf(con_list.get(i).getConsommation_id()),String.valueOf(con_list.get(i).getFlag()));
            if(con_list.get(i).getFlag()==0){
                handler.deleteCon(con_list.get(i));
            }else{
                con_list.get(i).setFlag(-1);
                handler.updateCon(con_list.get(i));
            }
            Log.d("del_con_after:"+String.valueOf(con_list.get(i).getConsommation_id()),String.valueOf(con_list.get(i).getFlag()));

        }
        for(i=0;i<additional_list.size();i++){
            Log.d("del_additional_before"+String.valueOf(additional_list.get(i).getAdditional_expense_id()),String.valueOf(additional_list.get(i).getFlag()));
            if(additional_list.get(i).getFlag()==0){
                handler.deleteAdditionalExpense(additional_list.get(i));
            }else{
                additional_list.get(i).setFlag(-1);
                handler.updateAdditionalExpense(additional_list.get(i));
            }
            Log.d("del_additional_after:"+String.valueOf(additional_list.get(i).getAdditional_expense_id()),String.valueOf(additional_list.get(i).getFlag()));

        }

        for(i=0;i<down_list.size();i++){
            Log.d("del_down_before"+String.valueOf(down_list.get(i).getSql_down_payment_id()),String.valueOf(down_list.get(i).getFlag()));
            if(down_list.get(i).getFlag()==0){
                handler.deleteDownPayment(down_list.get(i));
            }else{
                down_list.get(i).setFlag(-1);
                handler.updateDownPayment(down_list.get(i));
            }
            Log.d("del_down_after:"+String.valueOf(down_list.get(i).getDown_payment_id()),String.valueOf(down_list.get(i).getFlag()));

        }
        for(i=0;i<return_list.size();i++){
            Log.d("del_return_before"+String.valueOf(worker_list.get(i).getWorker_id()),String.valueOf(down_list.get(i).getFlag()));
            if(return_list.get(i).getFlag()==0){
                handler.deleteReturnedExpense(return_list.get(i));
            }else{
                return_list.get(i).setFlag(-1);
                handler.updateReturnedExpense(return_list.get(i));
            }
            Log.d("del_return_after:"+String.valueOf(return_list.get(i).getReturned_expense_id()),String.valueOf(return_list.get(i).getFlag()));

        }

        for(i=0;i<animal_list.size();i++){
            Log.d("del_animal_before"+String.valueOf(animal_list.get(i).getAnimal_id()),String.valueOf(animal_list.get(i).getFlag()));
            if(animal_list.get(i).getFlag()==0){
                handler.deletAnimal(animal_list.get(i));
            }else{
                animal_list.get(i).setFlag(-1);
                handler.updateAnimal(animal_list.get(i));
            }
            Log.d("del_animal_after"+String.valueOf(animal_list.get(i).getAnimal_id()),String.valueOf(animal_list.get(i).getFlag()));

        }

        for(i=0;i<death_list.size();i++){
            Log.d("del_death_before"+String.valueOf(death_list.get(i).getDeath_id()),String.valueOf(death_list.get(i).getFlag()));
            if(death_list.get(i).getFlag()==0){
                handler.deleteDeath(death_list.get(i));
            }else{
                death_list.get(i).setFlag(-1);
                handler.updateDeath(death_list.get(i));
            }
            Log.d("del_death_after"+String.valueOf(death_list.get(i).getDeath_id()),String.valueOf(death_list.get(i).getFlag()));

        }

        for(i=0;i<normal_egg_list.size();i++){
            Log.d("del_normal_egg_before"+String.valueOf(normal_egg_list.get(i).getEgg_id()),String.valueOf(normal_egg_list.get(i).getFlag()));
            if(normal_egg_list.get(i).getFlag()==0){
                handler.deleteNormalEgg(normal_egg_list.get(i));
            }else{
                normal_egg_list.get(i).setFlag(-1);
                handler.updateNormalEgg(normal_egg_list.get(i));
            }
            Log.d("del_normal_egg_after"+String.valueOf(normal_egg_list.get(i).getEgg_id()),String.valueOf(normal_egg_list.get(i).getFlag()));

        }

        for(i=0;i<broken_egg_list.size();i++){
            Log.d("del_broken_egg_before"+String.valueOf(broken_egg_list.get(i).getEgg_id()),String.valueOf(broken_egg_list.get(i).getFlag()));
            if(broken_egg_list.get(i).getFlag()==0){
                handler.deleteBrokenEgg(broken_egg_list.get(i));
            }else{
                broken_egg_list.get(i).setFlag(-1);
                handler.updateBrokenEgg(broken_egg_list.get(i));
            }
            Log.d("del_broken_egg_after"+String.valueOf(broken_egg_list.get(i).getEgg_id()),String.valueOf(broken_egg_list.get(i).getFlag()));

        }

        for(i=0;i<temp_list.size();i++){
            Log.d("del_temp_before"+String.valueOf(temp_list.get(i).getTemp_id()),String.valueOf(temp_list.get(i).getFlag()));
            if(temp_list.get(i).getFlag()==0){
                handler.deleteTemp(temp_list.get(i));
            }else{
                temp_list.get(i).setFlag(-1);
                handler.updateTemp(temp_list.get(i));
            }
            Log.d("del_temp_after"+String.valueOf(temp_list.get(i).getTemp_id()),String.valueOf(temp_list.get(i).getFlag()));

        }

        for(i=0;i<notes_list.size();i++){
            Log.d("del_notes_before"+String.valueOf(notes_list.get(i).getId()),String.valueOf(notes_list.get(i).getFlag()));
            if(notes_list.get(i).getFlag()==0){
                handler2.delete_note(notes_list.get(i));
            }else{
                notes_list.get(i).setFlag(-1);
                handler2.UpdateNote(notes_list.get(i));
            }
            Log.d("del_notes_after"+String.valueOf(notes_list.get(i).getId()),String.valueOf(notes_list.get(i).getFlag()));

        }

        for(i=0;i<image_list.size();i++){
            Log.d("del_image_before"+String.valueOf(image_list.get(i).getImage_id()),String.valueOf(image_list.get(i).getFlag()));
            if(image_list.get(i).getFlag()==0){
                handler.deleteImage(image_list.get(i));
            }else{
                image_list.get(i).setFlag(-1);
                handler.updateImage(image_list.get(i));
            }
            Log.d("del_image_after"+String.valueOf(image_list.get(i).getImage_id()),String.valueOf(image_list.get(i).getFlag()));

        }

    }

    public void delete_building(building_class building){
        handler=new db_handler(context);
        handler.onCreateTable(handler.getWritableDatabase());
        handler2 = new DataBaseHandler(context);

        List<equipment_> equipment_list=handler.get_all_equipment_by_ids(building.getCicle_id(),building.getBuilding_id());
        List<worker_class> worker_list=handler.get_all_worker(building.getCicle_id(), building.getBuilding_id());
        List<medical_> medical_list=handler.get_all_medical(building.getCicle_id(), building.getBuilding_id());
        List<food_class>food_list=handler.getAllFoodBYIDS(building.getCicle_id(), building.getCicle_id());
        List<consommation_>con_list=handler.get_all_con(building.getCicle_id(), building.getBuilding_id());
        List<additional_expense_class>additional_list=handler.get_all_additional_expense(building.getCicle_id(), building.getBuilding_id());
        List<returned_expense_class> return_list=handler.get_all_returned_expense(building.getCicle_id(), building.getBuilding_id());
        List<down_payment_class> down_list=handler.get_all_down_payment(building.getCicle_id(), building.getBuilding_id());
        List<animal_class>animal_list=handler.getAllAnimal(building.getCicle_id(), building.getBuilding_id());
        List<death_>death_list=handler.get_all_death(building.getCicle_id(), building.getBuilding_id());
        List<egg_n>normal_egg_list=handler.get_all_normal_egg(building.getCicle_id(), building.getBuilding_id());
        List<egg_b>broken_egg_list=handler.get_all_broken_egg(building.getCicle_id(), building.getBuilding_id());
        List<notes_file>notes_list=handler2.get_all_note(building.getCicle_id(), building.getBuilding_id());
        List<temp_>temp_list=handler.getAllTemp(building.getCicle_id(), building.getBuilding_id());
        List<image_class>image_list=handler.get_all_image(building.getCicle_id(),building.getBuilding_id());
        int i=0;
        if(building.getFlag()==0){
            handler.deleteBuilding(building);
        }
        else{
            building.setFlag(-1);
            handler.updateBuilding(building);
        }
        for(i=0;i<equipment_list.size();i++){
            Log.d("del_equip_before:"+String.valueOf(equipment_list.get(i).getEquip_id()),String.valueOf(equipment_list.get(i).getFlag()));
            if(equipment_list.get(i).getFlag()==0){
                handler.deleteEquipment(equipment_list.get(i));
            }else{
                equipment_list.get(i).setFlag(-1);
                handler.updateEquipment(equipment_list.get(i));
            }
            Log.d("del_equip_after:"+String.valueOf(equipment_list.get(i).getEquip_id()),String.valueOf(equipment_list.get(i).getFlag()));

        }
        for(i=0;i<worker_list.size();i++){
            Log.d("del_worker_before:"+String.valueOf(worker_list.get(i).getWorker_id()),String.valueOf(worker_list.get(i).getFlag()));
            if(worker_list.get(i).getFlag()==0){
                handler.deleteWorker(worker_list.get(i));
            }else{
                worker_list.get(i).setFlag(-1);
                handler.updateWorker(worker_list.get(i));
            }
            Log.d("del_worker_after:"+String.valueOf(worker_list.get(i).getWorker_id()),String.valueOf(worker_list.get(i).getFlag()));

        }


        for(i=0;i<food_list.size();i++){
            Log.d("del_food_before"+String.valueOf(food_list.get(i).getFood_id()),String.valueOf(food_list.get(i).getFlag()));
            if(food_list.get(i).getFlag()==0){
                handler.deleteFood(food_list.get(i));
            }else{
                food_list.get(i).setFlag(-1);
                handler.updateFood(food_list.get(i));
            }
            Log.d("del_food_after:"+String.valueOf(food_list.get(i).getFood_id()),String.valueOf(food_list.get(i).getFlag()));

        }
        for(i=0;i<con_list.size();i++){
            Log.d("del_con_before"+String.valueOf(con_list.get(i).getConsommation_id()),String.valueOf(con_list.get(i).getFlag()));
            if(con_list.get(i).getFlag()==0){
                handler.deleteCon(con_list.get(i));
            }else{
                con_list.get(i).setFlag(-1);
                handler.updateCon(con_list.get(i));
            }
            Log.d("del_con_after:"+String.valueOf(con_list.get(i).getConsommation_id()),String.valueOf(con_list.get(i).getFlag()));

        }

        for(i=0;i<medical_list.size();i++){
            Log.d("del_medical_before"+String.valueOf(medical_list.get(i).getMedical_id()),String.valueOf(medical_list.get(i).getFlag()));
            if(medical_list.get(i).getFlag()==0){
                handler.deleteMedical(medical_list.get(i));
            }else{
                medical_list.get(i).setFlag(-1);
                handler.updateMedical(medical_list.get(i));
            }
            Log.d("del_medical_after:"+String.valueOf(medical_list.get(i).getMedical_id()),String.valueOf(medical_list.get(i).getFlag()));

        }

        for(i=0;i<additional_list.size();i++){
            Log.d("del_additional_before"+String.valueOf(additional_list.get(i).getAdditional_expense_id()),String.valueOf(additional_list.get(i).getFlag()));
            if(additional_list.get(i).getFlag()==0){
                handler.deleteAdditionalExpense(additional_list.get(i));
            }else{
                additional_list.get(i).setFlag(-1);
                handler.updateAdditionalExpense(additional_list.get(i));
            }
            Log.d("del_additional_after:"+String.valueOf(additional_list.get(i).getAdditional_expense_id()),String.valueOf(additional_list.get(i).getFlag()));

        }

        for(i=0;i<down_list.size();i++){
            Log.d("del_down_before"+String.valueOf(worker_list.get(i).getWorker_id()),String.valueOf(worker_list.get(i).getFlag()));
            if(down_list.get(i).getFlag()==0){
                handler.deleteDownPayment(down_list.get(i));
            }else{
                down_list.get(i).setFlag(-1);
                handler.updateDownPayment(down_list.get(i));
            }
            Log.d("del_down_after:"+String.valueOf(down_list.get(i).getDown_payment_id()),String.valueOf(down_list.get(i).getFlag()));

        }
        for(i=0;i<return_list.size();i++){
            Log.d("del_return_before"+String.valueOf(return_list.get(i).getReturned_expense_id()),String.valueOf(return_list.get(i).getFlag()));
            if(return_list.get(i).getFlag()==0){
                handler.deleteReturnedExpense(return_list.get(i));
            }else{
                return_list.get(i).setFlag(-1);
                handler.updateReturnedExpense(return_list.get(i));
            }
            Log.d("del_return_after:"+String.valueOf(return_list.get(i).getReturned_expense_id()),String.valueOf(return_list.get(i).getFlag()));

        }

        for(i=0;i<animal_list.size();i++){
            Log.d("del_animal_before"+String.valueOf(animal_list.get(i).getAnimal_id()),String.valueOf(animal_list.get(i).getFlag()));
            if(animal_list.get(i).getFlag()==0){
                handler.deletAnimal(animal_list.get(i));
            }else{
                animal_list.get(i).setFlag(-1);
                handler.updateAnimal(animal_list.get(i));
            }
            Log.d("del_animal_after"+String.valueOf(animal_list.get(i).getAnimal_id()),String.valueOf(animal_list.get(i).getFlag()));

        }

        for(i=0;i<death_list.size();i++){
            Log.d("del_death_before"+String.valueOf(death_list.get(i).getDeath_id()),String.valueOf(death_list.get(i).getFlag()));
            if(death_list.get(i).getFlag()==0){
                handler.deleteDeath(death_list.get(i));
            }else{
                death_list.get(i).setFlag(-1);
                handler.updateDeath(death_list.get(i));
            }
            Log.d("del_death_after"+String.valueOf(death_list.get(i).getDeath_id()),String.valueOf(death_list.get(i).getFlag()));

        }

        for(i=0;i<normal_egg_list.size();i++){
            Log.d("del_normal_egg_before"+String.valueOf(normal_egg_list.get(i).getEgg_id()),String.valueOf(normal_egg_list.get(i).getFlag()));
            if(normal_egg_list.get(i).getFlag()==0){
                handler.deleteNormalEgg(normal_egg_list.get(i));
            }else{
                normal_egg_list.get(i).setFlag(-1);
                handler.updateNormalEgg(normal_egg_list.get(i));
            }
            Log.d("del_normal_egg_after"+String.valueOf(normal_egg_list.get(i).getEgg_id()),String.valueOf(normal_egg_list.get(i).getFlag()));

        }

        for(i=0;i<broken_egg_list.size();i++){
            Log.d("del_broken_egg_before"+String.valueOf(broken_egg_list.get(i).getEgg_id()),String.valueOf(broken_egg_list.get(i).getFlag()));
            if(broken_egg_list.get(i).getFlag()==0){
                handler.deleteBrokenEgg(broken_egg_list.get(i));
            }else{
                broken_egg_list.get(i).setFlag(-1);
                handler.updateBrokenEgg(broken_egg_list.get(i));
            }
            Log.d("del_broken_egg_after"+String.valueOf(broken_egg_list.get(i).getEgg_id()),String.valueOf(broken_egg_list.get(i).getFlag()));

        }

        for(i=0;i<temp_list.size();i++){
            Log.d("del_temp_before"+String.valueOf(temp_list.get(i).getTemp_id()),String.valueOf(temp_list.get(i).getFlag()));
            if(temp_list.get(i).getFlag()==0){
                handler.deleteTemp(temp_list.get(i));
            }else{
                temp_list.get(i).setFlag(-1);
                handler.updateTemp(temp_list.get(i));
            }
            Log.d("del_temp_after"+String.valueOf(temp_list.get(i).getTemp_id()),String.valueOf(temp_list.get(i).getFlag()));

        }

        for(i=0;i<notes_list.size();i++){
            Log.d("del_notes_before"+String.valueOf(notes_list.get(i).getId()),String.valueOf(notes_list.get(i).getFlag()));
            if(notes_list.get(i).getFlag()==0){
                handler2.delete_note(notes_list.get(i));
            }else{
                notes_list.get(i).setFlag(-1);
                handler2.UpdateNote(notes_list.get(i));
            }
            Log.d("del_notes_after"+String.valueOf(notes_list.get(i).getId()),String.valueOf(notes_list.get(i).getFlag()));

        }
        for(i=0;i<image_list.size();i++){
            Log.d("del_image_before"+String.valueOf(image_list.get(i).getImage_id()),String.valueOf(image_list.get(i).getFlag()));
            if(image_list.get(i).getFlag()==0){
                handler.deleteImage(image_list.get(i));
            }else{
                image_list.get(i).setFlag(-1);
                handler.updateImage(image_list.get(i));
            }
            Log.d("del_image_after"+String.valueOf(image_list.get(i).getImage_id()),String.valueOf(image_list.get(i).getFlag()));

        }

    }
}