import java.io.*;
import java.util.Arrays;

class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    transient String temporaryNote;
    int price;

    Product(String n, int p, String note) {
        name = n;
        price = p;
        temporaryNote = note;
    }

    public String toString() {
        return name + " price=" + price
                + " temporaryNote=" + temporaryNote;
    }
}

public class SaveLoad {

    public static void main(String[] args) throws Exception {

        Product[] products = {
            new Product("Pen", 10, "fragile note"),
            new Product("Book", 80, "keep dry")
        };

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("products.dat"))) {

            out.writeObject(products);
        }

        Product[] loaded;

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("products.dat"))) {

            loaded = (Product[]) in.readObject();
        }

        System.out.println(Arrays.toString(loaded));

        System.out.println(
                "Transient fields are null after loading."
        );
    }
}