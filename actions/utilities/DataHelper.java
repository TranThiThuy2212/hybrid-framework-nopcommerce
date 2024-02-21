package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private Locale local =new Locale("en");
    private Faker faker = new Faker(local);
    public static DataHelper getDataHelper() {
        return new DataHelper();
    }
    public String getFirstName() {
        return faker.name().firstName();
    }
    public String getLastName() {
        return faker.name().lastName();
    }
    public String getEmail() {
        return faker.internet().emailAddress();
    }
    public String getPassword() {
        return faker.internet().password(8, 12,true,true);
    }
    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    public String getAddress() {
        return faker.address().streetAddress();
    }
    public String getCity() {
        return faker.address().city();
    }
    public String getState() {
        return faker.address().state();
    }
    public String getZipCode() {
        return faker.address().zipCode();
    }
    public String getCountry() {
        return faker.address().country();
    }

}
