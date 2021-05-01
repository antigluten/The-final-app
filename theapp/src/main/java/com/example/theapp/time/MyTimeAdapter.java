package com.example.theapp.time;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.Period;

public class MyTimeAdapter {
    public String getPlus() {
        return "+";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getAddition(LocalDate firstDate, LocalDate secondDate) {
        LocalDate result = firstDate.plusYears(secondDate.getYear()).
                plusMonths(secondDate.getMonthValue()).plusDays(secondDate.getDayOfMonth());
        return result.toString();
    }

    public String getMinus() {
        return "-";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getSubtraction(LocalDate firstDate, LocalDate secondDate) {
        Period period = Period.between(secondDate, firstDate);
        return period.getYears() + "." + period.getMonths() + "." + period.getDays();
    }
}
