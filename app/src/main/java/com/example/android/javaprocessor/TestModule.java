package com.example.android.javaprocessor;

import android.app.Activity;
import android.widget.Toast;

import com.example.android.javaprocessor.base.TNModule;
import com.example.annotations.JSMethod;
import com.example.annotations.JSModule;

@JSModule(name = "testModule")
public class TestModule extends TNModule {

    @JSMethod(alias = "toast")
    public void toastMessage(Activity activity, String params) {
        Toast.makeText(activity, params, Toast.LENGTH_LONG).show();
    }

}
