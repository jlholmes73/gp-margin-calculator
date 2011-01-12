/*
    Copyright (c) 2009, John Holmes

    GpMarginCalculator.java is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 3 of the License, or
    (at your option) any later version.
*/


package nz.co.aztecinfo;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class view2 extends Activity {
    /** Called when the activity is first created. */
	public int taxRate=0;
	public static final String PREF_FILE_NAME = "PrefFile";
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
		
		//todo add code to check for saved settings here and pick up saved tax rate
        
		// if database exists use values
        SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_WORLD_WRITEABLE);
        int storedPreference = preferences.getInt("storedInt1", 0);
        int storedPreference2 = preferences.getInt("storedInt2", 0);
        int storedPreference3 = preferences.getInt("storedInt3", 0);
        int storedPreference4 = preferences.getInt("storedInt4", 0);
        boolean storedBoolPre = preferences.getBoolean("storedBoolPref", false);
        
        if(storedBoolPre){
        
        	final EditText entryNo1 = (EditText) findViewById(R.id.entry1);
        	entryNo1.append(""+storedPreference);
        	final EditText entryNo2 = (EditText) findViewById(R.id.entry2);
        	entryNo2.append(""+storedPreference2);
        	final EditText entryNo3 = (EditText) findViewById(R.id.entry3);
        	entryNo3.append(""+storedPreference3);
        	final EditText entryNo4 = (EditText) findViewById(R.id.entry4);
        	entryNo4.append(""+storedPreference4);
        }else{
        	final EditText entryNo1 = (EditText) findViewById(R.id.entry1);
        	entryNo1.append(""+0);
        	final EditText entryNo2 = (EditText) findViewById(R.id.entry2);
        	entryNo2.append(""+0);
        	final EditText entryNo3 = (EditText) findViewById(R.id.entry3);
        	entryNo3.append(""+0);
        	final EditText entryNo4 = (EditText) findViewById(R.id.entry4);
        	entryNo4.append(""+0);
        }
        
        Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	//SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_WORLD_WRITEABLE);
            	//SharedPreferences.Editor editor = preferences.edit();
            	//editor.putInt("storedInt", 15); // value to store
            	//editor.commit();
            	final EditText entryNo1 = (EditText) findViewById(R.id.entry1);
            	final EditText entryNo2 = (EditText) findViewById(R.id.entry2);
            	final EditText entryNo3 = (EditText) findViewById(R.id.entry3);
            	final EditText entryNo4 = (EditText) findViewById(R.id.entry4);
            	int int1=Integer.parseInt(entryNo1.getText().toString());
            	int int2=Integer.parseInt(entryNo2.getText().toString());
            	int int3=Integer.parseInt(entryNo3.getText().toString());
            	int int4=Integer.parseInt(entryNo4.getText().toString());
            	
            	
            	SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_WORLD_WRITEABLE);
            	SharedPreferences.Editor editor = preferences.edit();
            	editor.putInt("storedInt1", int1);
            	editor.putInt("storedInt2", int2);
            	editor.putInt("storedInt3", int3);
            	editor.putInt("storedInt4", int4); // value to store
            	editor.commit();
            	
            	if((int1+int2+int3+int4)==0){
            		SharedPreferences.Editor editor1 = preferences.edit();
            		editor1.putBoolean("storedBoolPref",false);
            		editor1.commit();
            	}else{
            		SharedPreferences.Editor editor1 = preferences.edit();
            		editor1.putBoolean("storedBoolPref",true);
            		editor1.commit();
            	}
            	
            	
            	
                Intent intent1 = new Intent();
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
        
        Button cancel = (Button) findViewById(R.id.Button01);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	//SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_WORLD_WRITEABLE);
            	//SharedPreferences.Editor editor = preferences.edit();
            	//editor.putInt("storedInt", 15); // value to store
            	//editor.commit();
            	
            	
                Intent intent1 = new Intent();
                setResult(1, intent1);
                finish();
            }
        });
        


        
        Button plus1 = (Button) findViewById(R.id.Button03);
        plus1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            		final EditText entryNo1 = (EditText) findViewById(R.id.entry1);
					if (entryNo1.getText().toString().equals("")){
						entryNo1.append("0");
					}
					else{
					final String value = entryNo1.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i<9){
							i++;
							entryNo1.getText().clear();
							entryNo1.append(""+i);
							
			            	
							
						}
						else{
							entryNo1.getText().clear();
							i=0;
							entryNo1.append(""+i);
							
						}
					
                }
            }
        });
        
		
		
		Button minus1 = (Button) findViewById(R.id.Button04);
        minus1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            		final EditText entryNo1 = (EditText) findViewById(R.id.entry1);
					if (entryNo1.getText().toString().equals("")){
						entryNo1.append("0");
					}
					else{
					final String value = entryNo1.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i==0){
							entryNo1.getText().clear();
							i=9;
							entryNo1.append(""+i);
						}
						else{
							i=i-1;
							entryNo1.getText().clear();
							entryNo1.append(""+i);
						}
					
                }
            }
        });
		
		Button plus2 = (Button) findViewById(R.id.Button05);
        plus2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            		final EditText entryNo2 = (EditText) findViewById(R.id.entry2);
					if (entryNo2.getText().toString().equals("")){
						entryNo2.append("0");
					}
					else{
					final String value = entryNo2.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i<9){
							i++;
							entryNo2.getText().clear();
							entryNo2.append(""+i);
							
							
						}
						else{
							entryNo2.getText().clear();
							i=0;
							entryNo2.append(""+i);
							//Toast.makeText(view2.this, "" + i , Toast.LENGTH_SHORT).show();
							
						}
					
                }
            }
        });
        
		
		
		Button minus2 = (Button) findViewById(R.id.Button06);
        minus2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            		final EditText entryNo2 = (EditText) findViewById(R.id.entry2);
					if (entryNo2.getText().toString().equals("")){
						entryNo2.append("0");
					}
					else{
					final String value = entryNo2.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i==0){
							entryNo2.getText().clear();
							i=9;
							entryNo2.append(""+i);
							
						}
						else{
							i=i-1;
							entryNo2.getText().clear();
							entryNo2.append(""+i);
							
							
						}
					
                }
            }
        });
        
        Button plus3 = (Button) findViewById(R.id.Button07);
        plus3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            		final EditText entryNo3 = (EditText) findViewById(R.id.entry3);
					if (entryNo3.getText().toString().equals("")){
						entryNo3.append("0");
					}
					else{
					final String value = entryNo3.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i<9){
							i++;
							entryNo3.getText().clear();
							entryNo3.append(""+i);
							
							
						}
						else{
							entryNo3.getText().clear();
							i=0;
							entryNo3.append(""+i);
							
							
						}
					
                }
            }
        });
        
		
		
		Button minus3 = (Button) findViewById(R.id.Button08);
        minus3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            		final EditText entryNo3 = (EditText) findViewById(R.id.entry3);
					if (entryNo3.getText().toString().equals("")){
						
						entryNo3.append("0");
						
					}
					else{
					final String value = entryNo3.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i==0){
							entryNo3.getText().clear();
							i=9;
							entryNo3.append(""+i);
							
							
						}
						else{
							i=i-1;
							entryNo3.getText().clear();
							entryNo3.append(""+i);
							
							
						}
					
                }
            }
        });

        Button plus4 = (Button) findViewById(R.id.Button09);
        plus4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            		final EditText entryNo4 = (EditText) findViewById(R.id.entry4);
					if (entryNo4.getText().toString().equals("")){
						entryNo4.append("0");
					}
					else{
					final String value = entryNo4.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i<9){
							i++;
							entryNo4.getText().clear();
							entryNo4.append(""+i);
							
							
						}
						else{
							entryNo4.getText().clear();
							i=0;
							entryNo4.append(""+i);
							
							
						}
					
                }
            }
        });

Button minus4 = (Button) findViewById(R.id.Button10);
        minus4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            		final EditText entryNo4 = (EditText) findViewById(R.id.entry4);
					if (entryNo4.getText().toString().equals("")){
						
						entryNo4.append("0");
						
					}
					else{
					final String value = entryNo4.getText().toString(); 
            		int i= Integer.parseInt(value);
						if (i==0){
							entryNo4.getText().clear();
							i=9;
							entryNo4.append(""+i);
							
							
						}
						else{
							i=i-1;
							entryNo4.getText().clear();
							entryNo4.append(""+i);
							
							
						}
					
                }
            }
        });
        
        
    }

}