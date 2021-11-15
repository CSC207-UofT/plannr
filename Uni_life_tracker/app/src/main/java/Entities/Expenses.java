package Entities;

import java.util.Date;

public class Expenses {
    private String name;
    private double value;

  /**
   * Construct an Expense, giving them the given name,
   * priority, start date, and end date.
   *
   * @param name      The Expense's name
   * @param value     The Expense's expenditures
   */
   public Expenses(String name, double value) {
       this.name = name;
       this.value = value;

   }

   /**
    * Getter method for name
    * @return a String that describes the Expense's name
    */
   public String getName() {return name;}

    /**
     * Getter method for value
     * @return a double that describes the Expense value
     */
    public double getValue() {
      return value;
    }

    /**
     * Setter method for name
     * @param name the new name of the expense
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for value of expense
     * @param value the new value of the expense
     */
    public void setValue(double value) {
        this.value = value;
    }

}
