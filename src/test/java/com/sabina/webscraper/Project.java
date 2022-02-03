package com.sabina.webscraper;

//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.io.FileWriter;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;


public class Project {
    public static void main(String[]args){
        new MyFrame();
    }
}

class MyFrame extends JFrame implements ActionListener {
    JButton button;
    JButton button2;
    JLabel label;
    ImageIcon image;
    ImageIcon image2;

    MyFrame(){
        label=new JLabel();
        image = new ImageIcon("img.png");
        image2 = new ImageIcon("java-training.png");
        label.setIcon(image);
        label.setBounds(0, 0, 500, 500);


        button = new JButton();
        button.setBounds(190, 193, 80, 70);
        button.addActionListener(this);
        button.setText("Get data");
        button.setFocusable(false);
        button.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        button.setBackground(Color.CYAN);
        button.setFocusPainted(false);

        button2 = new JButton("Select file");
        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
        button2.setBounds(270, 193, 80, 70);
        button2.setBackground(Color.CYAN);
        button2.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        button2.setFocusPainted(false);


        this.setTitle("eMAG scraper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setIconImage(image2.getImage());
        this.add(button);
        this.add(label);
        this.add(button2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            System.out.println("Button is working.");
            String url = "https://www.emag.ro/";
            ChromeOptions options = new ChromeOptions();
            ChromeDriver driver = new ChromeDriver(options);

            driver.manage().window().maximize();
            driver.get(url);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            driver.findElement(By.xpath("//*[@id=\"searchboxTrigger\"]")).sendKeys("Asus");
            driver.findElement(By.xpath("//*[@id=\"masthead\"]/div/div/div[2]/div/form/div[1]/div[2]/button[2]")).click();
            WebElement val= driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[3]"));
            String  a = val.getText();
            System.out.print(a);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            driver.findElement(By.xpath("//*[@id=\"searchboxTrigger\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"masthead\"]/div/div/div[2]/div/form/div[1]/div[2]/button[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"searchboxTrigger\"]")).sendKeys("Samsung");
            driver.findElement(By.xpath("//*[@id=\"masthead\"]/div/div/div[2]/div/form/div[1]/div[2]/button[2]")).click();
            WebElement val2= driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[3]"));
            String  a2 = val2.getText();
            System.out.print(a2);

            driver.findElement(By.xpath("//*[@id=\"searchboxTrigger\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"masthead\"]/div/div/div[2]/div/form/div[1]/div[2]/button[1]")).click();
            driver.findElement(By.xpath("//*[@id=\"searchboxTrigger\"]")).sendKeys("Desktop PC");
            driver.findElement(By.xpath("//*[@id=\"masthead\"]/div/div/div[2]/div/form/div[1]/div[2]/button[2]")).click();
            WebElement val3= driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[3]"));
            String  a3 = val3.getText();
            System.out.print(a3);

            driver.close();
            driver.quit();
            try {
                FileWriter myWriter = new FileWriter("StoredData.csv");
                myWriter.write("Asus results: " + a +  "\nSamsung results: " + a2 + "\nDesktop PC results: " + a3);
                myWriter.close();
                System.out.print(myWriter);
            } catch (IOException exc) {
                System.out.println("An error occurred.");
                exc.printStackTrace();
            }
        }
        if(e.getSource()==button2){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
            }
        }
    }

}

