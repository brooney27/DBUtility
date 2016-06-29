import static org.junit.Assert.*;
import DBUtility.DBUtility;
import org.junit.Test;

public class DBUtilityPrintTest {

	@Test
	public void testPrint(){
		String query = "select * from testtable";
		System.out.println(DBUtility.printQuery(query));
	}
}
