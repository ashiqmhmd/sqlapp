package com.example.sqlapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks;
    Button btnAddData;
    Button btnviewAll;
    private String title;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_Name);
        editSurname = (EditText) findViewById(R.id.editText_Surname);
        editMarks = (EditText) findViewById(R.id.editText_Marks);
        btnAddData = (Button) findViewById(R.id.button_Add);
        btnviewAll =(Button) findViewById(R.id.button_viewAll);
        viewAll();
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                    }
                };



    }

    private void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Cursor res= myDb.getAllData();
                      if (res.getCount()==0){
                          //show message
                          showMessage("Error","Nothing Found");
                          return;
                      }
                      StringBuffer buffer = new StringBuffer();
                      while (res.moveToNext()){
                          buffer.append("Id :"+res.getString(0)+"\n");
                          buffer.append("Name :"+res.getString(1)+"\n");
                          buffer.append("Surname :"+res.getString(2)+"\n");
                          buffer.append("Marks :"+res.getString(3)+"\n\n");

                      }
                      //show all Data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
abcdefg