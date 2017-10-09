package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private int pricePerCup = 5;
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void increment(View view) {

        quantity++;
        displayQuantity(quantity);

    }

    public void decrement(View view) {

        quantity--;
        displayQuantity(quantity);

    }

    /**
     * Create a summary of the order
     *
     * @param orderPrice      The price of the order
     * @param hasWhippedCream Does the user have Whipped Cream
     * @param hasChocolate    Does the user have Chocolate
     * @param name            The users name
     * @return The summary of the order
     */
    public String createOrderSummary(int orderPrice, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String orderSummary = "Name: " + name + "\n";
        orderSummary += "Add whipped cream?: " + hasWhippedCream + "\n";
        orderSummary += "Add chocolate?: " + hasChocolate + "\n";
        orderSummary += "Quantity: " + quantity + "\n";
        orderSummary += "Total: " + NumberFormat.getCurrencyInstance().format(orderPrice) + "\n";
        orderSummary += "Thank You!";
        return orderSummary;


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameEditText = findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();

        displaySummary(createOrderSummary(calculatePrice(), hasWhippedCream, hasChocolate, name));

    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {

        return quantity * pricePerCup;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCups) {

        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.format(Locale.US, "%d", numberOfCups));

    }


    /**
     * This method displays the given price on the screen.
     */
    private void displaySummary(String summary) {

        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(summary);

    }

    public void updateWhippedCream(View view) {

        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_check_box);
        hasWhippedCream = whippedCreamCheckBox.isChecked();

    }

    public void updateChocolate(View view) {

        CheckBox chosolateCheckBox = findViewById(R.id.chocolate_check_box);
        hasChocolate = chosolateCheckBox.isChecked();

    }

}