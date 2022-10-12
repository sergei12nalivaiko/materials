// A simple loan calculator applet.  
import java.awt.*;  
import java.awt.event.*;  
import java.applet.*;  
import java.text.*;  
/*  
  <applet code="RegPay" width=280 height=200>  
  </applet>  
*/  
  
public class RegPay extends Applet  
  implements ActionListener {  
  
  TextField amountText, paymentText, periodText, 
            rateText; 
  Button doIt;  
  
  double principal; // original princial 
  double intRate;   // interest rate 
  double numYears;  // length of loan in years 
 
  /* Number of payments per year.  You could 
     allow this value to be set by the user. */ 
  final int payPerYear = 12; 
 
  NumberFormat nf; 
 
  public void init() {  
    // Use a grid bag layout. 
    GridBagLayout gbag = new GridBagLayout(); 
    GridBagConstraints gbc = new GridBagConstraints(); 
    setLayout(gbag); 
 
    Label heading = new  
          Label("Compute Monthly Loan Payments"); 
  
    Label amountLab = new Label("Principal"); 
    Label periodLab = new Label("Years"); 
    Label rateLab = new Label("Interest Rate"); 
    Label paymentLab = new Label("Monthly Payments"); 
 
    amountText = new TextField(16);  
    periodText = new TextField(16);  
    paymentText = new TextField(16);  
    rateText = new TextField(16); 
 
    // Payment field for display only. 
    paymentText.setEditable(false); 
 
    doIt = new Button("Compute");  
 
    // Define the grid bag. 
    gbc.weighty = 1.0; // use a row weight of 1 
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbc.anchor = GridBagConstraints.NORTH; 
    gbag.setConstraints(heading, gbc); 
 
    // Anchor most components to the right. 
    gbc.anchor = GridBagConstraints.EAST; 
 
    gbc.gridwidth = GridBagConstraints.RELATIVE; 
    gbag.setConstraints(amountLab, gbc); 
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbag.setConstraints(amountText, gbc); 
 
    gbc.gridwidth = GridBagConstraints.RELATIVE; 
    gbag.setConstraints(periodLab, gbc); 
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbag.setConstraints(periodText, gbc); 
 
    gbc.gridwidth = GridBagConstraints.RELATIVE; 
    gbag.setConstraints(rateLab, gbc); 
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbag.setConstraints(rateText, gbc); 
 
    gbc.gridwidth = GridBagConstraints.RELATIVE; 
    gbag.setConstraints(paymentLab, gbc); 
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbag.setConstraints(paymentText, gbc); 
 
    gbc.anchor = GridBagConstraints.CENTER; 
    gbag.setConstraints(doIt, gbc); 
 
    // Add all the components. 
    add(heading);  
    add(amountLab);  
    add(amountText);  
    add(periodLab);  
    add(periodText); 
    add(rateLab); 
    add(rateText); 
    add(paymentLab);  
    add(paymentText);  
    add(doIt);  
  
    // Register to receive action events. 
    amountText.addActionListener(this);  
    periodText.addActionListener(this);  
    rateText.addActionListener(this);  
    doIt.addActionListener(this);      
 
    nf = NumberFormat.getInstance(); 
    nf.setMinimumFractionDigits(2); 
    nf.setMaximumFractionDigits(2); 
  }  
  
  /* User pressed Enter on a text field or 
     pressed Compute. */ 
  public void actionPerformed(ActionEvent ae) {  
    repaint();  
  }  
 
  // Display the result if all fields are completed.  
  public void paint(Graphics g) {  
    double result = 0.0; 
 
    String amountStr = amountText.getText();  
    String periodStr = periodText.getText();  
    String rateStr = rateText.getText(); 
 
    try {  
      if(amountStr.length() != 0 && 
         periodStr.length() != 0 && 
         rateStr.length() != 0) { 
 
        principal = Double.parseDouble(amountStr); 
        numYears = Double.parseDouble(periodStr);      
        intRate = Double.parseDouble(rateStr) / 100; 
 
        result = compute();   
  
        paymentText.setText(nf.format(result));  
      } 
  
      showStatus(""); // erase any previous error message  
    } catch (NumberFormatException exc) {  
      showStatus("Invalid Data"); 
      paymentText.setText("");  
    }  
  } 
 
  // Compute the loan payment. 
  double compute() { 
    double numer; 
    double denom; 
    double b, e; 
 
    numer = intRate * principal / payPerYear;   
    
    e = -(payPerYear * numYears);   
    b = (intRate / payPerYear) + 1.0;   
   
    denom = 1.0 - Math.pow(b, e);   
 
    return numer / denom;   
  }     
}

