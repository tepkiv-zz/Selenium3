package tests.group;

import fw.pages.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupDataGenerator {
    public void main(String[] args){
        if(args.length<3){
            System.out.println("ERROR! Some required parameters are absent : <testdata> <file> <format>");
            return;
        }
        int amount = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        String format = args[2];
        List<GroupData> groups = generateRandomGroups(amount);
        if("csv".equals(format)){
            saveGroupsToCsvFile(groups,file);
        }else if("xml".equals(format)){
            saveGroupsToXmlFile(groups,file);
        }
        else{
            System.out.println("Unknown format " + format);
            return;
        }
    }

    public static List<GroupData> generateRandomGroups(int amount) {
        List<GroupData> list = new ArrayList<GroupData>();
        for (int i = 0; i < 1; i++) {
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
            return "test" + rnd.nextInt();
        }
    }

    private static void saveGroupsToXmlFile(List<GroupData> groups, File file) {
    }

    private static void saveGroupsToCsvFile(List<GroupData> groups, File file) {
    }
}
