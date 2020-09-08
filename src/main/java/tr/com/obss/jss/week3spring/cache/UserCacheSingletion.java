package tr.com.obss.jss.week3spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tr.com.obss.jss.week3spring.model.UserDTO;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component("singletonCache")
public class UserCacheSingletion implements UserCache{
    private static final Logger LOGGLER = LoggerFactory.getLogger(UserCachePrototype.class);

    public Map<String, UserDTO> users;

    @PostConstruct
    public void init() {
        LOGGLER.info("singleton bean oluştu");
        users = new HashMap<>();
    }

    @Override
    public void put(UserDTO user) {
        users.put(user.getUsername(),user);
    }

    @Override
    public Map<String, UserDTO> getMap() {
        return users;
    }
}
