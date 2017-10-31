package com.ecochain.config;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultDto<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4617395735314977198L;

    /**
     * 200-成功
     */
    public final static String CODE_SUCCESS = "200";
    /**
     * 500-失败
     */
    public final static String CODE_FAILED = "500";

    private String code;

    private String message;

    private int curPage; //当前页数

    private int totalPage; //共计页数

    private int curCount; //当前记录数

    private long totalCount;//总记录数

    private T data;


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public ResultDto() {

    }

    public ResultDto(T data) {
        this.data = data;
    }

    public ResultDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultDto(String code, String message, int curPage, int totalPage, int curCount, T data, long totalCount) {
        this.code = code;
        this.message = message;
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.curCount = curCount;
        this.data = data;
        this.totalCount = totalCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCurCount() {
        return curCount;
    }

    public void setCurCount(int curCount) {
        this.curCount = curCount;
    }

    public static <T extends Object> ResultDto<T> addSuccess(Class<T> clazz) {
        return new ResultDto<T>(CODE_SUCCESS, null);
    }

    public static ResultDto<String> addSuccess(String message) {
        return new ResultDto<String>(CODE_SUCCESS, message);
    }

    public static ResultDto<String> addAddSuccess() {
        return new ResultDto<String>(CODE_SUCCESS, "新增成功");
    }

    public static ResultDto<String> addUpdateSuccess() {
        return new ResultDto<String>(CODE_SUCCESS, "更新成功");
    }

    public static ResultDto<String> addUpdateCheckSuccess() {
        return new ResultDto<String>(CODE_SUCCESS, "确定成功");
    }

    public static ResultDto<String> addDeleteSuccess() {
        return new ResultDto<String>(CODE_SUCCESS, "删除成功");
    }

    public static ResultDto<String> addOperationSuccess() {
        return new ResultDto<String>(CODE_SUCCESS, "操作成功");
    }

    public static ResultDto<String> processError() {
        return new ResultDto<>("500", "后端服务处理失败");
    }

    public static ResultDto<String> processError(String message) {
        return new ResultDto<>("500", message);
    }

}
