package pactest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InsertUserInfoTest.class,
				UserInfoUpdateTest.class,
				EducationTableCRUDTest.class,
				WorkHistoryTableCRUDTest.class,
				DestinationTableCRUDTest.class,
				MovieTableCRUDTest.class,
				MusicTableCRUDTest.class,
				InfluenceTableCRUDTest.class,
				HobbyTableCRUDTest.class })
public class RunAllTests {

} 
