package net.smalinuxer.spillover.frameDesign;

public interface ResponseHandler {

    public void callErrorBack(Request<?> request);

    public void callBack(Request<?> request, Response response);
}
