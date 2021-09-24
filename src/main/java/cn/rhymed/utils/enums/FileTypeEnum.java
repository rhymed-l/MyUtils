package cn.rhymed.utils.enums;

public enum FileTypeEnum
{
    //images
    JPEG("FFD8FF","JPEG","jpg"),
    PNG("89504E47","PNG","png"),
    GIF ("47494638","GIF","gif"),
    BMP("424D","Windows Bitmap","bmp"),
    TIFF("49492A00","TIFF","tif"),

    CAD("41433130","CAD","dwg"),
    //网页
    HTML("3c21444f435459504520","HTML","html"),
    HTM("3c21646f637479706520","HTM","htm"),
    CSS("48544d4c207b0d0a0942","CSS","css"),
    JS("696b2e71623d696b2e71","JS","js"),
    RTF("7B5C727466","Rich Text Format","rtf"),
    XML("3C3F786D6C","XML","xml"),
    //压缩文件
    ZIP("504B0304","ZIP","zip"),
    RAR("52617221","RAR","rar"),

    PSD("38425053","PSD","psd"),
    Email("44656C69766572792D646174653A","Email","eml"),
    //音影文件
    MP3("49443303000000002176","MP3","mp3"),
    MP4("0000001c667479706d70","MP4","mp4"),
    RMVB("2e524d46000000120001","MP4","rmvb"),//rmvb/rm相同
    FLV("464c5601050000000900","FLV","flv"),//flv与f4v相同
    MPG("000001ba210001000180","MPG","mpg"),
    WMV("3026b2758e66cf11a6d9","WAVE","wmv"),//wmv与asf相同
    WAVE("52494646e27807005741","WAVE","wav"),//Wave (wav)
    AVI("52494646d07d60074156","WAVE","avi"),
    //,
    INI("235468697320636f6e66","INI","ini"),
    JAR("504b03040a0000000000","JAR","jar"),
    EXE("4d5a9000030000000400","EXE","exe"),
    JSP("3c25402070616765206c","JSP","jsp"),
    mf("4d616e69666573742d56","MF","mf"),
    SQL("494e5345525420494e54","SQL","sql"),
    MIDI("4D546864","MIDI","mid");

    private String hex;
    private String type;
    private String postfix;

    FileTypeEnum(String hex, String type,String postfix) {
        this.hex = hex;
        this.type = type;
        this.postfix = postfix;
    }

    public static String getTypeByHex(String hex) {
        FileTypeEnum[] values = FileTypeEnum.values();
        for (FileTypeEnum e : values) {
            if (e.hex.equalsIgnoreCase(hex.substring(0,e.hex.length()))) {
                return e.type;
            }
        }
        return "";
    }

    public static String getPostfixByHex(String hex) {
        FileTypeEnum[] values = FileTypeEnum.values();
        for (FileTypeEnum e : values) {
            if (e.hex.equalsIgnoreCase(hex.substring(0,e.hex.length()))) {
                return e.postfix;
            }
        }
        return "";
    }
}
