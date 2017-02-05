package semestre.a1.a2016.estg.ei.ruben.calendar.model;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by Ruben on 19/12/2016.
 */

public class Date {
    private Calendar calendar;

    public Date(){ calendar=Calendar.getInstance();}

    public Date(int year,int month,int day){
        this();
        calendar.set(year,month-1,day);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public Date(int year, int month){ this(year ,month, 1);}

    public int getYear(){ return calendar.get(Calendar.YEAR); }

    public int getMonth(){ return calendar.get(Calendar.MONTH)+1; }

    public int getDay(){ return calendar.get(Calendar.DAY_OF_MONTH); }

    public String withZeros(int  number, int tam){
        String result= Integer.toString(number);
        while(result.length()<tam)
            result="0"+result;
        return result;
    }

    @Override
    public String toString(){
        return withZeros(getYear(),4)+"-"+withZeros(getMonth(),2)+"-"+withZeros(getDay(), 2);
    }

    public static Date parseDate(String s){
        if(s==null)
            return null;
        String separator="";
        if(s.contains("-"))
            separator="-";
        else
            separator="/";

        String []components=s.split(separator);
        int day=1;
        int month=0;
        int year=0;

        if(components.length<2 || components.length>3){
            throw new RuntimeException("Invalid date format ("+s+"). \n"+
                    "Valid formats: yyyy/mm, yyyy/mm/dd, yyyy-mm, yyyy-mm-dd)");
        }

        year = Integer.parseInt(components[0]);
        month = Integer.parseInt(components[1]);
        if(components.length==3){
            day=Integer.parseInt(components[2]);
        }
        return new Date(year, month, day);

    }

}
