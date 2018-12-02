package thread.maptest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String args[]){
        Map<String, String> map = new HashMap();

        Map<String, String> unmodifiable = Collections.unmodifiableMap(map);

        //unmodifiable = new HashMap<>();
        //unmodifiable.put("string", "string");


        map.put("1", "hashmap");
    }
}
