package com.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupDataGenerator {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("ERROR! Some required parameters are absent : <testdata> <file> <format>");
            return;
        }
        int amount = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        String format = args[2];

        if (file.exists()) {
            System.out.printf("Please remove file manually : " + file);
            return;
        }

        List<GroupData> groups = generateRandomGroups(amount);
        if ("csv".equals(format)) {
            saveGroupsToCsvFile(groups, file);
        } else if ("xml".equals(format)) {
            saveGroupsToXmlFile(groups, file);
        } else {
            System.out.println("Unknown format " + format);
            return;
        }
    }

    /* Group */
    public static List<GroupData> generateRandomGroups(int amount) {
        List<GroupData> list = new ArrayList<GroupData>();
        for (int i = 0; i < amount; i++) {
            GroupData group = new GroupData().withName(generateRandomString())
                    .withHeader(generateRandomString()).withFooter(generateRandomString());
            list.add(group);
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

    private static void saveGroupsToCsvFile(List<GroupData> groups, File file) throws IOException {
        FileWriter write = new FileWriter(file);

        for (GroupData group : groups) {
            write.write(group.getName() + "," + group.getHeader() + "," + group.getFooter() + "\n");
        }
        write.close();
    }

    private static void saveGroupsToXmlFile(List<GroupData> groups, File file) throws IOException {

    }

    public static List<GroupData> loadGroupsFromCsvFile(String file) throws IOException {
        List<GroupData> list = new ArrayList<GroupData>();
        FileReader reader = new FileReader(file);
        // wrap file reader to get extra methods
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            GroupData group = new GroupData().withName(part[1]).withHeader(part[2]).withFooter(part[2]);
            list.add(group);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return list;
    }

}
