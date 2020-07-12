package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;

public class FieldList implements Runnable, ActionListener {
    public static void main(String... args) {
        SwingUtilities.invokeLater(new FieldList());
    }

    final int maxFields = 2;

    JFrame frame;
    JPanel listing;

    @Override
    public void run() {
        frame = new JFrame("Text Field Listing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout());
        content.add(new JLabel("Input Form", JLabel.CENTER), BorderLayout.NORTH);

        listing = new JPanel();
        listing.setLayout(new BoxLayout(listing, BoxLayout.Y_AXIS));

        content.add(listing, BorderLayout.CENTER);
        frame.setContentPane(content);

        addNewField();

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void addNewField() {
        FieldButtonPair field = new FieldButtonPair();
        field.button.addActionListener(this);
        listing.add(field);
        frame.pack();
    }

    void removeLastField() {
        listing.remove(listing.getComponentCount() - 1);
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        AddRemoveButton source = (AddRemoveButton)ae.getSource();

        if(source.state == AddRemoveButton.State.ADD) {
            if(listing.getComponentCount() < maxFields) {
                addNewField();
                source.setState(AddRemoveButton.State.REMOVE);
            }
        } else if(source.state == AddRemoveButton.State.REMOVE) {
            removeLastField();
            source.setState(AddRemoveButton.State.ADD);
        }
    }
}

class FieldButtonPair extends JPanel {
    JTextField field;
    AddRemoveButton button;

    FieldButtonPair() {
        super(new BorderLayout());
        field = new JTextField();
        add(field, BorderLayout.CENTER);
        button = new AddRemoveButton();
        add(button, BorderLayout.EAST);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension pref = super.getPreferredSize();
        pref.width = Math.max(480, pref.width);
        return pref;
    }
}

class AddRemoveButton extends JButton {
    enum State { ADD, REMOVE }

    State state = State.ADD;

    AddRemoveButton() {
        setText(state.name());
    }

    void setState(State state) {
        setText(state.name());
        this.state = state;
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension pref = super.getPreferredSize();

        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int w = fm.stringWidth(State.REMOVE.name());
        Insets ins = getInsets();

        pref.width = (ins.left + w + ins.right);
        return pref;
    }
}