# OML4J: Native OML Implemantation in Java #
This is a simple client for OML which does not use liboml2 and its filters, but connects directly to the server using the oml text protocol.User can use this library to create Java applications which can send measurements to the OML collection server. A simple example, both on Android and on Native Java, on how to use this library is attached below.

## Usage ##

This module provides the OMLBase class, which contains the following methods:

- init
- start
- addmp
- inject
- close

To use OML in a java project you can either:
 
- import the omlBasePackage into your project. As far as eclipse is concerned,  perform :

        Import->General->Archive File->Browse : Choose the omlBasePackage.zip folder 
		Locate the extracted folder underneath the src path of project's directory tree.

- import the oml4j.jar file into your project. As far as eclipse is concerned, perform :

		Build Path->Add External Archives->Browse : Choose the oml4j.jar file
	
Then add the package to your project:

    import omlBasePackage.OMLBase;

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


## Examples ##

You can find a native java and an android example inside the repository. The first one uses the jar approach and the second one the package approach in loading the OMLBase class.