/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenudai;

/**
 *
 * @author sandeep
 */
public class JenuDai {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainPage mainPage=new MainPage();
        JDBCConnection connection=new JDBCConnection();
        connection.getConnection();
        mainPage.setVisible(true);
        
    }
    
}
