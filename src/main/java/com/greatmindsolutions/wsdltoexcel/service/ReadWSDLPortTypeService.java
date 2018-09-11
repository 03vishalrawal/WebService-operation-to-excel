package com.greatmindsolutions.wsdltoexcel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.WSDLException;
import javax.wsdl.xml.WSDLReader;

import com.ibm.wsdl.PortTypeImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;

public class ReadWSDLPortTypeService {

	@SuppressWarnings("unchecked")
	public static List<Operation> getPortTypeOperations(String wsdlUrl) {
		List<Operation> operationList = new ArrayList<Operation>();
		try {
			WSDLReader reader = new WSDLReaderImpl();
			reader.setFeature("javax.wsdl.verbose", false);
			Definition definition = reader.readWSDL(wsdlUrl.toString());
			Map<String, PortTypeImpl> defMap = definition.getAllPortTypes();
			Collection<PortTypeImpl> collection = defMap.values();
			for (PortTypeImpl portType : collection) {
				operationList.addAll(portType.getOperations());
			}
		} catch (WSDLException e) {
			System.out.println("get wsdl operation fail.");
			e.printStackTrace();
		}
		return operationList;
	}
}
