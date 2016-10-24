package phatnvm.nytimessearch.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import phatnvm.nytimessearch.Model.SearchRequest;
import phatnvm.nytimessearch.R;

public class SearchConfig extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    private Button btnConfig;
    private SearchRequest savedata;
    private Spinner spnOrder;
    private CheckBox cbArt, cbFashion, cbSport;
    private DatePicker dpDatePicker;

    int year, month, day, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_config);


        savedata = (SearchRequest) getIntent().getParcelableExtra("savedata");

        btnConfig = (Button) findViewById(R.id.btnSave);
        btnConfig.setOnClickListener(this);

        spnOrder = (Spinner) findViewById(R.id.spnOrder);
        spnOrder.setOnItemSelectedListener(this);

        cbArt = (CheckBox) findViewById(R.id.cbArts);
        cbFashion = (CheckBox) findViewById(R.id.cbFashionStyle);
        cbSport = (CheckBox) findViewById(R.id.cbSports);
        cbArt.setOnCheckedChangeListener(this);
        cbFashion.setOnCheckedChangeListener(this);
        cbSport.setOnCheckedChangeListener(this);

        Calendar calendar = Calendar.getInstance();
        dpDatePicker = (DatePicker) findViewById(R.id.dpDatePicker);


        int year, month, day, date;
        if(savedata.getBeginDate() != null) {
            date = Integer.valueOf(savedata.getBeginDate());
            day = date % 100;
            month = (date / 100) % 100;
            year = date / 10000;
            dpDatePicker.updateDate(year, month, day);
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        }
        dpDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String s_month;
                if ((monthOfYear)/10 == 0)
                    s_month = "0"+(monthOfYear);
                else
                    s_month = ""+(monthOfYear);
                String date = "" + year + s_month + dayOfMonth;
                savedata.setBeginDate(date);
            }
        });

        getdata();

    }
    private void getdata() {
        if (savedata.getOrder().equals("Newest"))
            spnOrder.setSelection(0);
        else
            spnOrder.setSelection(1);

        cbArt.setChecked(savedata.isHasArts());
        cbFashion.setChecked(savedata.isHasFashionAndStyle());
        cbSport.setChecked(savedata.isHasSports());
    }

    private void ToastCall(String display) {
        Toast.makeText(this, display, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.putExtra("savedata_back", savedata);
        setResult(RESULT_OK, i);

        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        savedata.setOrder(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbArts:
                if (isChecked)
                    savedata.setHasArts(true);
                else
                    savedata.setHasArts(false);
                break;
            case R.id.cbFashionStyle:
                if (isChecked)
                    savedata.setHasFashionAndStyle(true);
                else
                    savedata.setHasFashionAndStyle(false);
                break;
            case R.id.cbSports:
                if (isChecked)
                    savedata.setHasSports(true);
                else
                    savedata.setHasSports(false);
                break;
            default:
        }
    }
}
