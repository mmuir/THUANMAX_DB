package pactest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class DestinationTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createDestinationTable();
			
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
			
			ju.saveUserInfo(userInfo);
			
			Jupiter.UserDestination dest = new Jupiter.UserDestination();
			
			dest.setCity( "Chorazone" );
			dest.setCountry( "Palestine" );
			dest.setState( "Cursed" );			
			dest.setUserId( userId );
			
			int status = ju.saveUserDestination( dest );
			assertTrue( status == 1 );
			
			String destTable = ju.listTable( Jupiter.USER_DESTINATION_TABLE_NAME );
			System.out.println( destTable );
			
			Jupiter.UserDestination get = ju.getUserDestination( dest.getDestinationId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( dest.getCity().equals( get.getCity() ) );
			assertTrue( dest.getState().equals( get.getState() ) );
			assertTrue( dest.getCountry().equals( get.getCountry() ) );
			
			get.setCity( "UPDATE" + get.getCity() );
			get.setState( "UPDATE" + get.getState() );
			get.setCountry( "UPDATE" + get.getCountry() );
			
			status = ju.saveUserDestination( get );
			assertTrue( status == 1 );
			
			destTable = ju.listTable( Jupiter.USER_DESTINATION_TABLE_NAME );
			System.out.println( destTable );
		
			Jupiter.UserDestination update = ju.getUserDestination( get.getDestinationId() );
			
			assertTrue( update.getCity().equals( get.getCity() ) );
			assertTrue( update.getState().equals( get.getState() ) );
			assertTrue( update.getCountry().equals( get.getCountry() ) );
			
			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			destTable = ju.listTable( Jupiter.USER_DESTINATION_TABLE_NAME );
			System.out.println( destTable );
			
			boolean result = ju.deleteDestinationTableRow( update.getDestinationId() );
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
