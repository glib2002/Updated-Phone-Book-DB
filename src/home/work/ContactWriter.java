/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.work;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Глеб
 */
public class ContactWriter implements CommandHandler {

    public List<Contact> list = new ArrayList();
    public Scanner scanner = new Scanner(System.in);
    public static String name;
    public static String number;
    public static String e_mail;
    public static String skype;
    public FileOutputStream fos;
    public String id;
    public String command;
    public String fileName;
    public File file;

    public void start() throws IOException {
        System.out.println("Welcome to phone book");
        userCommandHendler();

    }

    @Override
    public void userCommandHendler() {

        while (true) {
            printCommands();

            //System.out.println("enter command");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "add":
                    addContact();
                    break;
                case "print":
                    printContacts();
                    break;
            }

            if (command.equals("write to the file")) {
                writeFile();
            }
            if (command.equals("delete contact")) {
                addContact();
            }
            if (command.equals("delete the file")) {
                addContact();
            }
            if (command.equals("delete all files")) {
                addContact();
            }
            if (command.equals("exit")) {
                System.out.println("good bye");
                System.exit(0);
            }
            if (command.equals("MySQL")) {
                System.out.println("Welcome to DB servise");
                MySQLoperator m = new MySQLoperator();
                //m.start();
            }
        }
    }

    @Override
    public void printCommands() {
        System.err.println("add - add contact\n"
                + "print - print all contacts\n"
                + "write to the file - write to the file contact\n"
                + "delete contact – delete contact\n"
                + "exit  – exit programe\n"
                + "delete the file –  delete the file\n"
                + "delete all files -  delete all files\n"
                + "MySQL - work with DB MySQL");
    }

    @Override
    public void addContact() {
        System.out.println("Create a new contact");

        Contact contact = new Contact();

        System.out.println("Enter name");
        name = scanner.nextLine();
        contact.setName(name);
        System.out.println("Enter number");
        number = scanner.nextLine();
        contact.setNumber(number);
        System.out.println("Enter e-mail");
        e_mail = scanner.nextLine();
        contact.setE_mail(e_mail);
        System.out.println("Enter Skype");
        skype = scanner.nextLine();
        contact.setSkype(skype);
        System.out.println("Enter ID number");
        id = scanner.nextLine();
        contact.setId(id);

        list.add(contact);

    }

    @Override
    public void printContacts() {
        for (Contact contact : list) {

            System.out.println("Contacts printed...");
            System.out.println(contact.getName());
            System.out.println(contact.getNumber());
            System.out.println(contact.getE_mail());
            System.out.println(contact.getSkype());
            System.out.println(contact.getId());
        }

    }

    @Override
    public void writeFile() {

        for (Contact contact : list) {
            fileName = contact.getName() + ".txt";

            try {
                fos = new FileOutputStream("c:\\Users\\Глеб\\Documents\\NetBeansProjects\\Home--Work\\src\\home\\work\\Contacts\\" + fileName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ContactWriter.class.getName()).log(Level.SEVERE, null, ex);
            }

            byte[] bytes = contact.getName().getBytes();
            byte[] bytes2 = contact.getNumber().getBytes();
            byte[] bytes3 = contact.getE_mail().getBytes();
            byte[] bytes4 = contact.getSkype().getBytes();

            try {
                fos.write(bytes);
                fos.write('\n'); // write (int)
                fos.write(bytes2);
                fos.write('\n'); // write (int)
                fos.write(bytes3);
                fos.write('\n'); // write (int)
                fos.write(bytes4);

                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ContactWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void deleteContact() {
//        list.remove(contact);
    }

    @Override
    public void deleteFile() {
        file = new File("home\\work\\Contacts\\" + fileName + ".txt");
        file.delete();
    }

    @Override
    public void deleteAllFiles() {
        file = new File("Contacts");
        file.delete();
    }

}
