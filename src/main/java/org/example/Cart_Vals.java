package org.example;

import java.util.Objects;

public class Cart_Vals {
    public static String hawaiian_pizza;
    public static String supreme_pizza;
    public static String ultimate_pepperoni;
    public static String toppings_one;
    public static String toppings_two;
    public static String toppings_three;
    public static String toppings_four;
    public static String toppings_five;
    public static String CrustType;

    public static String forName(String s) {
        if (Objects.equals(s, "hawaiian_pizza")) {
            return Cart_Vals.hawaiian_pizza;
        } else if (Objects.equals(s, "supreme_pizza")) {
            return Cart_Vals.supreme_pizza;
        } else if (Objects.equals(s, "ultimate_pepperoni")) {
            return Cart_Vals.ultimate_pepperoni;
        } else if (Objects.equals(s, "toppings_one")) {
            return Cart_Vals.toppings_one;
        } else if (Objects.equals(s, "toppings_two")) {
            return Cart_Vals.toppings_two;
        } else if (Objects.equals(s, "toppings_three")) {
            return Cart_Vals.toppings_three;
        } else if (Objects.equals(s, "toppings_four")) {
            return Cart_Vals.toppings_four;
        } else if (Objects.equals(s, "toppings_five")) {
            return Cart_Vals.toppings_five;
        } else if (Objects.equals(s, "CrustType")) {
            return Cart_Vals.CrustType;
        }
        return "Choose here";
    }
}
