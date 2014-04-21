package pactest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class UserInfoUpdateTest {

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
			
			// this shd be an update operation
			jupiter.saveUserInfo(userInfo);
			
			Jupiter.UserInfoBean result = jupiter.getUserInfo( userId );
			
			result.setFname( result.getFname() + "UPDATE" );
			result.setLname( result.getLname() + "UPDATE" );
			result.setProfile( result.getProfile() + "UPDATE" );
			String sdob = result.getDob();
			SimpleDateFormat sdfDOB = new SimpleDateFormat( "MMM d, yyyy" );
			try {
				Date dateDOB = sdfDOB.parse( sdob );
				Calendar cal = Calendar.getInstance();
				cal.setTime( dateDOB );
				cal.add( Calendar.YEAR, +1 );
				dateDOB = cal.getTime();
				sdob = sdfDOB.format( dateDOB );
				result.setDob( sdob );
			} catch (ParseException e) {
				e.printStackTrace();
				fail( String.format( "CANNOT PARSE DOB '%s'", sdob ) );
			}
			
			String semail = result.getEmail();
			result.setEmail( semail + "UPDATE" );
			Character g = result.getGender();
			if ( 'M' == g ) {
				g = 'F';
			} else if ( 'F' == g ) {
				g = 'M';
			}
			result.setGender( g );
			
			jupiter.saveUserInfo( result );
			
			Jupiter.UserInfoBean updated = jupiter.getUserInfo( userId );
			
			assertTrue( updated.getDob().equals( result.getDob() ) );
			assertTrue( updated.getEmail().equals( result.getEmail() ) );
			assertTrue( updated.getFname().equals( result.getFname() ) );
			assertTrue( updated.getGender().equals( result.getGender() ) );
			assertTrue( updated.getLname().equals( result.getLname() ) );
			assertTrue( updated.getUserId() == result.getUserId() );
			assertTrue( updated.getProfile().equals( result.getProfile() ) );
			assertTrue( updated.getCreateDate().equals( result.getCreateDate() ) );
			assertTrue( updated.getEditDate() != null );
			
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
