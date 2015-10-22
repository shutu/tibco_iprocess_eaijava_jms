package com.tibco.iprocess.eaijava.jms.domain;

public class ServiceResponse extends AbstractMessage {

  public static final String RETURNCODE_SUCCESS = "S";
  public static final String RETURNCODE_FAILED = "F";

  /** 返回编号(S:调用成功返回成功,F:调用成功返回失败,EXCEPTION:调用异常) */
  private String returnCode;

  /** 返回信息 */
  private String returnMsg;

  /** 返回结果(0:不通过，1:通过) */
  private String returnResult;

  private String retValue1;

  private String retValue2;

  private String retValue3;

  private String retValue4;

  private String retValue5;

  private String retValue6;

  private String retValue7;

  private String retValue8;

  private String retValue9;

  private String retValue10;

  /*-------------------------------------*/
  public String getFormatMessageString() {
    StringBuffer buffer = new StringBuffer();
    addFormatField(buffer, returnCode);
    addFormatField(buffer, returnMsg);
    addFormatField(buffer, returnResult);
    addFormatField(buffer, retValue1);
    addFormatField(buffer, retValue2);
    addFormatField(buffer, retValue3);
    addFormatField(buffer, retValue4);
    addFormatField(buffer, retValue5);
    addFormatField(buffer, retValue6);
    addFormatField(buffer, retValue7);
    addFormatField(buffer, retValue8);
    addFormatField(buffer, retValue9);
    addFormatField(buffer, retValue10);
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
      retValue1 = arr[3];
    }
    if (arr.length > 4) {
      retValue2 = arr[4];
    }
    if (arr.length > 5) {
      retValue3 = arr[5];
    }
    if (arr.length > 6) {
      retValue4 = arr[6];
    }
    if (arr.length > 7) {
      retValue5 = arr[7];
    }
    if (arr.length > 8) {
      retValue6 = arr[8];
    }
    if (arr.length > 9) {
      retValue7 = arr[9];
    }
    if (arr.length > 10) {
      retValue8 = arr[10];
    }
    if (arr.length > 11) {
      retValue9 = arr[11];
    }
    if (arr.length > 12) {
      retValue10 = arr[12];
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

  public String getRetValue1() {
    return retValue1;
  }

  public void setRetValue1(String retValue1) {
    this.retValue1 = retValue1;
  }

  public String getRetValue2() {
    return retValue2;
  }

  public void setRetValue2(String retValue2) {
    this.retValue2 = retValue2;
  }

  public String getRetValue3() {
    return retValue3;
  }

  public void setRetValue3(String retValue3) {
    this.retValue3 = retValue3;
  }

  public String getRetValue4() {
    return retValue4;
  }

  public void setRetValue4(String retValue4) {
    this.retValue4 = retValue4;
  }

  public String getRetValue5() {
    return retValue5;
  }

  public void setRetValue5(String retValue5) {
    this.retValue5 = retValue5;
  }

  public String getRetValue6() {
    return retValue6;
  }

  public void setRetValue6(String retValue6) {
    this.retValue6 = retValue6;
  }

  public String getRetValue7() {
    return retValue7;
  }

  public void setRetValue7(String retValue7) {
    this.retValue7 = retValue7;
  }

  public String getRetValue8() {
    return retValue8;
  }

  public void setRetValue8(String retValue8) {
    this.retValue8 = retValue8;
  }

  public String getRetValue9() {
    return retValue9;
  }

  public void setRetValue9(String retValue9) {
    this.retValue9 = retValue9;
  }

  public String getRetValue10() {
    return retValue10;
  }

  public void setRetValue10(String retValue10) {
    this.retValue10 = retValue10;
  }

}
