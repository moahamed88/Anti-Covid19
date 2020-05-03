package com.example.makank.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.makank.Alert;
import com.example.makank.LoadingDialog;
import com.example.makank.R;
import com.example.makank.SharedPref;
import com.example.makank.data.network.ApiClient;
import com.example.makank.data.network.ApiInterface;
import com.example.makank.data.model.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button btn;
    RadioGroup radioGroupGender;
    RadioButton radioButton;
    String local_id, ln;
    EditText f_name, s_name, l_name, phone,bDay;
    TextView typeGender,info,maleR,femaleR;
    Typeface typeface;
    LoadingDialog loadingDialog;
    Alert alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = findViewById(R.id.don_register);
        f_name = findViewById(R.id.f_name);
        s_name = findViewById(R.id.s_name);
        l_name = findViewById(R.id.l_name);
        phone = findViewById(R.id.phone);
        bDay = findViewById(R.id.age);
        typeGender = findViewById(R.id.txtGen);
       info = findViewById(R.id.insertInfo);
        maleR = findViewById(R.id.mail);
        femaleR = findViewById(R.id.female);
        typeface = Typeface.createFromAsset(this.getAssets(), "fonts/Hacen-Algeria.ttf");
        btn.setTypeface(typeface);
        f_name.setTypeface(typeface);
        s_name.setTypeface(typeface);
        l_name.setTypeface(typeface);
        phone.setTypeface(typeface);
        bDay.setTypeface(typeface);
        typeGender.setTypeface(typeface);
        info.setTypeface(typeface);
      //  maleR.setTypeface(typeface);
     //   femaleR.setTypeface(typeface);


        SharedPref.getInstance(RegisterActivity.this).storeUserLocal(ln);
        radioGroupGender = findViewById(R.id.gender_radiogroup);
        alert = new Alert(this);
        loadingDialog = new LoadingDialog(this);

        local_id = getIntent().getStringExtra("local_id");
        ln = getIntent().getStringExtra("local_name");

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                createPerson();

            }
        });
    }


    private void createPerson() {
        /* ProgressDialog progressDialog;
       progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("loading....");*/
        final String first_name = f_name.getText().toString();
        final String second_name = s_name.getText().toString();
        final String last_name = l_name.getText().toString();
        final String phone_number = phone.getText().toString();
        final String age = bDay.getText().toString();
        int selectedID = radioGroupGender.getCheckedRadioButtonId();
        radioButton = findViewById(selectedID);
        final String gender = (String) radioButton.getText();

        if (TextUtils.isEmpty(first_name)) {
            // Toast.makeText(getApplicationContext(), "يجب ملئ جميع الحقول", Toast.LENGTH_SHORT).show();
            alert.showAlertError("يجب ملئ جميع الحقول");
            return;
        }
        if (TextUtils.isEmpty(second_name)) {
            //  Toast.makeText(getApplicationContext(), "يجب ملئ جميع الحقول", Toast.LENGTH_SHORT).show();
            alert.showAlertError("يجب ملئ جميع الحقول");
            return;
        }
        if (TextUtils.isEmpty(last_name)) {

            // Toast.makeText(getApplicationContext(), "يجب ملئ جميع الحقول", Toast.LENGTH_SHORT).show();
            alert.showAlertError("يجب ملئ جميع الحقول");
            return;

        }
        if (TextUtils.isEmpty(phone_number)) {
            //Toast.makeText(getApplicationContext(), "يجب ملئ جميع الحقول", Toast.LENGTH_SHORT).show();
            alert.showAlertError("يجب ملئ جميع الحقول");
            return;

        }

        if (TextUtils.isEmpty(age)) {
            // Toast.makeText(getApplicationContext(), "يجب ملئ جميع الحقول", Toast.LENGTH_SHORT).show();
            alert.showAlertError("يجب ملئ جميع الحقول");
            return;

        }
        Person person = new Person(first_name, second_name, last_name, phone_number, gender, age, local_id);
        // progressDialog.show();
        loadingDialog.startLoadingDialog();

        ApiInterface apiService = ApiClient.getRetrofitClient().create(ApiInterface.class);
        Call<Person> call = apiService.getUserRegi(person);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                if (response.isSuccessful()) {
                    // progressDialog.dismiss();
                    loadingDialog.dismissDialog();

                    String id_person = String.valueOf(response.body().getId());
                    String f_name = response.body().getFirst_name();
                    String s_name = response.body().getSecond_name();
                    String l_name = response.body().getLast_name();
                    String phone = response.body().getPhone();
                    String gender = response.body().getGender();
                    String age = response.body().getAge();
                    String status = response.body().getStatus();

                    // Toast.makeText(RegisterActivity.this, age+"", Toast.LENGTH_SHORT).show();

                    SharedPref.getInstance(RegisterActivity.this).storeUserID(id_person, f_name, s_name, l_name, phone, gender, age, status);
                    Intent intent = new Intent(RegisterActivity.this, DiseaseActivity.class);
                    startActivity(intent);
                    finish();
                 Toast.makeText(RegisterActivity.this, "done", Toast.LENGTH_SHORT).show();
                    alert.showAlertSuccess("");
                }

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                //progressDialog.dismiss();
                loadingDialog.dismissDialog();

                alert.showAlertError("تــأكد من اتصالك بالإنترنت");
                // Toast.makeText(RegisterActivity.this, "خطاء في النظام الخارجي" + t, Toast.LENGTH_SHORT).show();

            }
        });
    }
}


