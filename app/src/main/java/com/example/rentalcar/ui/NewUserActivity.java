package com.example.rentalcar.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.viewmodel.UserViewModel;

public class NewUserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void addUser(View view) {
        EditText name=(EditText)findViewById(R.id.editName);
        EditText email=(EditText)findViewById(R.id.editEmailCreate);
        User user=new User();
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setMoney(10000);
        Intent intent=new Intent();
        intent.putExtra("user",user);
        setResult(RESULT_OK,intent);
        finish();
    }
}