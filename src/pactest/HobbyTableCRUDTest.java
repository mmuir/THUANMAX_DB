package pactest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class HobbyTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createHobbyTable();
			
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
			ju.saveUserInfo( userInfo );
			
			Jupiter.UserHobbyBean hobby = new Jupiter.UserHobbyBean();
			hobby.setHobbyName( "HIKING" );			
			hobby.setUserId( userId );
			
			int status = ju.saveUserHobby( hobby );
			assertTrue( status == 1 );
			
			String hobbyTable = ju.listTable( Jupiter.USER_HOBBY_TABLE_NAME );
			System.out.println( hobbyTable );
			
			Jupiter.UserHobbyBean get = ju.getUserHobby( hobby.getUserHobbyId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( hobby.getHobbyName().equals( get.getHobbyName() ) );
			
			get.setHobbyName( "UPDATE" + get.getHobbyName() );
	
			status = ju.saveUserHobby( get );
			assertTrue( status == 1 );
			
			hobbyTable = ju.listTable( Jupiter.USER_HOBBY_TABLE_NAME );
			System.out.println( hobbyTable );
		
			Jupiter.UserHobbyBean update = ju.getUserHobby( get.getUserHobbyId() );
			
			assertTrue( update.getHobbyName().equals( get.getHobbyName() ) );
		
			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			hobbyTable = ju.listTable( Jupiter.USER_HOBBY_TABLE_NAME );
			System.out.println( hobbyTable );
			
			boolean result = ju.deleteHobbyTableRow( update.getUserHobbyId() );
			assertFalse( result );
			
			result = ju.deleteUserInfoTableRow( update.getUserId() );
			assertFalse( result );
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace( System.err );
		} finally {
			if ( null != ju ) {
				try {
					ju.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace( System.err );
				}
			}
		}
	}
}

