package edu.citadel.hw1;

import java.lang.Comparable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee implements Comparable<Employee>{
    private String name;
    private LocalDate hireDate;

    public Employee(String name, LocalDate hireDate){
        this.name = name;
        this.hireDate = hireDate;
    }

    //Do we prefer the "getStuff()" way of writing
    //get methods or the "stuff()" method of writing
    //get methods? I feel like the "getStuff()" is a classic.
    public String getName(){return name;}

    public LocalDate getHireDate(){return hireDate;}

    public abstract double getMonthlyPay();

    public int compareTo(Employee otherEmployee){
        //If this employee makes more than the other one, this will
        //be positive. If the other one makes more than this one, then
        //this will be negative.
        double determineDifference = getMonthlyPay() - otherEmployee.getMonthlyPay();

        if (determineDifference > 0) return 1; //This employee makes more money.
        else if (determineDifference < 0) return -1; //The other employee makes more money.
        else return 0; //They make the same amount of money

    }

}
