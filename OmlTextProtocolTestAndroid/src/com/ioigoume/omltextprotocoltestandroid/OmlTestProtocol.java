/* Copyright (c) 2013 NITLab, University of Thessaly, CERTH, Greece
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*
* OML4J 
*/

package com.ioigoume.omltextprotocoltestandroid;

import java.util.ArrayList;
import java.util.Arrays;

import omlBasePackage.MPTYPES;
import omlBasePackage.OMLBase;
import omlBasePackage.OMLMPFieldDef;
import omlBasePackage.OMLSchemaCST;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OmlTestProtocol extends Activity {
	
	public static final String table_name = "dataTable";
	public static final String table_name_2 = "data2Table";
	public static final String table_name_3 = "data3Table";
 
	private TextView text;
	private Button btn_test;
	private OMLBase omlclient;
	private int counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oml_test_protocol);
		
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		     StrictMode.setThreadPolicy(policy);
		 }
	
		text = (TextView) findViewById(R.id.textView_main);
		text.setMovementMethod(new ScrollingMovementMethod());
		btn_test = (Button) findViewById(R.id.btn_connect);

		// Add on click listener
		btn_test.setOnClickListener(new View.OnClickListener() {
	
		    @Override
		    public void onClick(View v) {

		    	// Create the object
		    	omlclient = new OMLBase("TestAndroidApp", "AndroidTest-exp2", "ioigoume_testapp", "tcp:nitlab.inf.uth.gr:3003");
		    	text.setText("oml client created\n");
		    	
				OMLMPFieldDef vt1 = new OMLMPFieldDef("counter",MPTYPES.OML_INT32_VALUE.getMtype());
				OMLMPFieldDef vt2 = new OMLMPFieldDef("name",MPTYPES.OML_STRING_VALUE.getMtype());
				OMLMPFieldDef vt3 = new OMLMPFieldDef("surname",MPTYPES.OML_STRING_VALUE.getMtype());
				OMLMPFieldDef vt4 = new OMLMPFieldDef("other",MPTYPES.OML_INT32_VALUE.getMtype());
				OMLMPFieldDef vt5 = new OMLMPFieldDef("other2",MPTYPES.OML_INT32_VALUE.getMtype());
		        
				OMLSchemaCST mp_1 = new OMLSchemaCST(new ArrayList<OMLMPFieldDef>(Arrays.asList(vt1,vt2,vt3)));
				OMLSchemaCST mp_2 = new OMLSchemaCST(new ArrayList<OMLMPFieldDef>(Arrays.asList(vt1,vt2,vt3,vt4)));
				OMLSchemaCST mp_3 = new OMLSchemaCST(new ArrayList<OMLMPFieldDef>(Arrays.asList(vt4,vt5)));
		    	
		    	
		    	// Add schema
		    	omlclient.addmp(table_name, mp_1.retSchema(1));
		    	omlclient.addmp(table_name_2, mp_2.retSchema(1));
		    	omlclient.addmp(table_name_3, mp_3.retSchema(1));
	
		    	text.append("oml schema created\n");
		    	
		    	// Connect to server
		    	omlclient.start();
				text.append("oml client is connected\n");
				
				if (omlclient != null ) {
				   try {
					counter++;
					String[] data = { String.valueOf(counter), "Giannis", "Igoumenos" };
					String[] data2 = { String.valueOf(counter), "Giannis", "Igoumenos", String.valueOf(counter*2) };
					String[] data3 = { String.valueOf(counter*2), String.valueOf(counter + 2) };
		 
					omlclient.inject(table_name, data);
					text.append("oml:" + data[0] + " " + data[1] + " " + data[2] + " - is added.\n");					
					omlclient.inject(table_name_2, data2);
					text.append("oml:" + data2[0] + " " + data2[1] + " " + data2[2] + " " + data2[3] + " - is added.\n");					
					omlclient.inject(table_name_2, data2);
					text.append("oml:" + data2[0] + " " + data2[1] + " " + data2[2] + " " + data2[3] + " - is added.\n");
					omlclient.inject(table_name_3, data3);
					text.append("oml:" + data3[0] + " " + data3[1] + " " + " - is added.\n");
						
					// Close the database connection
					omlclient.close();
					text.append("Server Closed.\n");
					} catch (Exception e) {
						text.append("Database could not be updated\n");
				        }
				    }
				}
	
		});
	}
}
