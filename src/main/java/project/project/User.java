
package project.project;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 18/11/2016.
 */
public class User extends DAO  {
    int id;
    String nickname;
    String password;



    public User(int id, String name, String password){
        this.id = id;
        this.nickname = name;
        this.password = password;

    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }
    //
    public void setPassword(String password) {
        this.password = password;
    }

        public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("nickname: ").append(this.nickname).append("\n");
        sb.append("password: ").append(this.getPassword()).append("\n");

        return sb.toString();
    }



}
