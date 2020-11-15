package org.electricuniverse.lab_6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewContact extends AppCompatActivity
{
    private EditText mNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneEditText;
    private Button mAddBtn;
    private MyDBHelper dbHelper;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        Window window = AddNewContact.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(AddNewContact.this, R.color.colorAccent));

        mNameEditText = (EditText)findViewById(R.id.contactName);
        mLastNameEditText = (EditText)findViewById(R.id.contactLastName);
        mPhoneEditText = (EditText)findViewById(R.id.contactPhone);
        mAddBtn = (Button)findViewById(R.id.addNewContactButton);
        mAddBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveContact();
            }
        });
    }

    private void saveContact()
    {
        String name = mNameEditText.getText().toString().trim();
        String lastname = mLastNameEditText.getText().toString().trim();
        String phone = mPhoneEditText.getText().toString().trim();
        dbHelper = new MyDBHelper(this);
        if(name.isEmpty())
        {
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(lastname.isEmpty())
        {
            Toast.makeText(this, "You must enter a last name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.isEmpty())
        {
            Toast.makeText(this, "You must enter a le phone", Toast.LENGTH_SHORT).show();
            return;
        }
        Contact c = new Contact(name, lastname, phone);
        dbHelper.saveNewContact(c, this);
        finish();
    }


}