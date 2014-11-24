package net.smalinuxer.spillover.file;

import java.util.List;

/**
 * Created by David on 2014/11/1.
 */
public interface ConstPoolCalc {

    public long[] calcLength(Cache.Entry entry);

    public boolean isEmpty(Cache.Entry entry);

    public long[] parseIndexPool(byte[] indexPool);

    public List<byte[]> parseConstPool(long[] indexs,byte[] constPool);



}
