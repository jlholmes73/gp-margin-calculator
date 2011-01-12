/*
    Copyright (c) 2009, John Holmes

    GpMarginCalculator.java is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 3 of the License, or
    (at your option) any later version.
*/


package nz.co.aztecinfo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;


public class GpMarginCalculator extends TabActivity {
    /** Called when the activity is first created. */

	public boolean taxDefBool,tax11,tax21=false;
	public static final String PREF_FILE_NAME = "PrefFile";
	private static final int PREF_EDIT=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_WORLD_WRITEABLE);
        int storedPreference = preferences.getInt("storedInt", 0);
        int storedPreference2 = preferences.getInt("storedInt2", 0);
        int storedPreference3 = preferences.getInt("storedInt3", 0);
        int storedPreference4 = preferences.getInt("storedInt4", 0);
        
        if ((storedPreference+storedPreference2+storedPreference3+storedPreference4)==0)
        {
        	
        	//Toast.makeText(GpMarginCalculator.this, "Went here", Toast.LENGTH_SHORT).show();
        	SharedPreferences.Editor editor = preferences.edit();
         	editor.putBoolean("storedBoolPref", false); // value to store
         	editor.commit();
        	

        } 
        boolean storedBoolPre = preferences.getBoolean("storedBoolPref", false);
        
        if (storedBoolPre){
			
			final CheckBox check1Tab1 = (CheckBox) findViewById(R.id.checkbox1Tab1);
	    	final CheckBox check2Tab1 = (CheckBox) findViewById(R.id.checkbox2Tab1);
	        final CheckBox check1Tab2 = (CheckBox) findViewById(R.id.checkbox1Tab2);
	        final CheckBox check1Tab3 = (CheckBox) findViewById(R.id.checkbox1Tab3);
	        
	        
	        check1Tab1.setChecked(true);
	        check2Tab1.setChecked(true);
	        check1Tab2.setChecked(true);
	        check1Tab3.setChecked(true);
	        
	      //tax edit texts are entry3Tab3Text,entry3Tab2Text,entry3Tab1Text
	        
        	
	        EditText taxTab1 = (EditText) findViewById(R.id.entry3Tab1);
        	EditText taxTab2 = (EditText) findViewById(R.id.entry3Tab2);
        	EditText taxTab3 = (EditText) findViewById(R.id.entry3Tab3);
        	taxTab1.setEnabled(true);
        	taxTab2.setEnabled(true);
        	taxTab3.setEnabled(true);
        	taxTab1.getText().clear();
        	taxTab2.getText().clear();
        	taxTab3.getText().clear();
        	
        	String temp="";
        	
        	temp = (((""+storedPreference)+storedPreference2)+"."+storedPreference3)+storedPreference4;
        	
        	taxTab1.append(temp);
        	taxTab2.append(temp);
        	taxTab3.append(temp);
        	
        	taxDefBool=true;
        	
        	
	        
		}
        
        

        TabHost mTabHost = getTabHost();
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_GPcalc1").setIndicator("Calc %GP").setContent(R.id.calcTab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_CPcalc2").setIndicator("Calc Cost").setContent(R.id.calcTab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_SPcalc3").setIndicator("Calc SP").setContent(R.id.calcTab3));
        
        mTabHost.setCurrentTab(0);
        
    	final CheckBox check1Tab1 = (CheckBox) findViewById(R.id.checkbox1Tab1);
    	final CheckBox check2Tab1 = (CheckBox) findViewById(R.id.checkbox2Tab1);
        final CheckBox check1Tab2 = (CheckBox) findViewById(R.id.checkbox1Tab2);
        final CheckBox check1Tab3 = (CheckBox) findViewById(R.id.checkbox1Tab3);
        
        
        
        
        
        
        // set tax entry boxes to disable by default
        final EditText entry3Tab3Text = (EditText) findViewById(R.id.entry3Tab3);
        if(!taxDefBool)
        entry3Tab3Text.setEnabled(taxDefBool);
        final EditText entry3Tab2Text = (EditText) findViewById(R.id.entry3Tab2);
        if(!taxDefBool)
        entry3Tab2Text.setEnabled(taxDefBool);
        final EditText entry3Tab1Text = (EditText) findViewById(R.id.entry3Tab1);
        if(!taxDefBool)
        entry3Tab1Text.setEnabled(taxDefBool);
        
        
        
        final Button calc1Button = (Button) findViewById(R.id.calcTab1Button);
        calc1Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	double CP1 = 1; 
            	double SP1 = 2;
            	double tax1 = 0;
            	EditText entry1Tab1Text = (EditText) findViewById(R.id.entry1Tab1);
            	EditText entry2Tab1Text = (EditText) findViewById(R.id.entry2Tab1);
            	
            	EditText output1Text = (EditText) findViewById(R.id.entry4Tab1);
            	EditText output2Text = (EditText) findViewById(R.id.entry5Tab1);
            	EditText output3Text = (EditText) findViewById(R.id.entry6Tab1);
            	EditText output4Text = (EditText) findViewById(R.id.entry8Tab1);
            
            	//DecimalFormat twoDP = new DecimalFormat("0.00");
            	NumberFormat twoDP = NumberFormat.getCurrencyInstance(Locale.getDefault());
            	
            	if(!(output1Text.getText().toString().equals(""))) {
            		output1Text.getText().clear();
            		output2Text.getText().clear();
            		output3Text.getText().clear();
            		output4Text.getText().clear();
            	}
            	if (entry1Tab1Text.getText().toString().equals("")){
            		CP1 = 1;
            	}
            	else{
            		CP1 = Double.valueOf(entry1Tab1Text.getText().toString());
            	}
            	
            	if (entry2Tab1Text.getText().toString().equals("")){
            		SP1 = 2;
            	}
            	else{
            		SP1 = Double.valueOf(entry2Tab1Text.getText().toString());
            	}
            	
            	if (entry3Tab1Text.getText().toString().equals("")){
            		tax1 = 0;
            	}
            	else{
            		tax1 = Double.valueOf(entry3Tab1Text.getText().toString());
            	}
            	// GP% = (SP-CP) × 100/SP
            	
            	if (CP1>SP1){
            		Toast.makeText(GpMarginCalculator.this, "Your Cost price is higher than your selling price! I think you should start again  :-)", Toast.LENGTH_LONG).show();	
            	}
            	else{
            	
            		

            	if(check1Tab1.isChecked()||check2Tab1.isChecked()){
            		if(tax1==0){
            			// Tax checked but zero rate!
            			Toast.makeText(GpMarginCalculator.this, "You have checked that one of the prices includes Tax/VAT/GST but not entered a tax rate.", Toast.LENGTH_LONG).show();	
                    	
            		}
            		else{
            			output4Text.append(twoDP.format(round((SP1-(SP1/(100+tax1)*100)),2)));
            			if(check1Tab1.isChecked())CP1=CP1/(100+tax1)*100;
            			//Toast.makeText(GpMarginCalculator.this, Double.toString(CP1), Toast.LENGTH_SHORT).show();
            			if(check2Tab1.isChecked())SP1=SP1/(100+tax1)*100;	
            			output1Text.append(Double.toString(round((SP1-CP1)* 100/SP1,2))+"%");
            			//MU%=((SP-CP)/CP)*100
            			output2Text.append(Double.toString(round(((SP1-CP1)/CP1)*100,2))+"%");
            			output3Text.append(twoDP.format(round((SP1-CP1),2)));
            			
            			
            		}
          
            	}
            	else{
            		//still output a result if there is no checks-  i.e. no tax
            		output1Text.append(Double.toString(round((SP1-CP1)* 100/SP1,2))+"%");
        			output2Text.append(Double.toString(round(((SP1-CP1)/CP1)*100,2))+"%");
        			output3Text.append(twoDP.format(round((SP1-CP1),2)));
        			
            	}
            	}
            }
        });
        
		final Button calc2Button = (Button) findViewById(R.id.calcTab2Button);
        calc2Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	double SP2 = 1; 
            	double preTaxSP2=0;
            	double GP2 = 0;
            	double MP2 = 0;
            	double tax2 = 0;
            	EditText entry1Tab2Text = (EditText) findViewById(R.id.entry1Tab2);
            	EditText entry2Tab2Text = (EditText) findViewById(R.id.entry2Tab2);
            	EditText entry2aTab2Text = (EditText) findViewById(R.id.entry2aTab2);
            	
            	EditText output2Text = (EditText) findViewById(R.id.entry4Tab2);
            	EditText output2aText = (EditText) findViewById(R.id.entry5Tab2);
            	EditText output2bText = (EditText) findViewById(R.id.entry6Tab2);
            	//DecimalFormat twoDP = new DecimalFormat("0.00");
            	NumberFormat twoDP = NumberFormat.getCurrencyInstance(Locale.getDefault());
            	           	
            	
            	if(!(output2Text.getText().toString().equals(""))) {
            		output2Text.getText().clear();
            		output2aText.getText().clear();
            		output2bText.getText().clear();
            	}
            	if (entry1Tab2Text.getText().toString().equals("")){
            		SP2 = 2;
            	}
            	else{
            		SP2 = Double.valueOf(entry1Tab2Text.getText().toString());
            	}
            	
            	if (entry2Tab2Text.getText().toString().equals("")){
            		GP2 = 0;
            	}
            	else{
            		GP2 = Double.valueOf(entry2Tab2Text.getText().toString());
            	}
            	
            	if (entry2aTab2Text.getText().toString().equals("")){
            		MP2 = 0;
            	}
            	else{
            		MP2 = Double.valueOf(entry2aTab2Text.getText().toString());
            	}
            	
            	if (entry3Tab2Text.getText().toString().equals("")){
            		tax2 = 0;
            	}
            	else{
            		tax2 = Double.valueOf(entry3Tab2Text.getText().toString());
            	}
            	// cp=sp-(sp*gp/100)  CP=SPx100/(100+%M)
            	
            	
            	//first if tax is check and 0 rate
            	
            	if(check1Tab2.isChecked())
            	//Tax is checked
            	{if(tax2==0){
            		Toast.makeText(GpMarginCalculator.this, "Hmmm, you checked that SP includes Tax but have not entered a rate to deduct.", Toast.LENGTH_LONG).show();
            	}
            	else{preTaxSP2=SP2;
            		// tax side 
            		SP2=SP2/(100+tax2)*100;
                	//Now time to check no or double entry
            	if((GP2==0&&MP2==0)||(GP2>0&&MP2>0)){
            		Toast.makeText(GpMarginCalculator.this, "Please enter a desired %GP OR a desired %M.  One OR the other.", Toast.LENGTH_LONG).show();
            		}
            	else{
            		//Wow it is really time to do some maths now!       		
            		if(GP2>0){
            			output2Text.append(twoDP.format(round((SP2-(SP2*GP2/100)),2)));
            			output2bText.append(twoDP.format(round(preTaxSP2-SP2,2)));
            			output2aText.append(twoDP.format(round(round(preTaxSP2-(preTaxSP2-SP2),2)-(SP2-(SP2*GP2/100)),2)));
            			
            		}
            		else{
            			output2Text.append(twoDP.format(round((SP2*100/(100+MP2)),2)));
            			output2bText.append(twoDP.format(round(preTaxSP2-SP2,2)));
            			output2aText.append(twoDP.format(round(round(preTaxSP2-(preTaxSP2-SP2),2)-(SP2*100/(100+MP2)),2)));
            		}
            		
            	}
            		
            	}
            	
            	
            	}
            	else{
            	//Tax is NOT applicable	
            		//Now time to check no or double entry
                	if((GP2==0&&MP2==0)||(GP2>0&&MP2>0)){
                		Toast.makeText(GpMarginCalculator.this, "Please enter a desired %GP OR a desired %M.  One OR the other", Toast.LENGTH_LONG).show();
                		}
                	else{
                		//Wow it is really time to do some maths now!       		
                		if(GP2>0){
                			output2Text.append(twoDP.format(round((SP2-(SP2*GP2/100)),2)));
                			output2aText.append(twoDP.format(round((SP2*GP2/100),2)));
                		}
                		else{
                			output2Text.append(twoDP.format(round((SP2*100/(100+MP2)),2)));
                			output2aText.append(twoDP.format(round(SP2-(SP2*100/(100+MP2)),2)));
                		}
                		
                	}	
            		
            	}

            	
            }
        });

		final Button calc3Button = (Button) findViewById(R.id.calcTab3Button);
        calc3Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	double CP3 = 1; 
            	double GP3 = 0;
            	double MP3 = 0;
            	double tax3 = 0;
            	EditText entry1Tab3Text = (EditText) findViewById(R.id.entry1Tab3);
            	EditText entry2Tab3Text = (EditText) findViewById(R.id.entry2Tab3);
            	EditText entry2aTab3Text = (EditText) findViewById(R.id.entry2aTab3);
            	EditText output3Text = (EditText) findViewById(R.id.entry4Tab3);
            	EditText output3aText = (EditText) findViewById(R.id.entry5Tab3);
            	EditText output3bText = (EditText) findViewById(R.id.entry6Tab3);
            	//DecimalFormat twoDP = new DecimalFormat("0.00");
            	NumberFormat twoDP = NumberFormat.getCurrencyInstance(Locale.getDefault());
            	
            	
            	
            	if(!(output3Text.getText().toString().equals(""))) {
            		output3Text.getText().clear();
            		output3aText.getText().clear();
            		output3bText.getText().clear();
            	}
            	
            	
            	if (entry1Tab3Text.getText().toString().equals("")){
            		CP3 = 1;
            	}
            	else{
            		CP3 = Double.valueOf(entry1Tab3Text.getText().toString());
            	}
            	
            	if (entry2Tab3Text.getText().toString().equals("")){
            		GP3 = 0;
            	}
            	else{
            		GP3 = Double.valueOf(entry2Tab3Text.getText().toString());
            	}
            	
            	if (entry2aTab3Text.getText().toString().equals("")){
            		MP3 = 0;
            	}
            	else{
            		MP3 = Double.valueOf(entry2aTab3Text.getText().toString());
            	}
            	
            	if (entry3Tab3Text.getText().toString().equals("")){
            		tax3 = 0;
            	}
            	else{
            		tax3 = Double.valueOf(entry3Tab3Text.getText().toString());
            	}
            	// SP=CP/(1-GP%/100)     SP=CPx(M%/100+1)
            	
            	if((GP3==0&&MP3==0)||(GP3>0&&MP3>0)){
            		Toast.makeText(GpMarginCalculator.this, "Please enter a desired %GP OR a desired %M.  One OR the other.", Toast.LENGTH_LONG).show();
            		}
            	else{
            		// cool we have a mp or gp now check tax
            		if(check1Tab3.isChecked()){
            			//we have tax is tax3 greater than 0?
            			if(!(tax3>0)){
            				//complain like anything
            				Toast.makeText(GpMarginCalculator.this, "Hmmm, you checked that SP includes Tax but have not entered a rate to add.", Toast.LENGTH_LONG).show();
	
            			}
            			else{
            				
            				//do the one of the calcs and add tax
            				//Toast.makeText(GpMarginCalculator.this, "doing one of the calcs with tax", Toast.LENGTH_LONG).show();
                    		if(GP3>0){
                    			output3Text.append(twoDP.format((round((CP3/(1-GP3/100))*(1+tax3/100),2))));//SP
                    			output3aText.append(twoDP.format(round((CP3/(1-GP3/100))*tax3/100,2)));//tax
                    			//net profit = sp-tax
                    			output3bText.append(twoDP.format(round(round((CP3/(1-GP3/100))*(1+tax3/100),2)-round((CP3/(1-GP3/100))*tax3/100,2)-CP3,2)));
                    			
                    		}else
                    		{
                    			output3Text.append(twoDP.format(round((CP3*(MP3/100+1))*(1+tax3/100),2)));//sp
                    			output3aText.append(twoDP.format(round((CP3*(MP3/100+1))*tax3/100,2)));//tax
                    			//net profit = sp-tax
                    			output3bText.append(twoDP.format(round(round((CP3*(MP3/100+1))*(1+tax3/100),2)-round((CP3*(MP3/100+1))*tax3/100,2)-CP3,2)));
                    		}
            			}

            		}else{
            			//just do the calcs
            			
            			//Toast.makeText(GpMarginCalculator.this, "doing one of the calcs without tax", Toast.LENGTH_LONG).show();
                		if(GP3>0){
                			output3Text.append(twoDP.format(round((CP3/(1-GP3/100)),2)));
                			output3bText.append(twoDP.format(round((CP3/(1-GP3/100))-CP3,2)));
                		}else
                		{
                			output3Text.append(twoDP.format(round((CP3*(MP3/100+1)),2)));
                			output3bText.append(twoDP.format(round((CP3*(MP3/100+1))-CP3,2)));
                		}
	
            		}
            	}
            	//output3Text.append(Double.toString(round((CP3/(1-GP3/100))*(1+tax3/100),2)));     	
            }
        });
        
        check1Tab1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (check1Tab1.isChecked()) {
                    //Toast.makeText(GpMarginCalculator.this, "Selected", Toast.LENGTH_SHORT).show();

					entry3Tab1Text.setEnabled(true);

                } else {
                    //Toast.makeText(GpMarginCalculator.this, "Not selected", Toast.LENGTH_SHORT).show();

                	entry3Tab2Text.setEnabled(false);
                  
                }
            }
        });
        
        check1Tab1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (check1Tab1.isChecked()) {
                	if	(!check2Tab1.isChecked())
                		//Toast.makeText(GpMarginCalculator.this, "Selected", Toast.LENGTH_SHORT).show();
                		entry3Tab1Text.setEnabled(true);

                } else {
                    //Toast.makeText(GpMarginCalculator.this, "Not selected", Toast.LENGTH_SHORT).show();
                	if	(!check2Tab1.isChecked())
                		entry3Tab1Text.setEnabled(false);
                  
                }
            }
        });
        
        check2Tab1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	if	(check2Tab1.isChecked()){
                	if (!check1Tab1.isChecked()) 
                    //Toast.makeText(GpMarginCalculator.this, "Selected", Toast.LENGTH_SHORT).show();

					entry3Tab1Text.setEnabled(true);

                } else {
                    //Toast.makeText(GpMarginCalculator.this, "Not selected", Toast.LENGTH_SHORT).show();
                	if	(!check1Tab1.isChecked())
                		entry3Tab1Text.setEnabled(false);
                  
                }
            }
        });
        
        check1Tab2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	
                if (check1Tab2.isChecked()) {
                    //Toast.makeText(GpMarginCalculator.this, "Selected", Toast.LENGTH_SHORT).show();

					entry3Tab2Text.setEnabled(true);

                } else {
                    //Toast.makeText(GpMarginCalculator.this, "Not selected", Toast.LENGTH_SHORT).show();

                	entry3Tab2Text.setEnabled(false);
                  
                }
            }
        });
        
        check1Tab3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (check1Tab3.isChecked()) {
                    //Toast.makeText(GpMarginCalculator.this, "Selected", Toast.LENGTH_SHORT).show();

					entry3Tab3Text.setEnabled(true);

                } else {
                    //Toast.makeText(GpMarginCalculator.this, "Not selected", Toast.LENGTH_SHORT).show();

                	entry3Tab3Text.setEnabled(false);
                  
                }
            }
        });
        
       
    }
    
    public boolean onCreateOptionsMenu(Menu menu) 
    {
      super.onCreateOptionsMenu(menu);
      
      MenuItem items = menu.add("About");
      items = menu.add("Settings");
      items = menu.add("Email Calculation");
      

   // Return true so that the menu gets displayed.
      return true;
    }


    /**
     * {@inheritDoc}
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {

      if (item.hasSubMenu() == false)
      {
        // For this demo, lets just give back what
          //(" You selected " +  item.getTitle())
         if (item.getTitle()=="About") {
         //Toast.makeText(HelloFormStuff.this, (" You selected " +  item.getTitle()), Toast.LENGTH_SHORT).show();
          AlertDialog.Builder dialogBuilder = new 
          AlertDialog.Builder(this);
    
          //dialogBuilder.setMessage(" You selected " +   item.getTitle());
          dialogBuilder.setMessage("%GP and Margin Calculator V3.1.1\n\n(C) John Holmes 2009\n - V3.0.4 added support for tax to two decimal places" );
          dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {

              /* User clicked OK so do some stuff */
          	}
          });
          dialogBuilder.setCancelable(true);
          dialogBuilder.create().show();

      }
      
         if (item.getTitle()=="Email Calculation"){
        	 
        	 TabHost mTabHost = getTabHost();
        	 
        	 
        	 
        	 switch (mTabHost.getCurrentTab()) {

        	    case 0:
        	    	//Toast.makeText(GpMarginCalculator.this, "Tab: 1", Toast.LENGTH_SHORT).show();
        	    	emailTab1();	
        	        break;
        	    case 1:
        	    	//Toast.makeText(GpMarginCalculator.this, "Tab: 2", Toast.LENGTH_SHORT).show();
        	    	emailTab2();
        	        break;
        	    case 2:
        	    	//Toast.makeText(GpMarginCalculator.this, "Tab: 3", Toast.LENGTH_SHORT).show();
        	    	emailTab3();
        	        break;
        	   
        	}

        	 
             
            }
         
         if (item.getTitle()=="Settings"){
          	  //Toast.makeText(Twinviews.this, "Meanwhile back in NZ there is a chap working feaveroushly to make this play with dafault tax settings... More features coming soon  :-)", Toast.LENGTH_SHORT).show();
          	  Intent launchPreferencesIntent = new Intent().setClass(this, view2.class);
                
                // Make it a subactivity so we know when it returns
                startActivityForResult(launchPreferencesIntent, PREF_EDIT);
                
               }
         
      }
      
      // Consume the selection event.
      
      return true;
    }


    public static double round(double input, int roundFactor) {
    	double p1 = (double) Math.pow(10, roundFactor); // Results in pl = 100 for roundFactor=2 i.e. 10^2
    	input = input * p1; // Results in input*100 for roundFactor=2
    	double temp = Math.round(input); // Results in round to zero decimal places
    	return temp / p1;  // returns answer / 100 for roundFactor=2
    	}
    	
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode == Activity.RESULT_OK && requestCode == 0) {
    	  SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_WORLD_WRITEABLE);
          int storedPreference = preferences.getInt("storedInt1", 0);
          int storedPreference2 = preferences.getInt("storedInt2", 0);
          int storedPreference3 = preferences.getInt("storedInt3", 0);
          int storedPreference4 = preferences.getInt("storedInt4", 0);
          if ((storedPreference+storedPreference2+storedPreference3+storedPreference4)==0)
          {
          	
          	//Toast.makeText(GpMarginCalculator.this, "not fooling me", Toast.LENGTH_SHORT).show();
          	SharedPreferences.Editor editor = preferences.edit();
           	editor.putBoolean("storedBoolPref", false); // value to store
           	editor.commit();
          	

          } 
    		//Toast.makeText(GpMarginCalculator.this, "You want I should implement some settings?", Toast.LENGTH_SHORT).show();
        
    		boolean storedBoolPre = preferences.getBoolean("storedBoolPref", false);
    		if (storedBoolPre){
    			
    			final CheckBox check1Tab1 = (CheckBox) findViewById(R.id.checkbox1Tab1);
    	    	final CheckBox check2Tab1 = (CheckBox) findViewById(R.id.checkbox2Tab1);
    	        final CheckBox check1Tab2 = (CheckBox) findViewById(R.id.checkbox1Tab2);
    	        final CheckBox check1Tab3 = (CheckBox) findViewById(R.id.checkbox1Tab3);
    	        
    	        
    	        check1Tab1.setChecked(true);
    	        check2Tab1.setChecked(true);
    	        check1Tab2.setChecked(true);
    	        check1Tab3.setChecked(true);
    	        
    	      //tax edit texts are entry3Tab3Text,entry3Tab2Text,entry3Tab1Text
    	        
            	
    	        EditText taxTab1 = (EditText) findViewById(R.id.entry3Tab1);
            	EditText taxTab2 = (EditText) findViewById(R.id.entry3Tab2);
            	EditText taxTab3 = (EditText) findViewById(R.id.entry3Tab3);
            	taxTab1.setEnabled(true);
            	taxTab2.setEnabled(true);
            	taxTab3.setEnabled(true);
            	taxTab1.getText().clear();
            	taxTab2.getText().clear();
            	taxTab3.getText().clear();
            	
            	String temp="";
            	
            	temp = (((""+storedPreference)+storedPreference2)+"."+storedPreference3)+storedPreference4;
            	
            	taxTab1.append(temp);
            	taxTab2.append(temp);
            	taxTab3.append(temp);
            	
            	//clear the outputs
            	EditText output1Text = (EditText) findViewById(R.id.entry4Tab1);
            	EditText output2Text1 = (EditText) findViewById(R.id.entry5Tab1);
            	EditText output3Text1 = (EditText) findViewById(R.id.entry6Tab1);
            	EditText output4Text1 = (EditText) findViewById(R.id.entry8Tab1);
            	EditText output2Text = (EditText) findViewById(R.id.entry4Tab2);
            	EditText output2aText = (EditText) findViewById(R.id.entry5Tab2);
            	EditText output2bText = (EditText) findViewById(R.id.entry6Tab2);
            	EditText output3Text = (EditText) findViewById(R.id.entry4Tab3);
            	EditText output3aText = (EditText) findViewById(R.id.entry5Tab3);
            	EditText output3bText = (EditText) findViewById(R.id.entry6Tab3);
            	output1Text.getText().clear();
            	output2Text1.getText().clear();
            	output3Text1.getText().clear();
            	output4Text1.getText().clear();
            	output2Text.getText().clear();
            	output2aText.getText().clear();
        		output2bText.getText().clear();
            	output3Text.getText().clear();
            	output3aText.getText().clear();
        		output3bText.getText().clear();
            	
    		}
    		else {
    			final CheckBox check1Tab1 = (CheckBox) findViewById(R.id.checkbox1Tab1);
    	    	final CheckBox check2Tab1 = (CheckBox) findViewById(R.id.checkbox2Tab1);
    	        final CheckBox check1Tab2 = (CheckBox) findViewById(R.id.checkbox1Tab2);
    	        final CheckBox check1Tab3 = (CheckBox) findViewById(R.id.checkbox1Tab3);
    	        
    	        
    	        check1Tab1.setChecked(false);
    	        check2Tab1.setChecked(false);
    	        check1Tab2.setChecked(false);
    	        check1Tab3.setChecked(false);
    			
    	        //tax edit texts are entry3Tab3Text,entry3Tab2Text,entry3Tab1Text
    	        
            	
    	        EditText taxTab1 = (EditText) findViewById(R.id.entry3Tab1);
            	EditText taxTab2 = (EditText) findViewById(R.id.entry3Tab2);
            	EditText taxTab3 = (EditText) findViewById(R.id.entry3Tab3);
            	
            	taxTab1.getText().clear();
            	taxTab2.getText().clear();
            	taxTab3.getText().clear();
            	taxTab1.setEnabled(false);
            	taxTab2.setEnabled(false);
            	taxTab3.setEnabled(false);
            	
            	
            	
            	//clear the outputs
            	EditText output1Text = (EditText) findViewById(R.id.entry4Tab1);
            	EditText output2Text1 = (EditText) findViewById(R.id.entry5Tab1);
            	EditText output3Text1 = (EditText) findViewById(R.id.entry6Tab1);
            	EditText output4Text1 = (EditText) findViewById(R.id.entry8Tab1);
            	EditText output2Text = (EditText) findViewById(R.id.entry4Tab2);
            	EditText output2aText = (EditText) findViewById(R.id.entry5Tab2);
            	EditText output2bText = (EditText) findViewById(R.id.entry6Tab2);
            	EditText output3Text = (EditText) findViewById(R.id.entry4Tab3);
            	EditText output3aText = (EditText) findViewById(R.id.entry5Tab3);
            	EditText output3bText = (EditText) findViewById(R.id.entry6Tab3);
            	output1Text.getText().clear();
            	output2Text1.getText().clear();
            	output3Text1.getText().clear();
            	output4Text1.getText().clear();
            	output2Text.getText().clear();
            	output2aText.getText().clear();
        		output2bText.getText().clear();
            	output3Text.getText().clear();
            	output3aText.getText().clear();
        		output3bText.getText().clear();
            	
    		}
        
      }
      if (resultCode == 1 && requestCode == 0) {
        // do nothing  
  		//Toast.makeText(GpMarginCalculator.this, "You you ...", Toast.LENGTH_SHORT).show();
      	 
      
    }
      
      
      
    }
    
    private void emailTab1(){
    	 // First check calc has been done
    	EditText output1Text = (EditText) findViewById(R.id.entry4Tab1);
    	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
    	if((output1Text.getText().toString().equals(""))){
    		Toast.makeText(GpMarginCalculator.this, "Calculation not completed", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    	Intent i = new Intent(Intent.ACTION_SEND);  
    	//i.setType("text/plain"); //use this line for testing in the emulator  
    	i.setType("message/rfc822") ; // use for live device
    	i.putExtra(Intent.EXTRA_EMAIL, new String[]{""});  
    	  
    	
    	String temp = "";
    	String temp2 = "";
    	String temp3 = "";
    	String temp4 = "";
    	
    	
    	
    	EditText output2Text = (EditText) findViewById(R.id.entry5Tab1);
    	EditText output3Text = (EditText) findViewById(R.id.entry6Tab1);
    	EditText output4Text = (EditText) findViewById(R.id.entry8Tab1);
    	
    	EditText entry1Tab1Text = (EditText) findViewById(R.id.entry1Tab1);
    	EditText entry2Tab1Text = (EditText) findViewById(R.id.entry2Tab1);
    	
    	if((entry1Tab1Text.getText().toString().equals(""))){
    		temp2 = currencyFormatter.format(1.00);  
    	}else{
    		temp2= currencyFormatter.format(Double.valueOf(entry1Tab1Text.getText().toString()));
    		
    	}
    	
    	if((entry2Tab1Text.getText().toString().equals(""))){
    		temp3 =currencyFormatter.format(2.00);  
    	}else{
    		temp3= currencyFormatter.format(Double.valueOf(entry2Tab1Text.getText().toString()));
    		
    	}
    	
    	
    	temp=("This message has been automatically generated by the %Gross Profit and Margin Calculator on my" +
    			" Google Android Device.\n\nBased on a Cost Price of " + temp2 +
    			" and Selling Price of "+ temp3 +" the %GP will be: " + output1Text.getText().toString() +
    			" (%GP = (SP-CP) × 100/SP) and the %Margin will be " + output2Text.getText().toString() +
    			" (%M=((SP-CP)/CP)x100)");
    	i.putExtra(Intent.EXTRA_SUBJECT,"%GP and %Margin Calculation");
    	i.putExtra(Intent.EXTRA_TEXT,temp);  
    	startActivity(Intent.createChooser(i, "Select email application."));
    	}
    }
    
    private void emailTab2(){
    	 // First check calc has been done
    	EditText output2Text = (EditText) findViewById(R.id.entry4Tab2);
    	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
    	if((output2Text.getText().toString().equals(""))){
    		Toast.makeText(GpMarginCalculator.this, "Calculation not completed", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    	Intent i = new Intent(Intent.ACTION_SEND);  
    	//i.setType("text/plain"); //use this line for testing in the emulator  
    	i.setType("message/rfc822") ; // use for live device
    	i.putExtra(Intent.EXTRA_EMAIL, new String[]{""});  
    	 
    	
    	String temp = "";
    	String temp2 = "";
    	String temp3 = "";
    	String subject = "";
    	
    	EditText entry1Tab2Text = (EditText) findViewById(R.id.entry1Tab2);
    	EditText entry2Tab2Text = (EditText) findViewById(R.id.entry2Tab2);
    	EditText entry2aTab2Text = (EditText) findViewById(R.id.entry2aTab2);
    	
    	
    	if((entry1Tab2Text.getText().toString().equals(""))){
    		temp2 =currencyFormatter.format(2.00);   
    	}else{
    		temp2= currencyFormatter.format(Double.valueOf(entry1Tab2Text.getText().toString()));
    	}
    	
    	if((entry2aTab2Text.getText().toString().equals(""))){
    		temp3 ="GP of " + (entry2Tab2Text.getText().toString()); 
    		subject="%GP Cost Price Calculation";
    	}else{
    		temp3= "Margin of " + (entry2aTab2Text.getText().toString());
    		subject="%Margin Cost Price Calculation";
    	}
    	
    	
    	temp=("This message has been automatically generated by the %Gross Profit and Margin Calculator on my" +
    			" Google Android Device.\n\nBased on a Selling Price of " + temp2 +
    			" and a %"+ temp3 +" the Cost Price will need to be: " + output2Text.getText().toString() +
    			" ");
    	i.putExtra(Intent.EXTRA_SUBJECT,subject);  
    	i.putExtra(Intent.EXTRA_TEXT,temp);  
    	startActivity(Intent.createChooser(i, "Select email application."));
    	}
    	
    }

    private void emailTab3(){
    	 // First check calc has been done
    	EditText output3Text = (EditText) findViewById(R.id.entry4Tab3);
    	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
    	if((output3Text.getText().toString().equals(""))){
    		Toast.makeText(GpMarginCalculator.this, "Calculation not completed", Toast.LENGTH_LONG).show();
    	}
    	else
    	{
    	Intent i = new Intent(Intent.ACTION_SEND);  
    	//i.setType("text/plain"); //use this line for testing in the emulator  
    	i.setType("message/rfc822") ; // use for live device
    	i.putExtra(Intent.EXTRA_EMAIL, new String[]{""});  
    	
    	
    	String temp = "";
    	String temp2 = "";
    	String temp3 = "";
    	String subject = "";
    	
    	EditText entry1Tab3Text = (EditText) findViewById(R.id.entry1Tab3);
    	EditText entry2Tab3Text = (EditText) findViewById(R.id.entry2Tab3);
    	EditText entry2aTab3Text = (EditText) findViewById(R.id.entry2aTab3);
    	
    	
    	if((entry1Tab3Text.getText().toString().equals(""))){
    		temp2 =currencyFormatter.format(Double.valueOf(1.00));  
    	}else{
    		temp2= currencyFormatter.format(Double.valueOf(entry1Tab3Text.getText().toString()));
    	}
    	
    	if((entry2aTab3Text.getText().toString().equals(""))){
    		temp3 = ("GP of " + (entry2Tab3Text.getText().toString()));
    		subject="%GP recommended Selling Price Calculation";
    	}else{
    		temp3= ("Margin of " + (entry2aTab3Text.getText().toString()));
    		subject="%Margin recommended Selling Price Calculation";
    	}
    	
    	
    	temp=("This message has been automatically generated by the %Gross Profit and Margin Calculator on my" +
    			" Google Android Device.\n\nBased on a Cost Price of " + temp2 +
    			" and a required %"+ temp3 +" the recomended selling price is: " + output3Text.getText().toString() +
    			" ");
    	i.putExtra(Intent.EXTRA_SUBJECT,subject);  
    	i.putExtra(Intent.EXTRA_TEXT,temp);  
    	startActivity(Intent.createChooser(i, "Select email application."));
    	}
	
}
    	
}