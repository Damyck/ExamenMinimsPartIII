package project.project;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 20/11/2016.
 */
public abstract class  DAO {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/etakemongo", "root", "espacioJ714");
            System.out.println("Conexion creada!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }


    //insertar dato en base de datos
    public void insert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Connection con = getConnection(); //OBTENGO CONEXION DE LA BASE DE DATOS
        //  releaseConnection(con);

        //CONSTRUYO LA CONSULTA
        StringBuffer consulta = new StringBuffer();
        consulta.append("insert into ").append(this.getClass().getSimpleName()).append(" ("); //insert into TABLA/CLASE(

        Field[] fields = this.getClass().getDeclaredFields(); //campos--> obtener campos declarados en esta clase:name, address, id

        int numfields = 0;
        for (Field f : fields) {
            System.out.println(f.getName()); //los printo para ver que sean ellos
            if (numfields == fields.length - 1) { //para que no se printe la ultima coma
                consulta.append(f.getName());
            } else {
                consulta.append(f.getName()).append(",");
            }
            numfields++;
        }
        consulta.append(") VALUES ("); //consutla: insert into TABLA/CLASE (campo1,campo2,campo3...) VALUES (

        int numfields2 = 0;
        for (Field f : fields) {
            if (numfields2 == fields.length - 1) { //para que no se printe la ultima coma
                consulta.append("?");
            } else {
                consulta.append("?,");  //consutla: insert into TABLA/CLASE (campo1,campo2,campo3...) VALUES (?,?,?
            }
            numfields2++;
        }

        consulta.append(") "); //consutla: insert into TABLA/CLASE (campo1,campo2,campo3...) VALUES (?,?,?)
        System.out.println(consulta.toString()); //printo toda la consulta

        //EJECUTO LA CONSULTA, llamando a la conexion y pasandole la consulta
        try {
            PreparedStatement ptstm = con.prepareStatement(consulta.toString());
            //ptstm.setObject(1, );
            addParams2Stm(ptstm); //con esto le estoy diciendo que a los ?, les ponga el valor que corresponda
            ptstm.execute();        //con la funcion addParams2Stm y ejecutandola.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addParams2Stm(PreparedStatement ptstm) {
        Field[] fields = this.getClass().getDeclaredFields();
        int i = 1;
        for (Field f : fields) {

            String res = getValue(f);
            System.out.println("stm.setObject(" + i + ", " + res + ")");

            try {
                ptstm.setObject(i, res);
            } catch (SQLException e) {
                e.printStackTrace();

            }

            i++;
        }

    }

    private String getValue(Field f) {
        String ret = null;
        Method method = null;
        try {
            method = this.getClass().getMethod(getMethod(f.getName()));
            ret = (String) method.invoke(this, null);
            // if (instance of Date) Date d = (Date) res;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }

    // name, addres, id == >  getName getDescription getId
    private String getMethod(String m) {
        String M = mayuscula(m); //transformo id en Id, description en Description...primera letra en mayuscula
        String devolvermetodo = ("get" + M);
        System.out.println(devolvermetodo);
        return devolvermetodo;
    }

    private String mayuscula(String m) { //transforma la primera letra de una palabra en mayuscula
        String mayus = m.charAt(0) + "";
        mayus = mayus.toUpperCase();
        String M = m.replaceFirst(m.charAt(0) + "", mayus);
        return M;
    }


    public List<String> select(String pk, String what, String where) {
        Connection con = getConnection(); //OBTENGO CONEXION DE LA BASE DE DATOS

        //CONSTRUYO CONSULTA
        StringBuffer consulta = new StringBuffer();
        consulta.append("SELECT" + what + " FROM ").append(this.getClass().getSimpleName()).append(" WHERE" + where + "=" + pk);
        System.out.println(consulta);
        Statement stmt = null;
        List<String> table = new ArrayList<String>();
        try {
            //EJECUTO CONSULTA
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta.toString()); //Y RECOJO LOS DATOS EN rs
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();

            for (int i = 1; i < rsmd.getColumnCount() + 1; i++) { //lo ejecuto el numero de veces de columnas que tenga en la tabla
                try {
                    if (rsmd.getColumnTypeName(i).equals("INT")) {//para la columna i,si es del tipo int
                        System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getInt(i)); //obtengo la etiqueta de la columna y el entero (id=1...)
                    }
                    if (rsmd.getColumnTypeName(i).equals("VARCHAR")) { //si es del tipovarchar, obtengo lo que es tambien
                        System.out.println(rsmd.getColumnLabel(i) + " = " + rs.getString(i));
                        table.add(rs.getString(i));
                    }
                    if (i == rsmd.getColumnCount()) { //cuando i=numero de columnas, voy al siguiente y salgo del bucle,reiniciando i
                        rs.next();
                        i = 0;
                    }
                } catch (Exception e) {
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return table;
    }



    public void delete(int pk){ //eliminar dato de la base de datos
        Connection con = getConnection(); //OBTENGO CONEXION DE LA BASE DE DATOS
        //CONSTRUYO CONSULTA
        StringBuffer consulta = new StringBuffer();
        consulta.append("DELETE FROM ").append(this.getClass().getSimpleName()).append(" WHERE ID="+pk); //delete from tabla/clase
        System.out.println(consulta.toString());
        Statement stmt= null; //creo statement y lo inicializo a nulo
        //EJECUTO CONSULTA
        try {
            stmt=con.createStatement(); //le digo que es la conexion.
            stmt.execute(consulta.toString()); //ejecuto la consulta a la conexion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int pk){
        Connection con = getConnection(); //OBTENGO CONEXION DE LA BASE DE DATOS
        //CONSTRUYO CONSULTA
        StringBuffer consulta = new StringBuffer();
        consulta.append("UPDATE ").append(this.getClass().getSimpleName()).append(" SET ");

        Field[] fields = this.getClass().getDeclaredFields(); //campos--> obtener campos declarados en esta clase:name, address, id

        int numfields=0;
        for (Field f : fields) {
            if (f.getGenericType().toString().equals("int")){ //este if y else los hago pq si es un int no se pone ''
                consulta.append(f.getName()+"=?,");             //pero si es un string si.
            }
            else{   //dentro del else, diferencio si está en la ultima posicion para la coma (el id nunca estará en último lugar.
                if(numfields==fields.length-1) {
                    consulta.append(f.getName() + "=?");
                }
                else{
                    consulta.append(f.getName() + "=?,");
                }
            }
            numfields++;
        }
        consulta.append(" WHERE id="+pk);
        System.out.println(consulta.toString());

        //EJECUTO LA CONSULTA, llamando a la conexion y pasandole la consulta
        try {
            PreparedStatement ptstm = con.prepareStatement(consulta.toString());
            //ptstm.setObject(1, );
            addParams2Stm(ptstm); //con esto le estoy diciendo que a los ?, les ponga el valor que corresponda
            System.out.println(consulta.toString());
            ptstm.execute();        //con la funcion addParams2Stm y ejecutandola.

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

