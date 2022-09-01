package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0,0,0);
        int countDel = 0;
        int countCh = 0;
        int countAdd = 0;
        HashMap<Integer, User> mapPrevious = new HashMap<>();
        for (User user : previous) {
            mapPrevious.put(user.getId(), user);
        }
        HashMap<Integer, User> mapCurrent = new HashMap<>();
        for (User user : current) {
            mapCurrent.put(user.getId(), user);
        }
        for (Map.Entry<Integer, User> entry: mapPrevious.entrySet()) {
            Integer key = entry.getKey();
            User prevUser = entry.getValue();
            User curUser = mapCurrent.get(key);
            if (curUser == null) {
                countDel++;
            } else if (!curUser.getName().equals(prevUser.getName())) {
                countCh++;
            }
        }
        for (Integer key : mapCurrent.keySet()) {
            if (mapPrevious.get(key) == null) {
                countAdd++;
            }
        }
        info.setDeleted(countDel);
        info.setAdded(countAdd);
        info.setChanged(countCh);
        return info;
    }
}
