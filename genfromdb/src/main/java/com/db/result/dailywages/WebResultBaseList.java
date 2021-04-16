package com.db.result.dailywages;

import java.util.List;

public class WebResultBaseList<T> {

  private int result;
  private String msg;
  private T[] root;

  public T[] getRoot() {
    return root;
  }

  public void setRoot(List<T> root) {
    this.root = (T[]) root.toArray();
  }
  
  public void setRoot(T[] root) {
    this.root = root;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public static class StatusCode {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int PERMISSION_ERROR = -1;
  }
  public static class ProcessCode {
    public static final int COMPLETE = 0;
    public static final int GOING = 1;
    public static final int ERROR = -1;
  }
  public static class ProcessMessage {
    public static final String COMPLETE = "处理完成";
    public static final String GOING = "处理进行中";
    public static final String ERROR = "处理异常";
  }

  public static class StatusMessage {
    public static final String OK = "ok";
    public static final String FAIL = "fail";
  }
}
