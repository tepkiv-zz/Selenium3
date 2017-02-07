package com.data;

import com.data.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        for (int i = 0; i < 1; i++) {
            ContactData contact = new ContactData()
                    .withFirstName(generateRandomString()).withLastName("").withAddress1("").withHome("").withMobilePhoneNumber("").withWorkPhoneNumber("")
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

    private static void saveContactsToCsvFile(List<ContactData> groups, File file) throws IOException {
        FileWriter write = new FileWriter(file);
        write.close();
    }

    private static void saveContactToXmlFile(List<ContactData> groups, File file) {
    }

}
