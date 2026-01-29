package repository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Id {
    public static int getLastId() {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("file/lastId.txt"))) {
            return dataInputStream.readInt();
        } catch (IOException e) {
            return 1;
        }
    }

    public static void writeLastId(int ID) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("file/lastId.txt"))) {
            dataOutputStream.writeInt(ID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
