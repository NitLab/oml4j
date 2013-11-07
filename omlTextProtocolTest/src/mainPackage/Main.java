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

package mainPackage;

import omlBasePackage.OMLBase;

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[][] mp_1 = {	{"counter","OML_INT32_VALUE"},
							{"name", "OML_STRING_VALUE"},
							{"surname", "OML_STRING_VALUE"} };
		
		String[][] mp_2 = {	{"val1", "OML_INT32_VALUE"},
							{"val2", "OML_INT32_VALUE"}};
		
		OMLBase omlclient = new OMLBase("TestApp", "ioigoume-exp", "testapp", "tcp:nitlab.inf.uth.gr:3003");
        omlclient.addmp("tbl1", mp_1);
        omlclient.addmp("tbl2", mp_2);
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
