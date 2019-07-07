package ro.sci.gr14;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.sci.gr14.data.ICustomerRepository;
import ro.sci.gr14.model.Customer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DorelVineLaTineApplicationTests {

		@Autowired
		private ICustomerRepository customerRepository;

		private Customer customer = new Customer(0L, "username", "parola", "email", "fullname", "nrtel", "city", "county", "adress", 2);


	@Test
		public void saveCustomerAndFindByUsername() {
			customerRepository.save(customer);
			Assert.assertNotNull(customerRepository.findByUsername("username"));
		}
}


