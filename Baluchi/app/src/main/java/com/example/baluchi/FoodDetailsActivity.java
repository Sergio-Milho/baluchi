package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class FoodDetailsActivity extends AppCompatActivity {

    private TextView nameTextView, descriptionTextView, priceTextView;
    private ImageView imageView;
    private Button orderButton, backButton;
    private FirebaseFirestore db;
    private String foodItemId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        priceTextView = findViewById(R.id.priceTextView);
        imageView = findViewById(R.id.imageView);
        orderButton = findViewById(R.id.orderButton);
        backButton = findViewById(R.id.backButton);
        db = FirebaseFirestore.getInstance();

        foodItemId = getIntent().getStringExtra("foodItemId");

        db.collection("foodItems").document(foodItemId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    nameTextView.setText(document.getString("name"));
                    descriptionTextView.setText(document.getString("description"));
                    priceTextView.setText("$" + document.getDouble("price"));
                    Picasso.get().load(document.getString("image")).into(imageView);
                }
            }
        });

        orderButton.setOnClickListener(view -> {
            Intent intent = new Intent(FoodDetailsActivity.this, OrderActivity.class);
            intent.putExtra("foodItemId", foodItemId);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(FoodDetailsActivity.this, MenuActivity.class);
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
        android:id="@+id/nameTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="24sp"
        android:textStyle="bold" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:scaleType="centerCrop"
        android:layout_marginTop="16dp"/>

    <TextView
        android:id="@+id/descriptionTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp" />

    <TextView
        android:id="@+id/priceTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:textSize="18sp" />

    <Button
        android:id="@+id/orderButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Order" />

    <Button
        android:id="@+id/backButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Back to Menu" />
</LinearLayout>

* */