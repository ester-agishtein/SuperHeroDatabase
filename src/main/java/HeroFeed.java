import com.google.gson.annotations.SerializedName;
import java.util.*;
public class HeroFeed {
    ArrayList<Results> results;
    String response;

    class Results {
        int id;
        String name;
        Biography biography;
        Work work;
        Image image;
    }
    class Biography{
        @SerializedName("full-name")
        String fullName;
        @SerializedName("alter-egos")
        String alterEgos;
        String aliases[];
        @SerializedName("place-of-birth")
        String placeOfBirth;
        @SerializedName("first-appearance")
        String firstAppearance;

    }
    class Work{
        String occupation;
    }
    class Image{
        String url;
    }


}
