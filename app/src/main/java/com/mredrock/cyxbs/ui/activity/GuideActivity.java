package com.mredrock.cyxbs.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mredrock.cyxbs.Config;
import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.util.SpUtils;

public class GuideActivity extends Activity {

    private Button btnGoto;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        sp = SpUtils.getPreference(getApplicationContext());

        btnGoto = (Button) findViewById(R.id.btn_goto);
        btnGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(Config.LOCAL_SP_IS_GUID,false);//put false to sp,it won't guide when is opened next time
                editor.commit();

                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                GuideActivity.this.finish();
            }
        });
    }


}
