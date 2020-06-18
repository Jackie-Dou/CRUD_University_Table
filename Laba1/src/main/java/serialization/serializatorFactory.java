package serialization;

public class serializatorFactory {
    public Serializator createSerializator(String ext) {
        switch (ext) {
            case "dat":
                return new BinarySer();
            case "json":
                return new JsonSer();
            case "txt":
                return new OtherSer();
            default:
                return null;
        }
    }
}
