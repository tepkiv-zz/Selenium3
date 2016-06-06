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
        return "GroupData [name=" + firstName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (mobilephonenumber != null ? !mobilephonenumber.equals(that.mobilephonenumber) : that.mobilephonenumber != null)
            return false;
        if (workphonenumber != null ? !workphonenumber.equals(that.workphonenumber) : that.workphonenumber != null)
            return false;
        if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (byear != null ? !byear.equals(that.byear) : that.byear != null) return false;
        if (secondaryaddress != null ? !secondaryaddress.equals(that.secondaryaddress) : that.secondaryaddress != null)
            return false;
        return secondaryhomephonenumber != null ? secondaryhomephonenumber.equals(that.secondaryhomephonenumber) : that.secondaryhomephonenumber == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (mobilephonenumber != null ? mobilephonenumber.hashCode() : 0);
        result = 31 * result + (workphonenumber != null ? workphonenumber.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (byear != null ? byear.hashCode() : 0);
        result = 31 * result + (secondaryaddress != null ? secondaryaddress.hashCode() : 0);
        result = 31 * result + (secondaryhomephonenumber != null ? secondaryhomephonenumber.hashCode() : 0);
        return result;
    }

    public int compareTo(ContactData other) {
        return (firstName).toLowerCase().compareTo((other.firstName).toLowerCase());
    }
}