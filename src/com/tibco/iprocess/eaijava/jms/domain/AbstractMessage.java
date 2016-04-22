package com.tibco.iprocess.eaijava.jms.domain;

public abstract class AbstractMessage implements ServiceMessage {

	protected void addFormatField(StringBuffer buffer, String value, boolean isFirst) {
		if (!isFirst) {
			buffer.append(SPLITTER);
		}
		buffer.append(value == null ? "" : value);
	}

}
