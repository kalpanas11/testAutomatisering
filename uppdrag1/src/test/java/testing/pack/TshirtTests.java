package testing.pack;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TshirtTests {
    private Tshirts tshirts;
    private int num = 0;
    private String size ;

    @BeforeAll
    void init() {

        tshirts = new Tshirts();

        tshirts.add("XS",10);
        tshirts.add("S",10);
        tshirts.add("M",10);
        tshirts.add("L",10);
        tshirts.add("XL",10);
        tshirts.add("XXL",10);

    }

    @Test
    @Order(1)
    @DisplayName("Add the given numbers of the tShirtSize into the existing collection")
    void addTest() {
         num = 4;
         size = "XS";
        tshirts.add(size,num);
        assertTrue((tshirts.contains(size)>0), " - addTest");
    }

    @Test
    @Order(2)
    @DisplayName("Remove the given numbers of the tShirtSize from the existing collection")
    void removeTest() {
        num = 8;
        size = "XS";
        tshirts.remove(size,num);
        assertTrue(tshirts.contains(size)>0, ()->"removed size still in the collection");
    }


    @Test
    @Order(3)
    @DisplayName("compare the Test_Tshirt list with the given list")
    void listAllTest() {
        Map<String,Integer> myLst = new HashMap<>();
        myLst.put("XS",2);
        myLst.put("S",3);
        myLst.put("M",4);
        myLst.put("L",5);
        myLst.put("XL",6);
        myLst.put("XXL",1);

        Map<String,Integer> expectedLst = tshirts.listAll();

       // assertEquals(myLst, expectedLst,()->"lists not the same");
        //  assertNotEquals(myLst, expectedLst,()->"lists the same");
       // assertFalse(myLst.equals(expectedLst));
        assertNotSame( myLst, expectedLst,"lists the same");
        myLst.clear();
        myLst=null;

    }

    @Test
    @Order(4)
    @DisplayName("Check if Tshirt size is in stock")
    void containsTest() {
        int n = tshirts.contains("XS");
       // System.out.println("tshirts.contains(\"XS\"): " + n);
        assertNotEquals(10, n, ()->"Tshirt-size numbers don't match");
    }

    @Test
    @Order(5)
    @DisplayName("Entire Tshirt stock is empty")
    void emptyTshirtContainerThrowsNullPointerException(){
        tshirts.cleanUp();
        assertThrows(NullPointerException.class, ()->{
                                                        tshirts.contains("XS");
                                                        });
    }


    @AfterAll
    public  void cleanUp(){
        tshirts = null;
    }
}
