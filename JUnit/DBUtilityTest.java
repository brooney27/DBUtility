import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import DBUtility.DBUtility;
public class DBUtilityTest {

	@Test
	public void testGetString() {
		String dbname="testtable";
		String returncolumn="string1";
		
		ArrayList<String> output =DBUtility.getString(dbname, returncolumn);
		
		assertTrue(output.get(0).equals("fluffy"));
	}
	
	@Test
	public void testGetWhere(){
		String dbname="testtable";
		String searchcolumn="int1";
		String search="11";
		String returncolumn="string1";
		
		ArrayList<String> output =DBUtility.getStringWhere(dbname,searchcolumn,search,returncolumn);
		
		assertTrue(output.get(0).equals("mean"));
	}
	
	@Test
	public void testInsert(){
		String dbname="testtable";
		String[] columns={"int1","string1","string2"};
		String[] values={"5","chocolate","bunny"};
		boolean[] isString={false,true,true};
		
		DBUtility.insertIntoDB(dbname,columns,values,isString);
		
		ArrayList<String> output =DBUtility.getStringWhere(dbname,"int1","5","string1");
		
		assertTrue(output.get(0).equals("chocolate"));
	}
}
