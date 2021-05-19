package myfile;

import file.BigFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class FileReader {
    private int threadPoolSize;
    private Charset charset;
    private int bufferSize;
    private IFileHandle handle;
    private ExecutorService executorService;
    private long fileLength;
    private RandomAccessFile randomAccessFile;
    private Set<StartEndPair> startEndPairs;
    private CyclicBarrier cyclicBarrier;
    private AtomicLong counter = new AtomicLong();

    public FileReader(File file, IFileHandle handle, Charset charset, int bufferSize, int threadPoolSize){
        this.fileLength = file.length();
        this.handle = handle;
        this.charset = charset;
        this.bufferSize = bufferSize;
        this.threadPoolSize = threadPoolSize;
        try {
            this.randomAccessFile = new RandomAccessFile(file,"r");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("文件不存在！");
        }
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        startEndPairs = new HashSet<StartEndPair>();
    }



    private static class StartEndPair {
        public long start;
        public long end;

        @Override
        public String toString() {
            return "star=" + start + ";end=" + end;
        }
    }





}
