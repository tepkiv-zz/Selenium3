package fw;

public class ContactData implements Comparable<ContactData> {
    public String firstName;
    public String lastName;
    public String address1;
    public String home;
    public String mobilephonenumber;
    public String workphonenumber;
    public String email1;
    public String email2;
    public String byear;
    public String secondaryaddress;
    public String secondaryhomephonenumber;

    public ContactData(String firstName, String lastName, String address1,
                       String home, String mobilephonenumber, String workphonenumber,
                       String email1, String email2, String byear,
                       String secondaryaddress, String secondaryhomephonenumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.home = home;
        this.mobilephonenumber = mobilephonenumber;
        this.workphonenumber = workphonenumber;
        this.email1 = email1;
        this.email2 = email2;
        this.byear = byear;
        this.secondaryaddress = secondaryaddress;
        this.secondaryhomephonenumber = secondaryhomephonenumber;
    }

    public ContactData() {
    }

    @Override
    public String toString() {
        return "GroupData [name= " + firstName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return firstName.equals(that.firstName);

    }

    @Override
    public int hashCode() {
        return firstName.hashCode();
    }

    public int compareTo(ContactData other) {
        return (firstName).toLowerCase().compareTo((other.firstName).toLowerCase());
    }
}