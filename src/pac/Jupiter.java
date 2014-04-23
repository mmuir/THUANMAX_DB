package pac;

import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.SQLiteConfig;

/**
 * Class to create and manage the DB schema for the app.
 * 
 * @author Max
 *
 */
public class Jupiter {
	
	/**
	 * Bean to represent a row in the USER_MOVIE_LIKE table.
	 * 
	 * @author Max
	 *
	 */
	public static class UserMovieBean {
		
		/**
		 * Primary key of the USER_MOVIE_LIKE table.
		 */
		private int movieLikeId;
		
		/**
		 * Foreign key in the USER_MOVIE_LIKE table {primary in the USER_INFO table).
		 */
		private int userId;
		
		/**
		 * The name of the movie the user likes.
		 */
		private String movieName;
		
		/**
		 * An actor in the movie.
		 */
		private String actorName;
		
		/**
		 * Date this record was created.
		 */
		private Date createDate;
		
		/**
		 * Date this record was last edited.
		 */
		private Date editDate;

		/**
		 * Accessor method to return the Movie Like Id 
		 * (primary key of the USER_INFO table).
		 * 
		 * @return   The Movie Like Id.
		 */
		public int getMovieLikeId() {
			return movieLikeId;
		}

		/**
		 * Mutator method to initialize the Movie Like Id 
		 * (primary key of the USER_INFO table).
		 * 
		 * @param movieLikeId
		 *            The Movie Like Id.
		 */
		public void setMovieLikeId(int movieLikeId) {
			this.movieLikeId = movieLikeId;
		}

		/**
		 * Accessor method to return the User Id foreign key
		 * (primary key of the USER INFO table).
		 * 
		 * @return The User Id.
		 */
		public int getUserId() {
			return userId;
		}

		/**
		 * Mutator method to initialize the User Id foreign key
		 * (primary key of the USER INFO table).
		 * 
		 * @param userId
		 *            The User Id.
		 */
		public void setUserId(int userId) {
			this.userId = userId;
		}

		/**
		 * Accessor method to return the Name of the Movie that the User likes.
		 * 
		 * @return   The Name of the Movie the User likes.
		 */
		public String getMovieName() {
			return movieName;
		}

		/**
		 * Mutator method to initialize the name of the movie the User likes.
		 * 
		 * @param movieName
		 *            The Name of the Movie the User likes.
		 */
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}

		/**
		 * Accessor method to return the Name of the actor that the User likes.
		 * 
		 * @return   The Name of the actor the User likes.
		 */
		public String getActorName() {
			return actorName;
		}

		/**
		 * Mutator method to initialize the name of the actor the User likes.
		 * 
		 * @param actorName
		 *            The name of the actor the User likes.
		 */
		public void setActorName(String actorName) {
			this.actorName = actorName;
		}

		/**
		 * Accessor method to return the date this record was created.
		 * 
		 * @return  The record creation date.
		 */
		public Date getCreateDate() {
			return createDate;
		}

		/**
		 * Accessor method to return the date this record was last edited.
		 * 
		 * @return  The record edit date.
		 */
		public Date getEditDate() {
			return editDate;
		}

