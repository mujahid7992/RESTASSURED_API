package APIChaining;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
public class GenerateTestData_javafaker {
	
	@Test
	
	void fakeDataGenerator()
	{
		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();
		String email = faker.internet().safeEmailAddress();
		
		String password = faker.internet().password(5,8);
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		System.out.println("Full name :"+fullname);
		System.out.println("firstname :"+firstname);
		System.out.println("last name :"+lastname);
		System.out.println("email :"+email);
		
		
		
		
	}

}
