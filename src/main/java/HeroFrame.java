import javax.swing.*;
import java.awt.*;
public class HeroFrame extends JFrame{
    JPanel introPanel;
    JPanel inputPanel;
    JPanel responsePanel;

    JLabel instructions;
    JLabel inputLabel;
    JLabel response;
    JTextField userInput;

    JButton search;
    public HeroFrame() {

        setSize(1000, 800);
        setTitle("Hero Database");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout((new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)));

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
        response = new JLabel();
        search.addActionListener(actionEvent -> {
            response.setText(userInput.getText());
        });
        responsePanel.add(response);
        add(introPanel);
        add(inputPanel);
        add(responsePanel);
    }
    public static void main(String[] args) {
         HeroFrame frame = new HeroFrame();

        frame.setVisible(true);
    }
}
