package com.vod.fileTransfer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MergeFilesIntoOne {

	public static void createfile(String dateString) throws IOException

	{
		String fileLocation=LoadProperties.getPropertyValue(Constants.FILES_TO_BE_COPIED_LOCATION);
		File inputFolder = new File(fileLocation);
		Set<String> alreadyMetNames = new HashSet<>();
		//File output = File.createTempFile("output", ".csv");
		String newFileName=fileLocation+"/blackberry_"+dateString+"_1.csv";
		File output = new File(newFileName);
		boolean isFilesExist=false;
		try (FileWriter fw = new FileWriter(output); BufferedWriter bw = new BufferedWriter(fw)) 
		{
			boolean isRowsExit=false;
			String headerLine=LoadProperties.getPropertyValue(Constants.HEADERS);
			String[] headers=headerLine.split(",");
			String firstLine=headers[0]+","+headers[2]+","+headers[1];

			bw.write(firstLine);
			bw.newLine();
			if(inputFolder.listFiles().length>1)
			{
				isFilesExist=true;
				for (File file : inputFolder.listFiles()) {

					try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
						String line;

						// Search the header line
						while ((line = br.readLine()) != null) {
							if (line.equals(headerLine)) break;
						}

						// Start to parse the file at the first row containing data
						while ((line = br.readLine()) != null) {
							String[] columns=line.split(",");
							line=columns[0]+","+columns[2]+","+columns[1];
							bw.write(line);
							bw.newLine();
							isRowsExit=true;
						}
					}
					
					if(!file.getName().equals(newFileName) )
					{
						file.delete();
					}					

				}
			}
			
		}

		catch(Exception e)
		{
			throw e;
		}
		if(isFilesExist)
		{
			System.out.println("Your file is here : " + output.getAbsolutePath());
		}
		else
		{
			System.out.println("No Files found to transfer");
		}
	}

}
