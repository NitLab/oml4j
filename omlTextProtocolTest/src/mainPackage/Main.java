package mainPackage;

import omlBasePackage.OMLBase;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OMLBase omlclient = new OMLBase("TestApp", "ioigoume-exp", "testapp", "tcp:nitlab.inf.uth.gr:3003");
        omlclient.addmp("tbl1", "counter:int32 name:string surname:string");
        omlclient.addmp("tbl2", "val1:int32 val2:int32");
        omlclient.start();
        String[] data = { "1", "Ioannis", "Igoumenos" };
        String [] data2 = { "3", "5"};
        omlclient.inject("tbl1", data);
        omlclient.inject("tbl2", data2);
        data[0] = "2";
        data[1] = "Igoumenos";
        data[2] =  "Ioannis" ;
        data2[0] = "5" ;
        data2[1] = "99" ;
        omlclient.inject("tbl1", data);
        omlclient.inject("tbl2", data2);
        omlclient.close();

	}

}
