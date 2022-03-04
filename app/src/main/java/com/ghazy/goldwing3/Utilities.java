package com.ghazy.goldwing3;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Utilities {
    private static Utilities instance;

    public Utilities()
    {
    }
    public static Utilities getInstance()
    {
        if (instance == null)
            instance = new Utilities();
        return instance;
    }
    static public boolean EmailValidation(Context context, String username){
        String[] email = username.split("@");
        String part1 = email[0];
        String part2 = email[1];
        int n = 0;

        if (email.length != 2) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (username.trim().isEmpty()) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int j = 0; j < part1.length(); j++) {
            if (part1.charAt(j) == ' ') {
                Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if (!(part1.charAt(0) >= 'A' && part1.charAt(0) <= 'Z' ||
                part1.charAt(0) >= 'a' && part1.charAt(0) <= 'z' ||
                part1.charAt(0) >= '_')) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        for (int j = 1; j < part1.length(); j++) {
            if (!(part1.charAt(0) >= 'A' && part1.charAt(0) <= 'Z' ||
                    part1.charAt(0) >= 'a' && part1.charAt(0) <= 'z' ||
                    part1.charAt(0) >= '0' && part1.charAt(0) <= '9' ||
                    part1.charAt(0) >= '_' || part1.charAt(0) <= '.')) {
                Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if (part1.length() >= 70 || part1.length() < 3) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (part2.split(".").length >= 1 && part2.split(".").length <= 4) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!(part2.charAt(0) >= 'A' && part2.charAt(0) <= 'Z' ||
                part2.charAt(0) >= 'a' && part2.charAt(0) <= 'z' ||
                part2.charAt(0) >= '_')) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        if ((part2.charAt(0) == '.') || (part2.charAt(part2.length() - 1) == '.')) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_LONG).show();
            return false;
        }
        for (int j = 1; j < part2.length() - 1; j++) {
            if (part2.charAt(j) == '.')
                n++;
        }
        if (n == 0) {
            Toast.makeText(context, "Email is invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    static public boolean PasswordValidation(Context context, String password)
    {
        int countCapital = 0;
        int countSmall = 0;
        // Password validation requirements
        if (password.trim().isEmpty()) {
            Toast.makeText(context, "Password is invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //8 chars minimum, 30 max
        if (password.length() < 8 || password.length() > 30)
        {
            Toast.makeText(context, "Password is invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Minimum 1 capital letter && Minimum 1 small letter
        for (int i = 0; i < password.length(); i++)
        {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
                countCapital++;
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
                countSmall++;
        }
        if (countCapital == 0 || countSmall == 0) {
            Toast.makeText(context, "Password is invalid!", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Minimum 1 wild card (@, #, $, ….)
        for (int j = 0; j < password.length(); j++) {
            if (!(password.charAt(j) >= 'A' && password.charAt(j) <= 'Z' ||
                    password.charAt(j) >= 'a' && password.charAt(j) <= 'z' ||
                    password.charAt(j) >= '0' && password.charAt(j) <= '9')) {
                Toast.makeText(context, "Password is invalid!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }
}
