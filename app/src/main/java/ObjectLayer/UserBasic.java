package ObjectLayer;

public class UserBasic {

    int id;
    String firstName;
    String lastName;
    String email;
    String number;
    String address;

    public UserBasic() {
       id  = 0;
       firstName = "";
       lastName = "";
       email = "";
       number = "";
       address = "";
    }

    public UserBasic(String firstName, String lastName, String email, String number, String address) {
        this.id= 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.address = address;
    }

    public UserBasic(int id, String firstName, String lastName, String email, String number, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
