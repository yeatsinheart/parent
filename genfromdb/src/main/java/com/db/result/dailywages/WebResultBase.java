package com.db.result.dailywages;

public class WebResultBase<T> {

  private int result;
  private String msg;
  private String sign;
  private T root;

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public T getRoot() {
    return root;
  }

  public void setRoot(T root) {
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

    public static final int PROCEEDING = 2;
    public static final int PROCEEDED = 3;
    public static final int REJECT = 4;
    public static final int IDLE = 5;
    public static final int DEPRECATED = 6;

    public static final int PERMISSION_ERROR = -1;
    public static final int INVALID_SHOP_ADMIN = -2;
    public static final int INVALID_PASSWORD = -3;
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

  public static class UserRegMessage {
    public static final String GOING = "用户添加成功";
    public static final String ERROR = "用户已存在";
  }
}
