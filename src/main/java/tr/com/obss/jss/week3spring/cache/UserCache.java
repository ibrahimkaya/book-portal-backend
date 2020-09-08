package tr.com.obss.jss.week3spring.cache;

import tr.com.obss.jss.week3spring.model.UserDTO;

import java.util.Map;

public interface UserCache {
    void put(UserDTO user);
    Map<String, UserDTO> getMap();
}
