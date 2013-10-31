# OML4J: Native OML Implemantation in Java #
This is a simple client for OML which does not use liboml2 and its filters, but connects directly to the server using the oml text protocol.User can use this library to create Java applications which can send measurements to the OML collection server. A simple example, both on Android and on Native Java, on how to use this library is attached below.

## Usage-Real Example ##
### Android ###
- MainActivity.java

----------

    import android.app.Activity;
    import android.os.Bundle;
    import android.text.method.ScrollingMovementMethod;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.Button;
    import android.widget.TextView;

    public class MainActivity extends Activity {
    	public static final String table_name = "dataTable";
    	public static final String table_name_2 = "data2Table";
    	public static final String table_name_3 = "data3Table";
     
    	private TextView text;
    	private Button btn_server;
    	private Button btn_connect;
    	private Button btn_close;
    	private String appId = new String();
    	private OMLBase omlclient;
    	private int counter = 0;
     
    	@Override
    	public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_main);
     
    		text = (TextView) findViewById(R.id.textView_main);
    		text.setMovementMethod(new ScrollingMovementMethod());
    		btn_server = (Button) findViewById(R.id.button_Webconnect);
    		btn_close = (Button) findViewById(R.id.buttonClose);
    		btn_connect = (Button) findViewById(R.id.btn_connect);
     
    		// Application id
    		appId = getApplicationContext().getResources().getString(
    				R.string.app_name);
     
    		omlclient = new OMLBase(appId, appId + "-exp2", "ioigoume_" + appId
    				+ "DataBase", "tcp:nitlab.inf.uth.gr:3003");
    		text.setText("oml client created\n");
    		// Add schema
    		omlclient.addmp(table_name, "counter:long name:string surname:string");
    		omlclient.addmp(table_name_2, "counter:long name:string surname:string other:long");
    		omlclient.addmp(table_name_3, "counter:long name:string surname:string other:long other2:long");
     
    		text.append("oml schema created\n");
     
     
     
    		//
    		// Add the listeners
    		//
    		btn_connect.setOnClickListener(new OnClickListener(){
     
    			@Override
    			public void onClick(View v) {
    				omlclient.start();
    				text.append("oml client is connected\n");
    			}
     
    		});
     
     
    		btn_server.setOnClickListener(new OnClickListener() {
     
    		@Override
    		public void onClick(View v) {
    			if (omlclient != null && omlclient.isSrvConnected()) {
    			   try {
    				String[] data = { String.valueOf(counter), "Giannis", "Igoumenos" };
    				String[] data2 = { String.valueOf(counter), "Giannis", "Igoumenos", String.valueOf(counter*2) };
    				String[] data3 = { String.valueOf(counter), "Giannis", "Igoumenos", String.valueOf(counter*2), String.valueOf(counter + 2) };
     
    				omlclient.inject(table_name, data);
    				omlclient.inject(table_name_2, data2);
    				omlclient.inject(table_name_3, data3);
     
    				text.append("oml:" + data[0] + " " + data[1] + " " + data[2] + " - is added.\n");
    				text.append("oml:" + data2[0] + " " + data2[1] + " " + data2[2] + " " + data2[3] + " - is added.\n");
    				text.append("oml:" + data3[0] + " " + data3[1] + " " + data3[2] + " " + data3[3] + " " + data3[4] + " - is added.\n");
     
    				counter++;
    				} catch (Exception e) {
    					text.append("Database could not be updated\n");
    			}
    			}
    			}
     
    		});
     
    		btn_close.setOnClickListener(new OnClickListener() {
     
    			@Override
    			public void onClick(View v) {
    				if (omlclient != null && omlclient.isSrvConnected()) {
    					omlclient.close();
    					text.append("Server Closed.\n");
    				}
     
    			}
     
    		});
     
    	}
     
    }

- Layout.xml

----------

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >
 
    <TextView
        android:id="@+id/textView_main"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_gravity="top|fill_horizontal"
        android:layout_marginRight="37dp"
        android:layout_weight="1" />
 
    <Button
        android:id="@+id/btn_connect"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_weight="0"
        android:text="@string/btn_connect" />
 
    <Button
        android:id="@+id/button_Webconnect"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:layout_weight="0"
        android:text="@string/serverConnect_btn" />
 
    <Button
        android:id="@+id/buttonClose"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_weight="0"
        android:text="@string/close_btn" />
 
</LinearLayout>


- AndroidManifest.xml

----------

Remember to change the package name. It must match you application's package name

    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.ioigoume.webnitlabdatabase"
    android:versionCode="1"
    android:versionName="1.0" >
     
    <uses-sdk
    android:minSdkVersion="8"
    android:targetSdkVersion="15" />
     
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     
    <application
    android:icon="@drawable/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" >
    <activity
    android:name=".MainActivity"
    android:label="@string/title_activity_main" >
    <intent-filter>
    <action android:name="android.intent.action.MAIN" />
     
    <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
    </activity>
    </application>
     
    </manifest>

### Java ###

This module provides the OMLBase class, which contains the following methods:

- init
- start
- addmp
- inject
- close

To use OML in a java project, import the OMLBase class:


    import package.OMLBase

Start by initializing an OMLBase object. The init method takes up to four arguments:

- the name of the application,
- the name of the experiment,
- the name of the node,
- and the OML server URI in the form tcp:hostname:port

For example:

    OMLBase omlclient = new OMLBase("TestApp", "ioigoume-exp", "testapp", "tcp:nilab.inf.uth.gr:3003");

The only mandatory argument is the first one (the name of the application). If you skip any of the others, they may be defined by environment variables (OML_DOMAIN, OML_NAME, OML_COLLECT) or via command-line options. If these variables are not passed in explicitly and neither the command line options nor environment variables are defined then the application will run with OML disabled, and the measurements that would have been sent to OML will be printed on stdout instead.

Next, add one or more measurement points. Pass the name of the measurement point and a schema string to the start method. The schema string should be in the format measurement_name1:measurement_type1 measurement_name2:measurement_type2 for example:

    omlclient.addmp("tbl1", "counter:int32 name:string surname:string"); 
    omlclient.addmp("tbl2", "val1:int32 val2:int32");



When you have set up all your measurement points, call start():

    omlclient.start();

For presentation purposes we will create a few hardcoded measurement tuples:

    String[] data = { "1", "Ioannis", "Igoumenos" };
    String [] data2 = { "3", "5"};

Since you have measurement points ready to send to OML, use the inject function as follows:


    omlclient.inject("tbl1", data);
    omlclient.inject("tbl2", data2);


At the end of your program, call close to gracefully close the database:


    omlclient.close();
