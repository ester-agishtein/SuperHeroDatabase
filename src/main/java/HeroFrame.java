import javax.swing.*;
import java.awt.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HeroFrame extends JFrame{
    JPanel introPanel;
    JPanel inputPanel;
    JPanel responsePanel;

    JLabel instructions;
    JLabel inputLabel;
    JTextField userInput;

    JButton search;

    //Data to be accessed by controller
    public static JLabel name;
    public static JLabel fullName;
    public static JLabel alterEgos;
    public static JLabel firstAppear;
    public static JLabel job;
    public static JLabel url;


    public HeroFrame() {

        setSize(1000, 800);
        setTitle("Hero Database");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout((new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/api.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroService service = retrofit.create(HeroService.class);



        introPanel = new JPanel();
        inputPanel = new JPanel();
        responsePanel = new JPanel();

        introPanel.setOpaque(true);
        introPanel.setBackground(Color.RED);
        instructions = new JLabel();
        instructions.setText("Welcome to the Super Hero Database.\n Type in the hero's name to get their information.");
        introPanel.add(instructions);

        inputPanel.setOpaque(true);
        inputPanel.setBackground(Color.WHITE);
        inputLabel = new JLabel();
        inputLabel.setText("Hero's Name:");
        userInput = new JTextField();
        userInput.setPreferredSize(new Dimension(100,40));
        inputPanel.add(inputLabel);
        inputPanel.add(userInput);
        search = new JButton("Search");
        inputPanel.add(search);

        responsePanel.setOpaque(true);
        responsePanel.setBackground(Color.BLUE);

        search.addActionListener(actionEvent -> {

            HeroController controller = new HeroController(service);
            String hero = userInput.getText();
            controller.getData(hero);

            name = new JLabel();
            fullName = new JLabel();
            alterEgos = new JLabel();;
            firstAppear = new JLabel();
            job = new JLabel();
            url = new JLabel();

            responsePanel.add(name);
            responsePanel.add(fullName);
            responsePanel.add(alterEgos);
            responsePanel.add(firstAppear);
            responsePanel.add(job);
            responsePanel.add(url);

        });

        add(introPanel);
        add(inputPanel);
        add(responsePanel);
    }
    public static void main(String[] args) {
         HeroFrame frame = new HeroFrame();

        frame.setVisible(true);
    }
}
