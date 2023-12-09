package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;

import com.di.Dog;
import com.di.DoggyShelter;
import com.di.Pets;
import com.di.Animal;
import com.di.BasicEnvironment;
import com.di.Cat;
import com.di.ShelterFactory;


public class Main {
    public static void main(String[] args) {
        Environment environment = new BasicEnvironment();
        Container container = environment.configure((binder) -> {
            binder.bind(Pets.class, Dog.class);
        });
        Pets pets = container.getComponent(Pets.class);
        pets.makeNoise();
    }
}
