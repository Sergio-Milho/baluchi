package com.example.foodorderapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class BusinessManagementActivity extends AppCompatActivity {

    private EditText nameEditText, descriptionEditText, priceEditText, imageEditText, deleteNameEditText;
    private Button addButton, deleteButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_management);

        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        priceEditText = findViewById(R.id.priceEditText);
        imageEditText = findViewById(R.id.imageEditText);
        deleteNameEditText = findViewById(R.id.deleteNameEditText);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        db = FirebaseFirestore.getInstance();

        addButton.setOnClickListener(view -> addFoodItem());
        deleteButton.setOnClickListener(view -> deleteFoodItem());
    }

    private void addFoodItem() {
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        String image = imageEditText.getText().toString();

        FoodItem foodItem = new FoodItem(name, description, price, image);
        db.collection("foodItems").add(foodItem).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Successfully added food item
            } else {
                // Failed to add food item
            }
        });
    }

    private void deleteFoodItem() {
        String name = deleteNameEditText.getText().toString();

        db.collection("foodItems").whereEqualTo("name", name).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && !task.getResult().isEmpty()) {
                task.getResult().getDocuments().get(0).getReference().delete();
            }
        });
    }
}
/*
* <?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <EditText
            android:id="@+id/nameEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Food Name" />

        <EditText
            android:id="@+id/descriptionEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Food Description" />

        <EditText
            android:id="@+id/priceEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Food Price"
            android:inputType="numberDecimal" />

        <EditText
            android:id="@+id/imageEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Image URL" />

        <Button
            android:id="@+id/addButton"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Add Food Item"
            android:layout_marginTop="16dp" />

        <EditText
            android:id="@+id/deleteNameEditText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Food Name to Delete"
            android:layout_marginTop="32dp" />

        <Button
            android:id="@+id/deleteButton"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Delete Food Item"
            android:layout_marginTop="16dp" />
    </LinearLayout>
</ScrollView>

* */