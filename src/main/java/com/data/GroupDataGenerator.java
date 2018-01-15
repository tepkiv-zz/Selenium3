package com.data;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupDataGenerator {
    private final static String aliasName = "group";

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("ERROR! Some required parameters are absent : <rows count> <filename> <csv or xml>");
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
            GroupData group = new GroupData()
                    .withName(generateRandomString())
                    .withHeader(generateRandomString())
                    .withFooter(generateRandomString());
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

    private static void saveGroupsToXmlFile(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias(aliasName,GroupData.class);
        String xml = xStream.toXML(groups);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        System.out.println("XML data saved to " + file.getAbsolutePath());
        writer.close();
    }

    public static List<GroupData> loadGroupsFromXMLFile(File file) throws FileNotFoundException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(aliasName,GroupData.class);
        FileReader reader = new FileReader(file);
        // parse xml
        return (List<GroupData>) xStream.fromXML(reader);
    }

    private static void saveGroupsToCsvFile(List<GroupData> groups, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (GroupData group : groups) {
            fileWriter.write(String.format("%s,%s,%s,!" + "\n", group.getName(), group.getHeader(), group.getFooter()));
            System.out.println(String.format("%s,%s,%s,!" + "\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        fileWriter.close();
    }

    public static List<GroupData> loadGroupsFromCsvFile(String file) throws IOException {
        List<GroupData> list = new ArrayList<GroupData>();
        FileReader reader = new FileReader(file);
        // wrap file reader to get extra methods
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            GroupData group = new GroupData()
                    .withName(part[0])
                    .withHeader(part[1])
                    .withFooter(part[2]);
            list.add(group);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return list;
    }

}
