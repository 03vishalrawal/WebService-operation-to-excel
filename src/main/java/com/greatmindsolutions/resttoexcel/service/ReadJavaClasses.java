package com.greatmindsolutions.resttoexcel.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.greatmindsolutions.wsdltoexcel.modal.OperationEntity;
import com.greatmindsolutions.wsdltoexcel.service.ExcelWriterService;

public class ReadJavaClasses {

	public static void main(String args[]) throws FileNotFoundException {

		File file = new File(
				"Q:/Vishal/Work/CodeBase/mcp-core_develop/service/mcp-ui-service/src/main/java/com/mastercard/cpm/mcp/ui/service");
		File[] files = file.listFiles();
		readFolderAndFindServiceJavaClass(files);
	}

	public static void readFolderAndFindServiceJavaClass(File[] files) {
		for (File f : files) {
			if (f.isDirectory()) {
				readFolderAndFindServiceJavaClass(f.listFiles());
			} else {
				if (f.getName().contains("Service.java")) {
					//System.out.println("File Name------------> " + f.getAbsolutePath());
					try {
						readRestPath(f.getAbsolutePath(), f.getName());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public static List<OperationEntity> entityList = new ArrayList<OperationEntity>();
	
	public static void readRestPath(String filePath, String fileName) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		int i =0;
		String first = null;
		
		while (sc.hasNextLine()) {
			
			String line = sc.nextLine().trim(); 
			if (true == line.contains("@Path")){
				line = parse(line);
				if(i==0){
					first = line;
					line = "";
					i++;
				}else {
					OperationEntity entity = new OperationEntity();
					entity.setFileName(fileName);
					entity.setOperation(first+line);
					entityList.add(entity);
				}
			}
		}
		try {
			ExcelWriterService.writeExcel(entityList, "RestAssessment.xlsx", "RESTAssessment");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}
	
	public static String parse(String line){
		String[] split = line.split("\"");
		String toReturn = split[1];
		if(!toReturn.startsWith("/"))
			toReturn= "/"+toReturn;
		return toReturn;
	}
}
