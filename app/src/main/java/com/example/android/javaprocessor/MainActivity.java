package com.example.android.javaprocessor;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.javaprocessor.base.TNModule;
import com.example.android.javaprocessor.util.ParamsWrapper;
import com.example.android.javaprocessor.util.TNModuleHelp;

import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.bt_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAndInvoke(MainActivity.this, "success");
            }
        });
    }

    private void searchAndInvoke(Activity activity, String params) {
        String path = "com.example.android.javaprocessor.util.Mirror_";
        String jsModule = "testModule";
        String jsMethod = "toast";
        try {
            Class<?> clazz = Class.forName(path + jsModule);
            Method targetMethod = clazz.getMethod("invoke", String.class, ParamsWrapper.class);
            //find from cache pool
            TNModule target = TNModuleHelp.getInstance().findTNModuleByKey(jsModule);
            if (target == null) {
                target = (TNModule) clazz.newInstance();
                TNModuleHelp.getInstance().addToTNModulePool(jsModule, target);
            }
            HashMap<String, Object> parmrs = new HashMap();
            parmrs.put("activity", activity);
            parmrs.put("params", params);
            targetMethod.invoke(clazz.newInstance(), jsMethod, createParamsWrapper(parmrs));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ParamsWrapper createParamsWrapper(Object params) throws JSONException {
        ParamsWrapper wrapper = new ParamsWrapper(params);
        return wrapper;
    }

}
