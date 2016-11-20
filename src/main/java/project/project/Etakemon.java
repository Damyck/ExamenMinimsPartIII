package project.project;

/**
 * Created by Marc on 18/11/2016.
 */
public class Etakemon extends DAO{
    int id;
    String name;
    String type;


    public Etakemon(int id, String name, String type){
        this.id = id;
        this.name=name;
        this.type=type;
    }
    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }
    //
    public void setType(String password) {
        this.type = password;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("name: ").append(this.name).append("\n");
        sb.append("type: ").append(this.getType()).append("\n");

        return sb.toString();
    }

}
