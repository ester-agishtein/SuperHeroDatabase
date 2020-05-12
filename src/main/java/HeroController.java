import javafx.scene.control.Labeled;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HeroController {

    private HeroService service;
    private JLabel nameLabel;
    private JLabel fullName;
    private JLabel alterEgos;
    private JLabel firstAppear;
    private JLabel job;
    private JLabel image;
    private JPanel imgPanel;
    public HeroController(HeroService service, JLabel name, JLabel fullName, JLabel alterEgos, JLabel firstAppear, JLabel job, JLabel image, JPanel imgPanel){
        this.service = service;
        this.nameLabel = name;
        this.fullName = fullName;
        this.alterEgos = alterEgos;
        this.firstAppear = firstAppear;
        this.job = job;
        this.image = image;
        this.imgPanel = imgPanel;
    }
    public void getData(String name){
        service.getData(name).enqueue(new Callback<HeroFeed>(){

            @Override
            public void onResponse(Call<HeroFeed> call, Response<HeroFeed> response) {

                ArrayList <HeroFeed.Results> results = response.body().results;


                try {
                     nameLabel.setText("Name: " + results.get(0).name);
                     fullName.setText("Full Name: "+ results.get(0).biography.fullName);
                     alterEgos.setText("Alter Egos: " + results.get(0).biography.alterEgos);
                     firstAppear.setText("First Appearance: " + results.get(0).biography.firstAppearance);
                     job.setText("Day Job: " + results.get(0).work.occupation);

                    String path = results.get(0).image.url;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setRequestProperty(
                            "User-Agent",
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
                    BufferedImage buffImage = ImageIO.read(connection.getInputStream());

                    JLabel imgLabel = new JLabel(new ImageIcon(buffImage));
                    image.setText(path);
                    imgPanel.add(imgLabel, imgLabel.getSize());
                    connection.disconnect();

                } catch (Exception e) {
                    System.out.println("Couldn't create a connection to the link, please recheck the link.");
                    nameLabel.setText("This Hero's name is invalid. Please try again.");
                    imgPanel.removeAll();
//                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HeroFeed> call, Throwable t) {
                System.out.println("Failure in HeroController.");
            }
        });
    }

}





