package com.vod.fileTransfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CSVFileTransfer
{
	public static void main(String[] args) throws IOException 
	{
		String dateString=null;
		try
		{
			dateString=args[0];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			dateString=sdf.format(new Date());
		}
		System.out.println("Trying to pick the files of date ::"+dateString);
		File folder =new File(LoadProperties.getPropertyValue(Constants.FILES_TO_BE_PROCESSED_LOCATION));
		File folder2 =new File(LoadProperties.getPropertyValue(Constants.FILES_TO_BE_COPIED_LOCATION));
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) 
		{
			if (file.isFile() && file.getName().contains(dateString))
			{
				BufferedReader br=new BufferedReader(new FileReader(file));
				String line="";
				int numberOfLines=0;
				while((line=br.readLine())!=null)
				{
					numberOfLines=numberOfLines+1;
					if(numberOfLines>1)
					{
						Files.copy(file.toPath(), new File(folder2.toString()+"/"+file.getName()).toPath());
						break;
					}
				}
			}
		}
		MergeFilesIntoOne.createfile(dateString);
	}
}