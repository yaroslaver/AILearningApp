package Model;

public class ConstCollection {
    //final image size
    public static final int IMAGE_WIDTH        = 150;
    public static final int IMAGE_HEIGHT       = 100;

    //bounds of control size
    public static final int MIN_OBJECT_WIDTH   = 70;
    public static final int MAX_OBJECT_WIDTH   = 130;
    public static final int MIN_OBJECT_HEIGHT  = 30;
    public static final int MAX_OBJECT_HEIGHT  = 40;

    //default path to save images
    public static final String PATH            = "C://Dataset/";

    //bounds of noise size
    public static final int MIN_NOISE_WIDTH    = 60;
    public static final int MAX_NOISE_WIDTH    = 90;
    public static final int MIN_NOISE_HEIGHT   = 30;
    public static final int MAX_NOISE_HEIGHT   = 60;

    //number of threads in multithreading realization
    public static final int THREAD_COUNT       = 6;

    /* Next stage of development - recognition of different controls included in one form
    public static final int FORM_WIDTH         = 0;
    public static final int FORM_HEIGHT        = 0;
    */
}
