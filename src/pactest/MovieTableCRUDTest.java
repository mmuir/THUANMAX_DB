package pactest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class MovieTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createMovieTable();
			
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
			
			Jupiter.UserMovieBean movie = new Jupiter.UserMovieBean();
			movie.setMovieName( "EDGE OF TOMORROW" );
			movie.setActorName( "Tom Cruise" );
			movie.setUserId( userId );
			
			int status = ju.saveUserMovie( movie );
			assertTrue( status == 1 );
			
			String movieTable = ju.listTable( Jupiter.USER_MOVIE_TABLE_NAME );
			System.out.println( movieTable );
			
			Jupiter.UserMovieBean get = ju.getUserMovie( movie.getMovieLikeId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( movie.getMovieName().equals( get.getMovieName() ) );
			assertTrue( movie.getActorName().equals( get.getActorName() ) );
			
			get.setMovieName( "UPDATE" + get.getMovieName() );
			get.setActorName( "UPDATE" + get.getActorName() );
	
			status = ju.saveUserMovie( get );
			assertTrue( status == 1 );
			
			movieTable = ju.listTable( Jupiter.USER_MOVIE_TABLE_NAME );
			System.out.println( movieTable );
		
			Jupiter.UserMovieBean update = ju.getUserMovie( get.getMovieLikeId() );
			
			assertTrue( update.getMovieName().equals( get.getMovieName() ) );
			assertTrue( update.getActorName().equals( get.getActorName() ) );
		
			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			movieTable = ju.listTable( Jupiter.USER_MOVIE_TABLE_NAME );
			System.out.println( movieTable );
			
			boolean result = ju.deleteMovieTableRow( update.getMovieLikeId() );
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
