package tests;

import com.ApplicationManager;
import com.helpers.db.JdbcHelper;
import org.testng.annotations.Test;
import tests.learnIMethodInterceptor.UserFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class test extends SetupFolders{
    @Override
    public String toString() {
        return "tests.test{}";
    }

    @Test
    public void learnSplit() {
        String b = "tests.test  tests.test tests.test";
        String a = "tests.test";

        String[] array = b.split("\\s+");
        System.out.println(array.length);
        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println(array[2]);
    }

    @Test
    public void learnRegexp() {
        String a = "+7 (916) 123-45-67";
        System.out.println(a.matches("\\+\\d+\\s*\\(\\d+\\)\\s*[\\d\\-]+"));
        a = a.replaceAll("[ ()\\-]", "");
        System.out.println(a);
        System.out.println(a.matches("\\+\\d+"));
        a = "    +7 (916) 123-45-67     ";
        a.trim();
        a = a.replaceAll("[ ()\\-]", "");
        System.out.println(a);
        System.out.println(a.matches("\\+\\d+"));
    }

    @Test
    public void jdbc(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("local.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ApplicationManager app = new ApplicationManager(properties);
        JdbcHelper jdbc = new JdbcHelper(app,"jdbc:mysql://127.0.0.1/addressbook?user=root&password=");
        //System.out.println(jdbc.listGroups());
        System.out.println(jdbc.listContacts());
    }

    @Test
    public void hiberGroups(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("local.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ApplicationManager app = new ApplicationManager(properties);
        app.getHibernateHelper().getListOfGroups();
    }

    @Test
    public void hiberContacts() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("local.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ApplicationManager app = new ApplicationManager(properties);
        app.getHibernateHelper().getListOfContacts();
    }

    @Test
    public void getCVSData() {
        new UserFactory().getCSVData();
    }
}
