package pactest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class EducationTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createEducationTable();
			
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
			
			Jupiter.UserEducationBean edu = new Jupiter.UserEducationBean();
			edu.setMajor( "chemistry" );
			edu.setSchoolName( "UCLA" );
			edu.setYear( 2005 );
			edu.setUserId( userId );
			
			int status = ju.saveUserEducationInfo( edu );
			assertTrue( status == 1 );
			
			String educTable = ju.listTable( Jupiter.USER_EDUCATION_TABLE_NAME );
			System.out.println( educTable );
			
			Jupiter.UserEducationBean get = ju.getUserEducation( edu.getUserEducationId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( edu.getYear() == get.getYear() );
			assertTrue( edu.getMajor().equals( get.getMajor() ) );
			assertTrue( edu.getSchoolName().equals( get.getSchoolName() ) );
			
			get.setMajor( "BIO" + get.getMajor() );
			get.setSchoolName( "UPDATE" + get.getSchoolName() );
			get.setYear( get.getYear() + 1 );
			
			status = ju.saveUserEducationInfo( get );

			assertTrue( status == 1 );
			
			educTable = ju.listTable( Jupiter.USER_EDUCATION_TABLE_NAME );
			System.out.println( educTable );
			
			Jupiter.UserEducationBean update = ju.getUserEducation( get.getUserEducationId() );
			
			assertTrue( update.getYear() == get.getYear() );
			assertTrue( update.getMajor().equals( get.getMajor() ) );
			assertTrue( update.getSchoolName().equals( get.getSchoolName() ) );
			
			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			educTable = ju.listTable( Jupiter.USER_EDUCATION_TABLE_NAME );
			System.out.println( educTable );
			
			boolean result = ju.deleteEducationTableRow( update.getUserEducationId() );
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
