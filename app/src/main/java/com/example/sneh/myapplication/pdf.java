package com.example.sneh.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Himanshu on 11/16/2015.
 */
public class pdf {
    private Context context;
    db_handler handler;
    private  int cicle_id,building_id;
    List<equipment_> equipments;
    setting_class sc;
    public pdf(Context context, int cicle_id, int building_id){
        this.context=context;
        handler=new db_handler(context);
        handler.onCreateTable(handler.getWritableDatabase());
        this.cicle_id=cicle_id;
        this.building_id=building_id;
        sc=handler.get_all_setting();
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void createPdf(){
        all_object_list object_list=handler.get_all_objects(cicle_id, building_id);
        equipments=object_list.getEquipment_list();

        List<animal_class> animal_list=object_list.getAnimal_list();
        List<worker_class> worker_list=object_list.getWorker_list();
        List<food_class> food_list=object_list.getFood_list();
        List<medical_> medical_list=object_list.getMedical_list();
        List<returned_expense_class>returned_list=object_list.getReturned_expenses();
        List<down_payment_class> downpayment=object_list.getDown_payment_list();
        List<additional_expense_class>additional=object_list.getAdditional_expense_List();


        File exportDir = new File(Environment.getExternalStorageDirectory(), "CicleData");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        cicle cicle_=handler.getCicle(cicle_id);
        building_class building_=handler.getBuilding(building_id);
        String filename = cicle_.getTitle()+"_"+building_.getTitle()+".pdf";
        File file = new File(exportDir, filename);

        Document document;
        try {
            OutputStream output = new FileOutputStream(file);

            //Step 1
            document = new Document();

            //Step 2
            PdfWriter.getInstance(document, output);


            document.open();
            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD);
            Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.NORMAL, BaseColor.RED);
            Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                    Font.BOLD);
            Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.BOLD);
            //Step 4 Add content
            Font bfBold12 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            // Paragraph paragraph12=new Paragraph();
            // paragraph12.add(new Paragraph("SHOW EXPENSES",catFont));
            Paragraph paragraph=new Paragraph();
            paragraph.add(new Paragraph("SHOW EXPENSES",catFont));
            paragraph.setAlignment(Element.ALIGN_CENTER);



            // Paragraph paragraph = new Paragraph();





