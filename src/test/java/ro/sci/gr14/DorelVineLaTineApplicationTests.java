package ro.sci.gr14;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.sci.gr14.data.ICustomerRepository;
import ro.sci.gr14.model.Customer;

/**
 *
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
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


