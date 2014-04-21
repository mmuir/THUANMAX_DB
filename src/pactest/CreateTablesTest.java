package pactest;

import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

/**
 * Test class to verify the operation of the Jupiter class.
 * @author Max
 *
 */
public class CreateTablesTest {

	@Test
	public void testDBCreate() {
		Jupiter jupiter = null;
		try {
			jupiter = new Jupiter();
			jupiter.deleteAllTables();
			jupiter.createAllTables();

			Random ra = new Random();
			String lanem = Long.toHexString( System.currentTimeMillis() );
			Jupiter.UserInfoBean userInfo = new Jupiter.UserInfoBean();
			userInfo.setFname( "Joe" );
			userInfo.setLname( lanem );
			userInfo.setDob( "Mar 3, 1960" );
			userInfo.setEmail( lanem + "@schmoe.com" );
			userInfo.setGender( new Character( 'M' ) );
			userInfo.setProfile( "jazz, hiking, beer" );
			int userId = ra.nextInt();
			if ( userId < 0 ) {
				userId *= -1;
			}
			userInfo.setUserId( userId );
			
			jupiter.saveUserInfo(userInfo);
		} catch ( SQLException | ClassNotFoundException e ) {
			e.printStackTrace( System.err );
		}
		finally {
			try {
				jupiter.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace( System.err );
			}
		}
	}
}
