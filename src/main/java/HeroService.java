import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HeroService {
    @GET("139570084286938/search/{name}")
    Call<HeroFeed> getData(@Path("name") String name );
}