/*
                PdfPCell c1=new PdfPCell(new Phrase("Type",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                equipment_table.addCell(c1);

                 c1=new PdfPCell(new Phrase("Designation",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                equipment_table.addCell(c1);

                c1=new PdfPCell(new Phrase("Quantity",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                equipment_table.addCell(c1);

                 c1=new PdfPCell(new Phrase("Price",smallBold));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                equipment_table.addCell(c1);*/
            Paragraph paragraph100 = new Paragraph();
            paragraph100.add(new Paragraph());
            paragraph100.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths100 = {4f};
            PdfPTable photo_table=new PdfPTable(columnWidths100);
            photo_table.setWidthPercentage(30f);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pdf_icon);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapdata = stream.toByteArray();
            photo_table.addCell(Image.getInstance(bitmapdata));
            paragraph100.add(photo_table);
            addEmptyLine(paragraph100, 3);

            Paragraph paragraph1 = new Paragraph();
            paragraph1.add(new Paragraph("Equipment", bf12));
            paragraph1.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths = {4f, 4f, 4f , 4f};
            PdfPTable equipment_table=new PdfPTable(columnWidths);
            equipment_table.setWidthPercentage(90f);

            equipment_table.addCell("TYPE");
            equipment_table.addCell("DESIGNATION");
            equipment_table.addCell("QUANTITY");
            equipment_table.addCell("PRICE");
            equipment_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<equipments.size();i++)
            {
                equipment_table.addCell(equipments.get(i).getType());
                equipment_table.addCell(equipments.get(i).getDesignation());
                equipment_table.addCell(String.valueOf(equipments.get(i).getPrice()));
                equipment_table.addCell(String.valueOf(equipments.get(i).getQuantity())+sc.getMoney_format());
            }

            paragraph1.add(equipment_table);
            addEmptyLine(paragraph1, 3);

            Paragraph paragraph2 = new Paragraph();
            paragraph2.add(new Paragraph("Animals", bf12));
            paragraph2.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths2 = {4f, 4f, 4f , 4f};
            PdfPTable animal_table=new PdfPTable(columnWidths2);
            equipment_table.setWidthPercentage(90f);

            animal_table.addCell("TYPE");
            animal_table.addCell("OTHER");
            animal_table.addCell("QUANTITY");
            animal_table.addCell("PRICE");
            animal_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<animal_list.size();i++)
            {
                animal_table.addCell(animal_list.get(i).getType());
                animal_table.addCell(animal_list.get(i).getOther());
                animal_table.addCell(String.valueOf(animal_list.get(i).getPrice()));
                animal_table.addCell(String.valueOf(animal_list.get(i).getQuantity())+sc.getMoney_format());
            }

            paragraph2.add(animal_table);


            addEmptyLine(paragraph2, 3);

            Paragraph paragraph3 = new Paragraph();
            paragraph3.add(new Paragraph("Medical", bf12));
            paragraph3.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths3 = {4f, 4f, 4f , 4f,4f,4f};
            PdfPTable medical_table=new PdfPTable(columnWidths3);
            medical_table.setWidthPercentage(90f);

            medical_table.addCell("OPERATION TYPE");
            medical_table.addCell("OPERATION NAME");
            medical_table.addCell("PRODUCT");
            medical_table.addCell("DATE");
            medical_table.addCell("COMMENT");
            medical_table.addCell("PRICE");
            medical_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                medical_table.addCell(medical_list.get(i).getOperation_type());
                medical_table.addCell(medical_list.get(i).getOperation_name());
                medical_table.addCell(medical_list.get(i).getProduct());
                medical_table.addCell(medical_list.get(i).getDate());
                medical_table.addCell(medical_list.get(i).getComment());
                medical_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
            }

            paragraph3.add(medical_table);

            addEmptyLine(paragraph3, 3);

            Paragraph paragraph4 = new Paragraph();
            paragraph4.add(new Paragraph("Worker", bf12));
            paragraph4.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths4 = {4f, 4f, 4f , 4f,4f,4f,4f};
            PdfPTable worker_table=new PdfPTable(columnWidths4);
            worker_table.setWidthPercentage(90f);

            worker_table.addCell("WORKER NAME");
            worker_table.addCell("WORKER ADDRESS");
            worker_table.addCell("PHONE NUMBER");
            worker_table.addCell("ACTIVE OR NOT");
            worker_table.addCell("DATE");
            worker_table.addCell("PRICE");
            worker_table.addCell("SALARY");
            worker_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<worker_list.size();i++)
            {
                worker_table.addCell(worker_list.get(i).getName());
                worker_table.addCell(worker_list.get(i).getAddress());
                worker_table.addCell(worker_list.get(i).getTel());
                if(worker_list.get(i).getSetactive()==1)
                    worker_table.addCell("ACTIVE");
                else
                    worker_table.addCell("INACTIVE");
                worker_table.addCell(worker_list.get(i).getDate_start());
                worker_table.addCell(String.valueOf(worker_list.get(i).getPrice_per_day())+sc.getMoney_format());

                worker_table.addCell(String.valueOf(worker_list.get(i).getSalary())+sc.getMoney_format());

            }

            paragraph4.add(worker_table);

            addEmptyLine(paragraph4, 3);


            Paragraph paragraph5 = new Paragraph();
            paragraph5.add(new Paragraph("Antibiotic", bf12));
            paragraph5.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths5 = {4f, 4f, 4f , 4f};
            PdfPTable antibiotic_table =new PdfPTable(columnWidths5);
            antibiotic_table.setWidthPercentage(90f);

            antibiotic_table.addCell("PRODUCT NAME");
            antibiotic_table.addCell("DATE");
            antibiotic_table.addCell("COMMENT");

            antibiotic_table.addCell("PRICE");

            antibiotic_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Medical Product")&&medical_list.get(i).getOperation_type().equals("Antibiotic")){
                    antibiotic_table.addCell(medical_list.get(i).getProduct());
                    antibiotic_table.addCell(medical_list.get(i).getDate());

                    antibiotic_table.addCell(medical_list.get(i).getComment());

                    antibiotic_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph5.add(antibiotic_table);

            addEmptyLine(paragraph5, 3);


            Paragraph paragraph6 = new Paragraph();
            paragraph6.add(new Paragraph("Vaccine", bf12));
            paragraph6.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths6 = {4f, 4f, 4f , 4f};
            PdfPTable vaccine_table =new PdfPTable(columnWidths6);
            vaccine_table.setWidthPercentage(90f);

            vaccine_table.addCell("PRODUCT NAME");
            vaccine_table.addCell("DATE");
            vaccine_table.addCell("COMMENT");

            vaccine_table.addCell("PRICE");

            vaccine_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Medical Product")&&medical_list.get(i).getOperation_type().equals("Vaccine")){
                    vaccine_table.addCell(medical_list.get(i).getProduct());
                    vaccine_table.addCell(medical_list.get(i).getDate());

                    vaccine_table.addCell(medical_list.get(i).getComment());

                    vaccine_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph6.add(vaccine_table);
            addEmptyLine(paragraph6, 3);
            Paragraph paragraph7 = new Paragraph();
            paragraph7.add(new Paragraph("Vitamin", bf12));
            paragraph7.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths7 = {4f, 4f, 4f , 4f};
            PdfPTable vitamin_table =new PdfPTable(columnWidths7);
            vitamin_table.setWidthPercentage(90f);

            vitamin_table.addCell("PRODUCT NAME");
            vitamin_table.addCell("DATE");
            vitamin_table.addCell("COMMENT");

            vitamin_table.addCell("PRICE");

            vitamin_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Medical Product")&&medical_list.get(i).getOperation_type().equals("Vitamin")){
                    vitamin_table.addCell(medical_list.get(i).getProduct());
                    vitamin_table.addCell(medical_list.get(i).getDate());

                    vitamin_table.addCell(medical_list.get(i).getComment());

                    vitamin_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph7.add(vitamin_table);

            addEmptyLine(paragraph7, 3);



            Paragraph paragraph8 = new Paragraph();
            paragraph8.add(new Paragraph("Gas Litter", bf12));
            paragraph8.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths8 = {4f, 4f, 4f , 4f};
            PdfPTable gas_table =new PdfPTable(columnWidths8);
            gas_table.setWidthPercentage(90f);

            gas_table.addCell("PRODUCT NAME");
            gas_table.addCell("DATE");
            gas_table.addCell("COMMENT");

            gas_table.addCell("PRICE");

            gas_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Environment")&&medical_list.get(i).getOperation_type().equals("Gas Litter")){
                    gas_table.addCell(medical_list.get(i).getProduct());
                    gas_table.addCell(medical_list.get(i).getDate());

                    gas_table.addCell(medical_list.get(i).getComment());

                    gas_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph8.add(gas_table);
            addEmptyLine(paragraph8, 3);

            Paragraph paragraph9 = new Paragraph();
            paragraph9.add(new Paragraph("Hygiene", bf12));
            paragraph9.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths9 = {4f, 4f, 4f , 4f};
            PdfPTable hygiene_table =new PdfPTable(columnWidths6);
            hygiene_table.setWidthPercentage(90f);

            hygiene_table.addCell("PRODUCT NAME");
            hygiene_table.addCell("DATE");
            hygiene_table.addCell("COMMENT");

            hygiene_table.addCell("PRICE");

            hygiene_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Environment")&&medical_list.get(i).getOperation_type().equals("Hygiene")){
                    hygiene_table.addCell(medical_list.get(i).getProduct());
                    hygiene_table.addCell(medical_list.get(i).getDate());

                    hygiene_table.addCell(medical_list.get(i).getComment());

                    hygiene_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph9.add(hygiene_table);
            addEmptyLine(paragraph9, 3);


            Paragraph paragraph10 = new Paragraph();
            paragraph10.add(new Paragraph("Product", bf12));
            paragraph10.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths10 = {4f, 4f, 4f , 4f};
            PdfPTable  table_product =new PdfPTable(columnWidths10);
            table_product.setWidthPercentage(90f);

            table_product.addCell("PRODUCT NAME");
            table_product.addCell("DATE");
            table_product.addCell("COMMENT");

            table_product.addCell("PRICE");

            table_product.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Environment")&&medical_list.get(i).getOperation_type().equals("Product")){
                    table_product.addCell(medical_list.get(i).getProduct());
                    table_product.addCell(medical_list.get(i).getDate());

                    table_product.addCell(medical_list.get(i).getComment());

                    table_product.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph10.add(vaccine_table);
            addEmptyLine(paragraph10, 3);


            Paragraph paragraph11 = new Paragraph();
            paragraph11.add(new Paragraph("Other", bf12));
            paragraph11.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths11 = {4f, 4f, 4f , 4f};
            PdfPTable other_table =new PdfPTable(columnWidths11);
            other_table.setWidthPercentage(90f);

            other_table.addCell("PRODUCT NAME");
            other_table.addCell("DATE");
            other_table.addCell("COMMENT");

            other_table.addCell("PRICE");

            other_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<medical_list.size();i++)
            {
                if(medical_list.get(i).getOperation_name().equals("Various")&&medical_list.get(i).getOperation_type().equals("Other")){
                    other_table.addCell(medical_list.get(i).getProduct());
                    other_table.addCell(medical_list.get(i).getDate());

                    other_table.addCell(medical_list.get(i).getComment());

                    other_table.addCell(String.valueOf(medical_list.get(i).getPrice())+sc.getMoney_format());
                }


            }

            paragraph11.add(other_table);

            addEmptyLine(paragraph11, 3);


            Paragraph paragraph12 = new Paragraph();
            paragraph12.add(new Paragraph("Food", bf12));
            paragraph12.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths12 = {4f, 4f, 4f , 4f};
            PdfPTable food_table =new PdfPTable(columnWidths12);
            food_table.setWidthPercentage(90f);

            food_table.addCell("DESIGNATION");
            food_table.addCell("DATE");
            food_table.addCell("QUNATITY");

            food_table.addCell("PRICE");

            food_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<food_list.size();i++)
            {
                food_table.addCell(food_list.get(i).getDesignation());
                food_table.addCell(food_list.get(i).getDate());

                food_table.addCell(String.valueOf(food_list.get(i).getQuantity()));

                food_table.addCell(String.valueOf(food_list.get(i).getPrice())+sc.getMoney_format());
            }




            paragraph12.add(food_table);

            addEmptyLine(paragraph12, 3);


            Paragraph paragraph13 = new Paragraph();
            paragraph13.add(new Paragraph("Downpayment", bf12));
            paragraph13.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths13 = {4f, 4f, 4f };
            PdfPTable downpayment_table =new PdfPTable(columnWidths13);
            downpayment_table.setWidthPercentage(90f);

            downpayment_table.addCell("WORKER NAME");
            downpayment_table.addCell("DATE");


            downpayment_table.addCell("PAID MONEY");

            downpayment_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");

                equipment_table.addCell("1");*/
            if(downpayment!=null){
                for(int i=0;i<downpayment.size();i++)
                {
                    downpayment_table.addCell(downpayment.get(i).getWorker_name());
                    downpayment_table.addCell(downpayment.get(i).getDate());



                    downpayment_table.addCell(String.valueOf(downpayment.get(i).getPrice())+sc.getMoney_format());
                    Log.d("pdf_help:",downpayment.get(i).getWorker_name());
                }}




            paragraph13.add(downpayment_table);
            addEmptyLine(paragraph13, 3);

            Paragraph paragraph14 = new Paragraph();
            paragraph14.add(new Paragraph("Return Expenses", bf12));
            paragraph14.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths14 = {4f, 4f, 4f , 4f};
            PdfPTable returnexpenses_table=new PdfPTable(columnWidths14);
            equipment_table.setWidthPercentage(90f);

            returnexpenses_table.addCell("TYPE");
            returnexpenses_table.addCell("DATE");
            returnexpenses_table.addCell("QUANTITY");
            returnexpenses_table.addCell("PRICE");
            equipment_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<returned_list.size();i++)
            {
                returnexpenses_table.addCell(returned_list.get(i).getType());
                returnexpenses_table.addCell(returned_list.get(i).getDate());
                returnexpenses_table.addCell(String.valueOf(returned_list.get(i).getQuantity()));
                returnexpenses_table.addCell(String.valueOf(returned_list.get(i).getPrice()+sc.getMoney_format()));
            }

            paragraph14.add(returnexpenses_table);
            addEmptyLine(paragraph14, 3);

            Paragraph paragraph16 = new Paragraph();
            paragraph14.add(new Paragraph("Additional Expenses", bf12));
            paragraph14.setAlignment(Element.ALIGN_LEFT);
            float[] columnWidths16 = {4f, 4f, 4f , 4f,4f};
            PdfPTable additional_table=new PdfPTable(columnWidths16);
            additional_table.setWidthPercentage(90f);

            additional_table.addCell("TYPE");
            additional_table.addCell("DESIGNATION");
            additional_table.addCell("DATE");
            additional_table.addCell("QUANTITY");
            additional_table.addCell("PRICE");
            additional_table.setHeaderRows(1);
          /*      equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");
                equipment_table.addCell("1");*/
            for(int i=0;i<additional.size();i++)
            {
                additional_table.addCell(additional.get(i).getType());
                additional_table.addCell(additional.get(i).getDesignation());
                additional_table.addCell(additional.get(i).getDate());
                additional_table.addCell(String.valueOf(additional.get(i).getQuantity()));
                additional_table.addCell(String.valueOf(additional.get(i).getPrice_per_one()+ sc.getMoney_format()));
            }

            paragraph16.add(additional_table);
            addEmptyLine(paragraph16, 3);

            Paragraph paragraph15=new Paragraph();
            paragraph15.setAlignment(Element.ALIGN_RIGHT);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD);
            Chunk c = new Chunk("Total Expense:" + object_list.all_expense()+" "+sc.getMoney_format(), f);
            //c.setBackground(BaseColor.WHITE);
            paragraph15.add(c);


              /*  //specify column widths
                float[] columnWidths = {4f, 4f, 3f, 4f , 4f};
                //create PDF table with the given widths
                PdfPTable table = new PdfPTable(columnWidths);
                // set table width a percentage of the page width
                table.setWidthPercentage(90f);
*/
            //insert column headings
            /*    table.addCell("Designation");
                table.addCell("Date");
                table.addCell("Quantity");
                table.addCell("Price Per One");
                table.addCell("Total");*/


              /*  insertCell(table, "Designation", Element.ALIGN_LEFT, 1, bfBold12);
                insertCell(table, "Date"+" ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Qunatity"+")", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Price Per One", Element.ALIGN_LEFT, 1, bfBold12);
                insertCell(table, "Total", Element.ALIGN_CENTER, 1, bfBold12);
                table.setHeaderRows(1);
*/
/*
                //add transaction details
                for (int i = 0; i < tx.size(); i++) {
                    Transaction transaction = tx.get(i);
                    insertCell(table, transaction.getName(), Element.ALIGN_LEFT, 1, bf12);
                    if(datefor.equals("MM/DD/YYYY"))
                        insertCell(table, transaction.getDate(), Element.ALIGN_CENTER, 1, bf12);
                    else{
                        String[] ds=transaction.getDate().split("/");
                        insertCell(table, ds[1]+"/"+ds[0]+"/"+ds[2], Element.ALIGN_CENTER, 1, bf12);
                    }
                    insertCell(table, String.valueOf(transaction.getAmount()), Element.ALIGN_CENTER, 1, bf12);
                    insertCell(table, transaction.getDetails(), Element.ALIGN_LEFT, 1, bf12);
                    byte[] im=transaction.getImage();
                    if(im!=null)table.addCell(Image.getInstance(im));
                    else insertCell(table, "No Image", Element.ALIGN_CENTER, 1, bf12);
                }

                */
            //  document.add(paragraph12);

            document.add(paragraph100);

            document.add(paragraph);
            if(equipments!=null)
           document.add(paragraph1);
            if(animal_list!=null)
            document.add(paragraph2);
            if(medical_list!=null)
            document.add(paragraph3);
            if(worker_list!=null)
            document.add(paragraph4);

            document.add(paragraph5);

            document.add(paragraph6);

            document.add(paragraph7);

            document.add(paragraph8);

            document.add(paragraph9);

            document.add(paragraph10);

            document.add(paragraph11);
            if(food_list!=null)
            document.add(paragraph12);
            if(downpayment!=null)
            document.add(paragraph13);
            if(returned_list!=null)
            document.add(paragraph14);
            if(additional!=null)
                document.add(paragraph16);



                document.add(paragraph15);
            //Step 5: Close the document
            document.close();
            Toast.makeText(context, context.getResources().getString(R.string.saved_to) + filename, Toast.LENGTH_SHORT).show();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }

}