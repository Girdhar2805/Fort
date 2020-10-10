package com.example.fort.listsFolder.Model;

import android.content.Context;

import io.paperdb.Paper;

public class Password {
    private String password_key = "PASSWORD_KEY";
    public String STATUS_FIRST_STEP = "Draw an unlock Pattern";
    public String STATUS_NEXT_STEP = "Draw Pattern again to confirm";
    public String STATUS_PASSWORD_CORRECT = "Pattern Set";
    public String STATUS_PASSWORD_INCPRRECT = "Try Again";
    public String SHEMA_FAILED = "connect atleast 4 dots";

    private boolean isFirstStep = true;
    public Password(Context ctx)
    {
        Paper.init(ctx);
    }
    public void setPassword(String pwd)
    {
        Paper.book().write(password_key,pwd);
    }
    public String getPassword()
    {
        return Paper.book().read(password_key);
    }
    public boolean isFirstStep()
    {
        return isFirstStep;
    }
    public void setFirstStep(boolean firstStep)
    {
        isFirstStep = firstStep;
    }
    public boolean iscorrect(String pwd)
    {
     return pwd.equals(getPassword());
    }

}
