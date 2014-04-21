package pactest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class InfluenceTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createInfluenceTable();
			
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
			
			Jupiter.InfluenceBean influ = new Jupiter.InfluenceBean();
			influ.setPersonName( "Thuan Thuyne" );

			influ.setUserId( userId );
			
			int status = ju.saveInfluence( influ );
			assertTrue( status == 1 );
			
			String influTable = ju.listTable( Jupiter.USER_INFLUENCE_TABLE_NAME );
			System.out.println( influTable );
			
			Jupiter.InfluenceBean get = ju.getUserInfluence( influ.getUserInfluenceId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( influ.getPersonName().equals( get.getPersonName() ) );

			get.setPersonName( "UPDATE" + get.getPersonName() );
	
			status = ju.saveInfluence( get );
			assertTrue( status == 1 );
			
			influTable = ju.listTable( Jupiter.USER_INFLUENCE_TABLE_NAME );
			System.out.println( influTable );
		
			Jupiter.InfluenceBean update = ju.getUserInfluence( get.getUserInfluenceId() );
			
			assertTrue( update.getPersonName().equals( get.getPersonName() ) );

			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			influTable = ju.listTable( Jupiter.USER_INFLUENCE_TABLE_NAME );
			System.out.println( influTable );
			
			boolean result = ju.deleteInfluenceTableRow( update.getUserInfluenceId() );
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
