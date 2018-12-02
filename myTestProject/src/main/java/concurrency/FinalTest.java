package concurrency;

import java.util.*;

public class FinalTest {

    public static void main(String[] args) {

        final Set<String> storages = new HashSet<String>();

        storages.add("hehhe");


        Map map = new HashMap();
        List list = new ArrayList();
        Collections.synchronizedList(list);
    }

}
