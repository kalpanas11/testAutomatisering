package testing.pack;

import java.util.*;

public class Tshirts {

//    enum T-shirtSize{
//        XS, S, M, L, XL, XXL
//    }

    public static Map<String,Integer> sizes ;

    public Tshirts(int n){
        sizes = new HashMap<>();
        sizes.put("XS",n);
        sizes.put("S",n);
        sizes.put("M",n);
        sizes.put("L",n);
        sizes.put("XL",n);

    }

    /* incrementing the numbers of a T-shirt-size */
    public boolean add(String size, int n) {
        if (((size.equals("XS") || size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL")) && ( n>0 && n<=10))) {
//            if (sizes.get(size) != null) {
//                int value = sizes.get(size) + n;
//                sizes.put(size, value);
//                // System.out.println("Value at add-size(): " + value);
//           } else {
//                sizes.put(size, n);
//                System.out.println("Value at add/size(): " + n);
//            }
            if (sizes.get(size) != null) { n  = sizes.get(size) + n;}
            sizes.put(size, n);
            return true;
        }
        return false;
    }


    /* decrementing the number of a T-shirt size */

    public boolean remove(String size, int toRemove) throws LowStockException{  // && toRemove<=10
        if (((size.equals("XS") || size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL")) && ( toRemove>0))) {
            int inStock = sizes.get(size);
            if (inStock >= toRemove) {
                int reduced = inStock-toRemove;
                sizes.put(size, reduced);
                 // System.out.println("remaining tShirts for size: " +size+ " = " + reduced);
                return true;
            }else
                throw new LowStockException("demand is greater than inStock");
                //"asking for "+toRemove+" but only "+inStock+" in Stock. ");

        }
        return false;
    }

    /* list all of T-shirt sizes */
    public Map<String,Integer> getAll() {
     //   System.out.println("all tShirts : " +sizes);
            return sizes;
    }

    /* to Check if it contains the given size */
    public int contains(String size) {
        return sizes.get(size);
    }


    /* empty the T-shirt Container, ie., Map ==null */
    public void cleanUp(){
        sizes.clear();
        sizes=null;
    }


}


