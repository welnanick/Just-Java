package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void increment(View view) {

        if (quantity < 100) {

            quantity++;
            displayQuantity(quantity);

        } else {

            Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();

        }

    }

    public void decrement(View view) {

        if (quantity > 1) {

            quantity--;
            displayQuantity(quantity);

        } else {

            Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();

        }

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

        String orderSummary = String.format(getString(R.string.order_summary_name), name) + "\n";
        orderSummary += String.format(getString(R.string.order_summary_whipped_cream), hasWhippedCream) + "\n";
        orderSummary += String.format(getString(R.string.order_summary_chocolate), hasChocolate) + "\n";
        orderSummary += String.format(getString(R.string.order_summary_quantity), quantity) + "\n";
        orderSummary += String.format(getString(R.string.order_summary_price), NumberFormat.getCurrencyInstance().format(orderPrice)) + "\n";
        orderSummary += getString(R.string.thank_you);
        return orderSummary;


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameEditText = findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, String.format(getString(R.string.order_summary_email_subject), name));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(calculatePrice(), hasWhippedCream, hasChocolate, name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {

        int pricePerCup = 5;

        if (hasChocolate) {

            pricePerCup += 2;

        }

        if (hasWhippedCream) {

            pricePerCup += 1;

        }

        return quantity * pricePerCup;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCups) {

        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.format(Locale.US, "%d", numberOfCups));

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