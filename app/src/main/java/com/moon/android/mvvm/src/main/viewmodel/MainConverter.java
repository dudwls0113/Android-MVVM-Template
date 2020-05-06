package com.moon.android.mvvm.src.main.viewmodel;

public class MainConverter {
    public static boolean signInBtnValidate(String id, String pw) {
        if(id.length() < 1 || pw.length() < 1){
            return false;
        }
        else {
            return true;
        }
    }
}
