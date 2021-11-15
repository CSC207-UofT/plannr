package Entities;

import java.util.Date;

public class Expense {
    private String name;
    private double value;

    /**
     * Construct an Expense, giving them the given name,
     * priority, start date, and end date.
    *
    * @param name      The Expense's name
    * @param value     The Expense's expenditures
     */
   public Expense(String name, double value) {
       this.name = name;
       this.value = value;

   }

   /**
   * getter method for name
     * @return a String that describes the Expense's name
     */
    public String getName() {return name;}

    /**
    * getter method for value
    * @return a double that describes the Expense value
    */
    public double getValue() {
      return value;
    }

}
