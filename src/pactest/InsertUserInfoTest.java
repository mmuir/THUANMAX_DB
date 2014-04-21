package pactest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class InsertUserInfoTest {
	@Test
	public void testUserInfoInsert() {
		Jupiter jupiter = null;
		try {
			jupiter = new Jupiter();	
			jupiter.recreateUserInfoTable();
			
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
			
			Jupiter.UserInfoBean result = jupiter.getUserInfo( userId );
			
			assertTrue( userInfo.getDob().equals( result.getDob() ) );
			assertTrue( userInfo.getEmail().equals( result.getEmail() ) );
			assertTrue( userInfo.getFname().equals( result.getFname() ) );
			assertTrue( userInfo.getGender().equals( result.getGender() ) );
			assertTrue( userInfo.getLname().equals( result.getLname() ) );
			assertTrue( userInfo.getUserId() == result.getUserId() );
			assertTrue( userInfo.getProfile().equals( result.getProfile() ) );
			assertTrue( result.getCreateDate() != null );
			assertTrue( result.getEditDate() == null );
			
			boolean status = jupiter.deleteUserInfoTableRow( userId );
			assertFalse( status );
			
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
