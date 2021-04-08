package framework;

import models.Pet;


import java.util.Stack;

public class DataProvider {
    private static Stack<Pet> petsList = new Stack<>();


    public static Pet getFreePet(){
        return petsList.size()>0 ? petsList.pop() : null;
    }

    public static void addPet(Pet pet){
        petsList.push(pet);
    }
}
