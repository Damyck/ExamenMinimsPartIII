package project.project;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * Created by Marc on 18/11/2016.
 */
public class Singleton implements UserCommands {

    private static Singleton instance=null;
    private HashMap<Integer, User> users;
    private List<Etakemon> etaklist;
    private int idetus=0;
    private int idus=0;
    private Singleton(){

        users = new HashMap<Integer, User>();
        etaklist = new ArrayList<Etakemon>();

    }

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }



    public void insertUser(String name, String password) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        User user = new User(idus, name, password);
        user.insert();
        idus++;

    }


    public void modUser(String name, String password) {
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
             if (name == entry.getValue().nickname) {
                 User us = users.get(entry.getKey());
                 us.nickname = name;
                 us.password=password;
                 users.put(entry.getKey(), us);

             }

        }
    }


    public User showUser(String name) {
        User us=null;
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (name == entry.getValue().nickname) {
                us = entry.getValue();
            }

        }
        return us;
    }


    public  List<Etakemon> showuserEtakemon(String name) {
        etakemonperuser etakus = null;
        List<String> etakemon = etakus.select(name, "etakemon", "nickname");
        Iterator<String> it = etakemon.iterator();
        Etakemon etki = null;
        List<Etakemon> fin = new ArrayList<Etakemon>();
        int cont=0;

        while (it.hasNext()) {

            String etak = it.next();
            List<String> type = etki.select(etak, "type", "name");
            fin.add(new Etakemon(cont, etak, type.get(0)));

        }

        return  fin;
        //etaklist.get(1);


    }


    public void addEtakemon(String name, Etakemon etakemon) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        etakemon.insert();
        String etname = etakemon.name;
        etakemonperuser etus = new etakemonperuser(idetus, name, etname);
        etus.insert();
        idetus++;

        }
    }


