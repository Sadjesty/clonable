package com.sadjesty.deep;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class President implements Cloneable {
    private String firstName;
    private String lastName;

    @Override
    public President clone() throws CloneNotSupportedException {
        return (President) super.clone();
    }
}

@Data
@AllArgsConstructor
class City implements Cloneable {
    private int population;
    private String name;

    @Override
    protected City clone() throws CloneNotSupportedException {
        return (City) super.clone();
    }
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
        Country clone = (Country) super.clone();
        clone.setCountryPresident(this.countryPresident.clone());
        City[] clonedCities = Arrays.copyOf(clone.cities, clone.cities.length);
        for (int i = 0; i < clonedCities.length; i++) {
            clonedCities[i] = clonedCities[i].clone();
        }
        clone.setCities(clonedCities);

        return clone;
    }
}

public class DeepCloning {
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
