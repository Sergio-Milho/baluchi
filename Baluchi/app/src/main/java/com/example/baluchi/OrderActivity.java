package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrderActivity extends AppCompatActivity {

    private TextView orderSummaryTextView;
    private Button confirmButton;
    private FirebaseFirestore db;
    private String foodItemId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderSummaryTextView = findViewById(R.id.orderSummaryTextView);
        confirmButton = findViewById(R.id.confirmButton);
        db = FirebaseFirestore.getInstance();

        foodItemId = getIntent().getStringExtra("foodItemId");

        db.collection("foodItems").document(foodItemId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String orderSummary = "Order Summary:\n\n" +
                            "Name: " + document.getString("name") + "\n" +
                            "Description: " + document.getString("description") + "\n" +
                            "Price: $" + document.getDouble("price");
                    orderSummaryTextView.setText(orderSummary);
                }
            }
        });

        confirmButton.setOnClickListener(view -> {
            Intent intent = new Intent(OrderActivity.this, OrderConfirmationActivity.class);
            intent.putExtra("foodItemId", foodItemId);
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
        android:id="@+id/orderSummaryTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp" />

    <Button
        android:id="@+id/confirmButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Confirm Order" />
</LinearLayout>

* */