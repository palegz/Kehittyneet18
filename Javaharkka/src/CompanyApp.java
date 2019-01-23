import java.util.List;
import javax.swing.JOptionPane;

//AUTHOR PAULI MÄNTY 2018 MARRASKUU
//Ideana siis näyttää coinmarketcapin listaus yli kolmesta tuhannesta eri crytovaluutasta, häntäpäähän tulee uusimmat listaukset.
//Jos tykkää crytpovaluuttoihin rahansa laittaa  ja haluaa uusien matkaan samantein, tämän ohjjelman avulla näet uuden häntäpään helposti.

public class CompanyApp {

    public static void main(String[] args) {

        String apiUrl = "https://api.coinmarketcap.com/v2/listings/"; //apin urli, Tämä api listaa coinmarketcapiss olevat kaikki cryptovaluutat.
        List<Crypto> cryptoes;
        DataApi api = new DataApi(apiUrl);
                Thread apiThread = new Thread(api);
        apiThread.start();

        while (apiThread.isAlive()) {                       //threadin pyörittely, ja error handling
            System.out.println("Fetching data...");
            try {
                Thread.sleep(250);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        cryptoes = api.getCrypto();
        for (Crypto crypto : cryptoes) {
            System.out.println(crypto.getId()+crypto.getName()+ " " + crypto.getSymbol() + " "
            + crypto.getWebsite_slug());
        }

        String valinta;
        do {
            valinta = JOptionPane.showInputDialog(null, "1.Näytä coinmarketcap Listat. \n 2.Poista data  \n 4. Lopeta ohjelma");
            if (valinta.equals("1")){
                System.out.println(cryptoes);          //Printtaa taulukon tiedot, sisällä apin data.
            }
            else if (valinta.equals("2")){
                cryptoes.clear(); //list.clear avulla taulukon datan tyhjennys.
            }
        }while(!valinta.equals("4"));
        apiThread.interrupt();
    }

}
