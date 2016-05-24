package vrebo.itver.contactbook;

import java.io.Serializable;

/**
 * Created by CERO on 25/04/2016.
 */
public class Contact implements Serializable {

    private String name;
    private String lastName;
    private String telephone;
    private String email;
    private String address;

    public Contact(){}

    public Contact(String name, String lastName, String telephone, String email, String address){
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emal) {
        this.email = emal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
