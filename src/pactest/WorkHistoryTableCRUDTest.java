package pactest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class WorkHistoryTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createWorkHistoryTable();
			
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
			
			Jupiter.WorkHistoryBean work = new Jupiter.WorkHistoryBean();
			work.setCompanyName( "SNEI" );
			work.setPosition( "SEiT" );
			work.setYear( 2014 );
			work.setUserId( userId );
			
			int status = ju.saveWorkHistory( work );
			assertTrue( status == 1 );
			
			String workHist = ju.listTable( Jupiter.USER_WORK_HISTORY_TABLE_NAME );
			System.out.println( workHist );
			
			Jupiter.WorkHistoryBean get = ju.getWorkHistory( work.getWorkHistoryId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( work.getCompanyName().equals( get.getCompanyName() ) );
			assertTrue( work.getPosition().equals( get.getPosition() ) );
			assertTrue( work.getYear() == get.getYear() );

			get.setCompanyName( "UPDATE" + get.getCompanyName() );
			get.setPosition( "UPDATE" + get.getPosition() );
			get.setYear( 1 + get.getYear() );
	
			status = ju.saveWorkHistory( get );
			assertTrue( status == 1 );
			
			workHist = ju.listTable( Jupiter.USER_WORK_HISTORY_TABLE_NAME );
			System.out.println( workHist );
		
			Jupiter.WorkHistoryBean update = ju.getWorkHistory( get.getWorkHistoryId() );
			
			assertTrue( update.getCompanyName().equals( get.getCompanyName() ) );
			assertTrue( update.getPosition().equals( get.getPosition() ) );
			assertTrue( update.getYear() == get.getYear() );

			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			workHist = ju.listTable( Jupiter.USER_WORK_HISTORY_TABLE_NAME );
			System.out.println( workHist );
			
			boolean result = ju.deleteWorkHistoryTableRow( update.getWorkHistoryId() );
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
