package com.tibco.iprocess.eaijava.jms.domain;

public class ServiceResponse extends AbstractMessage {

	/** 返回编号(S:调用成功返回成功,F:调用成功返回失败,EXCEPTION:调用异常) */
	private String returnCode;

	/** 返回信息 */
	private String returnMsg;

	/** 返回结果(0:不通过，1:通过) */
	private String returnResult;

	private String extValue1;

	private String extValue2;

	private String extValue3;

	private String extValue4;

	private String extValue5;

	private String extValue6;

	private String extValue7;

	private String extValue8;

	private String extValue9;

	private String extValue10;

	/*-------------------------------------*/
	public String getFormatMessageString() {
		StringBuffer buffer = new StringBuffer();
		addFormatField(buffer, returnCode);
		addFormatField(buffer, returnMsg);
		addFormatField(buffer, returnResult);
		addFormatField(buffer, extValue1);
		addFormatField(buffer, extValue2);
		addFormatField(buffer, extValue3);
		addFormatField(buffer, extValue4);
		addFormatField(buffer, extValue5);
		addFormatField(buffer, extValue6);
		addFormatField(buffer, extValue7);
		addFormatField(buffer, extValue8);
		addFormatField(buffer, extValue9);
		addFormatField(buffer, extValue10);
		return buffer.toString();
	}

	public void initMessageFromFormatString(String s) {
		String[] arr = s.split(SPLITTER);
		if (arr.length > 0) {
			returnCode = arr[0];
		}
		if (arr.length > 1) {
			returnMsg = arr[1];
		}
		if (arr.length > 2) {
			returnResult = arr[2];
		}
		if (arr.length > 3) {
			extValue1 = arr[3];
		}
		if (arr.length > 4) {
			extValue2 = arr[4];
		}
		if (arr.length > 5) {
			extValue3 = arr[5];
		}
		if (arr.length > 6) {
			extValue4 = arr[6];
		}
		if (arr.length > 7) {
			extValue5 = arr[7];
		}
		if (arr.length > 8) {
			extValue6 = arr[8];
		}
		if (arr.length > 9) {
			extValue7 = arr[9];
		}
		if (arr.length > 10) {
			extValue8 = arr[10];
		}
		if (arr.length > 11) {
			extValue9 = arr[11];
		}
		if (arr.length > 12) {
			extValue10 = arr[12];
		}
	}

	/*-------------------------------------*/
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public String getExtValue1() {
		return extValue1;
	}

	public void setExtValue1(String extValue1) {
		this.extValue1 = extValue1;
	}

	public String getExtValue2() {
		return extValue2;
	}

	public void setExtValue2(String extValue2) {
		this.extValue2 = extValue2;
	}

	public String getExtValue3() {
		return extValue3;
	}

	public void setExtValue3(String extValue3) {
		this.extValue3 = extValue3;
	}

	public String getExtValue4() {
		return extValue4;
	}

	public void setExtValue4(String extValue4) {
		this.extValue4 = extValue4;
	}

	public String getExtValue5() {
		return extValue5;
	}

	public void setExtValue5(String extValue5) {
		this.extValue5 = extValue5;
	}

	public String getExtValue6() {
		return extValue6;
	}

	public void setExtValue6(String extValue6) {
		this.extValue6 = extValue6;
	}

	public String getExtValue7() {
		return extValue7;
	}

	public void setExtValue7(String extValue7) {
		this.extValue7 = extValue7;
	}

	public String getExtValue8() {
		return extValue8;
	}

	public void setExtValue8(String extValue8) {
		this.extValue8 = extValue8;
	}

	public String getExtValue9() {
		return extValue9;
	}

	public void setExtValue9(String extValue9) {
		this.extValue9 = extValue9;
	}

	public String getExtValue10() {
		return extValue10;
	}

	public void setExtValue10(String extValue10) {
		this.extValue10 = extValue10;
	}

}
