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

import java.util.ArrayList;

import omlBasePackage.OMLBase;
import omlBasePackage.OMLMPFieldDef;
import omlBasePackage.OMLTypes;
import omlBasePackage.OmlMP;

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		OMLBase omlclient = new OMLBase("TestApp", "ioigoume-exp", "testapp", "tcp:nitlab.inf.uth.gr:3003");
		
		ArrayList<OMLMPFieldDef> mp1 = new ArrayList<OMLMPFieldDef>();
		mp1.add(new OMLMPFieldDef("counter",OMLTypes.OML_INT32_VALUE));
		mp1.add(new OMLMPFieldDef("name",OMLTypes.OML_STRING_VALUE));
		mp1.add(new OMLMPFieldDef("surname",OMLTypes.OML_STRING_VALUE));
		
		ArrayList<OMLMPFieldDef> mp2 = new ArrayList<OMLMPFieldDef>();
		mp2.add(new OMLMPFieldDef("val1",OMLTypes.OML_INT32_VALUE));
		mp2.add(new OMLMPFieldDef("val2",OMLTypes.OML_INT32_VALUE));
		
		OmlMP mp_1 = new OmlMP(mp1);
		OmlMP mp_2 = new OmlMP(mp2);
		
		omlclient.addmp("tbl1", mp_1);
        omlclient.addmp("tbl2", mp_2);
        omlclient.start();
        String[] data = { "1", "Ioannis", "Igoumenos" };
        String [] data2 = { "3", "5"};
        for(int i=0; i<10 ; i++){
	        mp_1.inject(data);
	        mp_2.inject(data2);
	        data[0] += "2";
	        data[1] = "Igoumenos";
	        data[2] =  "Ioannis" ;
	        data2[0] = "5" ;
	        data2[1] = "99" ;
        }
        omlclient.close();

	}

}
