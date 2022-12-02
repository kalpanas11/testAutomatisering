package testing.pack;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
public class TshirtTests {
    private Tshirts tshirts;


    @BeforeAll
    void init() {
        tshirts = new Tshirts(10);
    }

    @Test
    void addTest1() {assertTrue(tshirts.add("XS",4));}
    @Test
    void addTest2() {assertFalse(tshirts.add("XS1",4));}
    @Test
    void addTest3() { assertFalse(tshirts.add("XS",-4));}
    @Test
    void addTest4() {assertFalse(tshirts.add("XS",100));}
    @Test
    void addTest5() {assertFalse(tshirts.add("XS1",100));}

    @Test
    void removeTest1() throws Exception{
        assertTrue(tshirts.remove("XS",8), "removeTest1");
    }

    @Test
    void removeTest2() throws Exception{
        assertTrue(tshirts.remove("XS1",8)==false,"removeTest2 failed");
    }

//    @Test
//    void removeTest3() throws LowStockException{
//        assertTrue(tshirts.remove(size"XS",20)==false, "removeTest3");
//    }
    @Test
    void containerThrowsLowStockExceptionTest(){
        /* want 20 tshirts of size XS, but have less than 20 in storage. So throws an Exception*/
        Exception e = assertThrows(LowStockException.class,  ()->{tshirts.remove("XS",20);});
        assertEquals("demand is greater than inStock", e.getMessage());
    }
    @Test
    void compareTest() {
        Map<String,Integer> myLst = new HashMap<>();
        myLst.put("XS",2);
        myLst.put("S",3);
        myLst.put("M",4);
        myLst.put("L",5);
        myLst.put("XL",6);
        Map<String,Integer> expectedLst = tshirts.getAll();
        assertNotSame( myLst, expectedLst,"lists the same");
        myLst.clear();
    }


    @AfterAll
    public  void cleanUp(){ tshirts.cleanUp(); tshirts = null;}
}
