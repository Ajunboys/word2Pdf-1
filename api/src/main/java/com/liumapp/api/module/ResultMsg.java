package com.liumapp.api.module;

public class ResultMsg {
    private String isSuccess;
    private String pdfPath;
    private String pdf1Path;
    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getPdf1Path() {
        return pdf1Path;
    }

    public void setPdf1Path(String pdf1Path) {
        this.pdf1Path = pdf1Path;
    }
}
