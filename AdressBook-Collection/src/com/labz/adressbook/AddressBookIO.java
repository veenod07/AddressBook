package com.labz.adressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class AddressBookIO {
	public static File address_book_file = new File("D:\\Bridgelabz\\java\\AdressBook-Collection\\src\\com\\labz\\adressbook\\AddressBook.text");

    public static void createfile() {
        try {
            address_book_file.createNewFile();
            System.out.println("\nEmpty File is created successfully....");
        } catch (IOException e) {
            System.out.println("" + e);
        }
    }

    public static void add_details_to_file(String input) {
        try {
            FileOutputStream fout = new FileOutputStream(address_book_file);
            byte b[] = input.getBytes();
            fout.write(b);
            fout.close();
            System.out.println("\ncontact added successfully...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void read_details_to_file() {
        try {
            String file= address_book_file.getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("\n"+line);
            }
        } catch (IOException io) {
            System.err.println(io);
        }
    }	
}
