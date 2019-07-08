package ro.sci.gr14.admin;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.sci.gr14.data.IAdminRepository;
import ro.sci.gr14.model.Admin;

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
public class AdminTests {

    @Mock
    private IAdminRepository adminRepository;


    private Admin admin = new Admin(0L, "username", "parola", "email", "fullname", "nrtel", "city", "county",  0);


    @Test
    public void saveAdmin() {
        adminRepository.save(admin);
    }


    @Test
    public void deleteCustomer() {
        adminRepository.delete(admin);
    }
}