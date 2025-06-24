package com.example.k22411csampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.connectors.SQLiteConnector;
import com.example.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassWord;
    CheckBox chkSaveLoginInfor;
    Button btnLogin;
    ImageView imgExit;

    BroadcastReceiver networkReceiver=null;

    String DATABASE_NAME="Sale_Database.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    private boolean doubleBackToExitPressedOnce = false;
    private static final long DOUBLE_BACK_PRESS_THRESHOLD = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addView();

        processCopy();

        setupBroadcastReceiver();
    }

    private void setupBroadcastReceiver() {
        networkReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //chút nữa tự động nhảy vào đây khi internet bị thay đổi trạng thái
                //Internet bị thay đổi trạng thái
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected())
                {//vào đây là có Internet không quan tâm WIFI hay 4G
                    btnLogin.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnLogin.setVisibility(View.INVISIBLE);
                }

            }
        };
    }

    private void addView() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassWord=findViewById(R.id.edtPassWord);
        chkSaveLoginInfor=findViewById(R.id.chkSaveLoginInfor);
        btnLogin=findViewById(R.id.btnLogin);
    }

    public void do_login(View view) {
        String usr = edtUserName.getText().toString();
        String pwd = edtPassWord.getText().toString();
        EmployeeConnector ec = new EmployeeConnector();

        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        sqLiteConnector.openDatabase();
        SQLiteDatabase database = sqLiteConnector.getDatabase(); // Lấy database từ cùng đối tượng
        if (database == null) {
            Toast.makeText(this, "Failed to open database!", Toast.LENGTH_LONG).show();
            return;
        }

        Employee emp = ec.login(database, usr, pwd);

        if (emp != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login failed - please check your account again!", Toast.LENGTH_LONG).show();
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
    // Lưu dữ liệu vào ổ cứng của điện thoại
    public void saveLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String usrname = edtUserName.getText().toString();
        String password = edtPassWord.getText().toString();
        boolean isSave=chkSaveLoginInfor.isChecked();
        editor.putString("USERNAME",usrname);
        editor.putString("PASSWORD",password);
        editor.putBoolean("SAVED",isSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
        if(networkReceiver!=null)
        {
            unregisterReceiver(networkReceiver);
        }
    }

    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usrname=preferences.getString("USERNAME","");
        String password=preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED",true);
        if(isSave)
        {
            edtUserName.setText(usrname);
            edtPassWord.setText(password);
            chkSaveLoginInfor.setChecked(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
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
    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);

            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}