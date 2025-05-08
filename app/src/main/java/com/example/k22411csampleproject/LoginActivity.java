package com.example.k22411csampleproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.connectors.EmployeeConnector;
import com.example.models.Employee;

import java.util.Timer;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassWord;
    CheckBox chkSaveLoginInfor;
    Button btnLogin;
    ImageView imgExit;
    private boolean doubleBackToExitPressedOnce = false;
    private static final long DOUBLE_BACK_PRESS_THRESHOLD = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassWord=findViewById(R.id.edtPassWord);
        chkSaveLoginInfor=findViewById(R.id.chkSaveLoginInfor);
    }

    public void do_login(View view) {
        String usrname = edtUserName.getText().toString();
        String password = edtPassWord.getText().toString();
        EmployeeConnector ec=new EmployeeConnector();
        Employee emp=ec.login(usrname,password);
        if (emp!=null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Login failed - please check your account again!",Toast.LENGTH_LONG).show();
        }
    }

    public void do_exit(View view) {
        //Xác thực, nhận tương tác của người dùng từ cửa sổ
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        //tiêu đề:
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        //nội dung:
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        //biểu tượng
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //thiết laapj tương tác Yes:
        builder.setPositiveButton((res.getText(R.string.confirm_exit_yes)), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //System.exit(0);
                finish();
            }
        });
        builder.setNegativeButton((res.getText(R.string.confirm_exit_no)), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final boolean[] doubleBackToExitPressedOnce = {false};
            if (doubleBackToExitPressedOnce[0]) {
                finish();
                return true;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce[0] = false;
                }
            }, DOUBLE_BACK_PRESS_THRESHOLD);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}