package project.project;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Marc on 18/11/2016.
 */
public interface UserCommands {

    public void insertUser(String name, String password) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    public void modUser(String name, String password);
    public User showUser(String name);
    public <Etakemon> List<Etakemon> showuserEtakemon(String name);
    public void addEtakemon(String user, Etakemon etakemon) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
