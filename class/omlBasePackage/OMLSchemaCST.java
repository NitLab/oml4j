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

package omlBasePackage;

import java.util.ArrayList;

public class OMLSchemaCST{
	private ArrayList<OMLMPFieldDef> field_array;
	private int protocol_version;

	public OMLSchemaCST(ArrayList<OMLMPFieldDef> field_array)
	{
		setField_array(field_array);
	}
	
	public String retSchema(int protocol_version){
		String schema = "";
		setProtocol_version(protocol_version);
		for(OMLMPFieldDef tmp : field_array){
			schema = schema + tmp.getSchemaPVS(protocol_version) + " ";
		}
		return schema.trim();
	}

	protected synchronized ArrayList<OMLMPFieldDef> getField_array() {
		return field_array;
	}

	protected synchronized void setField_array(ArrayList<OMLMPFieldDef> field_array) {
		this.field_array = field_array;
	}
	
	protected synchronized int getProtocol_version() {
		return protocol_version;
	}

	protected synchronized void setProtocol_version(int protocol_version) {
		this.protocol_version = protocol_version;
	}
}