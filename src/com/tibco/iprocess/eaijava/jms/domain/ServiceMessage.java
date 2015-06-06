package com.tibco.iprocess.eaijava.jms.domain;

public interface ServiceMessage {

	public static final String SPLITTER = "#!";

	public String getFormatMessageString();

	public void initMessageFromFormatString(String s);
}
