package com.neoworks.interviewtests.graph.models;

/**
 * Created by amolina on 5/05/17.
 */
public class Person implements Parseable, Comparable {

    private String name;
    private String email;
    private int age;

    public Person(){
        super();
    }

    public Person(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Parseable parse(String[] aElements) {
        return new Person(aElements[0],aElements[1],Integer.parseInt(aElements[2]));
    }

    @Override
    public int compareTo(Object o) {
        return (this.getName().compareTo(((Person)o).getName()));
    }
}
