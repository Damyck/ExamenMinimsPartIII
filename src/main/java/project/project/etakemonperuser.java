package project.project;

/**
 * Created by Marc on 20/11/2016.
 */
public class etakemonperuser extends DAO {
    int id;
    String nickname;
    String etakemon;
public etakemonperuser(int id, String user, String etakemon){
    this.id = id;
    nickname=user;
    this.etakemon=etakemon;
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

    public String getEtakemon() {
        return etakemon;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }
    //
    public void setEtakemon(String password) {
        this.etakemon = password;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("nickname: ").append(this.nickname).append("\n");
        sb.append("etakemon: ").append(this.getEtakemon()).append("\n");

        return sb.toString();
    }
}
