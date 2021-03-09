import java.util.HashMap;
import java.util.Map;

public class Constants {

    final static String MODEL_PATH = "model";


    static Map<String,String[]> getIOMap()
    {
        Map<String,String[]> IOMap = new HashMap<>();
        IOMap.put("input",new String[] {"address"});///以电厂、机组、故障现象作为输入
        IOMap.put("output",new String[] {"A","F","G","H"});///以编号、故障类别、故障原因、解决方法作为输出
        return IOMap;
    }

}