		/**
		 * Mutator method to set the creation date of this record.
		 * 
		 * @param createDate
		 *            The creation date of this record.
		 */
		private void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		
		/**
		 * Mutator method to set the edit date of this record.
		 * 
		 * @param editDate
		 *            The edit date of this record.
		 */
		private void setEditDate(Date editDate) {
			this.editDate = editDate;
		}
	}
	
	public static class WorkHistoryBean {

		private int workHistoryId;
		
		private int userId;
		
		private String companyName;
		
		private int year;
		
		private String position;
		
		private Date createDate;
		
		private Date editDate;
		
		public int getWorkHistoryId() {
			return workHistoryId;
		}

		public void setWorkHistoryId(int workHistoryId) {
			this.workHistoryId = workHistoryId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getEditDate() {
			return editDate;
		}

		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}
	}
	
	public static class InfluenceBean {	
		private int userInfluenceId;
		
		private int userId;
		
		private String personName;
		
		private Date createDate;
		
		private Date editDate;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getPersonName() {
			return personName;
		}

		public void setPersonName(String personName) {
			this.personName = personName;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getEditDate() {
			return editDate;
		}

		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}

		public int getUserInfluenceId() {
			return userInfluenceId;
		}

		public void setUserInfluenceId(int userInfluenceId) {
			this.userInfluenceId = userInfluenceId;
		}
	}
	
	public static class UserMusicBean {
		private int userMusicLikeId;
		
		private int userId;
		
		private String musicLike;
		
		private String artistName;
		
		private Date createDate;
		
		private Date editDate;

		public int getUserMusicLikeId() {
			return userMusicLikeId;
		}

		public void setUserMusicLikeId(int userMusicLikeId) {
			this.userMusicLikeId = userMusicLikeId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getMusicLike() {
			return musicLike;
		}

		public void setMusicLike(String musicLike) {
			this.musicLike = musicLike;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getEditDate() {
			return editDate;
		}

		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}

		public String getArtistName() {
			return artistName;
		}

		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}
	}
	
	public static class UserHobbyBean {
		
		private int userHobbyId;
		
		private int userId;
		
		private String hobbyName;
		
		private Date createDate;
		
		private Date editDate;

		public int getUserHobbyId() {
			return userHobbyId;
		}

		public void setUserHobbyId(int userHobbyId) {
			this.userHobbyId = userHobbyId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getHobbyName() {
			return hobbyName;
		}

		public void setHobbyName(String hobbyName) {
			this.hobbyName = hobbyName;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getEditDate() {
			return editDate;
		}

		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}
	}
	
	public static class UserDestination {

		private int destinationId;

		private int userId;

		private String city;

		private String state;

		private String country;

		private Date createDate;

		private Date editDate;

		public int getDestinationId() {
			return destinationId;
		}

		public void setDestinationId(int destinationId) {
			this.destinationId = destinationId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getEditDate() {
			return editDate;
		}

		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}
	}
	
	public static class UserEducationBean {
		private int userEducationId;
		private int userId;
		private String schoolName;
		private int year;
		private String major;
		private Date createDate;
		private Date editDate;
		
		public int getUserEducationId() {
			return userEducationId;
		}
		public void setUserEducationId(int userEducationId) {
			this.userEducationId = userEducationId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getSchoolName() {
			return schoolName;
		}
		public void setSchoolName(String schoolName) {
			this.schoolName = schoolName;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public String getMajor() {
			return major;
		}
		public void setMajor(String major) {
			this.major = major;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getEditDate() {
			return editDate;
		}
		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}
	}
	
	public static class UserInfoBean {
		private int userId;
		private String email;
		private String fname;
		private String lname;
		private String dob;
		private String profile;
		private Character gender;
		private Date lastLogin;
		private Date createDate;
		private Date editDate;
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lname) {
			this.lname = lname;
		}		
		public String getProfile() {
			return this.profile;
		}
		public void setProfile( final String profile ) {
			this.profile = profile;
		}		
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public Character getGender() {
			return gender;
		}
		public void setGender(Character gender) {
			this.gender = gender;
		}
		public Date getLastLogin() {
			return lastLogin;
		}
		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getEditDate() {
			return editDate;
		}
		public void setEditDate(Date editDate) {
			this.editDate = editDate;
		}
	}

	/**
	 * The SQLLite Driver Name.
	 */
	private static final String DRIVER_NAME = "org.sqlite.JDBC";

	/**
	 * The SQLLite connection URL.
	 */
	private static final String CONNECTION_URL = "jdbc:sqlite:test001.db";

	/**
	 * Create a logger for the Jupiter class.
	 */
	private static final Logger logger = Logger.getLogger(Jupiter.class
			.getSimpleName());
	
	public static final String USER_HOBBY_TABLE_NAME = "USER_HOBBY";
	
	private static final String HOBBY_ID_COLUMN_NAME = "USER_HOBBY_ID";
	
	/**
	 * Name of the USER_INFO table.
	 */
	private static final String USER_TABLE_NAME = "USER_INFO";
	
	/**
	 * Name of the USER_DESTINATION table.
	 */
	public static final String USER_DESTINATION_TABLE_NAME = "USER_DESTINATION";
	
	private static final String USER_DESTINATION_ID_COLUMN_NAME = "DESTINATION_ID";
	
	/**
	 * Name of the USER_MOVIE_LIKE table.
	 */
	public static final String USER_MOVIE_TABLE_NAME = "USER_MOVIE_LIKE";
	
	private static final String MOVIE_LIKE_ID_COLUMN_NAME = "USER_MOVIE_LIKE_ID";
	
	private static final String MOVIE_NAME_COLUMN_NAME = "MOVIE_NAME";
	
	private static final String ACTOR_NAME_COLUMN_NAME = "ACTOR_NAME";
	
	/**
	 * Name of the USER_INFLUENTIAL_PEOPLE table.
	 */
	public static final String USER_INFLUENCE_TABLE_NAME = "USER_INFLUENTIAL_PEOPLE";
	
	private static final String INFLUENCE_ID_COLUMN_NAME = "USER_INFLUENCE_ID";
	
	private static final String PERSON_NAME_COLUMN_NAME = "PERSON_NAME";
	
	/**
	 * Name of the USER_MUSIC_LIKE table.
	 */
	public static final String USER_MUSIC_TABLE_NAME = "USER_MUSIC_LIKE";
	
	private static final String USER_MUSIC_ID_COLUMN_NAME = "USER_MUSIC_LIKE_ID";
	
	/**
	 * Name of the USER_WORK_HISTORY table.
	 */
	public static final String USER_WORK_HISTORY_TABLE_NAME = "USER_WORK_HISTORY";
	
	private static final String WORK_HISTORY_ID_COLUMN_NAME = "USER_WORK_HISTORY_ID";
	
	private static final String COMPANY_NAME_COLUMN_NAME = "COMPANY_NAME";
	
	private static final String POSITION_COLUMN_NAME = "POSITION";
	
	/**
	 * Name of the USER_EDUCATION table.
	 */
	public static final String USER_EDUCATION_TABLE_NAME = "USER_EDUCATION";
	
	/**
	 * Format statement to store the SQL query to find out if a table exists.
	 */
	private static final String VERIFY_EXISTS_FMT =
			"SELECT name FROM sqlite_master WHERE type='table' AND name='%s'";

	private static final String SELECT_ALL_FROM_ID = "SELECT * FROM %s WHERE %s = '%d'";
	
	private static final String USER_EDUCATION_ID_COLUMN_NAME = "USER_EDUCATION_ID";
	
	private static final String USER_ID_COLUMN_NAME = "USER_ID";
	
	private static final String CREATE_DATE_COLUMN_NAME = "CREATE_DATE";
	
	private static final String EDIT_DATE_COLUMN_NAME = "EDIT_DATE";
	
	private static final String CITY_NAME_COLUMN_NAME = "CITY_NAME";
	
	private static final String HOBBY_NAME_COLUMN_NAME = "HOBBY_NAME";
	
	private static final String STATE_NAME_COLUMN_NAME = "STATE_NAME";
	
	private static final String COUNTRY_COLUMN_NAME = "COUNTRY";
	
	private static final String SCHOOL_NAME_COLUMN_NAME = "SCHOOL_NAME";
	
	private static final String MAJOR_COLUMN_NAME = "MAJOR";
	
	private static final String YEAR_COLUMN_NAME = "YEAR";
	
	private static final String DOB_COLUMN_NAME = "DOB";

	private static final String EMAIL_COLUMN_NAME = "EMAIL";

	private static final String FNAME_COLUMN_NAME = "FNAME";

	private static final String GENDER_COLUMN_NAME = "GENDER";

	private static final String LNAME_COLUMN_NAME = "LNAME";

	private static final String LAST_LOGIN_COLUMN_NAME = "LAST_LOGIN";
	
	private static final String PROFILE_COLUMN_NAME = "PROFILE";
	
	private static final String MUSIC_LIKE_COLUMN_NAME = "MUSIC_NAME";
	
	private static final String ARTIST_NAME_COLUMN_NAME = "ARTIST_NAME";
	
	private static final String INSERT_FAILED = "INSERT FAILED! ";
	
	private static final String UPDATE_FAILED = "UPDATE FAILED! ";
	
	private static final String SIMPLE_SELECT_FMT = "SELECT %s FROM %s WHERE %s = '%d'";
	
	private static final String UTC = "UTC";
	
	private static final char TAB_CHAR = '\t';
	
	private static final String DROP_TABLE = "drop table if exists ";
	
	private static final String INSERT_SQL_FMT = "INSERT INTO %s";
	
	private static final String UPDATE_SET_FMT = "UPDATE %s SET";
	
	/**
	 * Member variable to store the DB connection.
	 */
	private Connection connection = null;

	/**
	 * Constructor for the Jupiter class, which is used to create all the tables in the app.
	 * 
	 * @throws ClassNotFoundException  Thrown if the SQL driver class is not found (org.sqlite.JDBC).
	 * @throws SQLException  Thrown if we can't get a DB connection.
	 */
	public Jupiter() throws ClassNotFoundException, SQLException {

		Class.forName(Jupiter.DRIVER_NAME);
		this.connection = DriverManager.getConnection(Jupiter.CONNECTION_URL);

		logger.log(Level.INFO, "Opened database successfully");
	}
	
	public int saveUserMovie(UserMovieBean movie) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String movieName = movie.getMovieName();
		String actorName = movie.getActorName();
		
		int userId = movie.getUserId();
		int movieId = movie.getMovieLikeId();
		
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_MOVIE_TABLE_NAME;
		String fieldName = Jupiter.MOVIE_LIKE_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, movieId ) ) {
			
			sb.append( String.format( Jupiter.UPDATE_SET_FMT, tableName ) );
			
			if ( null != movieName ) {
				sb.append( " MOVIE_NAME=" );
				sb.append( String.format( "'%s'", movieName ) );
			}		
			if ( null != actorName ) {
				sb.append( ",ACTOR_NAME=" );
				sb.append( String.format( "'%s'", actorName ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append( String.format( 
									  " WHERE %s='%d'",
									  fieldName, movieId ) );

			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {
			String sql = String.format( 
					Jupiter.INSERT_SQL_FMT,
					tableName );
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );
			
			if ( null != movieName ) {
				colNames.append( ",MOVIE_NAME" );
				values.append( String.format( ",'%s'", movieName ) );
			}			
			if ( null != actorName ) {
				colNames.append( ",ACTOR_NAME" );
				values.append( String.format( ",'%s'", actorName ) );
			}

			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				movieId = rs.getInt( 1 );
				movie.setMovieLikeId( movieId );		
			}
		}
		return status;
	}
	
	public UserMovieBean getUserMovie( final int movieId ) throws SQLException {
		Statement stmt = this.connection.createStatement();		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_MOVIE_TABLE_NAME,
									Jupiter.MOVIE_LIKE_ID_COLUMN_NAME,
									movieId );
		
		ResultSet rs = stmt.executeQuery(sql);
		UserMovieBean bean = new UserMovieBean();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId( userId );
			
			int likeId = rs.getInt( Jupiter.MOVIE_LIKE_ID_COLUMN_NAME );
			bean.setMovieLikeId( likeId );
			
			String movieName = rs.getString( Jupiter.MOVIE_NAME_COLUMN_NAME );
			bean.setMovieName( movieName );

			String actorName = rs.getString( Jupiter.ACTOR_NAME_COLUMN_NAME );
			bean.setActorName( actorName );
			
			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);			
		}
		return bean;
	}
	
	/**
	 * Method to either add a new row to the DB or, if a row with the
	 * same Work History ID as specified in the work history value stored
	 * in the WorkHistory bean argument is found, update an existing row.
	 *  
	 * @param work
	 *            An instance of WorkHistoryBean to add or update the DB.
	 * @return    The number of rows in the Work History table that were changed.
	 * 
	 * @throws SQLException  Oops!  Something went wrong.
	 */
	public int saveWorkHistory( WorkHistoryBean work ) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String companyName = work.getCompanyName();
		String position = work.getPosition();
		int year = work.getYear();
		
		int userId = work.getUserId();
		int workHistoryId = work.getWorkHistoryId();
		
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_WORK_HISTORY_TABLE_NAME;
		String fieldName = Jupiter.WORK_HISTORY_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, workHistoryId ) ) {
			
			sb.append( String.format( Jupiter.UPDATE_SET_FMT, tableName ) );
			
			if ( null != companyName ) {
				sb.append( " COMPANY_NAME=" );
				sb.append( String.format( "'%s'", companyName ) );
			}		
			if ( null != position ) {
				sb.append( ",POSITION=" );
				sb.append( String.format( "'%s'", position ) );
			}		
			if ( 0 < year ) {
				sb.append( ",YEAR=" );
				sb.append( String.format( "'%d'", year ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append( String.format( 
									  " WHERE %s='%d'",
									  fieldName, workHistoryId ) );

			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {
			String sql = String.format( 
					Jupiter.INSERT_SQL_FMT,
					tableName );
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );
			
			if ( null != companyName ) {
				colNames.append( ",COMPANY_NAME" );
				values.append( String.format( ",'%s'", companyName ) );
			}			
			if ( null != position ) {
				colNames.append( ",POSITION" );
				values.append( String.format( ",'%s'", position ) );
			}			
			if ( 0 < year ) {
				colNames.append( ",YEAR" );
				values.append( String.format( ",'%d'", year ) );
			}

			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				workHistoryId = rs.getInt( 1 );
				work.setWorkHistoryId( workHistoryId );		
			}
		}
		return status;
	}

	/**
	 * This method looks up a row with the Work History ID
	 * specified in the workHistoryId parameter, and this
	 * row is returned as a WorkHistoryBean object.
	 * 
	 * @param workHistoryId
	 *            Look for a row with this Work History Id.    
	 * @return A WorkHistoryBean object.
	 * @throws SQLException  Oops!  Something went wrong.
	 */
	public WorkHistoryBean getWorkHistory( final int workHistoryId ) throws SQLException {
		Statement stmt = this.connection.createStatement();		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_WORK_HISTORY_TABLE_NAME,
									Jupiter.WORK_HISTORY_ID_COLUMN_NAME,
									workHistoryId );
		
		ResultSet rs = stmt.executeQuery(sql);
		WorkHistoryBean bean = new WorkHistoryBean();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId(userId);
			
			int whId = rs.getInt( Jupiter.WORK_HISTORY_ID_COLUMN_NAME );
			bean.setWorkHistoryId( whId );
			
			String company = rs.getString( Jupiter.COMPANY_NAME_COLUMN_NAME );
			bean.setCompanyName( company );
			
			String position = rs.getString( Jupiter.POSITION_COLUMN_NAME );
			bean.setPosition( position );

			int year = rs.getInt( Jupiter.YEAR_COLUMN_NAME );
			bean.setYear( year );
			
			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);			
		}
		return bean;
	}

	public InfluenceBean getUserInfluence( final int userInfluenceId ) throws SQLException {
		Statement stmt = this.connection.createStatement();		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_INFLUENCE_TABLE_NAME,
									Jupiter.INFLUENCE_ID_COLUMN_NAME,
									userInfluenceId );
		
		ResultSet rs = stmt.executeQuery(sql);
		InfluenceBean bean = new InfluenceBean();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId(userId);
			
			int infId = rs.getInt( Jupiter.INFLUENCE_ID_COLUMN_NAME );
			bean.setUserInfluenceId( infId );
			
			String person = rs.getString( Jupiter.PERSON_NAME_COLUMN_NAME );
			bean.setPersonName( person );

			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);
			
		}
		return bean;
	}
	
	public int saveInfluence( InfluenceBean influ ) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String personName = influ.getPersonName();
		
		int userId = influ.getUserId();
		int influenceId = influ.getUserInfluenceId();
		
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_INFLUENCE_TABLE_NAME;
		String fieldName = Jupiter.INFLUENCE_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, influenceId ) ) {
			
			sb.append( String.format( Jupiter.UPDATE_SET_FMT, tableName ) );
			
			if ( null != personName ) {
				sb.append( " PERSON_NAME=" );
				sb.append( String.format( "'%s'", personName ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append( String.format( 
									  " WHERE %s='%d'",
									  fieldName, influenceId ) );

			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {
			String sql = String.format( 
					Jupiter.INSERT_SQL_FMT,
					tableName );
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );						
			if ( null != personName ) {
				colNames.append( ",PERSON_NAME" );
				values.append( String.format( ",'%s'", personName ) );
			}

			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				influenceId = rs.getInt( 1 );
				influ.setUserInfluenceId( influenceId );		
			}
		}
		return status;
	}
	
	public UserMusicBean getUserMusic( int musicId ) throws SQLException {
		Statement stmt = this.connection.createStatement();		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_MUSIC_TABLE_NAME,
									Jupiter.USER_MUSIC_ID_COLUMN_NAME,
									musicId );
		
		ResultSet rs = stmt.executeQuery(sql);
		UserMusicBean bean = new UserMusicBean();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId(userId);
			
			int musId = rs.getInt( Jupiter.USER_MUSIC_ID_COLUMN_NAME );
			bean.setUserMusicLikeId( musId );
			
			String like = rs.getString( Jupiter.MUSIC_LIKE_COLUMN_NAME );
			bean.setMusicLike( like );
			
			String artist = rs.getString( Jupiter.ARTIST_NAME_COLUMN_NAME );
			bean.setArtistName( artist );

			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);
			
		}
		return bean;
	}
	
	public int saveUserMusic( UserMusicBean music ) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String musicLike = music.getMusicLike();
		String artistName = music.getArtistName();
		
		int userId = music.getUserId();
		int musicId = music.getUserMusicLikeId();
		
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_MUSIC_TABLE_NAME;
		String fieldName = Jupiter.USER_MUSIC_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, musicId ) ) {
			
			sb.append( String.format( Jupiter.UPDATE_SET_FMT, tableName ) );
			
			if ( null != musicLike ) {
				sb.append( " MUSIC_NAME=" );
				sb.append( String.format( "'%s'", musicLike ) );
			}
			
			if ( null != artistName ) {
				sb.append( ",ARTIST_NAME=" );
				sb.append( String.format( "'%s'", artistName ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append( String.format( 
									  " WHERE %s='%d'",
									  fieldName, musicId ) );

			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {
			String sql = String.format( 
					Jupiter.INSERT_SQL_FMT,
					tableName );
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );						
			if ( null != musicLike ) {
				colNames.append( ",MUSIC_NAME" );
				values.append( String.format( ",'%s'", musicLike ) );
			}
			
			if ( null != artistName ) {
				colNames.append( ",ARTIST_NAME" );
				values.append( String.format( ",'%s'", artistName ) );
			}

			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				musicId = rs.getInt( 1 );
				music.setUserMusicLikeId( musicId );		
			}
		}
		return status;
	}
	
	/**
	 * Method to persist a Hobby bean to the DB.
	 * @param hobby
	 * @return The number of rows updated or inserted by this method.
	 * @throws SQLException
	 */
	public int saveUserHobby( UserHobbyBean hobby ) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String hobbyName = hobby.getHobbyName();
		
		int userId = hobby.getUserId();
		int userHobbyId = hobby.getUserHobbyId();
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_HOBBY_TABLE_NAME;
		String fieldName = Jupiter.HOBBY_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, userHobbyId ) ) {
			
			sb.append( String.format( Jupiter.UPDATE_SET_FMT, tableName ) );
			
			if ( null != hobbyName ) {
				sb.append( " HOBBY_NAME=" );
				sb.append( String.format( "'%s'", hobbyName ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append( String.format( " WHERE %s='%d'", fieldName, userHobbyId ) );

			Jupiter.logger.log(Level.INFO, sb.toString());
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {

			String sql = String.format( 
					Jupiter.INSERT_SQL_FMT,
					tableName );
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );						
			if ( null != hobbyName ) {
				colNames.append( ",HOBBY_NAME" );
				values.append( String.format( ",'%s'", hobbyName ) );
			}			

			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				userHobbyId = rs.getInt( 1 );
				hobby.setUserHobbyId( userHobbyId );		
			}
		}
		return status;
	}
	
	public UserHobbyBean getUserHobby( final int hobbyId ) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_HOBBY_TABLE_NAME,
									Jupiter.HOBBY_ID_COLUMN_NAME,
									hobbyId );
		
		ResultSet rs = stmt.executeQuery(sql);
		UserHobbyBean bean = new UserHobbyBean();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId(userId);
			
			int hobbId = rs.getInt( Jupiter.HOBBY_ID_COLUMN_NAME );
			bean.setUserHobbyId( hobbId );

			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);
			
			String name = rs.getString( Jupiter.HOBBY_NAME_COLUMN_NAME );
			bean.setHobbyName( name );
		}
		return bean;
	}
	
	public int saveUserDestination( UserDestination userDest ) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String city = userDest.getCity();
		String country = userDest.getCountry();
		String state = userDest.getState();

		int userId = userDest.getUserId();
		int userDestinationId = userDest.getDestinationId();
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_DESTINATION_TABLE_NAME;
		String fieldName = Jupiter.USER_DESTINATION_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, userDestinationId ) ) {
			
			sb.append( "UPDATE USER_DESTINATION SET" );
			
			if ( null != city ) {
				sb.append( " CITY_NAME=" );
				sb.append( String.format( "'%s'", city ) );
			}
			if ( null != state ) {
				sb.append( ",STATE_NAME=" );
				sb.append( String.format( "'%s'", state ) );
			}
			if ( country != null ) {
				sb.append( ",COUNTRY=" );
				sb.append( String.format( "'%s'", country ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append(String.format(" WHERE DESTINATION_ID='%d'", userDestinationId ) );

			Jupiter.logger.log(Level.INFO, sb.toString());
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {

			String sql = String.format( 
					Jupiter.INSERT_SQL_FMT,
					Jupiter.USER_DESTINATION_TABLE_NAME );
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );						
			if ( null != city ) {
				colNames.append( ",CITY_NAME" );
				values.append( String.format( ",'%s'", city ) );
			}			
			if ( state != null ) {
				colNames.append( ",STATE_NAME" );
				values.append( String.format( ",'%s'", state ) );
			}		
			if ( null != country ) {
				colNames.append( ",COUNTRY" );
				values.append( String.format( ",'%s'", country ) );
			}
			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				userDestinationId = rs.getInt( 1 );
				userDest.setDestinationId( userDestinationId );		
			}
		}
		return status;

	}
	
	public UserDestination getUserDestination( final int destinationId ) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_DESTINATION_TABLE_NAME,
									Jupiter.USER_DESTINATION_ID_COLUMN_NAME,
									destinationId );
		
		ResultSet rs = stmt.executeQuery(sql);
		UserDestination bean = new UserDestination();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId(userId);
			
			int educationId = rs.getInt( Jupiter.USER_DESTINATION_ID_COLUMN_NAME );
			bean.setDestinationId( educationId );

			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);
			
			String city = rs.getString( Jupiter.CITY_NAME_COLUMN_NAME );
			bean.setCity( city );
			
			String state = rs.getString( Jupiter.STATE_NAME_COLUMN_NAME );
			bean.setState( state );
			
			String country = rs.getString( Jupiter.COUNTRY_COLUMN_NAME );
			bean.setCountry( country );
		}
		return bean;
	}
	
	public UserEducationBean getUserEducation( final int userEducationId ) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String sql = String.format( 
									Jupiter.SELECT_ALL_FROM_ID,
									Jupiter.USER_EDUCATION_TABLE_NAME,
									Jupiter.USER_EDUCATION_ID_COLUMN_NAME,
									userEducationId );
		
		ResultSet rs = stmt.executeQuery(sql);
		UserEducationBean bean = new UserEducationBean();
		while (rs.next()) {
			int userId = rs.getInt( Jupiter.USER_ID_COLUMN_NAME );
			bean.setUserId(userId);
			
			int educationId = rs.getInt( Jupiter.USER_EDUCATION_ID_COLUMN_NAME );
			bean.setUserEducationId( educationId );

			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);
			
			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);
			
			String schoolName = rs.getString( Jupiter.SCHOOL_NAME_COLUMN_NAME );
			bean.setSchoolName(schoolName);
			
			String major = rs.getString( Jupiter.MAJOR_COLUMN_NAME );
			bean.setMajor(major);
			
			int year = rs.getInt( Jupiter.YEAR_COLUMN_NAME );
			bean.setYear( year );
		}
		return bean;
	}
	
	/**
	 * Save a User Education bean to the DB.
	 * 
	 * @param userEduc
	 *            A user education bean.
	 * @return
	 *            The number of rows inserted or updated.
	 * @throws SQLException
	 *            Something went wrong.
	 */
	public int saveUserEducationInfo( UserEducationBean userEduc ) throws SQLException {
		int status = 0;
		Statement stmt = this.connection.createStatement();
		String major = userEduc.getMajor();
		String schoolName = userEduc.getSchoolName();
		int year = userEduc.getYear();
		int userId = userEduc.getUserId();
		int userEducationId = userEduc.getUserEducationId();
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		
		String tableName = Jupiter.USER_EDUCATION_TABLE_NAME;
		String fieldName = Jupiter.USER_EDUCATION_ID_COLUMN_NAME;
		if ( this.rowExists( tableName, fieldName, userEducationId ) ) {
			
			sb.append( "UPDATE USER_EDUCATION SET" );
			
			if ( null != major ) {
				sb.append( " MAJOR=" );
				sb.append( String.format( "'%s'", major ) );
			}
			if ( null != schoolName ) {
				sb.append( ",SCHOOL_NAME=" );
				sb.append( String.format( "'%s'", schoolName ) );
			}
			if ( year > 0 ) {
				sb.append( ",YEAR=" );
				sb.append( String.format( "'%d'", year ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append(",EDIT_DATE=");
			sb.append(String.format("'%s'", snow));

			sb.append(String.format(" WHERE USER_EDUCATION_ID='%d'", userEducationId ) );

			Jupiter.logger.log(Level.INFO, sb.toString());
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		}
		else {
			String sql = String.format(
										Jupiter.INSERT_SQL_FMT,
										tableName );
			
			sb.append( sql );			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );			
			colNames.append( Jupiter.USER_ID_COLUMN_NAME );
			values.append( userId );						
			if ( null != schoolName ) {
				colNames.append( ",SCHOOL_NAME" );
				values.append( String.format( ",'%s'", schoolName ) );
			}			
			if ( year > 0 ) {
				colNames.append( ",YEAR" );
				values.append( String.format( ",'%d'", year ) );
			}		
			if ( null != major ) {
				colNames.append( ",MAJOR" );
				values.append( String.format( ",'%s'", major ) );
			}
			colNames.append( String.format( 
											",%s",
											Jupiter.CREATE_DATE_COLUMN_NAME ) );
			values.append( String.format( ",'%s'", snow ) );
			
			// no edit date yet
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				userEducationId = rs.getInt( 1 );
				userEduc.setUserEducationId( userEducationId );		
			}
		}
		return status;
	}
	


	/**
	 * Method to retrieve a row from the USER_INFO DB table.
	 * 
	 * @param userId  The User ID of the row to find.
	 * @return An initialized UserInfoBean object.
	 * @throws SQLException
	 */
	public UserInfoBean getUserInfo( final int userId ) throws SQLException {		
		Statement stmt = this.connection.createStatement();
		String fmt = "SELECT * FROM USER_INFO WHERE USER_ID = '%d'";
		String sql = String.format(fmt, userId);
		ResultSet rs = stmt.executeQuery(sql);
		UserInfoBean bean = new UserInfoBean();
		while (rs.next()) {
			bean.setUserId(userId);
			Date createDate = rs.getDate( Jupiter.CREATE_DATE_COLUMN_NAME );
			bean.setCreateDate(createDate);

			String dob = rs.getString( Jupiter.DOB_COLUMN_NAME );
			bean.setDob(dob);

			Date editDate = rs.getDate( Jupiter.EDIT_DATE_COLUMN_NAME );
			bean.setEditDate(editDate);

			String email = rs.getString( Jupiter.EMAIL_COLUMN_NAME );
			bean.setEmail(email);

			String fname = rs.getString( Jupiter.FNAME_COLUMN_NAME );
			bean.setFname(fname);

			String sgender = rs.getString( Jupiter.GENDER_COLUMN_NAME );
			if (sgender != null && sgender.length() > 0) {
				Character gender = new Character(sgender.charAt(0));
				bean.setGender(gender);
			}
			Date lastLogin = rs.getDate( Jupiter.LAST_LOGIN_COLUMN_NAME );
			bean.setLastLogin(lastLogin);

			String lname = rs.getString( Jupiter.LNAME_COLUMN_NAME );
			bean.setLname(lname);
			
			String profile = rs.getString( Jupiter.PROFILE_COLUMN_NAME );
			bean.setProfile( profile );
		}
		return bean;
	}
	


	/**
	 * Save a User Info bean to the DB.
	 * 
	 * @param userInfo
	 *            The user info bean to save. 
	 * @return
	 *            The number of rows inserted or updated.
	 *            
	 * @throws SQLException
	 *            OOps, something went wrong.
	 */
	public int saveUserInfo( UserInfoBean userInfo ) throws SQLException {
		int status = 0;
		int userId = userInfo.getUserId();
		String email = userInfo.getEmail();
		String fname = userInfo.getFname();
		String lname = userInfo.getLname();
		String dob = userInfo.getDob();
		Character gender = userInfo.getGender();
		Date lastLogin = userInfo.getLastLogin();
		String profile = userInfo.getProfile();
		
		SimpleDateFormat sdf = new SimpleDateFormat( SQLiteConfig.DEFAULT_DATE_STRING_FORMAT );
		sdf.setTimeZone( TimeZone.getTimeZone( Jupiter.UTC ) );
		String snow = sdf.format( new Date() );
		StringBuilder sb = new StringBuilder();
		
		String tableName = Jupiter.USER_TABLE_NAME;
		String fieldName = Jupiter.USER_ID_COLUMN_NAME;
		
		if ( this.rowExists( tableName, fieldName, userId ) ) {

			sb.append( "UPDATE USER_INFO SET" );
			if ( null != email ) {
				sb.append( " EMAIL=" );
				sb.append( String.format( "'%s'", email ) );
			}
			if ( null != fname ) {
				sb.append( ",FNAME=" );
				sb.append( String.format( "'%s'", fname ) );
			}
			if ( null != lname ) {
				sb.append( ",LNAME=" );
				sb.append( String.format( "'%s'", lname ) );
			}
			if ( null != dob ) {
				sb.append( ",DOB=" );
				sb.append( String.format( "'%s'", dob ) );
			}
			if ( null != gender ) {
				sb.append( ",GENDER=" );
				sb.append( String.format( "'%s'", gender ) );
			}
			
			if ( null != profile ) {
				sb.append( ",PROFILE=" );
				sb.append( String.format( "'%s'", profile ) );
			}
			
			if ( null != lastLogin ) {
				sb.append( ",LAST_LOGIN=" );
				String sLastLogin = sdf.format( lastLogin );
				sb.append( String.format( "'%s'", sLastLogin ) );
			}
			
			// don't update CREATE_DATE
			
			sb.append( ",EDIT_DATE=" );
			sb.append( String.format( "'%s'", snow ) );
	
			sb.append( String.format( " WHERE USER_ID='%d'", userId ) );
			
			Statement stmt = this.connection.createStatement();
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.UPDATE_FAILED );
			}
		} else {			
			sb.append( "INSERT INTO USER_INFO" );
			
			StringBuilder colNames = new StringBuilder( "(" );
			StringBuilder values = new StringBuilder( "VALUES(" );
			
			colNames.append( "USER_ID" );
			values.append( userId );
			
			if ( null != email ) {
				colNames.append( ",EMAIL" );
				values.append( String.format( ",'%s'", email ) );
			}	
			if ( null != fname ) {
				colNames.append( ",FNAME" );
				values.append( String.format( ",'%s'", fname ) );
			}			
			if ( null != lname ) {
				colNames.append( ",LNAME" );
				values.append( String.format( ",'%s'", lname ) );
			}
			if ( null != dob ) {
				colNames.append( ",DOB" );
				values.append( String.format( ",'%s'", dob ) );
			}
			if ( null != gender ) {
				colNames.append( ",GENDER" );
				values.append( String.format( ",'%s'", gender ) );
			}
			if ( null != profile ) {
				colNames.append( ",PROFILE" );
				values.append( String.format( ",'%s'", profile ) );
			}			
			if ( null != lastLogin ) {
				colNames.append( ",LAST_LOGIN" );
				String sLastLogin = sdf.format( lastLogin );
				values.append( String.format( ",'%s'", sLastLogin ) );
			}
			
			colNames.append( ",CREATE_DATE" );
			values.append( String.format( ",'%s'", snow ) );
			
			colNames.append( ")" );
			values.append( ")" );
			
			sb.append( colNames.toString() );
			sb.append( values.toString() );
			
			Jupiter.logger.log( Level.INFO, sb.toString() );
			
			Statement stmt = this.connection.createStatement();
			status = stmt.executeUpdate( sb.toString() );
			if ( status != 1 ) {
				logger.log( Level.SEVERE, Jupiter.INSERT_FAILED + sb );
			}
		}
		return status;
	}
	
	/**
	 * Helper method to check if a row with the given fieldName and ID exists.
	 * 
	 * @param tableName
	 *            The table exists.
	 *            
	 * @param fieldName
	 *            The ID field exists.
	 *            
	 * @param userId
	 *            The value of the ID field to test with.
	 *            
	 * @return True if the row exists, else false.
	 * 
	 * @throws SQLException
	 *             Oops, something went wrong.
	 */
	private boolean rowExists( 
								final String tableName,
								final String fieldName,
								final int userId ) throws SQLException {
		Statement stmt = this.connection.createStatement();
		String fmt = Jupiter.SIMPLE_SELECT_FMT;
		String sql = String.format( fmt, fieldName, tableName, fieldName, userId );
		ResultSet rs = stmt.executeQuery(sql);
		return rs.next();
	}

	
	/**
	 * Convenience method to create the User Info table.
	 * 
	 * @throws SQLException
	 *             Oops, something went wrong. 
	 */
	public void createUserInfoTable() throws SQLException {
		Statement stmt = this.connection.createStatement();
		this.createUserInfoTable( stmt );
	}

	/**
	 * Convenience method to recreate the User Info table.
	 * 
	 * @throws SQLException
	 *             Oops, something went wrong. 
	 */
	public void recreateUserInfoTable( ) throws SQLException {
		Statement stmt = this.connection.createStatement();
		this.dropTableIfExists( stmt, Jupiter.USER_TABLE_NAME );
		this.createUserInfoTable( stmt );
	}
	
	public void createUserInfoTable( final Statement stmt ) throws SQLException {		
		StringBuilder sb = new StringBuilder();
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_TABLE_NAME ) );
		sb.append( "(USER_ID INT PRIMARY KEY     NOT NULL," ); // given by facebook
		sb.append( " EMAIL     CHAR( 80 ), " );
		sb.append( " FNAME     CHAR( 80 ), " );
		sb.append( " LNAME     CHAR( 80 ), " );
		sb.append( " DOB       CHAR( 80 ), " );
		sb.append( " PROFILE   CHAR( 128 )," );
		sb.append( " GENDER    CHAR( 1 ), " );
		sb.append( " LAST_LOGIN  datetime, " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime )" );
		stmt.executeUpdate( sb.toString() );		
		if ( this.verifyTableExists( stmt, Jupiter.USER_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_TABLE_NAME
				    	+ " table created successfully." );
		}
	}

	/**
	 * Convenience method to recreate the Hobby table.
	 * 
	 * @param stmt
	 *            Use this Statement object to deliver the SQL command.
	 * @throws SQLException
	 *            Oops, something went wrong.
	 */
	public void recreateHobbyTable( final Statement stmt ) throws SQLException {
		this.dropTableIfExists( stmt, Jupiter.USER_HOBBY_TABLE_NAME );
		this.recreateHobbyTable( stmt );
	}
	
	/**
	 * Convenience method to create the Hobby table.
	 *            
	 * @throws SQLException
	 *            Oops, something went wrong.
	 */
	public void createHobbyTable() throws SQLException {
		Statement stmt = this.connection.createStatement();
		this.createHobbyTable( stmt );
	}
	
	/**
	 * Convenience method to create the Hobby table.
	 * 
	 * @param stmt
	 *            Use this Statement object to deliver the SQL command.
	 *            
	 * @throws SQLException
	 *            Oops, something went wrong.
	 */
	public void createHobbyTable( final Statement stmt ) throws SQLException {
		
		StringBuilder sb = new StringBuilder();

		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_HOBBY_TABLE_NAME ) );
		sb.append( "(USER_HOBBY_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT      NOT NULL," );
		sb.append( " HOBBY_NAME       CHAR( 80 ), " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );
		stmt.executeUpdate( sb.toString() );
		if ( this.verifyTableExists( stmt, Jupiter.USER_HOBBY_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_HOBBY_TABLE_NAME
				    	+ " table created successfully." );
		}
	}
	
	public void recreateDestinationTable( final Statement stmt ) throws SQLException {
		this.dropTableIfExists( stmt, Jupiter.USER_DESTINATION_TABLE_NAME );
		this.createDestinationTable( stmt );
	}
	
	public void createDestinationTable() throws SQLException {
		Statement stmt = this.connection.createStatement();
		this.createDestinationTable( stmt );
	}
	
	public void createDestinationTable( final Statement stmt ) throws SQLException {		
		StringBuilder sb = new StringBuilder();
		sb = new StringBuilder();
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_DESTINATION_TABLE_NAME ) );
		sb.append( "(DESTINATION_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT      NOT NULL," );
		sb.append( " CITY_NAME      CHAR( 80 ), " );
		sb.append( " STATE_NAME     CHAR( 80 ), " );
		sb.append( " COUNTRY        CHAR( 80 ), " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );
		stmt.executeUpdate( sb.toString() );
		if ( this.verifyTableExists( stmt, Jupiter.USER_DESTINATION_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_DESTINATION_TABLE_NAME
				    	+ " table created successfully." );
		}
	}
	
	public void recreateMovieTable( final Statement stmt ) throws SQLException {
		this.dropTableIfExists( stmt, Jupiter.USER_MOVIE_TABLE_NAME );	
		this.createMovieTable( stmt );
	}
	
	public void createMovieTable() throws SQLException {
		Statement stmt = null;
		try {
			stmt = this.connection.createStatement();
			this.createMovieTable( stmt );
		} finally {
			if ( stmt != null ) {
				stmt.close();
			}
		}		
	}
	
	public void createMovieTable( final Statement stmt ) throws SQLException {
			
		StringBuilder sb = new StringBuilder();
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_MOVIE_TABLE_NAME ) );
		sb.append( "(USER_MOVIE_LIKE_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT      NOT NULL," );
		sb.append( " MOVIE_NAME      CHAR( 80 ), " );
		sb.append( " ACTOR_NAME      CHAR( 80 ), " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );
		stmt.executeUpdate( sb.toString() );
		if ( this.verifyTableExists( stmt, Jupiter.USER_MOVIE_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_MOVIE_TABLE_NAME
				    	+ " table created successfully." );
		}
	}
	
	public void recreateMusicTable( final Statement stmt ) throws SQLException {
		this.dropTableIfExists( stmt, Jupiter.USER_MUSIC_TABLE_NAME );
		this.createMusicTable( stmt );
	}
	
	public void createMusicTable() throws SQLException {
		Statement stmt = this.connection.createStatement();
		this.createMusicTable( stmt );
	}
	
	public void createMusicTable( final Statement stmt ) throws SQLException {	
		StringBuilder sb = new StringBuilder();
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_MUSIC_TABLE_NAME ) );
		sb.append( "(USER_MUSIC_LIKE_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT     NOT NULL," );
		sb.append( " MUSIC_NAME      CHAR( 80 ), " );
		sb.append( " ARTIST_NAME     CHAR( 80 ), " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );
		stmt.executeUpdate( sb.toString() );
		if ( this.verifyTableExists( stmt, Jupiter.USER_MUSIC_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_MUSIC_TABLE_NAME
				    	+ " table created successfully." );
		}
		
	}
	
	public void recreateInfluenceTable( final Statement stmt ) throws SQLException {
		this.dropTableIfExists( stmt, Jupiter.USER_INFLUENCE_TABLE_NAME );
		this.createInfluenceTable( stmt );
	}
	
	public void createInfluenceTable() throws SQLException {
		Statement stmt = this.connection.createStatement();
    	this.createInfluenceTable( stmt );
	}
	
	public void createInfluenceTable( final Statement stmt ) throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_INFLUENCE_TABLE_NAME ) );
		sb.append( "(USER_INFLUENCE_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT      NOT NULL," );
		sb.append( " PERSON_NAME      CHAR( 80 ), " );	
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );
		stmt.executeUpdate( sb.toString() );
		if ( this.verifyTableExists( stmt, Jupiter.USER_INFLUENCE_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_INFLUENCE_TABLE_NAME
				    	+ " table created successfully." );
		}
	}
	
	public void recreateWorkHistoryTable( final Statement stmt ) throws SQLException {
		this.dropTableIfExists( stmt, Jupiter.USER_WORK_HISTORY_TABLE_NAME );
		this.recreateWorkHistoryTable( stmt );
	}
	
	public void createWorkHistoryTable() throws SQLException {
    	Statement stmt = this.connection.createStatement();
    	this.createWorkHistoryTable( stmt );
	}
	
	public void createWorkHistoryTable( final Statement stmt ) throws SQLException {
		StringBuilder sb = new StringBuilder();		
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_WORK_HISTORY_TABLE_NAME ) );
		sb.append( "(USER_WORK_HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT      NOT NULL," );
		sb.append( " COMPANY_NAME     CHAR( 80 ), " );
		sb.append( " YEAR INT         NOT NULL," );
		sb.append( " POSITION         CHAR( 80 ), " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );		
		stmt.executeUpdate( sb.toString() );		
		if ( this.verifyTableExists( stmt, Jupiter.USER_WORK_HISTORY_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    	Jupiter.USER_WORK_HISTORY_TABLE_NAME
				    	+ " table created successfully." );
		}
	}
	
    public void recreateEducationTable( final Statement stmt ) throws SQLException {
    	this.dropTableIfExists( stmt, Jupiter.USER_EDUCATION_TABLE_NAME );
    	this.createEducationTable( stmt );
    }
	
    public void createEducationTable() throws SQLException {
    	Statement stmt = this.connection.createStatement();
    	this.createEducationTable( stmt );
    }
    
	public void createEducationTable( final Statement stmt ) throws SQLException {
		StringBuilder sb = new StringBuilder();		
		sb.append( String.format( "CREATE TABLE %s ", Jupiter.USER_EDUCATION_TABLE_NAME ) );
		sb.append( "(USER_EDUCATION_ID INTEGER PRIMARY KEY AUTOINCREMENT," );
		sb.append( " USER_ID INT      NOT NULL," );
		sb.append( " SCHOOL_NAME      CHAR( 80 ), " );
		sb.append( " YEAR INT         NOT NULL," );
		sb.append( " MAJOR            CHAR( 80 ), " );
		sb.append( " CREATE_DATE datetime, " );
		sb.append( " EDIT_DATE   datetime, " );
		sb.append( " FOREIGN KEY(USER_ID) REFERENCES USER_INFO(USER_ID) )" );		
		stmt.executeUpdate( sb.toString() );
		if ( this.verifyTableExists( stmt, Jupiter.USER_EDUCATION_TABLE_NAME ) ) {
			logger.log( Level.INFO, 
				    Jupiter.USER_EDUCATION_TABLE_NAME + " table created successfully." );
		}
	}
	
	/**
	 * Create all the tables in the app schema.
	 * 
	 * @throws SQLException  If something went wrong during the check.
	 */
	public void createAllTables() throws SQLException {
		
		Statement stmt = this.connection.createStatement();
		
		this.createUserInfoTable( stmt );
		
		this.createHobbyTable(stmt);
		
		this.createDestinationTable(stmt);
		
		this.createMovieTable(stmt);
		
		this.createInfluenceTable(stmt);

		this.createWorkHistoryTable(stmt);
		
		this.createEducationTable(stmt);
	}
	
	public void deleteAllTables() throws SQLException {
		Statement stmt = this.connection.createStatement();
		this.dropTableIfExists( stmt, Jupiter.USER_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_HOBBY_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_DESTINATION_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_MOVIE_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_MUSIC_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_INFLUENCE_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_WORK_HISTORY_TABLE_NAME );
		this.dropTableIfExists( stmt, Jupiter.USER_EDUCATION_TABLE_NAME );
	}
	
	/**
	 * Close the DB connection.
	 * 
	 * @throws SQLException If there is a problem.
	 */
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	/**
	 * Helper method to check if a table exists.
	 * 
	 * @param stmt
	 *            The Statement object to use.
	 * @param tableName
	 *            The name of the table to check.
	 * @return    True if the table exists, else false.
	 * @throws SQLException  If something went wrong during the check.
	 */
	public boolean verifyTableExists( final Statement stmt,
			                          final String tableName ) throws SQLException {
		boolean status = false;
		String sql = String.format( VERIFY_EXISTS_FMT, tableName );
		ResultSet rs = stmt.executeQuery( sql );
		status = rs.next();
		assertTrue( String.format( "TABLE %s NOT CREATED", tableName ), status );
		return status;
	}
	
	public String listTable( final String tableName ) throws SQLException {
		Statement stmt = this.connection.createStatement();
		String sql = String.format( "SELECT * FROM %s", tableName );
		ResultSet rs = stmt.executeQuery( sql );
		ResultSetMetaData meta = rs.getMetaData();
		int numCols = meta.getColumnCount();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		for ( int i = 0; i < numCols; i++ ) {
			String item = meta.getColumnName( i + 1 );
			pw.print( item );
			pw.print( Jupiter.TAB_CHAR );
		}
		pw.println();
		while( rs.next() ) {
			for ( int i = 0; i < numCols; i++ ) {
				String item = rs.getString( i + 1 );
				pw.print( item );
				pw.print( Jupiter.TAB_CHAR );
			}
			pw.println();
		}
		return sw.toString();
	}
	
	/**
	 * Helper method to drop a DB table if it exists.
	 * 
	 * @param stmt
	 *            The Statement object to use.
	 * @param tableName
	 *            The name of the table to drop.
	 * @throws SQLException  If something went wrong during the check.
	 */
	private void dropTableIfExists( final Statement stmt,
									final String tableName ) throws SQLException {
		String fmt = "Dropping table %s if it exists";
		String msg = String.format( fmt, tableName );
		Jupiter.logger.log( Level.INFO, msg );
		stmt.executeUpdate( Jupiter.DROP_TABLE + tableName );
	}

	public boolean deleteUserInfoTableRow( final int userId ) throws SQLException {
		String tableName = Jupiter.USER_TABLE_NAME;
		String idName = Jupiter.USER_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, userId );
	}
	
	public boolean deleteWorkHistoryTableRow( final int workHistoryId ) throws SQLException {
		String tableName = Jupiter.USER_WORK_HISTORY_TABLE_NAME;
		String idName = Jupiter.WORK_HISTORY_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, workHistoryId );
	}
	
	public boolean deleteDestinationTableRow( final int destinationId ) throws SQLException {
		String tableName = Jupiter.USER_DESTINATION_TABLE_NAME;
		String idName = Jupiter.USER_DESTINATION_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, destinationId );
	}
	
	public boolean deleteEducationTableRow( final int educationId ) throws SQLException {
		String tableName = Jupiter.USER_EDUCATION_TABLE_NAME;
		String idName = Jupiter.USER_EDUCATION_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, educationId );
	}
	
	public boolean deleteInfluenceTableRow( final int influenceId ) throws SQLException {
		String tableName = Jupiter.USER_INFLUENCE_TABLE_NAME;
		String idName = Jupiter.INFLUENCE_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, influenceId );
	}
	
	public boolean deleteMovieTableRow( final int movieId ) throws SQLException {
		String tableName = Jupiter.USER_MOVIE_TABLE_NAME;
		String idName = Jupiter.MOVIE_LIKE_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, movieId );
	}
	
	public boolean deleteMusicTableRow( final int musicId ) throws SQLException {
		String tableName = Jupiter.USER_MUSIC_TABLE_NAME;
		String idName = Jupiter.USER_MUSIC_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, musicId );
	}
	
	public boolean deleteHobbyTableRow( final int hobbyId ) throws SQLException {
		String tableName = Jupiter.USER_HOBBY_TABLE_NAME;
		String idName = Jupiter.HOBBY_ID_COLUMN_NAME;		
		return deleteRow( tableName, idName, hobbyId );
	}
	
	private boolean deleteRow(
							  final String tableName,
							  final String idName,
							  final int idValue ) throws SQLException {
		boolean status = false;
		Statement stmt = null;
		try {
			String sql = String.format( "DELETE FROM %s WHERE %s='%d'", tableName, idName, idValue );
			stmt = this.connection.createStatement();
			status = stmt.execute( sql );
		} finally {
			if ( null != stmt ) {
				stmt.close();
			}
		}
		return status;
	}



}
