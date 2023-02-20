import java.lang.annotation.Documented;

@Documented
@interface Credit {
    String title();
    String subTitle();
    String creator();
    String javaVersion();
    String jdkVersion();
    String ideInDev();
    String[] jarOutputTested();
}
