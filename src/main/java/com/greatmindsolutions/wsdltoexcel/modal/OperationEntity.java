package com.greatmindsolutions.wsdltoexcel.modal;

public class OperationEntity {

	private String fileName;
	private String operation;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String wsdlName) {
		this.fileName = wsdlName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.fileName + "  " + this.operation;
	}
}
