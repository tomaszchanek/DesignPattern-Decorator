package decorators;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator extends DataSourceDecorator {
    private int compLevel = 6;

    public int getCompressionLevel() {
        return compLevel;
    }

    public void setCompressionLevel(int compLevel) {
        this.compLevel = compLevel;
    }

    public CompressionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void writeData(String data) {
        super.writeData(compress(data));
    }

    @Override
    public String readData() {
        return decompress(super.readData());
    }

    private String compress(String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(baos, new Deflater(compLevel));
            dos.write(data);
            dos.close();
            baos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            System.out.println("Compress: " + e.getMessage());
            return null;
        }
    }

    private String decompress(String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
            int b;
            while ((b = iin.read()) != -1) {
                baos.write(b);
            }
            in.close();
            iin.close();
            baos.close();
            return new String(baos.toByteArray());
        } catch (IOException e) {
            System.out.println("Decompress: " + e.getMessage());
            return null;
        }
    }
}
