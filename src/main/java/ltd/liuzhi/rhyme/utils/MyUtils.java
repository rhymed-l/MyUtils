package ltd.liuzhi.rhyme.utils;

public class MyUtils
{
    private MyUtils(){}

    public static void notNull(Object obj, String str)
    {
        if(obj == null)
        {
            throw new RuntimeException(str);
        }
    }
}
