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

public class UpdateContact extends AppCompatActivity
{
    private EditText mNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneEditText;
    private Button mUpdateBtn;
    private MyDBHelper dbHelper;
    private long contactID;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        Window window = UpdateContact.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(UpdateContact.this, R.color.colorAccent));

        mNameEditText = (EditText)findViewById(R.id.UpdateContactName);
        mLastNameEditText = (EditText)findViewById(R.id.UpdateContactName);
        mPhoneEditText = (EditText)findViewById(R.id.UpdateContactPhone);
        mUpdateBtn = (Button)findViewById(R.id.UpdateContactButton);
        dbHelper = new MyDBHelper(this);
        try
        {
            contactID = getIntent().getLongExtra("CONTACT_ID", 1 );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Contact c = dbHelper.getContact(contactID);
        mNameEditText.setText(c.getName());
        mLastNameEditText.setText(c.getLastName());
        mPhoneEditText.setText(c.getPhoneNumber());
        mUpdateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateContact();
            }
        });
    }

    private  void updateContact()
    {
        String name = mNameEditText.getText().toString().trim();
        String lastname = mLastNameEditText.getText().toString().trim();
        String phone = mPhoneEditText.getText().toString().trim();
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
        Contact updatedContact = new Contact(name, lastname, phone);
        dbHelper.updateContact(contactID, this, updatedContact);
        finish();
    }

}