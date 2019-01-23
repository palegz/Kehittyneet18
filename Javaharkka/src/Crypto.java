public class Crypto {                   //crypto olio apin datalle
    private String id;
    private String name;
    private String symbol;
    private String website_slug;



    public Crypto(String id, String name, String symbol, String website_slug) {
        this.id = id;
        this.name = name;                                       //setterit
        this.symbol = symbol;
        this.website_slug = website_slug;

    }

    public String getId() {
        return id;
    }               //getterit

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getWebsite_slug() {
        return website_slug;
    }

    @Override                   //Data string muotoon
    public String toString() {
        return "Crypto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol=" + symbol +
                ", website_slug='" + website_slug + '\'' +
                '}';
    }
}
