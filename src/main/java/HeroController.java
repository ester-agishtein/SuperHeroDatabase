import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.*;
import java.util.List;


public class HeroController {

    private HeroService service;


    public HeroController(HeroService service){
        this.service = service;

    }
    public void getData(String name){
        service.getData(name).enqueue(new Callback<HeroFeed>(){

            @Override
            public void onResponse(Call<HeroFeed> call, Response<HeroFeed> response) {

                ArrayList <HeroFeed.Results> results = response.body().results;
                HeroFrame.name.setText(results.get(0).name);
                HeroFrame.fullName.setText(results.get(0).biography.fullName);
                HeroFrame.alterEgos.setText(results.get(0).biography.alterEgos);
                HeroFrame.firstAppear.setText(results.get(0).biography.firstAppearance);
                HeroFrame.job.setText(results.get(0).work.occupation);
                HeroFrame.url.setText(results.get(0).image.url);

            }

            @Override
            public void onFailure(Call<HeroFeed> call, Throwable t) {
                System.out.println("Failure in HeroController.");
            }
        });
    }

}
