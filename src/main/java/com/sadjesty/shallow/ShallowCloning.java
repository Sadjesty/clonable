package com.sadjesty.shallow;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class President {
    private String firstName;
    private String lastName;
}

@Data
@AllArgsConstructor
class City {
    private int population;
    private String name;
}

@Data
@AllArgsConstructor
class Country implements Cloneable{
    private String name;
    private String language;
    private President countryPresident;
    private City[] cities;

    @Override
    public Country clone() throws CloneNotSupportedException {
        return (Country) super.clone();
    }
}

public class ShallowCloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        President trump = new President("Donald", "Trump");
        City newYork = new City(12, "New York");
        City losAngeles = new City(15, "LosAngeles");
        City[] usCities = new City[]{newYork, losAngeles};

        Country usa = new Country("USA", "English", trump, usCities);
        Country usaClone = usa.clone();
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
