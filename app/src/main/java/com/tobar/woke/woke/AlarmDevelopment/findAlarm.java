package com.tobar.woke.woke.AlarmDevelopment;

import android.content.Context;
import android.media.Ringtone;
import android.net.Uri;

public class findAlarm {
    Context context;
    Uri uri;
    Ringtone ringtone;

    public findAlarm(Ringtone ringtone) {
//        this.context = context;
//        this.uri = uri;

        this.ringtone = ringtone;
    }


    public void stopAlarm() {

        this.ringtone.stop();
        this.ringtone = null;


    }
}
