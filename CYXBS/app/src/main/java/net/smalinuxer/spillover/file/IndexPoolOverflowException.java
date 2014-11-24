package net.smalinuxer.spillover.file;

/**
 * Created by David on 2014/11/1.
 */

public class IndexPoolOverflowException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IndexPoolOverflowException(){
        super("IndexPoolOverflow");
    }

    public IndexPoolOverflowException(String str){
        super(str);
    }
}
