package pactest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import pac.Jupiter;

public class MusicTableCRUDTest {

	@Test
	public void test() {
		Jupiter ju = null;
		try {
			ju = new Jupiter();
			ju.deleteAllTables();
			ju.createUserInfoTable();
			ju.createMusicTable();
			
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
			
			Jupiter.UserMusicBean music = new Jupiter.UserMusicBean();
			music.setMusicLike( "AMBIENT" );
			music.setArtistName( "THE ORB" );
			music.setUserId( userId );
			
			int status = ju.saveUserMusic( music );
			assertTrue( status == 1 );
			
			String musicTable = ju.listTable( Jupiter.USER_MUSIC_TABLE_NAME );
			System.out.println( musicTable );
			
			Jupiter.UserMusicBean get = ju.getUserMusic( music.getUserMusicLikeId() );
			Date createDate = get.getCreateDate();
			assertTrue( createDate != null );
			
			Date editDate = get.getEditDate();
			assertTrue( editDate == null );
			
			assertTrue( music.getMusicLike().equals( get.getMusicLike() ) );
			assertTrue( music.getArtistName().equals( get.getArtistName() ) );
			
			get.setMusicLike( "UPDATE" + get.getMusicLike() );
			get.setArtistName( "UPDATE" + get.getArtistName() );
	
			status = ju.saveUserMusic( get );
			assertTrue( status == 1 );
			
			musicTable = ju.listTable( Jupiter.USER_MUSIC_TABLE_NAME );
			System.out.println( musicTable );
		
			Jupiter.UserMusicBean update = ju.getUserMusic( get.getUserMusicLikeId() );
			
			assertTrue( update.getMusicLike().equals( get.getMusicLike() ) );
			assertTrue( update.getArtistName().equals( get.getArtistName() ) );
		
			editDate = update.getEditDate();
			assertTrue( editDate != null );
			
			musicTable = ju.listTable( Jupiter.USER_MUSIC_TABLE_NAME );
			System.out.println( musicTable );
			
			boolean result = ju.deleteMusicTableRow( update.getUserMusicLikeId() );
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
