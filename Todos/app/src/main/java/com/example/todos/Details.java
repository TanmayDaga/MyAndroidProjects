package com.example.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.todos.Database.AppDataBase;
import com.example.todos.Database.TodoEntry;
import com.example.todos.databinding.ActivityDetailsBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class Details extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ActivityDetailsBinding mBinding;

    private static final String LOG_TAG = Details.class.getSimpleName();



    public Date dateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);




        setValuesAndListeners();


    }


    private void setValuesAndListeners(){

//        Setting Button Listeners
        mBinding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        mBinding.datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "Clicked");
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(Details.this, Details.this, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH) + 1,
                        c.get(Calendar.DATE))
                        ;
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });


//        Setting Spinners
        ArrayAdapter<String> spinnerTableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerTableAdapter.addAll(Utilities.getListNames(this));
        mBinding.spinnerTable.setAdapter(spinnerTableAdapter);


        ArrayAdapter<String> spinnerPriorityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerPriorityAdapter.addAll(Utilities.getPriorityList());
        mBinding.prioritySpinnerView.setAdapter(spinnerPriorityAdapter);

        mBinding.prioritySpinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mBinding.priorityDotViewDetails.setBackgroundColor(Utilities.getColorIdFromPriority(Details.this,
                                Utilities.PRIORITY_HIGH));
                        break;
                    case 1:
                        mBinding.priorityDotViewDetails.setBackgroundColor(Utilities.getColorIdFromPriority(Details.this,
                                Utilities.PRIORITY_MEDIUM));
                        break;
                    default:
                        mBinding.priorityDotViewDetails.setBackgroundColor(Utilities.getColorIdFromPriority(Details.this,
                                Utilities.PRIORITY_LOW));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ;
            }
        });





    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dateOfMonth) {
        mBinding.dateTextView.setText(Utilities.dateFormatter(year, monthOfYear, dateOfMonth));
        dateSelected = Utilities.intToDate(year, monthOfYear, dateOfMonth);
    }


    private void setData(){
        String title = mBinding.titleEditText.getText().toString();
        if(title == null){
            Toast.makeText(this, "Title Cannot be null", Toast.LENGTH_SHORT).show();
            return;
        }
        String description = mBinding.descriptionEditText.getText().toString();
        int priority = Utilities.getPriorityFromPosition(
                mBinding.prioritySpinnerView.getSelectedItemPosition());


        String listName = mBinding.spinnerTable.getSelectedItem().toString();

        AppExecutors.getsInstance().diskIo().execute(new Runnable() {
            @Override
            public void run() {
                AppDataBase.getInstance(Details.this).todoDao().insertTodo(new TodoEntry(
                        title,description,dateSelected,listName,priority
                ));
                finish();
            }
        });



    }




}