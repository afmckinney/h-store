/***************************************************************************
 *  Copyright (C) 2012 by H-Store Project                                  *
 *  Brown University                                                       *
 *  Massachusetts Institute of Technology                                  *
 *  Yale University                                                        *
 *                                                                         *
 *  Original By: VoltDB Inc.											   *
 *  Ported By:  Justin A. DeBrabant (http://www.cs.brown.edu/~debrabant/)  *								   								   
 *                                                                         *
 *                                                                         *
 *  Permission is hereby granted, free of charge, to any person obtaining  *
 *  a copy of this software and associated documentation files (the        *
 *  "Software"), to deal in the Software without restriction, including    *
 *  without limitation the rights to use, copy, modify, merge, publish,    *
 *  distribute, sublicense, and/or sell copies of the Software, and to     *
 *  permit persons to whom the Software is furnished to do so, subject to  *
 *  the following conditions:                                              *
 *                                                                         *
 *  The above copyright notice and this permission notice shall be         *
 *  included in all copies or substantial portions of the Software.        *
 *                                                                         *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,        *
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF     *
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. *
 *  IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR      *
 *  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,  *
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR  *
 *  OTHER DEALINGS IN THE SOFTWARE.                                        *
 ***************************************************************************/

package edu.brown.benchmark.voterexperiments.demohstorecorrect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import edu.brown.benchmark.voterexperiments.demosstorecorrect.VoterDemoSStoreConstants;

public class PhoneCallGenerator {
	
    private LinkedList<PhoneCall> callList;
    private ListIterator<PhoneCall> callIterator;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
	
	// Initialize some common constants and variables
    private static final String[] AREA_CODE_STRS = ("907,205,256,334,251,870,501,479" +
													",480,602,623,928,520,341,764,628,831,925,909,562,661,510,650,949,760" +
													",415,951,209,669,408,559,626,442,530,916,627,714,707,310,323,213,424" +
													",747,818,858,935,619,805,369,720,303,970,719,860,203,959,475,202,302" +
													",689,407,239,850,727,321,754,954,927,352,863,386,904,561,772,786,305" +
													",941,813,478,770,470,404,762,706,678,912,229,808,515,319,563,641,712" +
													",208,217,872,312,773,464,708,224,847,779,815,618,309,331,630,317,765" +
													",574,260,219,812,913,785,316,620,606,859,502,270,504,985,225,318,337" +
													",774,508,339,781,857,617,978,351,413,443,410,301,240,207,517,810,278" +
													",679,313,586,947,248,734,269,989,906,616,231,612,320,651,763,952,218" +
													",507,636,660,975,816,573,314,557,417,769,601,662,228,406,336,252,984" +
													",919,980,910,828,704,701,402,308,603,908,848,732,551,201,862,973,609" +
													",856,575,957,505,775,702,315,518,646,347,212,718,516,917,845,631,716" +
													",585,607,914,216,330,234,567,419,440,380,740,614,283,513,937,918,580" +
													",405,503,541,971,814,717,570,878,835,484,610,267,215,724,412,401,843" +
													",864,803,605,423,865,931,615,901,731,254,325,713,940,817,430,903,806" +
													",737,512,361,210,979,936,409,972,469,214,682,832,281,830,956,432,915" +
													",435,801,385,434,804,757,703,571,276,236,540,802,509,360,564,206,425" +
													",253,715,920,262,414,608,304,307").split(",");
	
	// convert the area code array to a list of digits
    private static final long[] AREA_CODES = new long[AREA_CODE_STRS.length];
    static {
        for (int i = 0; i < AREA_CODES.length; i++)
            AREA_CODES[i] = Long.parseLong(AREA_CODE_STRS[i]);
	}
	
	public static class PhoneCall {
	    public final long voteId;
        public final int contestantNumber;
        public final long phoneNumber;
		
        protected PhoneCall(long voteId, int contestantNumber, long phoneNumber) {
            this.voteId = voteId;
            this.contestantNumber = contestantNumber;
            this.phoneNumber = phoneNumber;
        }
    }
	/**
	public PhoneCallGenerator(String filename) {
		callList = new LinkedList<PhoneCall>();
	    BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
		    String line = null;
		    
		    while((line = reader.readLine()) != null)
		    {
		    	String[] split = line.split(" ");
		    	callList.add(new PhoneCall(new Long(split[0]), new Integer(split[2]), new Long(split[1])));
		    }
		    reader.close();
		    callIterator = callList.listIterator();
		}
	    catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
    }
    
    public PhoneCall receive()
    {
        if(!callIterator.hasNext())
        	callIterator = callList.listIterator();
        // Return the generated phone number
        return callIterator.next();
    }
    */
	
	public PhoneCallGenerator() {
		try {
			InetAddress host = InetAddress.getLocalHost();
			String hostname;
			
			
			if(host.getHostName().startsWith(VoterDemoHStoreConstants.HOST_PREFIX) || 
					host.getHostName().startsWith(VoterDemoHStoreConstants.HOST_PREFIX_2) ||
					host.getHostName().startsWith(VoterDemoHStoreConstants.JIANG_SERVER_HOST_NAME))
			{
				hostname = VoterDemoHStoreConstants.SERVER_HOST_NAME;
			}
			else if (host.getHostName().startsWith(VoterDemoHStoreConstants.ISTC1_CLIENT) || 
				    host.getHostName().startsWith(VoterDemoHStoreConstants.ISTC1_HOST))
		    {
		    	hostname = VoterDemoHStoreConstants.ISTC1_HOST;
		    }
			else
			{
				hostname = VoterDemoHStoreConstants.LOCAL_HOST;
			}

			socket = new Socket(hostname, VoterDemoHStoreConstants.VOTE_PORT_NUM);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			//ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (UnknownHostException e){
			System.err.println("UnknownHostException");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.err.println("IOException");
			e.printStackTrace();
		}
    }
	
	public PhoneCall receive()
	{
		try {
			String[] response;
			out.print("next vote");
			out.flush();
		
			response = in.readLine().split(" ");
		
			return new PhoneCall(new Long(response[0]), new Integer(response[2]), new Long(response[1]));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}

}