package com.greatmindsolutions.wsdltoexcel.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.wsdl.Operation;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.greatmindsolutions.wsdltoexcel.modal.OperationEntity;

public class RunService {

	public static void main(String[] args) {
		/*if (args.length == 1 && !(args[1].equalsIgnoreCase(FilenameUtils.getExtension("xlsx"))
				|| args[1].equalsIgnoreCase(FilenameUtils.getExtension("xls")))) {
			System.out.println("File path is not matching");
			return;
		}*/
		File file = new File(args[0]);
		File[] files = file.listFiles();
		List<OperationEntity> enitityList = new ArrayList<>();
		System.out.println("Creating file....");
		System.out.println("");
		for (File f : files) {
			if (FilenameUtils.getExtension(f.getName()).equals("wsdl")) {
				List<Operation> list = ReadWSDLPortTypeService.getPortTypeOperations(f.getAbsolutePath());
				for (Operation operation : list) {
					OperationEntity enitiy = new OperationEntity();
					enitiy.setOperation(operation.getName());
					enitiy.setFileName(f.getName());
					enitityList.add(enitiy);
				}

			}
		}
		try {
			ExcelWriterService.writeExcel(enitityList, args[1], "WSDLAssesment");
			System.out.println("File created successfully.");
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

	}
}
