package com.tobar.woke.woke.AlarmDevelopment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

    public class DemoActivity extends FragmentActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            /** Creating an Alert Dialog Window */
            AlertFragment alert = new AlertFragment();

            /** Opening the Alert Dialog Window. This will be opened when the alarm goes off */
            alert.show(getSupportFragmentManager(), "AlertDemo");
        }
}
