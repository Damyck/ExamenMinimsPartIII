package project.project;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marc on 19/11/2016.
 */
public class MyTests {

    @Test
    public void tryingoutadduser() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Singleton.getInstance().insertUser("pepe","ruas");
        Singleton.getInstance().insertUser("JUAN","ruas");
        Etakemon porti = new Etakemon(0, "porti", "guay");
        Singleton.getInstance().addEtakemon("pepe", porti);
        Etakemon lu = new Etakemon(1, "lu", "guay");
        Singleton.getInstance().addEtakemon("pepe", lu);
        Singleton.getInstance().showuserEtakemon("pepe");
    }

}
