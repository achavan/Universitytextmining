/*
 *  coref.java
 *
 * Copyright (c) 2000-2012, The University of Sheffield.
 *
 * This file is part of GATE (see http://gate.ac.uk/), and is free
 * software, licenced under the GNU Library General Public License,
 * Version 3, 29 June 2007.
 *
 * A copy of this licence is included in the distribution in the file
 * licence.html, and is also available at http://gate.ac.uk/gate/licence.html.
 *
 *  ANISH, 12/2/2014
 *
 * For details on the configuration options, see the user guide:
 * http://gate.ac.uk/cgi-bin/userguide/sec:creole-model:config
 */

package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

import gate.*;
import gate.creole.*;
import gate.creole.metadata.*;
import gate.gui.docview.CorefEditor;
import gate.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

import javax.management.relation.RelationSupport;
		

/** 
 * This class is the implementation of the resource ASSIGNMENTRESOURCE.
 */
@CreoleResource(name = "assignmentresource",
        comment = "Add a descriptive comment about this resource")
public class coref  extends AbstractLanguageAnalyser  implements ProcessingResource
{
	 FileWriter fileWritter1=null;
	 Writer bw=null;
	 File file=null;
	 Document doc=null;
	 Hashtable<Long,Long> h=new Hashtable<Long,Long>();
	 ArrayList<Integer>list=new ArrayList<Integer>();
	 public void execute() throws ExecutionException
	 {
		
		 System.out.print("Hello World3");
		 doc=getDocument();
		 createfile();
		 corelation();
		 PrintUniversity();
		 printUniversityPerson();
		 printUniversityPosition();
		 Organizationunit();
		 //belongs_to();
		 //has_to();
		 try 
			{
				bw.close();
			} 	
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	 }
	
	
	 private void PrintUniversity()
	 {
		 AnnotationSet aanoset=doc.getAnnotations();
			AnnotationSet Uposition=aanoset.get("university");
			int i=0;
			 if(i==0)
	         {
				
				try {
					bw.append("<h1>university</h1>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	         }
			for(Annotation annot: Uposition)
			{   String content="";
				
				try	{ 
						content = doc.getContent().getContent
						(annot.getStartNode().getOffset(),
						annot.getEndNode().getOffset()).toString();
						bw.append("<br>"+content+"</br>");
						bw.append(System.getProperty("line.separator"));
					}
				
				catch(InvalidOffsetException e)
				
					{
						e.printStackTrace();
					
					} 
				catch(IOException e)
					{
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  } 
			
			
		
		
	}


	void createfile()
	 {
		try
			{
				Random or = new Random();
				file =new File("H:/ansih/javaioappendfile"+or.nextInt(99999)+".html");
				if(!file.exists())
				{
					file.createNewFile();
					
				}
				bw= new BufferedWriter(new OutputStreamWriter(
				        new FileOutputStream(file, true), "UTF-8"));
		
		


			}
		catch(IOException e)
			{
				e.printStackTrace();
			}
	 		}
	
	void corelation()
	{
		
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet blacklist1=aanoset.get("Person");
		AnnotationSet blacklist2=aanoset.get("Organization");
		AnnotationSet blacklist3=aanoset.get("Location");
		ArrayList<Long> annotCategory=new ArrayList<Long>();
		 
		for(Annotation annot: blacklist1)
		{   
			int bb1=0;
			FeatureMap map = annot.getFeatures();
			if((map.get("matches"))!=null)
			{
				annotCategory=((ArrayList<Long>) map.get("matches"));
		
				
						    
				
			for(Object off:annotCategory)
			{ 
		        if(bb1!=0)
		        {
				if(!list.contains((Integer)off))
				{	
					list.add((Integer)off);
					
				}
				}
		        System.out.println(list.size());
		        bb1++;
			}
			}
			
			
		}
		

		
		
		
		/*

		
		for(Annotation annot: blacklist2)
		{   
		
			FeatureMap map = annot.getFeatures();
			if((map.get("matches"))!=null)
			{
				annotCategory=((ArrayList<Long>) map.get("matches"));
		
				
						    
				int bb2=0;
			for(Object off:annotCategory)
			{ 
		        if(bb2!=0)
		        {
				if(!list.contains((Integer)off))
				{	
					list.add((Integer)off);
					
				}
				}
			
		        bb2++;
			}
			}
			
			
		}
		
		

		
		//annotCategory.removeAll(Collections.singleton(null));	
	
		
		for(Integer temp:list)
		{
			Annotation annot=blacklist1.get(temp);
			//System.out.println("temp "+temp);
			long staroffset=annot.getStartNode().getOffset();
			long endoffset=annot.getEndNode().getOffset();
			h.put(staroffset, endoffset);
			
		}

			
			
	
		
	}
	void  PrintUniversity()
	{/*
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet Uperson=aanoset.get("UniversityPosition");
		*/
		
	}
	
	void Organizationunit()
	{

		/*
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet Uperson=aanoset.get("UniversityPosition");
		*/

		
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet org=aanoset.get("OrganizationalUnit");
		int i=0;
		 if(i==0)
         {
			
			try {
				bw.append("<h1>OrganizationalUnit</h1>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
         }
		for(Annotation annot: org)
		{   String content="";
			
			try	{
					
				if(!h.containsKey(annot.getStartNode().getOffset())&&!h.containsValue(annot.getEndNode().getClass()))
				{
					content = doc.getContent().getContent
					(annot.getStartNode().getOffset(),
					annot.getEndNode().getOffset()).toString();
					bw.append("<br>"+content+"</br>");
					bw.append(System.getProperty("line.separator"));
				}
				
				}
			
			catch(InvalidOffsetException e)
			
				{
					e.printStackTrace();
				
				} 
			catch(IOException e)
				{
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  } 
		
		
	
	
		
	}
	void printUniversityPosition()
	{
		/*
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet Uperson=aanoset.get("UniversityPosition");
		*/

		
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet Uposition=aanoset.get("UniversityPosition");
		int i=0;
		 if(i==0)
         {
			
			try {
				bw.append("<h1>UniversityPosition</h1>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
         }
		for(Annotation annot: Uposition)
		{   String content="";
			
			try	{ 
					content = doc.getContent().getContent
					(annot.getStartNode().getOffset(),
					annot.getEndNode().getOffset()).toString();
					bw.append("<br>"+content+"</br>");
					bw.append(System.getProperty("line.separator"));
				}
			
			catch(InvalidOffsetException e)
			
				{
					e.printStackTrace();
				
				} 
			catch(IOException e)
				{
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  } 
		
		
	
	}
	void printUniversityPerson()
	{
		
		AnnotationSet aanoset=doc.getAnnotations();
		AnnotationSet Uperson=aanoset.get("UniversityPerson");
		int i=0;
		 if(i==0)
         {
			
			try {
				bw.append("<h1>UniversityPerson</h1>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
         }
		for(Annotation annot: Uperson)
		{   String content="";
			
			try	{ 
				
				if(!h.containsKey(annot.getStartNode().getOffset()))
				{System.out.println("=================================================================");
					System.out.println(annot.getStartNode().getOffset());
					content = doc.getContent().getContent
					(annot.getStartNode().getOffset(),
					annot.getEndNode().getOffset()).toString();
					bw.append("<br>"+content+"</br>");
					bw.append(System.getProperty("line.separator"));
				}
				}
			
			catch(InvalidOffsetException e)
			
				{
					e.printStackTrace();
				
				} 
			catch(IOException e)
				{
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  } 
		
	}
	void belongs_to()
	{
		
		
		
	}
	
	void has_to()
	{
		
		
		
		
	}
	
	
}