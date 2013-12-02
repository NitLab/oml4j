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
import java.util.List;

public class OmlMP{
	private ArrayList<OMLMPFieldDef> field_array = null;
	private int protocol_version;
	private OMLBase omlClientSwallowCopy = null;
	private String table_name; 
	private int measurementPointCounter;
	private int schemaCounter;
	private long head_time;
	private String schemaDescription;


	public OmlMP(ArrayList<OMLMPFieldDef> field_array)
	{
		setField_array(field_array);
		measurementPointCounter = -1;
		schemaCounter = -1;
		table_name = "-1";
		protocol_version = -1;
		head_time = -1;
		setSchemaDescription("-1");
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

	public void setOmlClientSwallowCopy(OMLBase omlClientSwallowCopy) {
		this.omlClientSwallowCopy = omlClientSwallowCopy;
	}
	
	/**
	 * SENDS SINGLE LINE TUPLE TO THE SERVER
	 * @param table_name
	 * @param data
	 * @return boolean status
	 */
	public synchronized boolean inject(String[] data){
		if(omlClientSwallowCopy == null)
			return false;
		
		StringBuilder tuple = create_tuples(data);
		System.out.println(tuple.toString());
		if(tuple == null || tuple.length()==0)
			return false;
		if(!omlClientSwallowCopy.write_to_sock(tuple))
			return false;
		
		return true;		
	}
	
	
	/**
	 * SENDS MULTIPLE TUPLES TO THE SERVER
	 * @param table_name
	 * @param data
	 * @return boolean status
	 */
	public synchronized boolean inject_mass(List<String[]> tuples){
		if(omlClientSwallowCopy == null)
			return false;
		
		for(String[] data : tuples){
			StringBuilder tuple = create_tuples(data);
			if(tuple == null || tuple.length()==0)
				return false;
			if(!omlClientSwallowCopy.write_to_sock(tuple))
				return false;
		}
		return true;	
	}
	
	
	/**
	 * Measurement Tuple Serialization
	 * Each tuple is serialized into a new-line terminated string with 
	 * all elements separated by a TAB. 
	 * 
	 * In addition, TREE NEW ELEMENTS are inserted before the measurements 
	 * themselves. These three elements are defined as follows:
	 * 
	 *  - time_stamp: A time stamp in seconds relative to the 
	 *                header's 'start-time'
	 *  - stream_id:  This is the same number as used in the 'schema' 
	 *                header definition
	 *  - seq_no: 	  A sequence number in the context of the 
	 *                specific measurement stream.
	 *   
	 *   The sequence numbers of each measurement stream is independent of 
	 *   the others. That is, the first measurement in a given stream should 
	 *   have *seq_no*=0, and all subsequent measurements in the stream 
	 *   should increment the sequence number by 1.
	 *
	 * CREATE TUPLE
	 */
	private synchronized StringBuilder create_tuples(String[] data){		
		StringBuilder tuples = new StringBuilder();
		head_time = omlClientSwallowCopy.getHead_time();
		measurementPointCounter++;
		/**
		 * CALCULATE THE TIME INTERVAL
		 */
		long cur_time = System.currentTimeMillis();
		long dif_time = cur_time - head_time;
		double lcl_dif_time = dif_time/1000.0;
		String dif_time_str = Double.toString(lcl_dif_time);
 
		/**
		 * CREATE THE TUPLE STRING
		 */
		tuples.append(	dif_time_str + // #1 : timestamp
						"\t" + 
						String.valueOf(schemaCounter) + // #2 : stream_id
						"\t" + 
						String.valueOf(measurementPointCounter) // #3 : seq_no
					); 
		
		for (int i = 0; i < data.length; i++) { // #4 : data
			tuples.append("\t" + data[i]);
		}
		
		tuples.append("\n");
		
		return tuples;
	}


	public synchronized int getMeasurementPointCounter() {
		return measurementPointCounter;
	}


	public synchronized void setMeasurementPointCounter(int measurementPointCounter) {
		this.measurementPointCounter = measurementPointCounter;
	}


	protected synchronized String getTable_name() {
		return table_name;
	}


	public synchronized void setTable_name(String table_name) {
		this.table_name = table_name;
	}


	public synchronized int getSchemaCounter() {
		return schemaCounter;
	}


	public synchronized void setSchemaCounter(int schemaCounter) {
		this.schemaCounter = schemaCounter;
	}
	
	public synchronized long getHead_time() {
		return head_time;
	}


	public synchronized void setHead_time(long head_time) {
		this.head_time = head_time;
	}


	public synchronized String getSchemaDescription() {
		return schemaDescription;
	}


	public synchronized void setSchemaDescription(String schemaDescription) {
		this.schemaDescription = schemaDescription;
	}
}