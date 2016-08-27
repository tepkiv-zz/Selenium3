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
                       String home, String mobilePhoneNumber, String workPhoneNumber,
                       String email1, String email2, String bYear,
                       String secondaryAddress, String secondaryHomePhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.home = home;
        this.mobilephonenumber = mobilePhoneNumber;
        this.workphonenumber = workPhoneNumber;
        this.email1 = email1;
        this.email2 = email2;
        this.byear = bYear;
        this.secondaryaddress = secondaryAddress;
        this.secondaryhomephonenumber = secondaryHomePhoneNumber;
    }

    public ContactData() {
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilephonenumber = mobilePhoneNumber;
        return this;
    }

    public ContactData withWorkPhoneNumber(String workPhoneNumber) {
        this.workphonenumber = workPhoneNumber;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withBirthDayYear(String bYear) {
        this.byear = bYear;
        return this;
    }

    public ContactData withSecondaryAddress(String secondaryAddress) {
        this.secondaryaddress = secondaryAddress;
        return this;
    }

    public ContactData withSecondaryPhoneNumber(String secondaryHomePhoneNumber) {
        this.secondaryhomephonenumber = secondaryHomePhoneNumber;
        return this;
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