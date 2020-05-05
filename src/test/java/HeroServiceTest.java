import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class HeroServiceTest {

    @Test
    public void getData() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroService service = retrofit.create(HeroService.class);
        HeroFeed feed = service.getData("A-Bomb").execute().body();

        System.out.println("response: " + feed.response );
        System.out.println("id = " + feed.results.get(0).id);
        System.out.println("Biography = " + feed.results.get(0).biography.fullName);
        System.out.println("Occupation = " + feed.results.get(0).work.occupation);
        System.out.println("Image URL = " + feed.results.get(0).image.url);

        assertNotNull(feed);
        assertNotNull(feed.results.get(0).biography.fullName);
        assertNotNull(feed.results.get(0).work.occupation);
        assertNotNull(feed.results.get(0).image.url);
    }


}