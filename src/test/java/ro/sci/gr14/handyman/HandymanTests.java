package ro.sci.gr14.handyman;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.Handyman;

import java.util.HashSet;
import java.util.TreeSet;

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
public class HandymanTests {

    @Mock
    private IHandymanRepository handymanRepository;


    private Handyman handyman = new Handyman(0L, "username", "parola", "email", "fullname", "nrtel", "city", "county", 1, new HashSet<>(), new TreeSet<>());


    @Test
    public void saveHandyman() {
        handymanRepository.save(handyman);
    }


    @Test
    public void deleteCustomer() {
        handymanRepository.delete(handyman);
    }
}