import org.junit.Test;

public class test {
    @Override
    public String toString() {
        return "test{}";
    }

    @Test
    public void test() {
        String b = "test  test test";
        String a = "test";

        String[] array = b.split("\\s+");
        System.out.println(array.length);
        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println(array[2]);
    }

    @Test
    public void regexp() {
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
}
