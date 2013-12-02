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

public enum OMLTypes
{
	OML_DOUBLE_VALUE("double"),
	OML_LONG_VALUE("long"),
	OML_PADDING1_VALUE("padding1"),
	OML_STRING_VALUE("string"),
	OML_INT32_VALUE("int32"),
	OML_UINT32_VALUE("uint32"),
	OML_INT64_VALUE("int64"),
	OML_UINT64_VALUE("uint64"),
	OML_BLOB_VALUE("blob"),
	OML_GUID_VALUE("guid"),
	OML_BOOL_VALUE("bool");
	
	private String mtype;
	
	private OMLTypes(String s){
		mtype = s;
	}
	
	public String getOMLTypes()
	{
		return mtype;
	}
	
	@Override
	public String toString() {
	     switch (this) {
	       case OML_DOUBLE_VALUE:
	            System.out.println(mtype);
	            break;
	       case OML_LONG_VALUE:
	            System.out.println(mtype);
	            break;
	       case OML_PADDING1_VALUE:
	            System.out.println(mtype);
	            break;
	       case OML_STRING_VALUE:
	            System.out.println(mtype);
	       case OML_INT32_VALUE:
	            System.out.println(mtype);
	       case OML_UINT32_VALUE:
	            System.out.println(mtype);
	       case OML_INT64_VALUE:
	            System.out.println(mtype);
	       case OML_BLOB_VALUE:
	            System.out.println(mtype);
	       case OML_UINT64_VALUE:
	            System.out.println(mtype);
	       case OML_GUID_VALUE:
	            System.out.println(mtype);
	       case OML_BOOL_VALUE:
	            System.out.println(mtype);
	      }
	      return super.toString();
	}
	
}