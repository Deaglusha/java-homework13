package service;

import model.Address;
import model.Company;
import model.Geo;
import model.User;

public class UserService {
    public static User getDefaultUser() {
        User newUser = new User();
        newUser.setId(1);
        newUser.setName("Арсеній Кікоть");
        newUser.setUsername("Deaglusha");
        newUser.setEmail("deaglusha@gmail.com");

        Address address = new Address();
        address.setCity("Дніпро");
        address.setStreet("Пр.Слобожанський");
        address.setSuite("123");
        address.setZipcode("123456789");

        Geo geo = new Geo();
        geo.setLat(123.456);
        geo.setLng(-123.456);
        address.setGeo(geo);

        newUser.setAddress(address);
        newUser.setPhone("123-45-67");
        newUser.setWebsite("dneprpublic.com");

        Company company = new Company();
        company.setName("Dnepr | Public");
        company.setCatchPhrase("Проект серверів по Counter-Strik 1.6");
        company.setBs("Гарний настрій гарантовано");

        newUser.setCompany(company);
        return newUser;
    }
}
