package net.bogus.android.numberverification;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumberVerificationActivity extends Activity {

    public static Button bt_Verify;
    public static EditText et_Number;
    
	/** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        et_Number = (EditText) findViewById(R.id.editText1);
        bt_Verify = (Button) findViewById(R.id.button1);
        
		bt_Verify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = et_Number.getText().toString();

				if (str == null || str.length() < 1) {
					Toast.makeText(NumberVerificationActivity.this,"You need to enter a number.",Toast.LENGTH_LONG).show();
					return;
				}

				str = str.replaceAll("[\\.\\,\\-\\Ê\\(\\)\\/]", ""); // strip .,-() /
				String number = VerifyNumber(str);
				
				if(number.startsWith("+")) {
					new AlertDialog.Builder(NumberVerificationActivity.this)
					.setTitle("Number OK")
					.setMessage(number)
					.setPositiveButton(android.R.string.ok, null)
					.show();
				} else if(number.startsWith("NOC")) {
					new AlertDialog.Builder(NumberVerificationActivity.this)
					.setTitle("Missing country code..")
					.setMessage("The number must start with +CCNNNN...")
					.setPositiveButton(android.R.string.ok, null)
					.show();
				} else if(number.startsWith("NTS")) {
					new AlertDialog.Builder(NumberVerificationActivity.this)
					.setTitle("Number too short")
					.setMessage("Then number must be longer than 5 digits.")
					.setPositiveButton(android.R.string.ok, null)
					.show();
				} else if(number.startsWith("NTL")) {
					new AlertDialog.Builder(NumberVerificationActivity.this)
					.setTitle("Number too long")
					.setMessage("Then number must be shorter than 13 digits.")
					.setPositiveButton(android.R.string.ok, null)
					.show();
				} else {
					new AlertDialog.Builder(NumberVerificationActivity.this)
					.setTitle("Number format unknown")
					.setMessage("Oops, I don't recognise this number format. Add to the issue list and give some examples!")
					.setPositiveButton(android.R.string.ok, null)
					.show();
				}	
			}

		});

    }
    
    // with good help from google and wikipedia; there may be errors
    
    private String VerifyNumber(String str) {
    	if(!str.startsWith("+")) {
    		return("NOC");								// no country code
    	}
    	    	
    	if(str.length() < 6) {							// number is too short (this is probably too short for cell phones, but ..)
    		return("NTS");
    	}
    	
    	if(str.length() > 13) {							// number is too long (12 digits appears to be max)
    		return("NTL");
    	}
    	
    	if(str.matches("^\\+1[0-9]{10}$")) { 			// NAMP (north america)
    		return(str);
    	}
    	if(str.matches("^\\+316[0-9]{8}$")) { 			// the netherlands (cellphones)
    		return(str);
    	}
    	if(str.matches("^\\+33[0-9]{9}$")) { 			// france
    		return(str);
    	}
    	if(str.matches("^\\+354[0-9]{7}$")) { 			// iceland
    		return(str);
    	}
    	if(str.matches("^\\+378[0-9]{9}$")) { 			// san marino
    		return(str);
    	}    	
    	if(str.matches("^\\+39[0-9]{9,11}$")) { 		// italy
    		return(str);
    	}
    	if(str.matches("^\\+4[57]{1}[0-9]{8}$")) { 		// denmark, norway
    		return(str);
    	}
    	if(str.matches("^\\+46[0-9]{8,10}$")) { 		// sweden
    		return(str);
    	}
    	if(str.matches("^\\+49[0-9]{10}$")) { 			// germany (din 5008; international format)
    		return(str);
    	}
    	if(str.matches("^\\+506[0-9]{8}$")) { 			// costa rica
    		return(str);
    	}
    	if(str.matches("^\\+52[0-9]{8,10}$")) { 		// mexico 
    		return(str);
    	}
    	if(str.matches("^\\+54[0-9]{11}$")) { 			// argentina
    		return(str);
    	}    
    	if(str.matches("^\\+601[0-9]{8,9}$")) { 		// malaysia (mobile)
    		return(str);
    	}    
    	if(str.matches("^\\+61[0-9]{9}$")) { 			// australia
    		return(str);
    	}
    	if(str.matches("^\\+65[0-9]{8}$")) { 			// singapore
    		return(str);
    	}
    	if(str.matches("^\\+81[0-9]{9}$")) { 			// japan
    		return(str);
    	}
    	if(str.matches("^\\+852[0-9]{8}$")) { 			// hong kong
    		return(str);
    	}    
    	if(str.matches("^\\+86[0-9]{11}$")) { 			// china
    		return(str);
    	} 
    	if(str.matches("^\\+91[0-9]{10}$")) { 			// india
    		return(str);
    	}
    	
    	// no match
    	
    	return("");
    }
    
}