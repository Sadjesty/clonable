package com.sadjesty.kryo;

import com.esotericsoftware.kryo.Kryo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
class President {
    private String firstName;
    private String lastName;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class City {
    private int population;
    private String name;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Country {
    private String name;
    private String language;
    private President countryPresident;
    private City[] cities;
}

public class KryoCloning {
    public static void main(String[] args) {
        Kryo kryo = new Kryo();
        kryo.register(Country.class);
        kryo.register(President.class);
        kryo.register(City.class);
        kryo.register(City[].class);
        President trump = new President("Donald", "Trump");
        City newYork = new City(12, "New York");
        City losAngeles = new City(15, "LosAngeles");
        City[] usCities = new City[]{newYork, losAngeles};

        Country usa = new Country("USA", "English", trump, usCities);
        Country usaClone = kryo.copy(usa);
        System.out.println("Original USA: " + usa);
        System.out.println("Cloned USA: " + usaClone);

        trump.setFirstName("Josh");
        usa.setLanguage("Spanish");
        losAngeles.setName("LA");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Original USA: " + usa);
        System.out.println("Cloned USA: " + usaClone);

    }
}
