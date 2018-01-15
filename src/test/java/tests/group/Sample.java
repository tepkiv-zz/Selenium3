package tests.group;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Sample {
    public static void main(String[] args){
        String line = "";
        System.out.println(line.split(",").length);
    }

    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][] {
                { "Cedric", new Integer(36) },
                { "Anne", new Integer(37)},
        };
    }

    @Test(dataProvider = "test1")
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }
}
