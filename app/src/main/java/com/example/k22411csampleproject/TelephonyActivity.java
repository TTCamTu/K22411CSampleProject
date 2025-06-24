package com.example.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.TelephonyInforAdapter;
import com.example.models.TelephonyInfor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelephonyActivity extends AppCompatActivity {

    private static final String TAG = "TelephonyActivity";
    ListView lvTelephony;
    TelephonyInforAdapter adapter;
    ArrayList<TelephonyInfor> allContacts; // Lưu toàn bộ danh bạ
    private static final List<String> VIETTEL_PREFIXES = Arrays.asList("032", "033", "034", "035", "036", "037", "038", "039", "086", "096", "097", "098");
    private static final List<String> MOBIFONE_PREFIXES = Arrays.asList("070", "077", "078", "079", "089", "090", "093");
    private static final String FILTER_ALL = "all";
    private static final String FILTER_VIETTEL = "viettel";
    private static final String FILTER_MOBIFONE = "mobifone";
    private static final String FILTER_OTHER = "other";
    private String currentFilter = FILTER_ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        getAllContacts();
        addEvents();
    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TelephonyInfor ti=adapter.getItem(i);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        if (allContacts == null) {
            Log.e(TAG, "allContacts is null, initializing...");
            allContacts = new ArrayList<>();
        }
        allContacts.clear();
        adapter.clear();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phone = cursor.getString(phoneIndex);

                // Chuẩn hóa số điện thoại
                String normalizedPhone = phone != null ? phone.replaceAll("[^0-9]", "") : "";
                if (normalizedPhone.startsWith("+84")) {
                    normalizedPhone = "0" + normalizedPhone.substring(3);
                }

                TelephonyInfor ti = new TelephonyInfor();
                ti.setName(name);
                ti.setPhone(normalizedPhone);
                ti.setCarrier(getCarrier(normalizedPhone));
                allContacts.add(ti);
            }
            cursor.close();
        } else {
            Log.e(TAG, "Cursor is null, cannot fetch contacts");
            Toast.makeText(this, "Không thể lấy danh bạ", Toast.LENGTH_SHORT).show();
        }

        filterContacts(currentFilter);
    }

    private String getCarrier(String phone) {
        if (phone != null && phone.length() >= 10) {
            String prefix = phone.substring(0, 3);
            if (VIETTEL_PREFIXES.contains(prefix)) {
                return "Viettel";
            } else if (MOBIFONE_PREFIXES.contains(prefix)) {
                return "Mobifone";
            } else {
                return "Other";
            }
        }
        return "Unknown";
    }

    private void filterContacts(String filter) {
        adapter.clear();
        for (TelephonyInfor ti : allContacts) {
            if (filter.equals(FILTER_ALL) || ti.getCarrier().equalsIgnoreCase(filter)) {
                adapter.add(ti);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.telephony_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_viettel) {
            currentFilter = FILTER_VIETTEL;
            filterContacts(FILTER_VIETTEL);
            Toast.makeText(this, "Lọc danh bạ Viettel", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu_mobifone) {
            currentFilter = FILTER_MOBIFONE;
            filterContacts(FILTER_MOBIFONE);
            Toast.makeText(this, "Lọc danh bạ Mobifone", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu_other) {
            currentFilter = FILTER_OTHER;
            filterContacts(FILTER_OTHER);
            Toast.makeText(this, "Lọc danh bạ nhà mạng khác", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.menu_all) {
            currentFilter = FILTER_ALL;
            filterContacts(FILTER_ALL);
            Toast.makeText(this, "Hiển thị tất cả danh bạ", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void addViews() {
        lvTelephony=findViewById(R.id.lvTelephonyInfor);
        adapter=new TelephonyInforAdapter(this,R.layout.item_telephony_infor);
        lvTelephony.setAdapter(adapter);
        allContacts = new ArrayList<>();

    }

    public void directCall(TelephonyInfor ti) {
        if (ti != null && ti.getPhone() != null) {
            Uri uri = Uri.parse("tel:" + ti.getPhone());
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(uri);
            startActivity(intent);
        }
    }
    public void dialupCall(TelephonyInfor ti) {
        if (ti != null && ti.getPhone() != null) {
            Uri uri = Uri.parse("tel:" + ti.getPhone());
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(uri);
            startActivity(intent);
        }
    }

}