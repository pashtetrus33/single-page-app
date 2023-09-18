package com.geekbrains.arcsoft.lesson7.spa.models;

public class Person {

    //region Private fields
    private static long count;
    private long id;
    private String name;
    private int age;
    private int height;
    private float weight;

    //endregion

    //region Init
    {
        id = ++count;
    }
    //endregion

    //region Constructors
    public Person(String name, int age, int height, float weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        System.out.println("Person created with all arguments. Count = " + count + ". ID = " + id);
    }

    public Person() {
        System.out.println("Person created. Count = " + count + ". ID = " + id);
    }
    //endregion

    //region Getters and Setters

    public long getId() {
        return id;
    }

    public static long getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //endregion

    //region Public methods
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }



    //endregion

}
