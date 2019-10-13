package id.co.malangcreative.eatapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.co.malangcreative.eatapp.R;
import id.co.malangcreative.eatapp.fragment.dessertFragment;
import id.co.malangcreative.eatapp.fragment.drinkFragment;
import id.co.malangcreative.eatapp.fragment.foodFragment;

public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new foodFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.action_food:
                fragment = new foodFragment();
                break;
            case R.id.action_drink:
                fragment = new drinkFragment();
                break;
            case R.id.action_dessert:
                fragment = new dessertFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void btn_handler_nasgor(View view) {
        Intent intent =  new Intent(this, nasgorActivity.class);
        startActivity(intent);
    }

    public void btn_handler_coklat(View view) {
        Intent intent =  new Intent(this, coklatActivity.class);
        startActivity(intent);
    }

    public void btn_handler_chocake(View view) {
        Intent intent =  new Intent(this, chocakeActivity.class);
        startActivity(intent);
    }
}
