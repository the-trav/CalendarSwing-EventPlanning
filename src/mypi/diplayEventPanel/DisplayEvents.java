/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.diplayEventPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mypi.CalendarApp;
import mypi.IO.RemoveItemFromList;

import mypi.pojo.Events;

// in future create ListCellRenderer class
// to customize and style up JList 
public class DisplayEvents extends JPanel {

    private DefaultListModel eventModel;
    private JList<String> eventList;
    private JList<String> removeList;
    private DefaultListModel removeModel;
    private JButton add;

    /*
    constructor that creates a event panel
    this constuctor will display all of the current events loged and properly place them in order of the dates
     */
    public DisplayEvents() {

        Box eventBox = Box.createVerticalBox();
        AddListener clickButton = new AddListener();
        add = new JButton("Add Custom Event");
        add.addActionListener(clickButton);
        eventBox.add(add);

        JPanel horizontal = initalizingEventList();

        eventBox.add(horizontal);
        this.add(eventBox);
        this.setVisible(true);
    }

    private void populateEventList() {
        try {
            for (Events currentEvent : CalendarApp.thePlannedEventList) {
                eventModel.addElement(currentEvent.toString());
                removeModel.addElement("Remove");
            }
        } catch (NullPointerException n) {
            System.out.println("nothing planned for events");
        }

    }

    private JPanel initalizingEventList() {
        eventModel = new DefaultListModel();
        eventList = new JList<>(eventModel);
        eventList.setFixedCellHeight(25);
        eventList.setEnabled(false);//so there not selectable

        removeModel = new DefaultListModel();
        removeList = new JList<>(removeModel);
        removeList.setFixedCellHeight(25);
        RemoveJListListener removeListen = new RemoveJListListener();
        removeList.addMouseListener(removeListen);
        
        populateEventList();
        
        JPanel horizontal = new JPanel();
        horizontal.add(eventList);
        horizontal.add(removeList);
        return horizontal;
    }

    public void refreshGUI() {
        eventModel.clear();
        removeModel.clear();
        populateEventList();
    }

    private class RemoveJListListener implements MouseListener {

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int index = removeList.getSelectedIndex();
            RemoveItemFromList deletingEvent = new RemoveItemFromList();
            deletingEvent.removeItem(eventModel.get(index).toString());
            removeModel.remove(index);
            eventModel.remove(index);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == add) {
                JOptionPane.showInputDialog("show month day year spinner + show a input box to input event details.");
            }
        }

    }

}
