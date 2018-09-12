
package com.example.android.javaprocessor.util;


import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Invoked by mirror extends {@link com.example.android.javaprocessor.base.TNModule}
 */
class ModuleDelegater {

    static final String _METHOD = "_METHOD";

    static void invoke(String path, Map<String, Object> mapping, Object target, ParamsWrapper params) {
        Method method = (Method) mapping.get(path + _METHOD);
        Activity activity = (Activity) params.get("activity");
        String jsParams = (String) params.get("params");
        try {
            method.invoke(target, activity, jsParams);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
