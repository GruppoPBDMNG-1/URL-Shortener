package URLShortener.Test;

import URLShortener.DAO.RedisConnection;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedisConnection_Test extends TestCase{

	String [] a  ={"www.google.it","www.amazon.com","www.vincenzoMOlfettaInf.it"};
	boolean [] b  ={false,true,true};
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


//
//	@Test
//	public void testSetKeyData() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetValue() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testIsExists() {

		for (int i = 0; i < a.length; i++) {
			boolean c = RedisConnection.getIstance().isExists(a[i]);

			assertTrue(c == b[i]);
		}
	}

//	@Test
//	public void testGetValue() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetKeyData() {
//		fail("Not yet implemented");
//	}

}
