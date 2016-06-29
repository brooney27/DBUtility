package DBUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtility {
	
	private static Connection con = null;
	private static String classForName = "oracle.jdbc.driver.OracleDriver";
	private static String connectionPath = "jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl";
	
	public static ArrayList<String> getStringWhere(String dbname,String searchcolumn,String search,String returncolumn){
		ArrayList<String> output=new ArrayList<String>();
		try{
			ResultSet rs = null;
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			String query = "select "+returncolumn+" from "+dbname+" where "+searchcolumn+"="+search+" order by rownum";
			Statement sql = con.createStatement();
			rs=sql.executeQuery(query);
			while(rs.next()){
				output.add(rs.getString(1));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}
	public static ArrayList<String> getString(String dbname,String returncolumn){
		ArrayList<String> output=new ArrayList<String>();
		try{
			ResultSet rs = null;
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			String query = "select "+returncolumn+" from "+dbname;
			Statement sql = con.createStatement();
			rs=sql.executeQuery(query);
			while(rs.next()){
				output.add(rs.getString(1));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}
	public static ArrayList<Integer> getJoinedValueByID(String dbname1,String dbname2,String commonid,String searchcolumn,String searchvalue, String returncolumn){
		ArrayList<Integer> output=new ArrayList<Integer>();
		try{
			ResultSet rs = null;
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			String query = "select "+returncolumn+" from "+dbname1+" inner join "+dbname2+" on "+dbname1+"."+commonid+"="+dbname2+"."+commonid+"where"+searchcolumn+"="+searchvalue;
			Statement sql = con.createStatement();
			rs=sql.executeQuery(query);
			while(rs.next()){
				output.add(rs.getInt(1));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}
	public static void insertIntoDB(String dbname,String[] columnNames,String[] values,boolean[] isString){
		try{
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			String update = "insert into "+dbname+" (";
			for(int i=0;i<columnNames.length;i++){
				if(i!=0)update+=",";
				update+=columnNames[i];
			}
			update+=") values (";
			for(int i=0;i<columnNames.length;i++){
				if(i!=0)update+=",";
				if(isString[i])update+="'";
				update+=values[i];
				if(isString[i])update+="'";
			}
			update+=")";
			System.out.println(update);
			Statement sql = con.createStatement();
			sql.executeUpdate(update);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static String printQuery(String query){
		String output="";
		try{
			ResultSet rs = null;
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			Statement sql = con.createStatement();
			rs=sql.executeQuery(query);
			String dash = "----\t";
			String dashes = "";
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			for(int i = 0; i < numColumns; i++){
				output+=rsmd.getColumnName(i+1)+"\t";
				dashes+=dash;
			}
			output+="\n"+dashes;
			while(rs.next()){
				output+="\n";
				for(int i = 1; i <= numColumns;i++){
					boolean isString = rsmd.getColumnTypeName(i).equals("VARCHAR2");
					if(isString)output+=rs.getString(i);
					else output+=rs.getDouble(i);
					output+="\t";
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}
	public static String printQueryHead(String query,int lines){
		String output="";
		try{
			ResultSet rs = null;
			Class.forName(classForName);
			con = DriverManager.getConnection(connectionPath);
			Statement sql = con.createStatement();
			rs=sql.executeQuery(query);
			String dash = "----\t";
			String dashes = "";
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			for(int i = 0; i < numColumns; i++){
				output+=rsmd.getColumnName(i+1)+"\t";
				dashes+=dash;
			}
			output+="\n"+dashes;
			while(rs.next()&&lines>0){
				output+="\n";
				lines--;
				for(int i = 1; i <= numColumns;i++){
					boolean isString = rsmd.getColumnTypeName(i).equals("VARCHAR2");
					if(isString)output+=rs.getString(i);
					else output+=rs.getDouble(i);
					output+="\t";
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}
	
}
