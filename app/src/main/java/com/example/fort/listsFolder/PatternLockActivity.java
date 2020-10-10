package com.example.fort.listsFolder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.fort.ListsActivity;
import com.example.fort.listsFolder.Model.Password;
import com.example.fort.listsFolder.Services.BackgroundManager;
import com.example.fort.listsFolder.utils.Utils;
import com.example.fort.R;
import com.shuhart.stepview.StepView;

import java.util.List;

public class PatternLockActivity extends AppCompatActivity {

    StepView stepView;
    LinearLayout normallayout;
    TextView status_password;
    RelativeLayout relativeLayout;
    Password Utilpasssword;
    String userpasssword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_lock);
        BackgroundManager.getInstance().init(this).startService();
        

        stepView = findViewById(R.id.step_view);
        normallayout = findViewById(R.id.normal_layout);
        relativeLayout = findViewById(R.id.root);
        status_password = findViewById(R.id.statuspassword);
        try {
            status_password.setText(Utilpasssword.STATUS_FIRST_STEP);
        }
        catch (NullPointerException ignored)
        {

        }

        Utilpasssword = new Password(this);
        if (Utilpasssword.getPassword() == null) {
            normallayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.VISIBLE);
            stepView.setStepsNumber(2);
            stepView.go(0, true);

        } else {
            normallayout.setVisibility(View.VISIBLE);
            stepView.setVisibility(View.GONE);


            int backcolor = ResourcesCompat.getColor(getResources(), R.color.blue, null);
            relativeLayout.setBackgroundColor(backcolor);
            status_password.setTextColor(Color.GREEN);
        }

        initIconApp();
        initpatternlistener();

    }

    private void initIconApp() {
        if(getIntent().getStringExtra("broadcast_reciever") != null)
        {
            ImageView icons = findViewById(R.id.icons_apps);
            String current_app = new Utils(this).getLastApp();
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = getPackageManager().getApplicationInfo(current_app,0);
            }
            catch (PackageManager.NameNotFoundException e)
            {
                e.printStackTrace();
            }
            icons.setImageDrawable(applicationInfo.loadIcon(getPackageManager()));
        }

    }

    private void initpatternlistener() {
        final PatternLockView patternLockView = findViewById(R.id.patternview);
        patternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {

                String pwd = PatternLockUtils.patternToString(patternLockView, pattern);
                if (pwd.length() < 4) {
                    status_password.setText(Utilpasssword.SHEMA_FAILED);
                    patternLockView.clearPattern();
                }
                if (Utilpasssword.getPassword() == null) {
                    if (Utilpasssword.isFirstStep())
                    {
                        userpasssword = pwd;
                        Utilpasssword.setFirstStep(false);
                        status_password.setText(Utilpasssword.STATUS_NEXT_STEP);
                        stepView.go(1,true);

                    }
                    else
                    {
                        if (userpasssword.equals(pwd))
                        {
                            Utilpasssword.setPassword(userpasssword);
                            status_password.setText(Utilpasssword.STATUS_PASSWORD_CORRECT);
                            stepView.done(true);
                            startAct();

                        }
                        else
                        {
                            status_password.setText(Utilpasssword.STATUS_PASSWORD_INCPRRECT);

                        }
                    }
                }

                else
                {
                    if (Utilpasssword.iscorrect(pwd))
                    {
                        status_password.setText(Utilpasssword.STATUS_PASSWORD_CORRECT);
                        startAct();

                    }
                    else
                    {
                        status_password.setText(Utilpasssword.STATUS_PASSWORD_INCPRRECT);

                    }
                }
                patternLockView.clearPattern();
            }

            @Override
            public void onCleared() {

            }
        });
    }

    private void startAct() {
        if(getIntent().getStringExtra("broadcast_reciever") == null)
        {
            startActivity(new Intent(this,ListsActivity.class));
        }
        finish();
        startActivity(new Intent(this, ListsActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {

        if (Utilpasssword.getPassword()==null && !Utilpasssword.isFirstStep())
        {
            stepView.go(0,true);
            Utilpasssword.setFirstStep(true);
            try {

                status_password.setText(Utilpasssword.STATUS_FIRST_STEP);
            }
            catch (NullPointerException ignored)
            {

            }
        }
        else
        {
            startCurrentHomepackage();
            finish();
            super.onBackPressed();

        }

    }

    private void startCurrentHomepackage() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY);


        ActivityInfo activityInfo = resolveInfo.activityInfo;
        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName,activityInfo.name);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
        new Utils(this).clearLastApp();



    }
}