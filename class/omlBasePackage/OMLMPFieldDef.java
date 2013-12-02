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

public class OMLMPFieldDef
{
	private String mpname;
	private String mptype;
	
	public OMLMPFieldDef(String mpname, OMLTypes mptype)
	{
		setMpname(mpname);
		setMptype(mptype.getOMLTypes());
	}
	
	@Deprecated
	public OMLMPFieldDef(String mpname, String mptype)
	{
		setMpname(mpname);
		setMptype(mptype);
	}

	protected synchronized String getMpname() {
		return mpname;
	}

	protected synchronized void setMpname(String mpname) {
		this.mpname = mpname;
	}

	protected synchronized String getMptype() {
		return mptype;
	}

	protected synchronized void setMptype(String mptype) {
		this.mptype = mptype;
	}

	
	/**
	 * SCHEMA CREATING USING PROTOCOL VERSION
	 */
	public String getSchemaPVS(int protocol_version)
	{
		String schema = "";
		try{
			if(protocol_version < 0)
			{
				return "Could not get schema. Check the function parameters";
			}
			switch (protocol_version){
				case 1:
					schema = this.getMpname() + ":" + this.getMptype();
					break;
				default:
					System.out.println("The version number is not valid:" + String.valueOf(protocol_version));
			}
		}catch(NullPointerException e)
		{
			return "Error occured: null pointer Exception.";
		}
		
		return schema;
	}
}
