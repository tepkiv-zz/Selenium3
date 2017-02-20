package com.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("ERROR! Some required parameters are absent : <testdata> <file> <format>");
            return;
        }
        int amount = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        String format = args[2];

        if(file.exists()){
            System.out.printf("Please remove file manually : " + file);
            return;
        }

        List<ContactData> groups = generateRandomContacts(amount);
        if ("csv".equals(format)) {
            saveContactsToCsvFile(groups, file);
        } else if ("xml".equals(format)) {
            saveContactToXmlFile(groups, file);
        } else {
            System.out.println("Unknown format " + format);
            return;
        }
    }

    /* Contact */
    public static List<ContactData> generateRandomContacts(int amount) {
        List<ContactData> list = new ArrayList<ContactData>();
        for (int i = 0; i < amount; i++) {
            ContactData contact = new ContactData()
                    .withFirstName(generateRandomString()).withLastName(generateRandomString()).withAddress1("").withHome("").withMobilePhoneNumber("").withWorkPhoneNumber("")
                    .withEmail1("").withEmail2("").withBirthDayYear("").withSecondaryAddress("").withSecondaryPhoneNumber("");
            list.add(contact);
        }
        return list;
    }

    public static String generateRandomString() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 0) {
            return "";
        } else {
            return "tests.test" + rnd.nextInt();
        }
    }

    private static void saveContactsToCsvFile(List<ContactData> contacts, File file) throws IOException {
        FileWriter write = new FileWriter(file);
        for(ContactData contact : contacts){
            write.write(contact.getFirstName() +","+contact.getLastName()+"\n");
        }
        write.close();
    }

    private static void saveContactToXmlFile(List<ContactData> groups, File file) {
    }

    public static List<ContactData> loadContactsFromCsvFile(String file) throws IOException {
        List<ContactData> list = new ArrayList<ContactData>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            ContactData contact = new ContactData().withFirstName(part[0]).withLastName(part[1]);
            list.add(contact);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        return list;
    }
}
