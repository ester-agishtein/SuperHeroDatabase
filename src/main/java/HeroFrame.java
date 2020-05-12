import javax.swing.*;
import java.awt.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javax.swing.JPanel;

public class HeroFrame extends JFrame{
    JPanel introPanel;
    JPanel inputPanel;
    JPanel responsePanel;
    JLabel instructions;
    JLabel inputLabel;
    JTextField userInput;
    JButton search;
    JPanel searchPanel;
    //Data to be accessed by controller
    private JPanel imgPanel;
    private JLabel name;
    private JLabel fullName;
    private JLabel alterEgos;
    private JLabel firstAppear;
    private JLabel job;
    private JLabel image;

    public HeroFrame() {

        setSize(1000, 800);
        setTitle("Hero Database");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));


        introPanel = new JPanel(new GridLayout(2,1));
        inputPanel = new JPanel(new GridLayout(2,1));
        responsePanel = new JPanel();

        introPanel.setOpaque(true);

        instructions = new JLabel();
        instructions.setText("<html>Welcome to the Super Hero Database.<br> Type in the hero's name to get their information.</html>");


        inputPanel.setOpaque(true);
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(instructions);

        inputLabel = new JLabel();
        inputLabel.setText("Hero's Name:");
        userInput = new JTextField();

        userInput.setPreferredSize(new Dimension(100,40));
        search = new JButton("Search");
        searchPanel = new JPanel(new GridLayout(1, 3));
        searchPanel.add(inputLabel);
        searchPanel.add(userInput);
        searchPanel.add(search);

        inputPanel.add(searchPanel);


        //1st row of INTRO
        introPanel.add(inputPanel);

        responsePanel.setOpaque(true);
        responsePanel.setBackground(Color.BLUE);
        responsePanel.setLayout(new GridLayout(5,1));

        name = new JLabel();
        fullName = new JLabel();
        alterEgos = new JLabel();;
        firstAppear = new JLabel();
        job = new JLabel();

        image = new JLabel();
        imgPanel = new JPanel();


        add(introPanel);
        introPanel.add(responsePanel);
        imgPanel.add(image);
        add(imgPanel);
        search.addActionListener(actionEvent -> {
            this.clear();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.superheroapi.com/api.php/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            HeroService service = retrofit.create(HeroService.class);
            HeroController controller = new HeroController(service, name, fullName,alterEgos,firstAppear,job,image,imgPanel);
            String hero = this.checkName(userInput.getText());
            controller.getData(hero);

            this.addComponents();

            imgPanel.add(image);
            add(imgPanel);


        });

    }

    private void addComponents() {
        responsePanel.add(name);
        responsePanel.add(fullName);
        responsePanel.add(alterEgos);
        responsePanel.add(firstAppear);
        responsePanel.add(job);
    }

    private void clear(){
        name.setText(" ");
        fullName.setText(" ");
        alterEgos.setText(" ");
        firstAppear.setText(" ");
        job.setText(" ");
        image.setText(" ");
        responsePanel.removeAll();
        imgPanel.removeAll();
        responsePanel.updateUI();
    }

    public String checkName(String hero){

        if(hero.contains(" "))
        {
            hero = hero.replace(" ", "-");
        }
        return hero;
    }

    public static void main(String[] args) {
         HeroFrame frame = new HeroFrame();

        frame.setVisible(true);
    }
}
