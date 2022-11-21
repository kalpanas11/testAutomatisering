package testing.pack;

import java.util.*;

public class Tshirts {

//    enum tshirtSize{
//        XS, S, M, L, XL, XXL
//    }

    public static Map<String,Integer> sizes ;

    public Tshirts(){
        sizes = new HashMap<>();
    }

    /* incrementing the number of a Tshirt size */
    public void add(String size, int n) {
        if (sizes.get(size)!=null){
            int value = sizes.get(size)+n;
            sizes.put(size,value);
         // System.out.println("Value at addsize(): " + value);
        }
        else{
             sizes.put(size,n);
          // System.out.println("Value at add size(): " + n);
        }
    }


    /* decrementing the number of a Tshirt size */
    public void remove(String size, int n) {
        int reduced = sizes.get(size)-n;
        sizes.put(size,reduced);
      //  System.out.println("remaining tShirts for size: " +size+ " = " + reduced);
    }

    /* list all of Tshirt sizes */
    public Map<String,Integer> listAll() {
     //   System.out.println("all tShirts : " +sizes);
            return sizes;
    }

    /* to Check if it contains the given size */
    public int contains(String size) {
        int value = sizes.get(size);
     //   System.out.println("contains tShirts for size: " +size+ " = " + value);
        return value;
    }

    /* empty the Tshirt Container, ie., Map ==null */
    public void cleanUp(){
        sizes.clear();
        sizes=null;
    }
}


