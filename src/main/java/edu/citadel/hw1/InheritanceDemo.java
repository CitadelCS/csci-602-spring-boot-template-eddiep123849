package edu.citadel.hw1;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;

public class InheritanceDemo {
    //Yes Intellij, I'm aware that my placement of the brackets
    //is non-standard Java and is a "C-style array type
    //declaration", but it's still valid.
    public static void main(String args[]){
        //I'm using the shorter parametrized syntax here.
        //I don't feel like typing <Employee> again. Wait...
        ArrayList<Employee> listOfEmployees = new ArrayList<>();
        //Add the HourlyEmployees to the list.
        listOfEmployees.add(
                new HourlyEmployee("John Doe",
                        LocalDate.of(2009, 5 , 21),
                        50.5,
                        160.0
                )
        );
        listOfEmployees.add(new HourlyEmployee(
                "Jane Doe",
                LocalDate.of(2005, 9, 1),
                150.5,
                80.0
                )
        );
        //Add the SalariedEmployees to the list.
        listOfEmployees.add(new SalariedEmployee(
                "Moe Howard",
                LocalDate.of(2004, 1, 1),
                75000.0
                )
        );
        listOfEmployees.add(new SalariedEmployee(
                "Curly Howard",
                LocalDate.of(2018, 1, 1),
                105000.0
                )
        );

        //Here, we print the employees before sorting the list.
        System.out.println("\nList of Employees (before sorting)");
        for (Employee employee : listOfEmployees)
            System.out.println(employee);
        //Print a blank line to make the output look nicer.
        System.out.println();
        //Sort the list of employees.
        Collections.sort(listOfEmployees);
        //Print the list again, which should be sorted this time.
        System.out.println("List of Employees (after sorting)");
        for (Employee employee : listOfEmployees)
            System.out.println(employee);
        //Print out a blank line
        System.out.println();
        //Print the monthly pay introduction
        System.out.println("Monthly Pay");
        //Print each employee's monthly pay and keep track of the total.
        double totalOfEmployeesPay = 0.0;
        for (Employee workingMan : listOfEmployees) {
            System.out.printf("%s: $%,.2f\n", workingMan.getName(),
                    workingMan.getMonthlyPay());
            totalOfEmployeesPay += workingMan.getMonthlyPay();
        }
        System.out.printf("Total Monthly Pay: $%,.2f\n",
                totalOfEmployeesPay);
    }

}
