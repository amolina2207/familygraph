package com.neoworks.interviewtests.graph.models;

/**
 * Created by amolina on 5/05/17.
 */
public class Relationships implements Parseable, Comparable{

    private String emailA;
    private String type;
    private String emailB;

    public Relationships(){
        super();
    }

    public Relationships(String emailA, String type, String emailB) {
        this.emailA = emailA;
        this.type = type;
        this.emailB = emailB;
    }

    public String getEmailA() {
        return emailA;
    }

    public void setEmailA(String emailA) {
        this.emailA = emailA;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmailB() {
        return emailB;
    }

    public void setEmailB(String emailB) {
        this.emailB = emailB;
    }

    @Override
    public Parseable parse(String[] aElements) {
        return new Relationships(aElements[0],aElements[1],aElements[2]);
    }

    @Override
    public int compareTo(Object o) {
        return (this.getEmailA().compareTo(((Relationships)o).getEmailA()));
    }

}
