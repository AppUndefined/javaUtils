import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTypeTest {
    public static void main(String[] args) throws EvaluationException {


//        // 待处理字符串
//        String wpp = "jdbc:mysql://${wpp1}:${wpp2}/${wpp3}?&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull";
//        //\u0024\u007B\u0028\u002E\u002A\u003F\u0029}
//        // 匹配方式
//        Pattern p = Pattern.compile("\\$\\{(.*?)}");
//        // 匹配】
//        Matcher matcher = p.matcher(wpp);
//        // 处理匹配到的值
//        while (matcher.find()) {
//            System.out.println("啊啊啊啊: " + matcher.group().substring(2,matcher.group().length()-1));
//        }



        double degrees = 180.0;
        double radians = Math.toRadians(degrees);

        String exp = " tan("+radians+") ";

        // compile
        Evaluator jevalEvaluator = new Evaluator();
//        jevalEvaluator.setVariables(Collections.singletonMap("x", Double.toString(12)));
        // evaluate
        double result = Double.parseDouble(jevalEvaluator.evaluate(exp));

        System.out.println(result);//-> 2.0
        }
}
