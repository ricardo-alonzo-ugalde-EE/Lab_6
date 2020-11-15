package org.electricuniverse.lab_6;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyDBHelper dbHelper;
    private MyRecyclerAdapter adapter;
    private String filter = "";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("TAG", " OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = MainActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

       mRecyclerView = (RecyclerView)findViewById(R.id.mainRecyclerView);
       mLayoutManager = new LinearLayoutManager(this);
       mLayoutManager.scrollToPosition(0);
       mRecyclerView.setLayoutManager(mLayoutManager);
       populateRecyclerView(filter);
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

       fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "clicked cig", Toast.LENGTH_SHORT).show();
                goToAddUserActivity();



            }
        });

    }
    private void populateRecyclerView(String filter)
    {
        dbHelper = new MyDBHelper(this);
        adapter = new MyRecyclerAdapter(dbHelper.contactList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList(dbHelper.contactList(filter));
        Log.d("TAG", "Resume");

    }

 
    private void goToAddUserActivity()
    {
        Intent intent = new Intent (MainActivity.this, AddNewContact.class);
        startActivity(intent);
    }


}

