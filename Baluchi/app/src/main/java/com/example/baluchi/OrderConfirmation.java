package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class OrderConfirmationActivity extends AppCompatActivity {

    private TextView confirmationTextView;
    private Button backToMenuButton;
    private FirebaseFirestore db;
    private String foodItemId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        confirmationTextView = findViewById(R.id.confirmationTextView);
        backToMenuButton = findViewById(R.id.backToMenuButton);
        db = FirebaseFirestore.getInstance();

        foodItemId = getIntent().getStringExtra("foodItemId");

        // Store the order in Firebase (assuming you have a separate orders collection)
        db.collection("orders").add(new Order(foodItemId)).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                confirmationTextView.setText("Your order has been placed successfully!");
            } else {
                confirmationTextView.setText("There was an issue placing your order. Please try again.");
            }
        });

        backToMenuButton.setOnClickListener(view -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, MenuActivity.class);
            startActivity(intent);
        });
    }
}
/*
* <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/confirmationTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp" />

    <Button
        android:id="@+id/backToMenuButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Back to Menu" />
</LinearLayout>

* */